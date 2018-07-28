package com.whut.epSystem.web;

import com.whut.epSystem.entity.PersonInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/whut",method = RequestMethod.GET)
public class whut {
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String whut(HttpSession session){
        PersonInfo user=(PersonInfo)session.getAttribute("user");
        if (user!=null) {
            return "index";
        }
        return "login";
    }
}
