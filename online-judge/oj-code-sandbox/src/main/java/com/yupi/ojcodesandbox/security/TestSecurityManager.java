package com.yupi.ojcodesandbox.security;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 测试安全管理器
 */
public class TestSecurityManager {

    public static void main(String[] args) {
        System.setSecurityManager(new MySecurityManager());

        //FileUtil.readLines(new File("E:\\Download\\Idea\\Java\\online-judge\\oj-code-sandbox\\src\\main\\resources\\application.yml"), StandardCharsets.UTF_8);
        FileUtil.writeString("aa", "aaa", Charset.defaultCharset());
    }
}
