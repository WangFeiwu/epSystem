package com.whut.epSystem.web.exerciseslistadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/exerciseslistadmin",method = RequestMethod.GET)
public class ExercisesListAdminController {
    @RequestMapping("/exerciseslistoperation")
    public String exercisesListOperation(){
        return "exerciseslistoperation";
    }
}
