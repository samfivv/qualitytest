package com.midai.miya.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midai.miya.sys.dao.SubjectDao;
import com.midai.miya.sys.service.SubjectService;
import com.midai.miya.sys.model.Subject;
import com.midai.miya.utils.PageUtil;

@Service
public class SubjectServiceImpl implements SubjectService {

     @Autowired
     private SubjectDao subjectDao;

     @Override
     public List<Subject> findByConditions(Subject subject,PageUtil page) {
    	 if(subject.getSubjectTitle()!=null){
    		 subject.setSubjectTitle(subject.getSubjectTitle().replace(" ", ""));
    	 }
    	 if(subject.getSubjectText()!=null){
    		 subject.setSubjectText(subject.getSubjectText().trim());
    	 }
        List<Subject> lists=subjectDao.findByConditions(subject,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(Subject subject) {
    	 if(subject.getSubjectTitle()!=null){
    		 subject.setSubjectTitle(subject.getSubjectTitle().replace(" ", ""));
    	 }
    	 if(subject.getSubjectText()!=null){
    		 subject.setSubjectText(subject.getSubjectText().trim());
    	 }
        long count=subjectDao.findByConditionsCount(subject);
        return count;
     }

     @Override
     public void save(Subject subject) {
    	 if(subject.getSubjectTitle()!=null){
    		 subject.setSubjectTitle(subject.getSubjectTitle().trim());
    	 }
    	 if(subject.getSubjectText()!=null){
    		 subject.setSubjectText(subject.getSubjectText().trim());
    	 }
        subjectDao.save(subject);
     }

     @Override
     public void update(Subject subject) {
    	 if(subject.getSubjectTitle()!=null){
    		 subject.setSubjectTitle(subject.getSubjectTitle().trim());
    	 }
    	 if(subject.getSubjectText()!=null){
    		 subject.setSubjectText(subject.getSubjectText().trim());
    	 }
        subjectDao.update(subject);
     }

     @Override
     public void delete(Subject subject) {
        subjectDao.delete(subject);
     }

	@Override
	public Subject findById(String subjectId) {
		Subject subject=subjectDao.findById(subjectId);
		return subject;
	}

     @Override
     public int findBySubjectTitle(String subjectTitle,String subjectId){
        int Subject = subjectDao.findBySubjectTitle(subjectTitle,subjectId);
        return Subject;
     }
}