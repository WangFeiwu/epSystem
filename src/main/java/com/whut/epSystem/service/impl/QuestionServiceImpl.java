package com.whut.epSystem.service.impl;

import com.whut.epSystem.dao.QuestionDao;
import com.whut.epSystem.dto.QuestionExecution;
import com.whut.epSystem.entity.Question;
import com.whut.epSystem.enums.QuestionStateEnum;
import com.whut.epSystem.exceptions.QuestionOperationException;
import com.whut.epSystem.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionDao questionDao;

    @Override
    public Question getByQueId(long queId) {
        return questionDao.queryByQueId(queId);
    }

    @Override
    public QuestionExecution getQuestionList(long listId) throws QuestionOperationException {
        List<Question> questionList=questionDao.queryQuestionList(listId);
        QuestionExecution qe=new QuestionExecution();
        if (questionList!=null)
            qe.setQuestionList(questionList);
        else
            qe.setState(QuestionStateEnum.INNER_ERROR.getState());
        return qe;
    }

    @Override
    @Transactional
    public QuestionExecution batchAddQuestion(List<Question> questionList) throws QuestionOperationException {
        if (questionList!=null&&questionList.size()>0){
            try {
                for (Question q:questionList){
                    q.setCreateTime(new Date());
                    q.setLastEditTime(new Date());
                }
                int effectedNum=questionDao.batchInsertQuestion(questionList);
                if (effectedNum<=0){
                    throw new QuestionOperationException("习题添加失败");
                }else {
                    return new QuestionExecution(QuestionStateEnum.SUCCESS);
                }
            }catch (Exception e){
                throw new QuestionOperationException("batchAddProductCategory error:"+e.getMessage());
            }
        }else {
            return new QuestionExecution(QuestionStateEnum.EMPTY_LIST);
        }
    }

    @Override
    @Transactional
    public QuestionExecution modifyQuestion(Question question) throws QuestionOperationException {
        if (question==null||question.getQueId()==null){
            return new QuestionExecution(QuestionStateEnum.NULL_QUESTION);
        }
        if (question.getQueContent()==""){
            return new QuestionExecution(QuestionStateEnum.NULL_QUECONTENT);
        }
        try {
            question.setLastEditTime(new Date());
            int effectedNum=questionDao.updateQuestion(question);
            if (effectedNum<=0){
                return new QuestionExecution(QuestionStateEnum.INNER_ERROR);
            }else {
                question=questionDao.queryByQueId(question.getQueId());
                return new QuestionExecution(QuestionStateEnum.SUCCESS,question);
            }
        }catch (Exception e){
            throw new QuestionOperationException("modifyQuestion error:"+e.getMessage());
        }
    }

    @Override
    @Transactional
    public QuestionExecution deleteQuestion(long queId,long listId) throws QuestionOperationException {
        try {
            int effectedNum=questionDao.deleteQuetion(queId,listId);
            if (effectedNum<=0){
                throw new QuestionOperationException("删除失败");
            }else {
                return new QuestionExecution(QuestionStateEnum.SUCCESS);
            }
        }catch (Exception e){
            throw new QuestionOperationException("deleteQuestion error:"+e.getMessage());
        }
    }
}
