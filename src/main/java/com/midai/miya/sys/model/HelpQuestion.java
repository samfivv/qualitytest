package com.midai.miya.sys.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HelpQuestion implements Serializable {
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final long serialVersionUID = -8772284875458403249L;
	private String helpQuestionId;
	private String helpQuestion;
	private String helpUrl;
	private String helpContactWay;
	private String helpRegion;
	private Integer helpState;
	private String helpSuggestion;
	private String helpUserId;
	private Date helpCreateTime;
	private String helpCreateTimeStr;
	private String helpDealTimeStr;
	private Date helpDealTime;
	private String operatorName;
	private String queryTimeBegin;
	private String queryTimeEnd;
	private String handleTimeStart;
	private String handleTimeEnd;
	private String helpStateStr;
	public String getHelpQuestionId() {
		return helpQuestionId;
	}
	public void setHelpQuestionId(String helpQuestionId) {
		this.helpQuestionId = helpQuestionId;
	}
	public String getHelpQuestion() {
		return helpQuestion;
	}
	public void setHelpQuestion(String helpQuestion) {
		this.helpQuestion = helpQuestion;
	}
	public String getHelpUrl() {
		return helpUrl;
	}
	public void setHelpUrl(String helpUrl) {
		this.helpUrl = helpUrl;
	}
	public String getHelpContactWay() {
		return helpContactWay;
	}
	public void setHelpContactWay(String helpContactWay) {
		this.helpContactWay = helpContactWay;
	}
	public String getHelpRegion() {
		return helpRegion;
	}
	public void setHelpRegion(String helpRegion) {
		this.helpRegion = helpRegion;
	}
	public Integer getHelpState() {
		return helpState;
	}
	public void setHelpState(Integer helpState) {
		this.helpState = helpState;
	}
	public String getHelpSuggestion() {
		return helpSuggestion;
	}
	public void setHelpSuggestion(String helpSuggestion) {
		this.helpSuggestion = helpSuggestion;
	}
	public String getHelpUserId() {
		return helpUserId;
	}
	public void setHelpUserId(String helpUserId) {
		this.helpUserId = helpUserId;
	}
	public Date getHelpCreateTime() {
		return helpCreateTime;
	}
	public void setHelpCreateTime(Date helpCreateTime) {
		this.helpCreateTime = helpCreateTime;
	}
	public Date getHelpDealTime() {
		return helpDealTime;
	}
	public void setHelpDealTime(Date helpDealTime) {
		this.helpDealTime = helpDealTime;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
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
	public String getHandleTimeStart() {
		return handleTimeStart;
	}
	public void setHandleTimeStart(String handleTimeStart) {
		this.handleTimeStart = handleTimeStart;
	}
	public String getHandleTimeEnd() {
		return handleTimeEnd;
	}
	public void setHandleTimeEnd(String handleTimeEnd) {
		this.handleTimeEnd = handleTimeEnd;
	}
	public String getHelpStateStr() {
		if(helpState!=null){
			if(helpState==0){
				return "未处理";
			}else{
				return "已处理";
			}
		}else{
			return "未处理";
		}
	}
	public void setHelpStateStr(String helpStateStr) {
		this.helpStateStr = helpStateStr;
	}
	public String getHelpCreateTimeStr() {
		if(helpCreateTime==null){
			return null;
		}else{
		return sdf.format(helpCreateTime);
		}
	}
	public void setHelpCreateTimeStr(String helpCreateTimeStr) {
		this.helpCreateTimeStr = helpCreateTimeStr;
	}
	public String getHelpDealTimeStr() {
		if(helpDealTime==null){
			return null;
		}else{
		return sdf.format(helpDealTime);
		}
	}
	public void setHelpDealTimeStr(String helpDealTimeStr) {
		this.helpDealTimeStr = helpDealTimeStr;
	}
	

}
