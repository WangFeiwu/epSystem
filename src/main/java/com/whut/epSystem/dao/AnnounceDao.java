package com.whut.epSystem.dao;

import com.whut.epSystem.entity.Announce;

import java.util.List;

public interface AnnounceDao {
    Announce queryById(long announceId);
    List<Announce> queryAnnounceList(int type);
    int insertAnnounce(Announce announce);
    int updateAnnounce(Announce announce);
    int deleteAnnounce(long announceId);
}
