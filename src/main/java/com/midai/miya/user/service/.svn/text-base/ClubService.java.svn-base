package com.midai.miya.user.service;

import com.midai.miya.user.model.Club;

import java.util.List;

import com.midai.miya.utils.PageUtil;

public interface ClubService {

     List<Club> findByConditions(Club club,PageUtil page);

     long findByConditionsCount(Club club);

     void save(Club club);

     void update(Club club);

     void delete(Club club);


   	int findClubCountByNum(String clubNum);

   	int findClubCountByName(String clubName);

   	Club findClubByNum(String clubNum);

   	int findClubCountByNumNomine(Club club);

   	int findClubCountByNameNomine(Club club);
}