package com.whut.epSystem.dao;

import com.whut.epSystem.BaseTest;
import com.whut.epSystem.entity.Announce;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class AnnounceDaoTest extends BaseTest {
    @Autowired
    private AnnounceDao announceDao;

    @Test
    public void testInsertAnnounce(){
        Announce announce=new Announce();
        announce.setType(1);
        announce.setTitle("公告二");
        announce.setCreateTime(new Date());
        announce.setLastEditTime(new Date());
        int effectedNum=announceDao.insertAnnounce(announce);
        System.out.println(effectedNum);
    }
    @Test
    public void testQueryById(){
        Announce announce=announceDao.queryById(1);
        System.out.println(announce.getTitle());
    }
    @Test
    public void testQueryAnnounceList(){
        List<Announce> announceList=announceDao.queryAnnounceList(1);
        System.out.println(announceList.size());
    }
    @Test
    public void testUpdateAnnounce(){
        Announce announce=new Announce();
        announce.setAnnounceId(3L);
        announce.setContent("公告二的内容");
        announce.setLastEditTime(new Date());
        int effectedNum=announceDao.updateAnnounce(announce);
        System.out.println(effectedNum);
    }
    @Test
    public void testDeleteAnnounce(){
        int effectedNum=announceDao.deleteAnnounce(3);
        System.out.println(effectedNum);
    }
}
