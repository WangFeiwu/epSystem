package com.whut.epSystem.service;

import com.whut.epSystem.dto.QuestionExecution;
import com.whut.epSystem.entity.Question;
import com.whut.epSystem.exceptions.QuestionOperationException;

import java.util.List;

public interface QuestionService {
    Question getByQueId(long queId);

    QuestionExecution getQuestionList(long listId) throws QuestionOperationException;

    QuestionExecution batchAddQuestion(List<Question> questionList) throws QuestionOperationException;

    QuestionExecution modifyQuestion(Question question) throws QuestionOperationException;

    QuestionExecution deleteQuestion(long queId,long listId) throws QuestionOperationException;
}
