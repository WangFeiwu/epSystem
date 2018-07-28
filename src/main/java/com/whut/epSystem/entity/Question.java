package com.whut.epSystem.entity;

import java.util.Date;

public class Question {
    private Long queId;
    private Integer queType;//1主观题，2客观题
    private String queContent;
    private String queOptionA;
    private String queOptionB;
    private String queOptionC;
    private String queOptionD;
    private String queAnswer;
    private Date createTime;
    private Date lastEditTime;
    private Long listId;

    public Long getQueId() {
        return queId;
    }

    public void setQueId(Long queId) {
        this.queId = queId;
    }

    public Integer getQueType() {
        return queType;
    }

    public void setQueType(Integer queType) {
        this.queType = queType;
    }

    public String getQueContent() {
        return queContent;
    }

    public void setQueContent(String queContent) {
        this.queContent = queContent;
    }

    public String getQueOptionA() {
        return queOptionA;
    }

    public void setQueOptionA(String queOptionA) {
        this.queOptionA = queOptionA;
    }

    public String getQueOptionB() {
        return queOptionB;
    }

    public void setQueOptionB(String queOptionB) {
        this.queOptionB = queOptionB;
    }

    public String getQueOptionC() {
        return queOptionC;
    }

    public void setQueOptionC(String queOptionC) {
        this.queOptionC = queOptionC;
    }

    public String getQueOptionD() {
        return queOptionD;
    }

    public void setQueOptionD(String queOptionD) {
        this.queOptionD = queOptionD;
    }

    public String getQueAnswer() {
        return queAnswer;
    }

    public void setQueAnswer(String queAnswer) {
        this.queAnswer = queAnswer;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
        this.listId = listId;
    }
}
