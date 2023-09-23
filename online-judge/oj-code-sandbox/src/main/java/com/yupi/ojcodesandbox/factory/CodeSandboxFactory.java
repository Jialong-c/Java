package com.yupi.ojcodesandbox.factory;

import com.yupi.ojcodesandbox.CodeSandboxTemplate;
import com.yupi.ojcodesandbox.CppNativeCodeSandbox;
import com.yupi.ojcodesandbox.JavaNativeCodeSandbox;
import com.yupi.ojcodesandbox.model.enums.QuestionSubmitLanguageEnum;


public class CodeSandboxFactory {
    public static CodeSandboxTemplate getInstance(QuestionSubmitLanguageEnum language) {
        switch (language){
            case JAVA:
                return new JavaNativeCodeSandbox();
            case CPP:
                return new CppNativeCodeSandbox();
            default:
                throw new RuntimeException("暂不支持");
        }
    }
}
