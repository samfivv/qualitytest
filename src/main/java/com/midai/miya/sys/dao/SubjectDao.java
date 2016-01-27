package com.midai.miya.sys.dao;

import com.midai.miya.sys.model.Subject;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.midai.miya.utils.PageUtil;

public interface SubjectDao {

     List<Subject> findByConditions(@Param("subject")Subject subject,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("subject")Subject subject);

     void save(@Param("subject")Subject subject);

     void update(@Param("subject")Subject subject);

     void delete(@Param("subject")Subject subject);
     
     Subject findById(@Param("subjectId")String subjectId);


   	 int findBySubjectTitle(@Param("subjectTitle")String subjectTitle,@Param("subjectId")String subjectId);
}