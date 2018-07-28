package com.whut.epSystem.web.announceadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.whut.epSystem.dto.AnnounceExecution;
import com.whut.epSystem.entity.Announce;
import com.whut.epSystem.enums.AnnounceStateEnum;
import com.whut.epSystem.exceptions.AnnounceOperationException;
import com.whut.epSystem.service.AnnounceService;
import com.whut.epSystem.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/announceadmin")
public class AnnounceManagementController {
    @Autowired
    private AnnounceService announceService;

    @RequestMapping(value = "/getannouncebyid",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> getAnnounceById(HttpServletRequest request){
        Map<String,Object> modelMap=new HashMap<String,Object>();
        Long announceId= HttpServletRequestUtil.getLong(request,"announceId");
        if (announceId>-1){
            try {
                Announce announce=announceService.getById(announceId);
                modelMap.put("announce",announce);
                modelMap.put("success",true);
            }catch (Exception e){
                modelMap.put("success",false);
                modelMap.put("errMsg",e.toString());
            }
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","empty announceId");
        }
        return modelMap;
    }

    @RequestMapping(value = "/getannouncelist",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> getAnnounceList(HttpServletRequest request){
        Map<String,Object> modelMap=new HashMap<String,Object>();
        int type=HttpServletRequestUtil.getInt(request,"type");
        if (type>-1){
            try {
                AnnounceExecution ae=announceService.getAnnounceList(type);
                modelMap.put("announceList",ae.getAnnounceList());
                modelMap.put("success",true);
            }catch (Exception e){
                modelMap.put("success",false);
                modelMap.put("errMsg",e.getMessage());
            }
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","empty type");
        }
        return modelMap;
    }

    @RequestMapping(value = "/addannounce",method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> addAnnounce(HttpServletRequest request){
        Map<String,Object> modelMap=new HashMap<String,Object>();
        String announceStr= HttpServletRequestUtil.getString(request,"announceStr");
        ObjectMapper mapper=new ObjectMapper();
        Announce announce;
        try {
            announce=mapper.readValue(announceStr,Announce.class);
            announce.setType(1);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }
        if (announce!=null){
            AnnounceExecution ae;
            try {
                ae=announceService.addAnnounce(announce);
                if (ae.getState()== AnnounceStateEnum.SUCCESS.getState()){
                    modelMap.put("success",true);
                }else {
                    modelMap.put("success",false);
                    modelMap.put("errMsg",ae.getStateInfo());
                }
            }catch (AnnounceOperationException e){
                modelMap.put("success",false);
                modelMap.put("errMsg",e.getMessage());
            }
            return modelMap;
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入公告信息");
            return modelMap;
        }
    }

    @RequestMapping(value = "/modifyannounce",method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> modifyAnnounce(HttpServletRequest request){
        Map<String,Object> modelMap=new HashMap<String,Object>();
        String announceStr= HttpServletRequestUtil.getString(request,"announceStr");
        ObjectMapper mapper=new ObjectMapper();
        Announce announce;
        try {
            announce=mapper.readValue(announceStr,Announce.class);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }
        if (announce!=null||announce.getAnnounceId()!=null){
            AnnounceExecution ae;
            try {
                ae=announceService.modifyAnnounce(announce);
                if (ae.getState()== AnnounceStateEnum.SUCCESS.getState()){
                    modelMap.put("success",true);
                }else {
                    modelMap.put("success",false);
                    modelMap.put("errMsg",ae.getStateInfo());
                }
            }catch (AnnounceOperationException e){
                modelMap.put("success",false);
                modelMap.put("errMsg",e.getMessage());
            }
            return modelMap;
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入id");
            return modelMap;
        }
    }

    @RequestMapping(value = "/removeannounce",method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> removeAnnounce(Long announceId){
        Map<String,Object> modelMap=new HashMap<String,Object>();
        if (announceId!=null&&announceId>0){
            try {
                AnnounceExecution ae=announceService.deleteAnnounce(announceId);
                if (ae.getState()==AnnounceStateEnum.SUCCESS.getState()){
                    modelMap.put("success",true);
                }else {
                    modelMap.put("success",false);
                    modelMap.put("errMsg",ae.getStateInfo());
                }
            }catch (AnnounceOperationException e){
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
