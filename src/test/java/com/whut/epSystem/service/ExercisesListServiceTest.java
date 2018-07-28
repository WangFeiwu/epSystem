package com.whut.epSystem.service;

import com.whut.epSystem.BaseTest;
import com.whut.epSystem.dto.ExercisesListExecution;
import com.whut.epSystem.entity.ExercisesList;
import com.whut.epSystem.enums.ExercisesListStateEnum;
import com.whut.epSystem.exceptions.ExercisesListOperationException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ExercisesListServiceTest extends BaseTest {
    @Autowired
    private ExercisesListService exercisesListService;

    @Test
    public void testAddExercisesList(){
        ExercisesList exercisesList=new ExercisesList();
        exercisesList.setListName("习题一");
        exercisesList.setListDesc("提交截止日期：4.1");
        exercisesList.setListType(1);
        exercisesList.setLastEditTime(new Date());
        ExercisesListExecution ele=exercisesListService.addExercisesList(exercisesList);
        assertEquals(ExercisesListStateEnum.SUCCESS.getState(),ele.getState());
    }
}
