package com.whut.epSystem.dto;

import com.whut.epSystem.entity.Announce;
import com.whut.epSystem.enums.AnnounceStateEnum;

import java.util.List;

public class AnnounceExecution {
    //结果状态
    private int state;

    //状态标识
    private String stateInfo;

    //数量
    private int count;

    //操作的announce（增删改的时候用到）
    private Announce announce;

    //announceList列表（查询的时候使用）
    private List<Announce> announceList;

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

    public Announce getAnnounce() {
        return announce;
    }

    public void setAnnounce(Announce announce) {
        this.announce = announce;
    }

    public List<Announce> getAnnounceList() {
        return announceList;
    }

    public void setAnnounceList(List<Announce> announceList) {
        this.announceList = announceList;
    }

    public AnnounceExecution(){

    }

    //习题列表操作失败的时候使用的构造器
    public AnnounceExecution(AnnounceStateEnum stateEnum){
        this.state=stateEnum.getState();
        this.stateInfo=stateEnum.getStateInfo();
    }

    //习题列表操作成功的时候使用的构造器
    public AnnounceExecution(AnnounceStateEnum stateEnum, Announce announce){
        this.state=stateEnum.getState();
        this.stateInfo=stateEnum.getStateInfo();
        this.announce=announce;
    }

    //习题列表操作成功的时候使用的构造器
    public AnnounceExecution(AnnounceStateEnum stateEnum, List<Announce> announceList){
        this.state=stateEnum.getState();
        this.stateInfo=stateEnum.getStateInfo();
        this.announceList=announceList;
    }
}
