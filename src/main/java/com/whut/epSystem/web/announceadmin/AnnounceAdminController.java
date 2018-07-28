package com.whut.epSystem.web.announceadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/announceadmin",method = RequestMethod.GET)
public class AnnounceAdminController {
    @RequestMapping("/announceoperation")
    public String announceOperation(){
        return "announceoperation";
    }
}
