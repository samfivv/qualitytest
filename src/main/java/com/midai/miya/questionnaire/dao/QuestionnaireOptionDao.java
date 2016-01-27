package com.midai.miya.questionnaire.dao;

import com.midai.miya.questionnaire.model.QuestionnaireOption;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.midai.miya.utils.PageUtil;

public interface QuestionnaireOptionDao {

     List<QuestionnaireOption> findAll(@Param("QuestionnaireOption")QuestionnaireOption QuestionnaireOption,@Param("page")PageUtil page);

     long findAllCount(@Param("QuestionnaireOption")QuestionnaireOption QuestionnaireOption);

     void save(@Param("QuestionnaireOption")QuestionnaireOption QuestionnaireOption);

     void update(@Param("QuestionnaireOption")QuestionnaireOption QuestionnaireOption);

     void delete(@Param("QuestionnaireOption")QuestionnaireOption QuestionnaireOption);

}