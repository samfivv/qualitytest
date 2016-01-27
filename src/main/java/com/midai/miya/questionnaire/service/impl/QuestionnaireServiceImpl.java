package com.midai.miya.questionnaire.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midai.miya.questionnaire.dao.QuestionnaireDao;
import com.midai.miya.questionnaire.service.QuestionnaireAnswerService;
import com.midai.miya.questionnaire.service.QuestionnaireOptionService;
import com.midai.miya.questionnaire.service.QuestionnaireQuestionService;
import com.midai.miya.questionnaire.service.QuestionnaireRecordService;
import com.midai.miya.questionnaire.service.QuestionnaireService;
import com.midai.miya.questionnaire.model.QuestionSurvey;
import com.midai.miya.questionnaire.model.Questionnaire;
import com.midai.miya.questionnaire.model.QuestionnaireAnswer;
import com.midai.miya.questionnaire.model.QuestionnaireOption;
import com.midai.miya.questionnaire.model.QuestionnaireQuestion;
import com.midai.miya.questionnaire.model.QuestionnaireRecord;
import com.midai.miya.utils.PageUtil;
import com.midai.miya.utils.UUIDUtil;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

     @Autowired
     private QuestionnaireDao questionnaireDao;
     @Autowired
 	 private QuestionnaireQuestionService questionnaireQuestionService;
     @Autowired
     private QuestionnaireOptionService questionnaireOptionService;
     @Autowired
     private QuestionnaireAnswerService questionnaireAnswerService;
     @Autowired 
     private QuestionnaireRecordService questionnaireRecordService;


     @Override
     public List<Questionnaire> findAll(Questionnaire Questionnaire,PageUtil page) {
    	 if(Questionnaire.getQuestionnaireTitle()!=null){
    		 Questionnaire.setQuestionnaireTitle(Questionnaire.getQuestionnaireTitle().replace(" ", ""));
    	 }
        List<Questionnaire> lists=questionnaireDao.findAll(Questionnaire,page);
        return lists;
     }

     @Override
     public long findAllCount(Questionnaire Questionnaire) {
    	 if(Questionnaire.getQuestionnaireTitle()!=null){
    		 Questionnaire.setQuestionnaireTitle(Questionnaire.getQuestionnaireTitle().replace(" ", ""));
    	 }
        long count=questionnaireDao.findAllCount(Questionnaire);
        return count;
     }

     @Override
     public void save(Questionnaire Questionnaire) {
        questionnaireDao.save(Questionnaire);
     }

     @Override
     public void update(Questionnaire Questionnaire) {
        questionnaireDao.update(Questionnaire);
     }

     @Override
     public void delete(Questionnaire Questionnaire) {
        questionnaireDao.delete(Questionnaire);
     }

	@Override
	public void saveQuestionAndOption(
			QuestionnaireQuestion questionnaireQuestion) {
		questionnaireQuestion.setQuestionnaireQuestionId(UUIDUtil.getUUID());
		questionnaireQuestion.setQuestionnaireQuestionCreateTime(new Date());
		questionnaireQuestionService.save(questionnaireQuestion);
		List<QuestionnaireOption> lists=questionnaireQuestion.getQuestionnaireOptionList();
		int questionnaireQuestionSort=1;
		for(QuestionnaireOption questionnaireOption:lists){
			if(questionnaireOption.getQuestionnaireOptionDesc()!=null&&!questionnaireOption.getQuestionnaireOptionDesc().equals("")){
				questionnaireOption.setQuestionnaireOptionId(UUIDUtil.getUUID());
				questionnaireOption.setQuestionnaireQuestionSort(questionnaireQuestionSort);
				questionnaireOption.setQuestionnaireQuestionId(questionnaireQuestion.getQuestionnaireQuestionId());
				questionnaireOption.setQuestionnaireOptionDesc(questionnaireOption.getQuestionnaireOptionDesc());
				questionnaireOptionService.save(questionnaireOption);
				questionnaireQuestionSort++;
			}
		}
	}

	@Override
	public List<QuestionnaireQuestion> findQuestionnaireQuestion(
			String questionnaireId) {
		List<QuestionnaireQuestion> lists=questionnaireQuestionService.findQuestionnaireQuestion(questionnaireId);
		return lists;
	}

	@Override
	public Questionnaire findById(String questionnaireId) {
		Questionnaire questionnaire=questionnaireDao.findById(questionnaireId);	
		return questionnaire;
	}

	@Override
	public  List<QuestionnaireAnswer> findCountByOption(String questionnaireId) {
		 List<QuestionnaireAnswer> count=questionnaireAnswerService.findCountByOption(questionnaireId);
		return count;
	}
	/**
	 * 截止到目前收到几份问卷
	 */
	@Override
	public long selectCount(String questionnaireId) {
		long count=questionnaireRecordService.selectCount(questionnaireId);
		return count;
	}
	/**
	 * 填某问卷的平均时间
	 */
	@Override
	public Double selectAvg(String questionnaireId,Integer optionValue) {
		Double avg=questionnaireRecordService.selectAvg(questionnaireId,optionValue);
		return avg;
	}
	/**
	 * 某问卷的浏览量
	 */
	@Override
	public long selectViewCount(String questionnaireId,Integer optionValue) {
		long count=questionnaireRecordService.selectViewCount(questionnaireId,optionValue);
		return count;
	}
	/**
	 * 查出某问卷的起止时间
	 */
	@Override
	public QuestionnaireRecord findQuestionnaireRecordById(String questionnaireId) {
		QuestionnaireRecord questionnaireRecord=questionnaireRecordService.findById(questionnaireId);
		return questionnaireRecord;
	}
    /**
     * 独立ip数量
     */
	@Override
	public long selectIp(String questionnaireId,Integer optionValue) {
		long count=questionnaireRecordService.selectIp(questionnaireId,optionValue);
		return count;
	}
	/**
	 * 填写完成率
	 */
	@Override
	public long selectFinishingRate(String questionnaireId,Integer optionValue) {
		long count=questionnaireRecordService.selectFinishingRate(questionnaireId,optionValue);
		return count;
	}
	/**
	 * 问卷起止时间内每天的浏览量
	 */
	@Override
	public List<QuestionSurvey> findViewCountByDay(Questionnaire questionnaire) {
		List<QuestionSurvey> questionSurvey=questionnaireRecordService.findViewCountByDay(questionnaire);
		return questionSurvey;
	}
	/**
	 * 问卷起止时间内每天的ip数
	 */
	@Override
	public List<QuestionSurvey> findIpCountByDay(Questionnaire questionnaire) {
		List<QuestionSurvey> questionSurvey=questionnaireRecordService.findIpCountByDay(questionnaire);
		return questionSurvey;
	}
}

