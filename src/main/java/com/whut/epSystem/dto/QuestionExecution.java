package com.whut.epSystem.dto;

import com.whut.epSystem.entity.Question;
import com.whut.epSystem.enums.QuestionStateEnum;

import java.util.List;

public class QuestionExecution {
    //结果状态
    private int state;

    //状态标识
    private String stateInfo;

    //数量
    private int count;

    //操作的question（增删改的时候用到）
    private Question question;

    //questionList列表（查询的时候使用）
    private List<Question> questionList;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public QuestionExecution(){

    }

    //习题列表操作失败的时候使用的构造器
    public QuestionExecution(QuestionStateEnum stateEnum){
        this.state=stateEnum.getState();
        this.stateInfo=stateEnum.getStateInfo();
    }

    //习题列表操作成功的时候使用的构造器
    public QuestionExecution(QuestionStateEnum stateEnum, Question question){
        this.state=stateEnum.getState();
        this.stateInfo=stateEnum.getStateInfo();
        this.question=question;
    }

    //习题列表操作成功的时候使用的构造器
    public QuestionExecution(QuestionStateEnum stateEnum, List<Question> questionList){
        this.state=stateEnum.getState();
        this.stateInfo=stateEnum.getStateInfo();
        this.questionList=questionList;
    }
}
