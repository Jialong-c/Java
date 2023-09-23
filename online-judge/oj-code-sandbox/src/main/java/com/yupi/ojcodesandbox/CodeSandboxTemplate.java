package com.yupi.ojcodesandbox;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ObjectUtil;
import com.yupi.ojcodesandbox.model.*;
import com.yupi.ojcodesandbox.utils.ProcessUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.bouncycastle.operator.DefaultAlgorithmNameFinder;
import org.springframework.util.StopWatch;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Slf4j
public abstract class CodeSandboxTemplate implements CodeSandbox {

    String prefix;

    String globalCodeDirPath;

    String globalCodeFileName;

    /**
     * 超时时间，超过10秒则结束
     */
    public static final Long DEFAULT_TIME_OUT = 5000L;

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {

        List<String> inputList = executeCodeRequest.getInputList();
        String code = executeCodeRequest.getCode();

        //1. 把用户的代码保存为文件
        File userCodeFile = saveCodeToFile(code);
        String userCodePath = userCodeFile.getAbsolutePath();
        String userCodeParentPath = userCodeFile.getParentFile().getAbsolutePath();
        CodeSandboxCmd cmdFromLanguage = getCmd(userCodeParentPath, userCodePath);
        String compileCmd = cmdFromLanguage.getCompileCmd();
        String runCmd = cmdFromLanguage.getRunCmd();

        //2. 编译代码
        try {
            ExecuteMessage executeMessage = compileCode(compileCmd);
            if (executeMessage.getExitValue() != 0) {
                FileUtil.del(userCodeParentPath);
                return ExecuteCodeResponse
                        .builder()
                        .status(2)
                        .message("编译错误")
                        .build();
            }
        } catch (IOException e) {
            FileUtil.del(userCodeParentPath);
            return errorResponse(e);
        }

        // 3. 执行代码，得到输出结果
        try {
            List<ExecuteMessage> executeMessageList = runCode(inputList, runCmd);
            // 返回处理结果
            ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
            executeCodeResponse.setStatus(1);
            JudgeInfo judgeInfo = new JudgeInfo();
            executeCodeResponse.setJudgeInfo(judgeInfo);
            List<String> outputList = new LinkedList<>();
            long maxTime = 0;

            for (ExecuteMessage executeMessage : executeMessageList) {
                if (ObjectUtil.equal(0, executeMessage.getExitValue())) {
                    outputList.add(executeMessage.getMessage());
                } else {
                    executeCodeResponse.setMessage(executeMessage.getErrorMessage());
                    executeCodeResponse.setStatus(3);
                    break;
                }
                maxTime = Math.max(maxTime, executeMessage.getTime());
            }
            judgeInfo.setTime(maxTime);
            executeCodeResponse.setOutputList(outputList);
            FileUtils.forceDelete(new File(userCodeParentPath));
            return executeCodeResponse;
        } catch (Exception e) {
            FileUtil.del(userCodeParentPath);
            return errorResponse(e);
        }
    }

    private File saveCodeToFile(String code) {
        //返回当前用户工作目录
        String userDir = System.getProperty("user.dir");
        String globalCodePath = userDir + globalCodeDirPath;
        // 判断全局代码目录是否存在，没有则新建
        if (!FileUtil.exist(globalCodePath)) {
            FileUtil.mkdir(globalCodePath);
        }

        // 把用户的代码隔离存放
        String userCodeParentPath = globalCodePath + prefix + File.separator + UUID.randomUUID();
        String userCodePath = userCodeParentPath + globalCodeFileName;
        return FileUtil.writeString(code, userCodePath, StandardCharsets.UTF_8);
    }

    /**
     * 每个实现类必须实现编译以及运行的cmd
     * *
     @param userCodeParentPath 代码所在的父目录
      * @param userCodePath 代码所在目录
     */
    abstract CodeSandboxCmd getCmd(String userCodeParentPath, String userCodePath);

    /**
     * 编译代码，注意编译代码要返回编译的信息
     *
     * @param compileCmd 编译命令
     * @return {@link ExecuteMessage}
     * @throws IOException IOException
     */
    private ExecuteMessage compileCode(String compileCmd) throws IOException {
        Process compileProcess = Runtime.getRuntime().exec(compileCmd);
        return ProcessUtils.runProcessAndGetMessage(compileProcess, "编译");
    }

    /**
     * 运行代码
     *
     * @param inputList 输入用例
     * @param runCmd    运行的cmd
     * @return {@link List}<{@link ExecuteMessage}>
     * @throws RuntimeException RuntimeException
     */
    private List<ExecuteMessage> runCode(List<String> inputList, String runCmd) throws RuntimeException {
        List<ExecuteMessage> executeMessageList = new LinkedList<>();
        for (String input : inputList) {
            Process runProcess;
            Thread computeTimeThread;
            String run=runCmd;
            if(this.prefix.contains("java")){
                run = new StringBuilder(runCmd).append(" " + input).toString();
            }
            try {
                runProcess = Runtime.getRuntime().exec(run);
                computeTimeThread = new Thread(() -> {
                    try {
                        Thread.sleep(DEFAULT_TIME_OUT);
                        if (runProcess.isAlive()) {
                            log.info("超时了，中断");
                            runProcess.destroy();
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
                computeTimeThread.start();
                StopWatch stopWatch = new StopWatch();
                stopWatch.start();
                ExecuteMessage executeMessage = new ExecuteMessage();
                switch (this.prefix.substring(1)){
                    case "java":
                        executeMessage=ProcessUtils.runProcessAndGetMessage(runProcess, "运行");
                        break;
                    case "cpp":
                        executeMessage=ProcessUtils.handleProcessInteraction(runProcess,input,"运行");
                        break;
                    default:
                        executeMessage=ProcessUtils.runProcessAndGetMessage(runProcess, "运行");
                }
                stopWatch.stop();
                computeTimeThread.stop();
                executeMessage.setTime(stopWatch.getLastTaskTimeMillis());
                executeMessageList.add(executeMessage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return executeMessageList;
    }

    final ExecuteCodeResponse errorResponse(Throwable e) {
        return ExecuteCodeResponse
                .builder()
                .outputList(new ArrayList<>())
                .message(e.getMessage())
                .judgeInfo(new JudgeInfo())
                .status(2)
                .build();
    }
}
