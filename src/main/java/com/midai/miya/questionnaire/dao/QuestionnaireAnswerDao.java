package com.midai.miya.questionnaire.dao;

import com.midai.miya.questionnaire.model.QuestionnaireAnswer;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.midai.miya.utils.PageUtil;

public interface QuestionnaireAnswerDao {

     List<QuestionnaireAnswer> findAll(@Param("QuestionnaireAnswer")QuestionnaireAnswer QuestionnaireAnswer,@Param("page")PageUtil page);

     long findAllCount(@Param("QuestionnaireAnswer")QuestionnaireAnswer QuestionnaireAnswer);

     void save(@Param("QuestionnaireAnswer")QuestionnaireAnswer QuestionnaireAnswer);

     void update(@Param("QuestionnaireAnswer")QuestionnaireAnswer QuestionnaireAnswer);

     void delete(@Param("QuestionnaireAnswer")QuestionnaireAnswer QuestionnaireAnswer);
     
     List<QuestionnaireAnswer> findCountByOption(@Param("questionnaireId")String questionnaireId);

}