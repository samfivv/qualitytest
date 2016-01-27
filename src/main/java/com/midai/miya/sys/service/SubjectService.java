package com.midai.miya.sys.service;

import com.midai.miya.sys.model.Subject;

import java.util.List;

import com.midai.miya.utils.PageUtil;

public interface SubjectService {

     List<Subject> findByConditions(Subject subject,PageUtil page);

     long findByConditionsCount(Subject subject);

     void save(Subject subject);

     void update(Subject subject);

     void delete(Subject subject);
     
     Subject findById(String subjectId);


   	int findBySubjectTitle(String subjectTitle,String subjectId);
}