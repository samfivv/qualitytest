package com.midai.miya.questionnaire.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class QuestionnaireQuestion implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 调查问卷问题ID
     */
    private String questionnaireQuestionId;
    /**
     * 调查问卷问题标题
     */
    private String questionnaireQuestionTitle;
    /**
     * 调查问卷问题类型 1单选 2复选
     */
    private Integer questionnaireQuestionType;
    /**
     * 调查问卷ID
     */
    private String questionnaireId;
    /**
     * 问题选项
     */
    private List<QuestionnaireOption> questionnaireOptionList ;
    /**
     * 创建时间
     */
    private Date questionnaireQuestionCreateTime;
    
    
    
    public String getQuestionnaireQuestionId(){
        return questionnaireQuestionId;
    }
    public void setQuestionnaireQuestionId(String questionnaireQuestionId){
        this.questionnaireQuestionId=questionnaireQuestionId;
    }
    public String getQuestionnaireQuestionTitle(){
        return questionnaireQuestionTitle;
    }
    public void setQuestionnaireQuestionTitle(String questionnaireQuestionTitle){
        this.questionnaireQuestionTitle=questionnaireQuestionTitle;
    }
    public Integer getQuestionnaireQuestionType(){
        return questionnaireQuestionType;
    }
    public void setQuestionnaireQuestionType(Integer questionnaireQuestionType){
        this.questionnaireQuestionType=questionnaireQuestionType;
    }
    public String getQuestionnaireId(){
        return questionnaireId;
    }
    public void setQuestionnaireId(String questionnaireId){
        this.questionnaireId=questionnaireId;
    }
	public List<QuestionnaireOption> getQuestionnaireOptionList() {
		return questionnaireOptionList;
	}
	public void setQuestionnaireOptionList(
			List<QuestionnaireOption> questionnaireOptionList) {
		this.questionnaireOptionList = questionnaireOptionList;
	}
	public Date getQuestionnaireQuestionCreateTime() {
		return questionnaireQuestionCreateTime;
	}
	public void setQuestionnaireQuestionCreateTime(
			Date questionnaireQuestionCreateTime) {
		this.questionnaireQuestionCreateTime = questionnaireQuestionCreateTime;
	}
	
}