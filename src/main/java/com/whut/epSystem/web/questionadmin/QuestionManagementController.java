package com.whut.epSystem.web.questionadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.whut.epSystem.dto.QuestionExecution;
import com.whut.epSystem.entity.ExercisesList;
import com.whut.epSystem.entity.Question;
import com.whut.epSystem.enums.QuestionStateEnum;
import com.whut.epSystem.exceptions.QuestionOperationException;
import com.whut.epSystem.service.QuestionService;
import com.whut.epSystem.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/questionadmin")
public class QuestionManagementController {
    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/getquestionbyid",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> getQuestionById(HttpServletRequest request){
        Map<String,Object> modelMap=new HashMap<String,Object>();
        Long queId= HttpServletRequestUtil.getLong(request,"queId");
        if (queId>-1){
            try {
                Question question=questionService.getByQueId(queId);
                modelMap.put("question",question);
                modelMap.put("success",true);
            }catch (Exception e){
                modelMap.put("success",false);
                modelMap.put("errMsg",e.toString());
            }
        }else{
            modelMap.put("success",false);
            modelMap.put("errMsg","empty queId");
        }
        return modelMap;
    }

    @RequestMapping(value = "/addquestions",method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> addQuestions(@RequestBody List<Question> questionList, HttpServletRequest request){
        Map<String,Object> modelMap=new HashMap<String,Object>();
        Long listId= HttpServletRequestUtil.getLong(request,"listId");
        for (Question q:questionList){
            q.setListId(listId);
        }
        if (questionList!=null&&questionList.size()>0){
            try {
                QuestionExecution qe=questionService.batchAddQuestion(questionList);
                if (qe.getState() == QuestionStateEnum.SUCCESS.getState()){
                    modelMap.put("success",true);
                }else {
                    modelMap.put("success",false);
                    modelMap.put("errMsg",qe.getStateInfo());
                }
            }catch (QuestionOperationException e){
                modelMap.put("success",false);
                modelMap.put("errMsg",e.toString());
                return modelMap;
            }
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","请至少输入一个习题");
        }
        return modelMap;
    }

    @RequestMapping(value = "/modifyquestion",method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> modifyQuestion(HttpServletRequest request){
        Map<String,Object> modelMap=new HashMap<String,Object>();
        String questionStr= HttpServletRequestUtil.getString(request,"questionStr");
        ObjectMapper mapper=new ObjectMapper();
        Question question;
        try {
            question=mapper.readValue(questionStr,Question.class);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }
        if (question!=null&&question.getQueId()!=null){
            QuestionExecution qe;
            try {
                qe=questionService.modifyQuestion(question);
                if (qe.getState()==QuestionStateEnum.SUCCESS.getState()){
                    modelMap.put("success",true);
                }else {
                    modelMap.put("success",false);
                    modelMap.put("errMsg",qe.getStateInfo());
                }
            }catch (QuestionOperationException e){
                modelMap.put("success",false);
                modelMap.put("errMsg",e.getMessage());
            }
            return modelMap;
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入题目id");
            return modelMap;
        }
    }

    @RequestMapping(value = "/getquestionlist",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> getQuestionList(HttpServletRequest request){
        Map<String,Object> modelMap=new HashMap<String,Object>();
        List<Question> questionList=new ArrayList<Question>();
        long listId=HttpServletRequestUtil.getLong(request,"listId");
        try {
            if (listId>=0){
                ExercisesList currentExercisesList=new ExercisesList();
                currentExercisesList.setListId(listId);
                request.getSession().setAttribute("currentExercisesList",currentExercisesList);
            }
            QuestionExecution qe=questionService.getQuestionList(listId);
            modelMap.put("questionList",qe.getQuestionList());
            modelMap.put("success",true);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
        }
        return modelMap;
    }

    @RequestMapping(value = "/removequestion",method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> removeQuestion(Long queId,HttpServletRequest request){
        Map<String,Object> modelMap=new HashMap<String,Object>();
        if (queId!=null&&queId>0){
            try {
                ExercisesList currentExercisesList=(ExercisesList) request.getSession().getAttribute("currentExercisesList");
                QuestionExecution qe=questionService.deleteQuestion(queId,currentExercisesList.getListId());
                if (qe.getState()==QuestionStateEnum.SUCCESS.getState()){
                    modelMap.put("success",true);
                }else {
                    modelMap.put("success",false);
                    modelMap.put("errMsg",qe.getStateInfo());
                }
            }catch (QuestionOperationException e){
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
