package com.whut.epSystem.service.impl;

import com.whut.epSystem.dao.AnnounceDao;
import com.whut.epSystem.dto.AnnounceExecution;
import com.whut.epSystem.entity.Announce;
import com.whut.epSystem.enums.AnnounceStateEnum;
import com.whut.epSystem.exceptions.AnnounceOperationException;
import com.whut.epSystem.service.AnnounceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class AnnounceServiceImpl implements AnnounceService {
    @Autowired
    private AnnounceDao announceDao;

    @Override
    public Announce getById(long announceId) {
        return announceDao.queryById(announceId);
    }

    @Override
    public AnnounceExecution getAnnounceList(int type) {
        List<Announce> announceList=announceDao.queryAnnounceList(type);
        AnnounceExecution ae=new AnnounceExecution();
        if (announceList!=null){
            ae.setAnnounceList(announceList);
        }else {
            ae.setState(AnnounceStateEnum.INNER_ERROR.getState());
        }
        return ae;
    }

    @Override
    @Transactional
    public AnnounceExecution addAnnounce(Announce announce) throws AnnounceOperationException {
        if (announce==null){
            return new AnnounceExecution(AnnounceStateEnum.NULL_ANNOUNCE);
        }
        try {
            announce.setCreateTime(new Date());
            announce.setLastEditTime(new Date());
            int effectedNum=announceDao.insertAnnounce(announce);
            if (effectedNum<=0){
                throw new AnnounceOperationException("公告创建失败");
            }
        }catch (Exception e){
            throw new AnnounceOperationException("addAnnounce error:"+e.getMessage());
        }
        return new AnnounceExecution(AnnounceStateEnum.SUCCESS,announce);
    }

    @Override
    @Transactional
    public AnnounceExecution modifyAnnounce(Announce announce) throws AnnounceOperationException {
        if (announce==null||announce.getAnnounceId()==null){
            return new AnnounceExecution(AnnounceStateEnum.NULL_ANNOUNCE);
        }
        try {
            announce.setLastEditTime(new Date());
            int effectedNum=announceDao.updateAnnounce(announce);
            if (effectedNum<=0){
                return new AnnounceExecution(AnnounceStateEnum.NULL_ANNOUNCE);
            }else {
                announce=announceDao.queryById(announce.getAnnounceId());
                return new AnnounceExecution(AnnounceStateEnum.SUCCESS,announce);
            }
        }catch (Exception e){
            throw new AnnounceOperationException("modifyAnnounce error:"+e.getMessage());
        }
    }

    @Override
    @Transactional
    public AnnounceExecution deleteAnnounce(long announceId) throws AnnounceOperationException {
        try {
            int effectedNum=announceDao.deleteAnnounce(announceId);
            if (effectedNum<=0){
                throw new AnnounceOperationException("公告删除失败");
            }else {
                return new AnnounceExecution(AnnounceStateEnum.SUCCESS);
            }
        }catch (Exception e){
            throw new AnnounceOperationException("deleteAnnounce error:"+e.getMessage());
        }
    }
}
