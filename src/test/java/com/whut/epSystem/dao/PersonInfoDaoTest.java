package com.whut.epSystem.dao;

import com.whut.epSystem.BaseTest;
import com.whut.epSystem.entity.PersonInfo;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PersonInfoDaoTest extends BaseTest {
    @Autowired
    private PersonInfoDao personInfoDao;

    @Test
    @Ignore
    public void testQueryPersonInfo(){
        List<PersonInfo> personInfoList=personInfoDao.queryPersonInfo();
        assertEquals(2,personInfoList.size());
    }

    @Test
    public void testFindUser(){
        PersonInfo user=new PersonInfo();
        user.setNumber("001");
        user.setName("a1");
        List<PersonInfo> personInfoList=personInfoDao.findUsers(user);
        PersonInfo user1=personInfoDao.findUser(user);
        assertEquals(1,personInfoList.size());
        System.out.println(user1.getName());
    }

    @Test
    public void testAddUser(){
        PersonInfo person=new PersonInfo();
        person.setName("ming");
        person.setNumber("002");
        person.setPassword("002");
        person.setGender("男");
        person.setUserType(1);
        person.setCreateTime(new Date());
        person.setLastEditTime(new Date());
        int effectedNum=personInfoDao.insertUser(person);
        assertEquals(1,effectedNum);
    }

    @Test
    public void testBatchAddUser(){
        PersonInfo person=new PersonInfo();
        person.setName("hong");
        person.setNumber("003");
        person.setPassword("003");
        person.setGender("女");
        person.setUserType(1);
        person.setCreateTime(new Date());
        person.setLastEditTime(new Date());
        PersonInfo person2=new PersonInfo();
        person2.setName("gang");
        person2.setNumber("004");
        person2.setPassword("004");
        person2.setGender("男");
        person2.setUserType(1);
        person2.setCreateTime(new Date());
        person2.setLastEditTime(new Date());
        List<PersonInfo> personList=new ArrayList<>();
        personList.add(person);
        personList.add(person2);
        int effectedNum=personInfoDao.batchAddUser(personList);
        assertEquals(2,effectedNum);
    }

    @Test
    public void testUpdatePersonInfo(){
        PersonInfo person=new PersonInfo();
        person.setUserId(3L);
        person.setName("小明");
        int effectedNum=personInfoDao.updatePersonInfo(person);
        assertEquals(1,effectedNum);
    }

    @Test
    public void testDeletePersonInfo(){
        int effectedNum=personInfoDao.deletePersonInfo(7);
        assertEquals(1,effectedNum);
    }
}
