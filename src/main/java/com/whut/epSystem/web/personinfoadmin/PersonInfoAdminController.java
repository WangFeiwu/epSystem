package com.whut.epSystem.web.personinfoadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/personinfoadmin",method = RequestMethod.GET)
public class PersonInfoAdminController {
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "login";
    }

    @RequestMapping("/batchadduser")
    public String batchAddUser(){
        return "batchadduser";
    }
}
