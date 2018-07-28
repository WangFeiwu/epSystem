package com.whut.epSystem.service;

import com.whut.epSystem.BaseTest;
import com.whut.epSystem.entity.PersonInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PersonInfoServiceTest extends BaseTest {
    @Autowired
    private PersonInfoService personInfoService;

    @Test
    public void testGetPersonInfoList(){
        List<PersonInfo> personInfoList=personInfoService.getPersonInfoList();
        assertEquals("a1",personInfoList.get(0).getName());
    }
}
