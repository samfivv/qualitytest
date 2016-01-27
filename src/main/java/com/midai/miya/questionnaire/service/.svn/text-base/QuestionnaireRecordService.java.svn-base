package com.midai.miya.questionnaire.service;

import com.midai.miya.questionnaire.model.QuestionSurvey;
import com.midai.miya.questionnaire.model.Questionnaire;
import com.midai.miya.questionnaire.model.QuestionnaireRecord;

import java.util.List;

import com.midai.miya.utils.PageUtil;

public interface QuestionnaireRecordService {

     List<QuestionnaireRecord> findAll(QuestionnaireRecord QuestionnaireRecord,PageUtil page);

     long findAllCount(QuestionnaireRecord QuestionnaireRecord);

     void save(QuestionnaireRecord QuestionnaireRecord);

     void update(QuestionnaireRecord QuestionnaireRecord);

     void delete(QuestionnaireRecord QuestionnaireRecord);
     
     long selectCount(String questionnaireId);
     
     QuestionnaireRecord findById(String questionnaireId);
     
     Double selectAvg(String questionnaireId,Integer optionValue);
     
     long selectViewCount(String questionnaireId,Integer optionValue);
     
     long selectIp(String questionnaireId,Integer optionValue);//独立IP数量
     
     long selectFinishingRate(String questionnaireId,Integer optionValue);
     
     List<QuestionSurvey> findViewCountByDay(Questionnaire questionnaire);
     
     List<QuestionSurvey> findIpCountByDay(Questionnaire questionnaire);

}