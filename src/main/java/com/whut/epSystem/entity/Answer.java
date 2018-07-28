package com.whut.epSystem.entity;

import java.util.Date;

public class Answer {
    private Long ansId;
    private String ansContent;
    private String ansFileName;
    private Date createTime;
    private Date lastEditTime;
    private PersonInfo owner;
    private Question question;

    public Long getAnsId() {
        return ansId;
    }

    public void setAnsId(Long ansId) {
        this.ansId = ansId;
    }

    public String getAnsContent() {
        return ansContent;
    }

    public void setAnsContent(String ansContent) {
        this.ansContent = ansContent;
    }

    public String getAnsFileName() {
        return ansFileName;
    }

    public void setAnsFileName(String ansFileName) {
        this.ansFileName = ansFileName;
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

    public PersonInfo getOwner() {
        return owner;
    }

    public void setOwner(PersonInfo owner) {
        this.owner = owner;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
