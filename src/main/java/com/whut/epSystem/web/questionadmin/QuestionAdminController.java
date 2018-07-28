package com.whut.epSystem.web.questionadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/questionadmin",method = RequestMethod.GET)
public class QuestionAdminController {
    @RequestMapping("/questionlist")
    public String questionList(){
        return "questionlist";
    }
    @RequestMapping("/batchaddquestion")
    public String batchAddQuestion(){
        return "batchaddquestion";
    }
    @RequestMapping("/modifyquestion")
    public String modifyQuestion(){
        return "modifyquestion";
    }
    @RequestMapping("/questionlistadmin")
    public String questionAdmin(){
        return "questionadmin";
    }
}
