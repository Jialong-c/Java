package com.yupi.oj.model.dto.question;

import lombok.Data;

/**
 * 判题配置
 */
@Data
public class JudgeCase {

    /**
     * 输入用例
     */
    private String input;

    /**
     * 输出用例
     */
    private String output;
}
