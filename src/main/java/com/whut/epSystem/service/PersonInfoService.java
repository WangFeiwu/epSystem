package com.whut.epSystem.service;

import com.whut.epSystem.entity.PersonInfo;

import java.util.List;

public interface PersonInfoService {
    List<PersonInfo> getPersonInfoList();

    PersonInfo checkLogin(PersonInfo person);

    PersonInfo addUser(PersonInfo person);

    int batchAddUsers(List<PersonInfo> personList);

    PersonInfo modifyPersonInfo(PersonInfo person);

    int removePersonInfo(long userId);
}
