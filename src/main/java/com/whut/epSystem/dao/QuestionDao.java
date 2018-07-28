package com.whut.epSystem.dao;

import com.whut.epSystem.entity.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionDao {
    /**
     * 通过listId查询题目
     * @param listId
     * @return List<Question>
     */
    List<Question> queryQuestionList(long listId);

    Question queryByQueId(long queId);

    /**
     * 批量新增题目
     * @param questionList
     * @return
     */
    int batchInsertQuestion(List<Question> questionList);

    int updateQuestion(Question question);

    /**
     * 删除指定题目
     * @param queId
     * @param listId
     * @return
     */
    int deleteQuetion(@Param("queId") long queId,@Param("listId") long listId);
}
