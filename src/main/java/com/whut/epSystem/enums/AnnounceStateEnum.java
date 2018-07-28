package com.whut.epSystem.enums;

public enum AnnounceStateEnum {
    SUCCESS(1,"操作成功"),INNER_ERROR(-1001,"内部系统错误"), NULL_ANNOUNCRID(-1002,"announceId为空"),
    NULL_ANNOUNCE(-1003,"ANNOUNCE信息为空");
    private int state;
    private String stateInfo;

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    private AnnounceStateEnum(int state, String stateInfo){
        this.state=state;
        this.stateInfo=stateInfo;
    }

    /**
     * 依据传入的state返回相应的enum值
     */
    public static AnnounceStateEnum stateOf(int state){
        for (AnnounceStateEnum stateEnum:values()){
            if(stateEnum.getState()==state){
                return stateEnum;
            }
        }
        return null;
    }

}
