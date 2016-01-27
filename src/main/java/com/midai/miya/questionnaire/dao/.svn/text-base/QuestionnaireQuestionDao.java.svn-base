package com.midai.miya.questionnaire.dao;

import com.midai.miya.questionnaire.model.QuestionnaireQuestion;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.midai.miya.utils.PageUtil;

public interface QuestionnaireQuestionDao {

     List<QuestionnaireQuestion> findAll(@Param("QuestionnaireQuestion")QuestionnaireQuestion QuestionnaireQuestion,@Param("page")PageUtil page);

     long findAllCount(@Param("QuestionnaireQuestion")QuestionnaireQuestion QuestionnaireQuestion);

     void save(@Param("QuestionnaireQuestion")QuestionnaireQuestion QuestionnaireQuestion);

     void update(@Param("QuestionnaireQuestion")QuestionnaireQuestion QuestionnaireQuestion);

     void delete(@Param("QuestionnaireQuestion")QuestionnaireQuestion QuestionnaireQuestion);
     
     List<QuestionnaireQuestion> findQuestionnaireQuestion(@Param("questionnaireId")String questionnaireId);

}