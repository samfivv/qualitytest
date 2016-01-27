package com.midai.miya.questionnaire.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Questionnaire implements Serializable {
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    private static final long serialVersionUID = 1L;
    /**
     * 调查问卷ID
     */
    private String questionnaireId;
    /**
     * 调查问卷标题
     */
    private String questionnaireTitle;
    /**
     * 调查问卷类型 默认1
     */
    private Integer questionnaireType;
    
    private String questionnaireTypeStr;
    /**
     * 调查问卷描述
     */
    private String questionnaireDesc;
    /**
     * 创建时间
     */
    private Date questionnaireCreateTime;
    /**
     * 终止时间
     */
    private Date questionnaireEndTime;
    private String questionnaireEndTimeStr;
    /**
     * 状态 1 正常 2删除  默认1
     */
    private Integer questionnaireState;
    
    private String queryTimeBegin;
    private String queryTimeEnd;
    public String getQuestionnaireId(){
        return questionnaireId;
    }
    public void setQuestionnaireId(String questionnaireId){
        this.questionnaireId=questionnaireId;
    }
    public String getQuestionnaireTitle(){
        return questionnaireTitle;
    }
    public void setQuestionnaireTitle(String questionnaireTitle){
        this.questionnaireTitle=questionnaireTitle;
    }
    public Integer getQuestionnaireType(){
        return questionnaireType;
    }
    public void setQuestionnaireType(Integer questionnaireType){
        this.questionnaireType=questionnaireType;
    }
    public String getQuestionnaireDesc(){
        return questionnaireDesc;
    }
    public void setQuestionnaireDesc(String questionnaireDesc){
        this.questionnaireDesc=questionnaireDesc;
    }
    public Date getQuestionnaireCreateTime(){
        return questionnaireCreateTime;
    }
    public void setQuestionnaireCreateTime(Date questionnaireCreateTime){
        this.questionnaireCreateTime=questionnaireCreateTime;
    }
    public Date getQuestionnaireEndTime(){
        return questionnaireEndTime;
    }
    public void setQuestionnaireEndTime(Date questionnaireEndTime){
        this.questionnaireEndTime=questionnaireEndTime;
    }
    public Integer getQuestionnaireState(){
        return questionnaireState;
    }
    public void setQuestionnaireState(Integer questionnaireState){
        this.questionnaireState=questionnaireState;
    }
	public String getQuestionnaireTypeStr() {
		if(questionnaireType==1){
			questionnaireTypeStr="深度调查";
		}else if(questionnaireType==2){
			questionnaireTypeStr="社会生活";
		}else if(questionnaireType==3){
			questionnaireTypeStr="文体娱乐";
		}else if(questionnaireType==4){
			questionnaireTypeStr="科技财经";
		}else if(questionnaireType==5){
			questionnaireTypeStr="情感话题";
		}else if(questionnaireType==6){
			questionnaireTypeStr="千奇百怪";
		}
		return questionnaireTypeStr;
	}
	public void setQuestionnaireTypeStr(String questionnaireTypeStr) {
		this.questionnaireTypeStr = questionnaireTypeStr;
	}
	public String getQueryTimeBegin() {
		return queryTimeBegin;
	}
	public void setQueryTimeBegin(String queryTimeBegin) {
		this.queryTimeBegin = queryTimeBegin;
	}
	public String getQueryTimeEnd() {
		return queryTimeEnd;
	}
	public void setQueryTimeEnd(String queryTimeEnd) {
		this.queryTimeEnd = queryTimeEnd;
	}
	public String getQuestionnaireEndTimeStr() {
		if(questionnaireEndTime!=null){
			questionnaireEndTimeStr=sdf.format(questionnaireEndTime);
		}
		return questionnaireEndTimeStr;
	}
	public void setQuestionnaireEndTimeStr(String questionnaireEndTimeStr) {
		this.questionnaireEndTimeStr = questionnaireEndTimeStr;
	}
}