package com.midai.miya.sys.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Operator implements Serializable {
	private static final long serialVersionUID = 8557484431774677143L;
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String operatorId;
	private String operatorName;
	private String operatorRealName;
	private String operatorPassword;
	private String operatorState;
	private String operatorMail;
	private String operatorMobile;
	private String operatorPosition;
	private String operatorDept;
	private Date operatorCreateTime;
	private String queryTimeBegin;
	private String queryTimeEnd;
	private String operatorCreatetimeStr;
	private String operatorStateStr;
	
	
	public String getOperatorStateStr() {
		if(operatorState !=null){
			if(Integer.parseInt(operatorState) ==1){
				return "正常";
			}else{
				return "禁用";
			}
		}
		return operatorStateStr;
	}
	public void setOperatorStateStr(String operatorStateStr) {
		this.operatorStateStr = operatorStateStr;
	}
	public String getOperatorCreatetimeStr() {
		if(operatorCreateTime ==null){
			return null;
		}else{
			return sdf.format(operatorCreateTime);
		}
	}
	public void setOperatorCreatetimeStr(String operatorCreatetimeStr) {
		this.operatorCreatetimeStr = operatorCreatetimeStr;
	}
	public Date getOperatorCreateTime() {
		return operatorCreateTime;
	}
	public void setOperatorCreateTime(Date operatorCreateTime) {
		this.operatorCreateTime = operatorCreateTime;
	}
	public String getOperatorRealName() {
		return operatorRealName;
	}
	public void setOperatorRealName(String operatorRealName) {
		this.operatorRealName = operatorRealName;
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
	public String getOperatorDept() {
		return operatorDept;
	}
	public void setOperatorDept(String operatorDept) {
		this.operatorDept = operatorDept;
	}
	public String getOperatorPosition() {
		return operatorPosition;
	}
	public void setOperatorPosition(String operatorPosition) {
		this.operatorPosition = operatorPosition;
	}
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getOperatorPassword() {
		return operatorPassword;
	}
	public void setOperatorPassword(String operatorPassword) {
		this.operatorPassword = operatorPassword;
	}
	public String getOperatorState() {
		return operatorState;
	}
	public void setOperatorState(String operatorState) {
		this.operatorState = operatorState;
	}
	public String getOperatorMail() {
		return operatorMail;
	}
	public void setOperatorMail(String operatorMail) {
		this.operatorMail = operatorMail;
	}
	public String getOperatorMobile() {
		return operatorMobile;
	}
	public void setOperatorMobile(String operatorMobile) {
		this.operatorMobile = operatorMobile;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
