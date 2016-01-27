package com.midai.miya.questionnaire.dao;

import com.midai.miya.questionnaire.model.Questionnaire;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.midai.miya.utils.PageUtil;

public interface QuestionnaireDao {

     List<Questionnaire> findAll(@Param("Questionnaire")Questionnaire Questionnaire,@Param("page")PageUtil page);

     long findAllCount(@Param("Questionnaire")Questionnaire Questionnaire);

     void save(@Param("Questionnaire")Questionnaire Questionnaire);

     void update(@Param("Questionnaire")Questionnaire Questionnaire);

     void delete(@Param("Questionnaire")Questionnaire Questionnaire);
     
     Questionnaire findById(@Param("questionnaireId")String questionnaireId);

}