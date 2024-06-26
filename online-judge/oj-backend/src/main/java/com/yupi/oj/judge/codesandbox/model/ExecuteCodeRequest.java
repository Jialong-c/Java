package com.yupi.oj.judge.codesandbox.model;

import com.yupi.oj.model.enums.QuestionSubmitLanguageEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExecuteCodeRequest {

    private List<String> inputList;

    private String code;

    private QuestionSubmitLanguageEnum language;
}
