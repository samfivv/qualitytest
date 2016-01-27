package com.midai.miya.user.dao;

import com.midai.miya.user.model.Club;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.midai.miya.utils.PageUtil;

public interface ClubDao {

     List<Club> findByConditions(@Param("club")Club club,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("club")Club club);

     void save(@Param("club")Club club);

     void update(@Param("club")Club club);

     void delete(@Param("club")Club club);


   	 int findClubCountByNum(@Param("clubNum")String clubNum);

   	 int findClubCountByName(@Param("clubName")String clubName);

   	 Club findClubByNum(@Param("clubNum")String clubNum);

   	 int findClubCountByNumNomine(@Param("club")Club club);

   	 int findClubCountByNameNomine(@Param("club")Club club);
}