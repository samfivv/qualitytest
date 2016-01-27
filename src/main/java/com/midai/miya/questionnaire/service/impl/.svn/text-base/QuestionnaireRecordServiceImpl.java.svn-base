package com.midai.miya.questionnaire.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midai.miya.questionnaire.dao.QuestionnaireRecordDao;
import com.midai.miya.questionnaire.service.QuestionnaireRecordService;
import com.midai.miya.questionnaire.model.QuestionSurvey;
import com.midai.miya.questionnaire.model.Questionnaire;
import com.midai.miya.questionnaire.model.QuestionnaireRecord;
import com.midai.miya.utils.PageUtil;

@Service
public class QuestionnaireRecordServiceImpl implements QuestionnaireRecordService {

     @Autowired
     private QuestionnaireRecordDao questionnaireRecordDao;

     @Override
     public List<QuestionnaireRecord> findAll(QuestionnaireRecord QuestionnaireRecord,PageUtil page) {
        List<QuestionnaireRecord> lists=questionnaireRecordDao.findAll(QuestionnaireRecord,page);
        return lists;
     }

     @Override
     public long findAllCount(QuestionnaireRecord QuestionnaireRecord) {
        long count=questionnaireRecordDao.findAllCount(QuestionnaireRecord);
        return count;
     }

     @Override
     public void save(QuestionnaireRecord QuestionnaireRecord) {
        questionnaireRecordDao.save(QuestionnaireRecord);
     }

     @Override
     public void update(QuestionnaireRecord QuestionnaireRecord) {
        questionnaireRecordDao.update(QuestionnaireRecord);
     }

     @Override
     public void delete(QuestionnaireRecord QuestionnaireRecord) {
        questionnaireRecordDao.delete(QuestionnaireRecord);
     }

	@Override
	public long selectCount(String questionnaireId) {
		long count=questionnaireRecordDao.selectCount(questionnaireId);
		return count;
	}

	@Override
	public Double selectAvg(String questionnaireId,Integer optionValue) {
		Double avg=questionnaireRecordDao.selectAvg(questionnaireId,optionValue);
		return avg;
	}

	@Override
	public long selectViewCount(String questionnaireId,Integer optionValue) {
		long count=questionnaireRecordDao.selectViewCount(questionnaireId,optionValue);
		return count;
	}

	@Override
	public QuestionnaireRecord findById(String questionnaireId) {
		QuestionnaireRecord questionnaireRecord=questionnaireRecordDao.findById(questionnaireId);
		return questionnaireRecord;
	}

	@Override
	public long selectIp(String questionnaireId,Integer optionValue) {
		long count=questionnaireRecordDao.selectIp(questionnaireId,optionValue);
		return count;
	}

	@Override
	public long selectFinishingRate(String questionnaireId,Integer optionValue) {
		long count=questionnaireRecordDao.selectFinishingRate(questionnaireId,optionValue);
		return count;
	}

	@Override
	public List<QuestionSurvey> findViewCountByDay(Questionnaire questionnaire) {
		List<QuestionSurvey> questionSurvey=questionnaireRecordDao.findViewCountByDay(questionnaire);
		return questionSurvey;
	}

	@Override
	public List<QuestionSurvey> findIpCountByDay(Questionnaire questionnaire) {
		List<QuestionSurvey> questionSurvey=questionnaireRecordDao.findIpCountByDay(questionnaire);
		return questionSurvey;
	}

	 
}

