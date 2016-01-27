package com.midai.miya.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midai.miya.sys.dao.HelpQuestionDao;
import com.midai.miya.sys.model.HelpQuestion;
import com.midai.miya.sys.service.HelpQuestionService;
import com.midai.miya.utils.PageUtil;
@Service
public class HelpQuestionServiceImpl implements HelpQuestionService {
	@Autowired
	private HelpQuestionDao helpQuestionDao;
	@Override
	public List<HelpQuestion> findAll(HelpQuestion helpQuestion,PageUtil page) {
		if(helpQuestion.getHelpQuestion()!=null){
			helpQuestion.setHelpQuestion(helpQuestion.getHelpQuestion().replace(" ", ""));
		}
		if(helpQuestion.getOperatorName()!=null){
			helpQuestion.setOperatorName(helpQuestion.getOperatorName().replace(" ", ""));
		}
		List<HelpQuestion> lists=helpQuestionDao.findAll(helpQuestion,page);
		return lists;
	}

	@Override
	public void update(HelpQuestion helpQuestion) {
		if(helpQuestion.getHelpSuggestion()!=null){
			helpQuestion.setHelpSuggestion(helpQuestion.getHelpSuggestion().replace(" ", ""));
		}
		helpQuestionDao.update(helpQuestion);
	}

	@Override
	public long findCount(HelpQuestion helpQuestion, PageUtil page) {
		long count=helpQuestionDao.findCount(helpQuestion, page);
		return count;
	}

	@Override
	public HelpQuestion findById(String helpQuestionId) {
		HelpQuestion help=helpQuestionDao.findById(helpQuestionId);
		return help;
	}

}
