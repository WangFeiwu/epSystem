package com.whut.epSystem.service;

import com.whut.epSystem.dto.ExercisesListExecution;
import com.whut.epSystem.entity.ExercisesList;
import com.whut.epSystem.exceptions.ExercisesListOperationException;

public interface ExercisesListService {
    /**
     * 分页返回习题列表
     * @param pageIndex
     * @param pageSize
     * @return
     */
    ExercisesListExecution getExercisesList(ExercisesList exercisesCondition,int pageIndex,int pageSize);

    ExercisesList getByListId(Long listId);

    ExercisesListExecution modifyExercisesList(ExercisesList exercisesList) throws ExercisesListOperationException;

    ExercisesListExecution addExercisesList(ExercisesList exercisesList) throws ExercisesListOperationException;

    ExercisesListExecution deleteExercisesList(long listId) throws ExercisesListOperationException;
}
