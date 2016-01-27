package com.midai.miya.questionnaire.model;

import java.io.Serializable;
import java.util.List;
public class QuestionnaireOption implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 调查问卷选项ID
     */
    private String questionnaireOptionId;
    /**
     * 调查问卷选项描述
     */
    private String questionnaireOptionDesc;
    /**
     * 调查问卷问题ID
     */
    private String questionnaireQuestionId;
    /**
     * 排序
     */
    private Integer questionnaireQuestionSort;
   /**
    * 人数 
    */
    private Integer person;
    /**
     * 概率
     */
    private String percent;
    public String getQuestionnaireOptionId(){
        return questionnaireOptionId;
    }
    public void setQuestionnaireOptionId(String questionnaireOptionId){
        this.questionnaireOptionId=questionnaireOptionId;
    }
    public String getQuestionnaireOptionDesc(){
        return questionnaireOptionDesc;
    }
    public void setQuestionnaireOptionDesc(String questionnaireOptionDesc){
        this.questionnaireOptionDesc=questionnaireOptionDesc;
    }
    public String getQuestionnaireQuestionId(){
        return questionnaireQuestionId;
    }
    public void setQuestionnaireQuestionId(String questionnaireQuestionId){
        this.questionnaireQuestionId=questionnaireQuestionId;
    }
	public Integer getQuestionnaireQuestionSort() {
		return questionnaireQuestionSort;
	}
	public void setQuestionnaireQuestionSort(Integer questionnaireQuestionSort) {
		this.questionnaireQuestionSort = questionnaireQuestionSort;
	}
	public Integer getPerson() {
		return person;
	}
	public void setPerson(Integer person) {
		this.person = person;
	}
	public String getPercent() {
		return percent;
	}
	public void setPercent(String percent) {
		this.percent = percent;
	}
	 
	
}