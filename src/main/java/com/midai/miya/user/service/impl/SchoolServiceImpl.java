package com.midai.miya.user.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.midai.miya.user.dao.SchoolDao;
import com.midai.miya.user.service.SchoolService;
import com.midai.miya.user.model.School;
import com.midai.miya.utils.PageUtil;

@Service
public class SchoolServiceImpl implements SchoolService {

     @Autowired
     private SchoolDao schoolDao;

     @Override
     public List<School> findByConditions(School school,PageUtil page) {
    	if(school.getSchoolNum()!=null){
    		school.setSchoolNum(school.getSchoolNum().replace(" ", ""));
    	}
    	if(school.getSchoolName()!=null){
    		school.setSchoolName(school.getSchoolName().replace(" ", ""));
    	}
        List<School> lists=schoolDao.findByConditions(school,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(School school) {
    	 if(school.getSchoolNum()!=null){
     		school.setSchoolNum(school.getSchoolNum().replace(" ", ""));
     	}
     	if(school.getSchoolName()!=null){
     		school.setSchoolName(school.getSchoolName().replace(" ", ""));
     	}
        long count=schoolDao.findByConditionsCount(school);
        return count;
     }

     @Override
     public void save(School school) {
    	 if(school.getSchoolNum()!=null){
      		school.setSchoolNum(school.getSchoolNum().replace(" ", ""));
      	}
      	if(school.getSchoolName()!=null){
      		school.setSchoolName(school.getSchoolName().replace(" ", ""));
      	}
        schoolDao.save(school);
     }

     @Override
     public void update(School school) {
    	 if(school.getSchoolNum()!=null){
       		school.setSchoolNum(school.getSchoolNum().replace(" ", ""));
       	}
       	if(school.getSchoolName()!=null){
       		school.setSchoolName(school.getSchoolName().replace(" ", ""));
       	}
        schoolDao.update(school);
     }

     @Override
     public void delete(School school) {
        schoolDao.delete(school);
     }

     @Override
     public School findSchoolByNum(String schoolNum){
        School School = schoolDao.findSchoolByNum(schoolNum);
        return School;
     }

     @Override
     public int findSchoolCountByNum(String schoolNum){
        int School = schoolDao.findSchoolCountByNum(schoolNum);
        return School;
     }

     @Override
     public int findSchoolCountByName(String schoolName){
        int School = schoolDao.findSchoolCountByName(schoolName);
        return School;
     }


     @Override
     public int findSchoolCountByNumNomine(School school){
        int School = schoolDao.findSchoolCountByNumNomine(school);
        return School;
     }

     @Override
     public int findSchoolCountByNameNomine(School school){
        int School = schoolDao.findSchoolCountByNameNomine(school);
        return School;
     }
}