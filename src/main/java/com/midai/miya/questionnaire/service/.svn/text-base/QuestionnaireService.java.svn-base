package com.midai.miya.questionnaire.service;

import com.midai.miya.questionnaire.model.QuestionSurvey;
import com.midai.miya.questionnaire.model.Questionnaire;
import com.midai.miya.questionnaire.model.QuestionnaireAnswer;
import com.midai.miya.questionnaire.model.QuestionnaireQuestion;
import com.midai.miya.questionnaire.model.QuestionnaireRecord;

import java.util.List;

import com.midai.miya.utils.PageUtil;

public interface QuestionnaireService {

     List<Questionnaire> findAll(Questionnaire Questionnaire,PageUtil page);

     long findAllCount(Questionnaire Questionnaire);

     void save(Questionnaire Questionnaire);

     void update(Questionnaire Questionnaire);

     void delete(Questionnaire Questionnaire);
     
     void saveQuestionAndOption(QuestionnaireQuestion questionnaireQuestion);
     
     List<QuestionnaireQuestion> findQuestionnaireQuestion(String questionnaireId);
     
     Questionnaire findById(String questionnaireId);
     
     List<QuestionnaireAnswer> findCountByOption(String questionnaireId);
     
     long selectCount(String questionnaireId);
     
     QuestionnaireRecord findQuestionnaireRecordById(String questionnaireId);
     
     Double selectAvg(String questionnaireId,Integer optionValue);
     
     long selectViewCount(String questionnaireId,Integer optionValue);
     
     long selectIp(String questionnaireId,Integer optionValue);//独立IP数量
     
     long selectFinishingRate(String questionnaireId,Integer optionValue);
     
     List<QuestionSurvey> findViewCountByDay(Questionnaire questionnaire);
     
     List<QuestionSurvey> findIpCountByDay(Questionnaire questionnaire);

}