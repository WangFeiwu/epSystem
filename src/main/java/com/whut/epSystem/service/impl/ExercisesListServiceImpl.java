package com.whut.epSystem.service.impl;

import com.whut.epSystem.dao.ExercisesListDao;
import com.whut.epSystem.dao.QuestionDao;
import com.whut.epSystem.dto.ExercisesListExecution;
import com.whut.epSystem.entity.ExercisesList;
import com.whut.epSystem.entity.Question;
import com.whut.epSystem.enums.ExercisesListStateEnum;
import com.whut.epSystem.exceptions.ExercisesListOperationException;
import com.whut.epSystem.service.ExercisesListService;
import com.whut.epSystem.util.PageCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ExercisesListServiceImpl implements ExercisesListService {
    @Autowired
    private ExercisesListDao exercisesListDao;

    @Autowired
    private QuestionDao questionDao;

    @Override
    public ExercisesListExecution getExercisesList(ExercisesList exercisesCondition,int pageIndex, int pageSize) {
        int rowIndex= PageCalculator.calculateRowIndex(pageIndex,pageSize);
        List<ExercisesList> exercisesLists=exercisesListDao.queryExercisesList(exercisesCondition,rowIndex,pageSize);
        int count=exercisesListDao.queryExercisesListCount(exercisesCondition);
        ExercisesListExecution ele=new ExercisesListExecution();
        if (exercisesLists!=null){
            ele.setExercisesListList(exercisesLists);
            ele.setCount(count);
        }else {
            ele.setState(ExercisesListStateEnum.INNER_ERROR.getState());
        }
        return ele;
    }

    @Override
    public ExercisesList getByListId(Long listId) {
        return exercisesListDao.queryByListId(listId);
    }

    @Override
    @Transactional
    public ExercisesListExecution modifyExercisesList(ExercisesList exercisesList) throws ExercisesListOperationException {
        if (exercisesList==null||exercisesList.getListId()==null){
            return new ExercisesListExecution(ExercisesListStateEnum.NULL_EXERCISELIST);
        }
        if (exercisesList.getListName()==""){
            return new ExercisesListExecution(ExercisesListStateEnum.NULL_LISTNAME);
        }
        try {
            exercisesList.setLastEditTime(new Date());
            int effectedNum= exercisesListDao.updateExercisesList(exercisesList);
            if (effectedNum<=0){
                return new ExercisesListExecution(ExercisesListStateEnum.INNER_ERROR);
            }else {
                exercisesList=exercisesListDao.queryByListId(exercisesList.getListId());
                return new ExercisesListExecution(ExercisesListStateEnum.SUCCESS,exercisesList);
            }
        }catch (Exception e){
            throw new ExercisesListOperationException("modifyExercisesList error:"+e.getMessage());
        }
    }

    @Override
    @Transactional
    public ExercisesListExecution addExercisesList(ExercisesList exercisesList) throws ExercisesListOperationException {
        if (exercisesList==null){
            return new ExercisesListExecution(ExercisesListStateEnum.NULL_EXERCISELIST);
        }
        if (exercisesList.getListName()==""){
            return new ExercisesListExecution(ExercisesListStateEnum.NULL_LISTNAME);
        }
        try {
            exercisesList.setCreateTime(new Date());
            exercisesList.setLastEditTime(new Date());
            int effectedNum= exercisesListDao.insertExercisesList(exercisesList);
            if (effectedNum<=0){
                throw new ExercisesListOperationException("习题创建失败！");
            }
        }catch (Exception e){
            throw new ExercisesListOperationException("addExercisesList error:"+e.getMessage());
        }
        return new ExercisesListExecution(ExercisesListStateEnum.SUCCESS,exercisesList);
    }

    @Override
    @Transactional
    public ExercisesListExecution deleteExercisesList(long listId) throws ExercisesListOperationException {
        //TODO 将此习题下的题目全部删掉
        List<Question> questionList=questionDao.queryQuestionList(listId);
        if (questionList.size()>0){
            for (Question q:questionList){
                questionDao.deleteQuetion(q.getQueId(),listId);
            }
        }
        try {
            int effectedNum=exercisesListDao.deleteExercisesList(listId);
            if (effectedNum<=0){
                throw new ExercisesListOperationException("习题删除失败");
            }else {
                return new ExercisesListExecution(ExercisesListStateEnum.SUCCESS);
            }
        }catch (Exception e){
            throw new ExercisesListOperationException("deleteExercisesList error:"+e.getMessage());
        }
    }
}
