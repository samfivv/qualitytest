package com.midai.miya.user.service;

import com.midai.miya.user.model.School;
import java.util.List;
import com.midai.miya.utils.PageUtil;

public interface SchoolService {

     List<School> findByConditions(School school,PageUtil page);

     long findByConditionsCount(School school);

     void save(School school);

     void update(School school);

     void delete(School school);


   	School findSchoolByNum(String schoolNum);

   	int findSchoolCountByNum(String schoolNum);

   	int findSchoolCountByName(String schoolName);


   	int findSchoolCountByNumNomine(School school);

   	int findSchoolCountByNameNomine(School school);
}