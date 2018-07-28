package com.whut.epSystem.dao;

import com.whut.epSystem.BaseTest;
import com.whut.epSystem.entity.ExercisesList;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ExerciseListDaoTest extends BaseTest {
    @Autowired
    private ExercisesListDao exercisesListDao;

    @Test
    @Ignore
    public void testDeleteExercisesList(){
        long listId=25;
        int x=exercisesListDao.deleteExercisesList(listId);
        System.out.println(x);
    }

    @Test
    @Ignore
    public void testQueryByListId(){
        long listId=2;
        ExercisesList exercisesList=exercisesListDao.queryByListId(listId);
        System.out.println(exercisesList.getListName());
    }

    @Test
    @Ignore
    public void testInsertExercisesList(){
        ExercisesList exercisesList=new ExercisesList();
        exercisesList.setListName("第一次作业");
        exercisesList.setListDesc("提交截止日期：4.1");
        exercisesList.setListType(1);
        int effectedNum=exercisesListDao.insertExercisesList(exercisesList);
        assertEquals(1,effectedNum);
    }

    @Test
    @Ignore
    public void testUpdateExercisesList(){
        ExercisesList exercisesList=new ExercisesList();
        exercisesList.setListId(1L);
        exercisesList.setListName("第一次考试");
        exercisesList.setListDesc("提交截止日期：4.1");
        exercisesList.setListType(2);
        exercisesList.setLastEditTime(new Date());
        int effectedNum=exercisesListDao.updateExercisesList(exercisesList);
        assertEquals(1,effectedNum);
    }
}
