package com.yupi.oj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.oj.model.dto.question.QuestionQueryRequest;
import com.yupi.oj.model.entity.Question;
import com.yupi.oj.model.entity.Question;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.oj.model.vo.QuestionManageVO;
import com.yupi.oj.model.vo.QuestionVO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
* @author Jialong
* @description 针对表【question(题目)】的数据库操作Service
* @createDate 2023-09-14 15:09:48
*/
@Service
public interface QuestionService extends IService<Question> {

    /**
     * 校验
     *
     * @param question
     * @param add
     */
    void validQuestion(Question question, boolean add);

    /**
     * 获取查询条件
     *
     * @param questionQueryRequest
     * @return
     */
    QueryWrapper<Question> getQueryWrapper(QuestionQueryRequest questionQueryRequest);

    /**
     * 获取题目封装
     *
     * @param question
     * @return
     */
    QuestionVO getQuestionVO(Question question);

    /**
     * 分页获取题目封装
     *
     * @param questionPage
     * @return
     */
    Page<QuestionVO> getQuestionVOPage(Page<Question> questionPage);

    /**
     * 管理题目列表页面
     *
     * @param questionPage   题目分页
     * @param queryWrapper 查询条件
     * @return {@link Page}<{@link QuestionManageVO}>
     */
    Page<QuestionManageVO> listManageQuestionByPage(Page<Question> questionPage, QueryWrapper<Question> queryWrapper);
}
