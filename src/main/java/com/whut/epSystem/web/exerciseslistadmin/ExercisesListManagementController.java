package com.whut.epSystem.web.exerciseslistadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.whut.epSystem.dto.ExercisesListExecution;
import com.whut.epSystem.entity.ExercisesList;
import com.whut.epSystem.enums.ExercisesListStateEnum;
import com.whut.epSystem.exceptions.ExercisesListOperationException;
import com.whut.epSystem.service.ExercisesListService;
import com.whut.epSystem.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/exerciseslistadmin")
public class ExercisesListManagementController {
    @Autowired
    private ExercisesListService exercisesListService;

    @RequestMapping(value = "/getexerciseslistmanagementinfo",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> getExercisesListManagementInfo(HttpServletRequest request){
        Map<String,Object> modelMap=new HashMap<String,Object>();
        long listId=HttpServletRequestUtil.getLong(request,"listId");
        if (listId<0){
            Object currentExercisesListObj=request.getSession().getAttribute("currentExercisesListObj");
            if (currentExercisesListObj==null){
                modelMap.put("redirect",true);
                modelMap.put("url","/epSystem/whut/index");
            }else {
                ExercisesList currentExercisesList=(ExercisesList)currentExercisesListObj;
                modelMap.put("redirect",false);
                modelMap.put("listId",currentExercisesList.getListId());
            }
        }else {
            ExercisesList currentExercisesList=new ExercisesList();
            currentExercisesList.setListId(listId);
            request.getSession().setAttribute("currentExercisesList",currentExercisesList);
            modelMap.put("redirect",false);
        }
        return modelMap;
    }

    @RequestMapping(value = "/getexerciseslist",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> getExercisesList(HttpServletRequest request){
        Map<String,Object> modelMap=new HashMap<String,Object>();
        List<ExercisesList> exercisesLists=new ArrayList<ExercisesList>();
        int listType=HttpServletRequestUtil.getInt(request,"listType");
        if (listType>-1){
            try {
                ExercisesList exercisesCondition=new ExercisesList();
                exercisesCondition.setListType(listType);
                ExercisesListExecution ele=exercisesListService.getExercisesList(exercisesCondition,0,100);
                modelMap.put("exercisesLists",ele.getExercisesListList());
                modelMap.put("success",true);
            }catch (Exception e){
                modelMap.put("success",false);
                modelMap.put("errMsg",e.getMessage());
            }
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","empty listType");
        }
        return modelMap;
    }

    @RequestMapping(value = "/getexerciseslistbyid",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> getExercisesListById(HttpServletRequest request){
        Map<String,Object> modelMap=new HashMap<String,Object>();
        Long listId=HttpServletRequestUtil.getLong(request,"listId");
        if (listId>-1){
            try {
                ExercisesList exercisesList=exercisesListService.getByListId(listId);
                modelMap.put("exercisesList",exercisesList);
                modelMap.put("success",true);
            }catch (Exception e){
                modelMap.put("success",false);
                modelMap.put("errMsg",e.toString());
            }
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","empty listId");
        }
        return modelMap;
    }

    @RequestMapping(value = "/addexerciseslist",method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> addExercisesList(HttpServletRequest request){
        Map<String,Object> modelMap=new HashMap<String,Object>();
        String listStr= HttpServletRequestUtil.getString(request,"listStr");
        ObjectMapper mapper=new ObjectMapper();
        ExercisesList exercisesList;
        try {
            exercisesList=mapper.readValue(listStr,ExercisesList.class);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }
        if (exercisesList!=null){
            ExercisesListExecution ele=null;
            try {
                ele=exercisesListService.addExercisesList(exercisesList);
                if (ele.getState()== ExercisesListStateEnum.SUCCESS.getState()){
                    modelMap.put("success",true);
                    List<ExercisesList> exercisesListList=(List<ExercisesList>)request.getSession().getAttribute("exercisesListList");
                    if (exercisesListList==null||exercisesListList.size()==0){
                        exercisesListList=new ArrayList<ExercisesList>();
                    }
                    exercisesListList.add(ele.getExercisesList());
                    request.getSession().setAttribute("exercisesListList",exercisesListList);
                }else {
                    modelMap.put("success",false);
                    modelMap.put("errMsg",ele.getStateInfo());
                }
            }catch (ExercisesListOperationException e){
                modelMap.put("success",false);
                modelMap.put("errMsg",e.getMessage());
            }
            return modelMap;
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入习题信息");
            return modelMap;
        }
    }

    @RequestMapping(value = "/modifyexerciseslist",method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> modifyExercisesList(HttpServletRequest request){
        Map<String,Object> modelMap=new HashMap<String,Object>();
        String listStr= HttpServletRequestUtil.getString(request,"listStr");
        ObjectMapper mapper=new ObjectMapper();
        ExercisesList exercisesList;
        try {
            exercisesList=mapper.readValue(listStr,ExercisesList.class);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }
        if (exercisesList!=null||exercisesList.getListId()!=null){
            ExercisesListExecution ele;
            try {
                ele=exercisesListService.modifyExercisesList(exercisesList);
                if (ele.getState()== ExercisesListStateEnum.SUCCESS.getState()){
                    modelMap.put("success",true);
                }else {
                    modelMap.put("success",false);
                    modelMap.put("errMsg",ele.getStateInfo());
                }
            }catch (ExercisesListOperationException e){
                modelMap.put("success",false);
                modelMap.put("errMsg",e.getMessage());
            }
            return modelMap;
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入习题id");
            return modelMap;
        }
    }

    @RequestMapping(value = "/removeexercise",method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> removeExercise(Long listId){
        Map<String,Object> modelMap=new HashMap<String,Object>();
        if (listId!=null&&listId>0){
            try {
                ExercisesListExecution ele=exercisesListService.deleteExercisesList(listId);
                if (ele.getState()==ExercisesListStateEnum.SUCCESS.getState()){
                    modelMap.put("success",true);
                }else {
                    modelMap.put("success",false);
                    modelMap.put("errMsg",ele.getStateInfo());
                }
            }catch (ExercisesListOperationException e){
                modelMap.put("success",false);
                modelMap.put("errMsg",e.toString());
                return modelMap;
            }
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","请至少选择一个");
        }
        return modelMap;
    }
}
