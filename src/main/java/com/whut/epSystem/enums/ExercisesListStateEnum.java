package com.whut.epSystem.enums;

public enum ExercisesListStateEnum {
    SUCCESS(1,"操作成功"),INNER_ERROR(-1001,"内部系统错误"), NULL_LISTID(-1002,"listId为空"),
    NULL_EXERCISELIST(-1003,"EXERCISELIST信息为空"),NULL_LISTNAME(-1004,"listName为空");
    private int state;
    private String stateInfo;

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    private ExercisesListStateEnum(int state, String stateInfo){
        this.state=state;
        this.stateInfo=stateInfo;
    }

    /**
     * 依据传入的state返回相应的enum值
     */
    public static ExercisesListStateEnum stateOf(int state){
        for (ExercisesListStateEnum stateEnum:values()){
            if(stateEnum.getState()==state){
                return stateEnum;
            }
        }
        return null;
    }

}
