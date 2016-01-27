package com.midai.miya.questionnaire.model;

import java.util.List;

public class Question {
	private List<QuestionnaireQuestion> questionnaireQuestionList;
	private long count;
	private String newDate;
	public List<QuestionnaireQuestion> getQuestionnaireQuestionList() {
		return questionnaireQuestionList;
	}
	public void setQuestionnaireQuestionList(
			List<QuestionnaireQuestion> questionnaireQuestionList) {
		this.questionnaireQuestionList = questionnaireQuestionList;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public String getNewDate() {
		return newDate;
	}
	public void setNewDate(String newDate) {
		this.newDate = newDate;
	}
	
}
