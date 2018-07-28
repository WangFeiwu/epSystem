package com.whut.epSystem.dao;

import com.whut.epSystem.entity.PersonInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PersonInfoDao {
    List<PersonInfo> queryPersonInfo();

    PersonInfo findUser(@Param("userCondition") PersonInfo userCondition);

    List<PersonInfo> findUsers(@Param("userCondition") PersonInfo userCondition);

    int insertUser(PersonInfo person);

    int batchAddUser(List<PersonInfo> personList);

    int updatePersonInfo(PersonInfo personInfo);

    int deletePersonInfo(long userId);
}
