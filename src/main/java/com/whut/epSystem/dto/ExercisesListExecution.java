package com.whut.epSystem.dto;

import com.whut.epSystem.entity.ExercisesList;
import com.whut.epSystem.enums.ExercisesListStateEnum;

import java.util.List;

public class ExercisesListExecution {
    //结果状态
    private int state;

    //状态标识
    private String stateInfo;

    //数量
    private int count;

    //操作的exercisesList（增删改的时候用到）
    private ExercisesList exercisesList;

    //exercisesList列表（查询的时候使用）
    private List<ExercisesList> exercisesListList;

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

    public ExercisesList getExercisesList() {
        return exercisesList;
    }

    public void setExercisesList(ExercisesList exercisesList) {
        this.exercisesList = exercisesList;
    }

    public List<ExercisesList> getExercisesListList() {
        return exercisesListList;
    }

    public void setExercisesListList(List<ExercisesList> exercisesListList) {
        this.exercisesListList = exercisesListList;
    }

    public ExercisesListExecution(){

    }

    //习题列表操作失败的时候使用的构造器
    public ExercisesListExecution(ExercisesListStateEnum stateEnum){
        this.state=stateEnum.getState();
        this.stateInfo=stateEnum.getStateInfo();
    }

    //习题列表操作成功的时候使用的构造器
    public ExercisesListExecution(ExercisesListStateEnum stateEnum,ExercisesList exercisesList){
        this.state=stateEnum.getState();
        this.stateInfo=stateEnum.getStateInfo();
        this.exercisesList=exercisesList;
    }

    //习题列表操作成功的时候使用的构造器
    public ExercisesListExecution(ExercisesListStateEnum stateEnum,List<ExercisesList> exercisesListList){
        this.state=stateEnum.getState();
        this.stateInfo=stateEnum.getStateInfo();
        this.exercisesListList=exercisesListList;
    }
}
