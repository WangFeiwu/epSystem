package com.whut.epSystem.web.superadmin;

import com.whut.epSystem.entity.PersonInfo;
import com.whut.epSystem.service.PersonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/superadmin")
public class PersonInfoController {
    @Autowired
    private PersonInfoService personInfoService;

    @RequestMapping(value = "/listperson",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listPersonInfo(){
        Map<String,Object> modelMap=new HashMap<String,Object>();
        List<PersonInfo> list=new ArrayList<PersonInfo>();
        try {
            list=personInfoService.getPersonInfoList();
            modelMap.put("rows",list);
            modelMap.put("total",list.size());
        }catch (Exception e){
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.toString());
        }
        return modelMap;
    }
}
