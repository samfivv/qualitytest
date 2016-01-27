package com.midai.miya.sys.dao;

import com.midai.miya.sys.model.SubjectInterest;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.midai.miya.utils.PageUtil;

public interface SubjectInterestDao {

     List<SubjectInterest> findByConditions(@Param("subjectInterest")SubjectInterest subjectInterest,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("subjectInterest")SubjectInterest subjectInterest);

     void save(@Param("subjectInterest")SubjectInterest subjectInterest);

     void update(@Param("subjectInterest")SubjectInterest subjectInterest);

     void delete(@Param("subjectInterest")SubjectInterest subjectInterest);


   	 SubjectInterest findSubjectInterestById(@Param("subjectInterestId")String subjectInterestId);

   	SubjectInterest findSubjectInterestCountById(String interestId);
}