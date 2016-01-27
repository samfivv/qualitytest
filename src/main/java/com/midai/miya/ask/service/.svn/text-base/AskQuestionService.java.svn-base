package com.midai.miya.ask.service;

import com.midai.miya.ask.model.AskQuestion;

import java.util.List;

import com.midai.miya.utils.PageUtil;

public interface AskQuestionService {

     List<AskQuestion> findByConditions(AskQuestion askQuestion,PageUtil page);

     long findByConditionsCount(AskQuestion askQuestion);

     void save(AskQuestion askQuestion);

     void update(AskQuestion askQuestion);

     void delete(AskQuestion askQuestion);
     
     AskQuestion findById(String questionId);

}