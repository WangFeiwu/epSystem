package com.whut.epSystem.enums;

public enum QuestionStateEnum {
    SUCCESS(1,"操作成功"),INNER_ERROR(-1001,"内部系统错误"),
    NULL_QUEID(-1002,"queId为空"),EMPTY_LIST(-1003,"添加数少于1"),
    NULL_QUECONTENT(-1004,"queContent为空"),NULL_QUESTION(-1005,"QUESTION信息为空");
    private int state;
    private String stateInfo;

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    private QuestionStateEnum(int state, String stateInfo){
        this.state=state;
        this.stateInfo=stateInfo;
    }

    /**
     * 依据传入的state返回相应的enum值
     */
    public static QuestionStateEnum stateOf(int state){
        for (QuestionStateEnum stateEnum:values()){
            if(stateEnum.getState()==state){
                return stateEnum;
            }
        }
        return null;
    }

}
