package com.whut.epSystem.service.impl;

import com.whut.epSystem.dao.PersonInfoDao;
import com.whut.epSystem.entity.PersonInfo;
import com.whut.epSystem.service.PersonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class PersonInfoServiceImpl implements PersonInfoService {
    @Autowired
    private PersonInfoDao personInfoDao;

    @Override
    public List<PersonInfo> getPersonInfoList() {
        return personInfoDao.queryPersonInfo();
    }

    @Override
    public PersonInfo checkLogin(PersonInfo person) {
        PersonInfo user=personInfoDao.findUser(person);
        if (user!=null&&user.getPassword().equals(person.getPassword())){
            return user;
        }
        return null;
    }

    @Override
    @Transactional
    public PersonInfo addUser(PersonInfo person) {
        if (person!=null&&person.getNumber()!=null){
            person.setCreateTime(new Date());
            person.setLastEditTime(new Date());
            int effectedNum=personInfoDao.insertUser(person);
            if (effectedNum>0){
                PersonInfo user=personInfoDao.findUser(person);
                return user;
            }
        }
        return null;
    }

    @Override
    public int batchAddUsers(List<PersonInfo> personList) {
        if (personList!=null&&personList.size()>0){
            for (PersonInfo p:personList){
                p.setUserType(1);
                p.setCreateTime(new Date());
                p.setLastEditTime(new Date());
            }
            int effectedNum=personInfoDao.batchAddUser(personList);
            if (effectedNum>0){
                return 1;
            }
        }
        return 0;
    }

    @Override
    @Transactional
    public PersonInfo modifyPersonInfo(PersonInfo person) {
        if (person!=null&&person.getUserId()!=null){
            person.setLastEditTime(new Date());
            int effectedNum=personInfoDao.updatePersonInfo(person);
            if (effectedNum>0){
                person=personInfoDao.findUser(person);
                return person;
            }
        }
        return null;
    }

    @Override
    public int removePersonInfo(long userId) {
        int effecctedNum=personInfoDao.deletePersonInfo(userId);
        if (effecctedNum>0){
            return 1;
        }
        return 0;
    }
}
