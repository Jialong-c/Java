package com.yupi.oj.judge;

import com.yupi.oj.model.entity.QuestionSubmit;
import com.yupi.oj.model.vo.QuestionSubmitVO;

public interface JudgeService {

    /**
     * 判题
     * @param questionSubmitId
     * @return
     */
    QuestionSubmit doJudge(long questionSubmitId);
}
