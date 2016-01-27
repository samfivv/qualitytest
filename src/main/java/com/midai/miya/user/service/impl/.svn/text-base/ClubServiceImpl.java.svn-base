package com.midai.miya.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midai.miya.user.dao.ClubDao;
import com.midai.miya.user.service.ClubService;
import com.midai.miya.user.model.Club;
import com.midai.miya.utils.PageUtil;

@Service
public class ClubServiceImpl implements ClubService {

     @Autowired
     private ClubDao clubDao;

     @Override
     public List<Club> findByConditions(Club club,PageUtil page) {
    	 if(club.getClubNum()!=null){
    		 club.setClubNum(club.getClubNum().replace(" ", ""));
    	 }
    	 if(club.getClubName()!=null){
    		 club.setClubName(club.getClubName().replace(" ", ""));
    	 }
    	 if(club.getSchoolNum()!=null){
    		 club.setSchoolNum(club.getSchoolNum().replace(" ", ""));
    	 }
        List<Club> lists=clubDao.findByConditions(club,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(Club club) {
    	 if(club.getClubNum()!=null){
    		 club.setClubNum(club.getClubNum().replace(" ", ""));
    	 }
    	 if(club.getClubName()!=null){
    		 club.setClubName(club.getClubName().replace(" ", ""));
    	 }
    	 if(club.getSchoolNum()!=null){
    		 club.setSchoolNum(club.getSchoolNum().replace(" ", ""));
    	 }
        long count=clubDao.findByConditionsCount(club);
        return count;
     }

     @Override
     public void save(Club club) {
        clubDao.save(club);
     }

     @Override
     public void update(Club club) {
        clubDao.update(club);
     }

     @Override
     public void delete(Club club) {
        clubDao.delete(club);
     }

     @Override
     public int findClubCountByNum(String clubNum){
        int Club = clubDao.findClubCountByNum(clubNum);
        return Club;
     }

     @Override
     public int findClubCountByName(String clubName){
        int Club = clubDao.findClubCountByName(clubName);
        return Club;
     }

     @Override
     public Club findClubByNum(String clubNum){
        Club Club = clubDao.findClubByNum(clubNum);
        return Club;
     }

     @Override
     public int findClubCountByNumNomine(Club club){
        int Club = clubDao.findClubCountByNumNomine(club);
        return Club;
     }

     @Override
     public int findClubCountByNameNomine(Club club){
        int Club = clubDao.findClubCountByNameNomine(club);
        return Club;
     }
}