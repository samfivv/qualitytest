package com.midai.miya.sys.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.midai.miya.sys.dao.SubjectInterestDao;
import com.midai.miya.sys.service.SubjectInterestService;
import com.midai.miya.sys.model.SubjectInterest;
import com.midai.miya.utils.PageUtil;

@Service
public class SubjectInterestServiceImpl implements SubjectInterestService {

     @Autowired
     private SubjectInterestDao subjectInterestDao;

     @Override
     public List<SubjectInterest> findByConditions(SubjectInterest subjectInterest,PageUtil page) {
        List<SubjectInterest> lists=subjectInterestDao.findByConditions(subjectInterest,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(SubjectInterest subjectInterest) {
        long count=subjectInterestDao.findByConditionsCount(subjectInterest);
        return count;
     }

     @Override
     public void save(SubjectInterest subjectInterest) {
        subjectInterestDao.save(subjectInterest);
     }

     @Override
     public void update(SubjectInterest subjectInterest) {
        subjectInterestDao.update(subjectInterest);
     }

     @Override
     public void delete(SubjectInterest subjectInterest) {
        subjectInterestDao.delete(subjectInterest);
     }

     @Override
     public SubjectInterest findSubjectInterestById(String subjectInterestId){
        SubjectInterest SubjectInterest = subjectInterestDao.findSubjectInterestById(subjectInterestId);
        return SubjectInterest;
     }

     @Override
     public SubjectInterest findSubjectInterestCountById(String interestId){
    	 SubjectInterest SubjectInterest = subjectInterestDao.findSubjectInterestCountById(interestId);
        return SubjectInterest;
     }
}