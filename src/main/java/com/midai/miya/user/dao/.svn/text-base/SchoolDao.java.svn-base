package com.midai.miya.user.dao;

import com.midai.miya.user.model.School;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.midai.miya.utils.PageUtil;

public interface SchoolDao {

     List<School> findByConditions(@Param("school")School school,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("school")School school);

     void save(@Param("school")School school);

     void update(@Param("school")School school);

     void delete(@Param("school")School school);


   	 School findSchoolByNum(@Param("schoolNum")String schoolNum);

   	 int findSchoolCountByNum(@Param("schoolNum")String schoolNum);

   	 int findSchoolCountByName(@Param("schoolName")String schoolName);


   	 int findSchoolCountByNumNomine(@Param("school")School school);

   	 int findSchoolCountByNameNomine(@Param("school")School school);
}