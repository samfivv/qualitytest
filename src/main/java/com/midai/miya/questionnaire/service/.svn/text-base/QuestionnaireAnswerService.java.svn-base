package com.midai.miya.questionnaire.service;

import com.midai.miya.questionnaire.model.QuestionnaireAnswer;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.midai.miya.utils.PageUtil;

public interface QuestionnaireAnswerService {

     List<QuestionnaireAnswer> findAll(QuestionnaireAnswer QuestionnaireAnswer,PageUtil page);

     long findAllCount(QuestionnaireAnswer QuestionnaireAnswer);

     void save(QuestionnaireAnswer QuestionnaireAnswer);

     void update(QuestionnaireAnswer QuestionnaireAnswer);

     void delete(QuestionnaireAnswer QuestionnaireAnswer);
     
     List<QuestionnaireAnswer> findCountByOption(String questionnaireId);

}