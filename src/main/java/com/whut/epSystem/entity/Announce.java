package com.whut.epSystem.entity;

import java.util.Date;

public class Announce {
    private Long announceId;
    private Integer type;//1公告，2评分标准
    private String title;
    private String content;
    private Date createTime;
    private Date lastEditTime;

    public Long getAnnounceId() {
        return announceId;
    }

    public void setAnnounceId(Long announceId) {
        this.announceId = announceId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
}
