package com.midai.miya.questionnaire.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midai.miya.questionnaire.dao.QuestionnaireQuestionDao;
import com.midai.miya.questionnaire.service.QuestionnaireQuestionService;
import com.midai.miya.questionnaire.model.QuestionnaireQuestion;
import com.midai.miya.utils.PageUtil;

@Service
public class QuestionnaireQuestionServiceImpl implements QuestionnaireQuestionService {

     @Autowired
     private QuestionnaireQuestionDao questionnaireQuestionDao;

     @Override
     public List<QuestionnaireQuestion> findAll(QuestionnaireQuestion QuestionnaireQuestion,PageUtil page) {
        List<QuestionnaireQuestion> lists=questionnaireQuestionDao.findAll(QuestionnaireQuestion,page);
        return lists;
     }

     @Override
     public long findAllCount(QuestionnaireQuestion QuestionnaireQuestion) {
        long count=questionnaireQuestionDao.findAllCount(QuestionnaireQuestion);
        return count;
     }

     @Override
     public void save(QuestionnaireQuestion QuestionnaireQuestion) {
        questionnaireQuestionDao.save(QuestionnaireQuestion);
     }

     @Override
     public void update(QuestionnaireQuestion QuestionnaireQuestion) {
        questionnaireQuestionDao.update(QuestionnaireQuestion);
     }

     @Override
     public void delete(QuestionnaireQuestion QuestionnaireQuestion) {
        questionnaireQuestionDao.delete(QuestionnaireQuestion);
     }

	@Override
	public List<QuestionnaireQuestion> findQuestionnaireQuestion(
			String questionnaireId) {
		List<QuestionnaireQuestion> lists=questionnaireQuestionDao.findQuestionnaireQuestion(questionnaireId);
		return lists;
	}
}

