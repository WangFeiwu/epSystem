package com.whut.epSystem.dao;

import com.whut.epSystem.BaseTest;
import com.whut.epSystem.entity.ExercisesList;
import com.whut.epSystem.entity.Question;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuestionDaoTest extends BaseTest {
    @Autowired
    private QuestionDao questionDao;

    @Test
    public void testQueryByQueId(){
        long queId=1;
        Question question=questionDao.queryByQueId(queId);
        System.out.println(question.getQueContent());
    }

    @Test
    @Ignore
    public void testUpdataQuestion(){
        Question question=new Question();
        question.setQueId(1L);
        question.setQueContent("1+2=()");
        question.setQueAnswer("B");
        int effectedNum=questionDao.updateQuestion(question);
        System.out.println(effectedNum);
    }

    @Test
    @Ignore
    public void testBatchInsertQuestion(){
        ExercisesList exercisesList=new ExercisesList();
        Question question=new Question();
        question.setQueType(2);
        question.setQueContent("1+11=()");
        question.setQueOptionA("10");
        question.setQueOptionB("11");
        question.setQueOptionC("12");
        question.setQueOptionD("13");
        question.setQueAnswer("c");
        question.setCreateTime(new Date());
        question.setListId(26L);
        Question question2=new Question();
        question2.setQueType(1);
        question2.setQueContent("3+1=?");
        question2.setQueAnswer("4");
        question2.setCreateTime(new Date());
        question2.setListId(26L);
        List<Question> questionList=new ArrayList<Question>();
        questionList.add(question);
        questionList.add(question2);
        int effectedNum=questionDao.batchInsertQuestion(questionList);
        System.out.println(effectedNum);
    }

    @Test
    @Ignore
    public void testQueryQuestionList(){
        long listId=26;
        List<Question> questionList=questionDao.queryQuestionList(listId);
        System.out.println("题目数量为："+questionList.size());
    }

    @Test
    @Ignore
    public void testDeleteQuestion(){
        long queId=2;
        long listId=26;
        int effectedNum=questionDao.deleteQuetion(queId,listId);
        System.out.println(effectedNum);
    }
}
