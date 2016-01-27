package com.midai.miya.questionnaire.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midai.miya.questionnaire.dao.QuestionnaireAnswerDao;
import com.midai.miya.questionnaire.service.QuestionnaireAnswerService;
import com.midai.miya.questionnaire.model.QuestionnaireAnswer;
import com.midai.miya.utils.PageUtil;

@Service
public class QuestionnaireAnswerServiceImpl implements QuestionnaireAnswerService {

     @Autowired
     private QuestionnaireAnswerDao questionnaireAnswerDao;

     @Override
     public List<QuestionnaireAnswer> findAll(QuestionnaireAnswer QuestionnaireAnswer,PageUtil page) {
        List<QuestionnaireAnswer> lists=questionnaireAnswerDao.findAll(QuestionnaireAnswer,page);
        return lists;
     }

     @Override
     public long findAllCount(QuestionnaireAnswer QuestionnaireAnswer) {
        long count=questionnaireAnswerDao.findAllCount(QuestionnaireAnswer);
        return count;
     }

     @Override
     public void save(QuestionnaireAnswer QuestionnaireAnswer) {
        questionnaireAnswerDao.save(QuestionnaireAnswer);
     }

     @Override
     public void update(QuestionnaireAnswer QuestionnaireAnswer) {
        questionnaireAnswerDao.update(QuestionnaireAnswer);
     }

     @Override
     public void delete(QuestionnaireAnswer QuestionnaireAnswer) {
        questionnaireAnswerDao.delete(QuestionnaireAnswer);
     }

	@Override
	public  List<QuestionnaireAnswer> findCountByOption(String questionnaireId) {
		 List<QuestionnaireAnswer> count=questionnaireAnswerDao.findCountByOption(questionnaireId);
		return count;
	}
}

