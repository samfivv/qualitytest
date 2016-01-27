package com.midai.miya.questionnaire.dao;

import com.midai.miya.questionnaire.model.QuestionSurvey;
import com.midai.miya.questionnaire.model.Questionnaire;
import com.midai.miya.questionnaire.model.QuestionnaireRecord;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.midai.miya.utils.PageUtil;

public interface QuestionnaireRecordDao {

     List<QuestionnaireRecord> findAll(@Param("QuestionnaireRecord")QuestionnaireRecord QuestionnaireRecord,@Param("page")PageUtil page);

     long findAllCount(@Param("QuestionnaireRecord")QuestionnaireRecord QuestionnaireRecord);

     void save(@Param("QuestionnaireRecord")QuestionnaireRecord QuestionnaireRecord);

     void update(@Param("QuestionnaireRecord")QuestionnaireRecord QuestionnaireRecord);

     void delete(@Param("QuestionnaireRecord")QuestionnaireRecord QuestionnaireRecord);
     
     long selectCount(@Param("questionnaireId")String questionnaireId);//截止到当前时间收到几份问卷
     
     QuestionnaireRecord findById(@Param("questionnaireId")String questionnaireId);//根据id查找该问卷信息
     
     Double selectAvg(@Param("questionnaireId")String questionnaireId,@Param("optionValue")Integer optionValue);//平均用时
     
     long selectViewCount(@Param("questionnaireId")String questionnaireId,@Param("optionValue")Integer optionValue);//浏览量
     
     long selectIp(@Param("questionnaireId")String questionnaireId,@Param("optionValue")Integer optionValue);//独立IP数量
     
     long selectFinishingRate(@Param("questionnaireId")String questionnaireId,@Param("optionValue")Integer optionValue);//填写完成率
     
     List<QuestionSurvey> findViewCountByDay(@Param("questionnaire")Questionnaire questionnaire);
     
     List<QuestionSurvey> findIpCountByDay(@Param("questionnaire")Questionnaire questionnaire);

}