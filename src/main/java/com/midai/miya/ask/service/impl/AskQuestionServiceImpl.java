package com.midai.miya.ask.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midai.miya.ask.dao.AskQuestionDao;
import com.midai.miya.ask.service.AskQuestionService;
import com.midai.miya.ask.model.AskQuestion;
import com.midai.miya.utils.PageUtil;

@Service
public class AskQuestionServiceImpl implements AskQuestionService {

     @Autowired
     private AskQuestionDao askQuestionDao;

     @Override
     public List<AskQuestion> findByConditions(AskQuestion askQuestion,PageUtil page) {
    	 if(askQuestion.getQuestionTitle()!=null){
    		 askQuestion.setQuestionTitle(askQuestion.getQuestionTitle().replace(" ", ""));
    	 }
    	 if(askQuestion.getQuestionDesc()!=null){
    		 askQuestion.setQuestionDesc(askQuestion.getQuestionDesc().replace(" ", ""));
    	 }
    	 if(askQuestion.getQuestionTag()!=null){
    		 askQuestion.setQuestionTag(askQuestion.getQuestionTag().replace(" ", ""));
    	 }
        List<AskQuestion> lists=askQuestionDao.findByConditions(askQuestion,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(AskQuestion askQuestion) {
    	 if(askQuestion.getQuestionTitle()!=null){
    		 askQuestion.setQuestionTitle(askQuestion.getQuestionTitle().replace(" ", ""));
    	 }
    	 if(askQuestion.getQuestionDesc()!=null){
    		 askQuestion.setQuestionDesc(askQuestion.getQuestionDesc().replace(" ", ""));
    	 }
    	 if(askQuestion.getQuestionTag()!=null){
    		 askQuestion.setQuestionTag(askQuestion.getQuestionTag().replace(" ", ""));
    	 }
        long count=askQuestionDao.findByConditionsCount(askQuestion);
        return count;
     }

     @Override
     public void save(AskQuestion askQuestion) {
    	 if(askQuestion.getQuestionTitle()!=null){
    		 askQuestion.setQuestionTitle(askQuestion.getQuestionTitle().replace(" ", ""));
    	 }
    	 if(askQuestion.getQuestionDesc()!=null){
    		 askQuestion.setQuestionDesc(askQuestion.getQuestionDesc().replace(" ", ""));
    	 }
    	 if(askQuestion.getQuestionTag()!=null){
    		 askQuestion.setQuestionTag(askQuestion.getQuestionTag().replace(" ", ""));
    	 }
        askQuestionDao.save(askQuestion);
     }

     @Override
     public void update(AskQuestion askQuestion) {
    	 if(askQuestion.getQuestionTitle()!=null){
    		 askQuestion.setQuestionTitle(askQuestion.getQuestionTitle().replace(" ", ""));
    	 }
    	 if(askQuestion.getQuestionDesc()!=null){
    		 askQuestion.setQuestionDesc(askQuestion.getQuestionDesc().replace(" ", ""));
    	 }
    	 if(askQuestion.getQuestionTag()!=null){
    		 askQuestion.setQuestionTag(askQuestion.getQuestionTag().replace(" ", ""));
    	 }
        askQuestionDao.update(askQuestion);
     }

     @Override
     public void delete(AskQuestion askQuestion) {
        askQuestionDao.delete(askQuestion);
     }

	@Override
	public AskQuestion findById(String questionId) {
		AskQuestion askQuestion=askQuestionDao.findById(questionId);
		return askQuestion;
	}
}

