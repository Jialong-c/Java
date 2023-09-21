package com.yupi.ojcodesandbox;

import com.yupi.ojcodesandbox.model.ExecuteCodeRequest;
import com.yupi.ojcodesandbox.model.ExecuteCodeResponse;

public interface CodeSandbox {

    /**
     * 执行代码
     *
     * @param executeCodeRequest
     * @return
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
