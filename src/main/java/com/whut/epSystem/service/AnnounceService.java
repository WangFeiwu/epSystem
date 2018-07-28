package com.whut.epSystem.service;

import com.whut.epSystem.dto.AnnounceExecution;
import com.whut.epSystem.entity.Announce;
import com.whut.epSystem.exceptions.AnnounceOperationException;

public interface AnnounceService {
    Announce getById(long announceId);
    AnnounceExecution getAnnounceList(int type);
    AnnounceExecution addAnnounce(Announce announce) throws AnnounceOperationException;
    AnnounceExecution modifyAnnounce(Announce announce) throws AnnounceOperationException;
    AnnounceExecution deleteAnnounce(long announceId) throws AnnounceOperationException;
}
