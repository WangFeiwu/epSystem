package com.whut.epSystem.web.personinfoadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.whut.epSystem.entity.PersonInfo;
import com.whut.epSystem.service.PersonInfoService;
import com.whut.epSystem.util.HttpServletRequestUtil;
import com.whut.epSystem.util.ImportExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/personinfoadmin")
@SessionAttributes("user")
public class PersonInfoManagementController {
    @Autowired
    private PersonInfoService personInfoService;

    @RequestMapping(value = "/checklogin",method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> checkLogin(HttpServletRequest request){
        Map<String,Object> modelMap=new HashMap<String,Object>();
        String personStr= HttpServletRequestUtil.getString(request,"personStr");
        ObjectMapper mapper=new ObjectMapper();
        PersonInfo user;
        try {
            user=mapper.readValue(personStr,PersonInfo.class);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }
        if (user.getNumber()!=""&&user.getPassword()!=""){
            try {
                user=personInfoService.checkLogin(user);
                if (user!=null){
                    request.getSession().setAttribute("user",user);
                    modelMap.put("user",user);
                    modelMap.put("success",true);
                }else {
                    modelMap.put("success",false);
                    modelMap.put("errMsg","账号不存在或密码错误");
                }
            }catch (Exception e){
                modelMap.put("success",false);
                modelMap.put("errMsg",e.getMessage());
            }
            return modelMap;
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入登录信息");
            return modelMap;
        }
    }

    @RequestMapping(value = "/getuserinfo",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> getUserInfo(HttpSession session){
        Map<String,Object> modelMap=new HashMap<String,Object>();
        PersonInfo user=(PersonInfo)session.getAttribute("user");
        if (user!=null){
            modelMap.put("user",user);
            modelMap.put("success",true);
        }else {
            modelMap.put("success",false);
        }
        return modelMap;
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> register(HttpServletRequest request){
        Map<String,Object> modelMap=new HashMap<String,Object>();
        String personStr= HttpServletRequestUtil.getString(request,"personStr");
        ObjectMapper mapper=new ObjectMapper();
        PersonInfo person;
        try {
            person=mapper.readValue(personStr,PersonInfo.class);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }
        if (person!=null){
            try {
                person=personInfoService.addUser(person);
                if (person!=null){
                    modelMap.put("success",true);
                }else {
                    modelMap.put("success",false);
                    modelMap.put("errMsg","注册失败");
                }
            }catch (Exception e){
                modelMap.put("success",false);
                modelMap.put("errMsg",e.getMessage());
            }
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入用户信息");
        }
        return modelMap;
    }

    @RequestMapping(value = "/addUsers",method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> addUsers(HttpServletRequest request){
        Map<String,Object> modelMap=new HashMap<String,Object>();
        CommonsMultipartFile personFile;
        CommonsMultipartResolver commonsMultipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)){
            MultipartHttpServletRequest multipartHttpServletRequest=(MultipartHttpServletRequest)request;
            personFile=(CommonsMultipartFile)multipartHttpServletRequest.getFile("personFile");
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","上传文件不能为空");
            return modelMap;
        }
        Map<String, String> m = new HashMap<String, String>();
        m.put("学号","number");
        m.put("姓名","name");
        m.put("性别","gender");
        try {
            List<Map<String, Object>> ls= ImportExcelUtil.parseExcel(personFile.getInputStream(),personFile.getOriginalFilename(),m);
            List<PersonInfo> personInfoList = new ArrayList<>();
            for (Map map:ls){
                PersonInfo tmp = new PersonInfo();
                tmp.setNumber((String) map.get("number"));
                tmp.setPassword((String) map.get("number"));
                tmp.setName((String) map.get("name"));
                tmp.setGender((String) map.get("gender"));
                personInfoList.add(tmp);
            }
            int effectedNum=personInfoService.batchAddUsers(personInfoList);
            if (effectedNum>0){
                modelMap.put("success",true);
            }else {
                modelMap.put("success",false);
                modelMap.put("errMsg","添加失败");
            }
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
        }
        return modelMap;
    }
}
