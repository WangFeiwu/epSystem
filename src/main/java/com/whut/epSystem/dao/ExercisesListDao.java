package com.whut.epSystem.dao;

import com.whut.epSystem.entity.ExercisesList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExercisesListDao {
    /**
     * 分页查询习题表
     * @param exercisesCondition 习题类别
     * @param rowIndex 从第几行开始取数据
     * @param pageSize 返回的条数
     * @return
     */
    List<ExercisesList> queryExercisesList(@Param("exercisesCondition") ExercisesList exercisesCondition, @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

    /**
     * 返回习题表总数
     * @return
     */
    int queryExercisesListCount(@Param("exercisesCondition") ExercisesList exercisesCondition);

    /**
     * 通过listId查询习题
     * @param listId
     * @return
     */
    ExercisesList queryByListId(long listId);

    /**
     * 新增一次习题
     * @param exercisesList
     * @return 1成功，-1失败
     */
    int insertExercisesList(ExercisesList exercisesList);

    /**
     * 更新习题相关信息
     * @param exercisesList
     * @return
     */
    int updateExercisesList(ExercisesList exercisesList);

    /**
     * 删除指定的习题
     * @param listId
     * @return
     */
    int deleteExercisesList(long listId);
}
