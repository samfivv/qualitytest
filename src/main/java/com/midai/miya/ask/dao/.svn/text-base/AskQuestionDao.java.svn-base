package com.midai.miya.ask.dao;

import com.midai.miya.ask.model.AskQuestion;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.midai.miya.utils.PageUtil;

public interface AskQuestionDao {

     List<AskQuestion> findByConditions(@Param("askQuestion")AskQuestion askQuestion,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("askQuestion")AskQuestion askQuestion);

     void save(@Param("askQuestion")AskQuestion askQuestion);

     void update(@Param("askQuestion")AskQuestion askQuestion);

     void delete(@Param("askQuestion")AskQuestion askQuestion);
     
     AskQuestion findById(@Param("questionId")String questionId);

}