package com.midai.miya.user.model;

import java.io.Serializable;

public class UserDay implements Serializable {

	private static final long serialVersionUID = -8440761904208160714L;
	private String days;
	private long count;
	private String queryBeginRegisterTimeStr;
	private String queryEndRegisterTimeStr;
	private Integer type;
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public String getQueryBeginRegisterTimeStr() {
		return queryBeginRegisterTimeStr;
	}
	public void setQueryBeginRegisterTimeStr(String queryBeginRegisterTimeStr) {
		this.queryBeginRegisterTimeStr = queryBeginRegisterTimeStr;
	}
	public String getQueryEndRegisterTimeStr() {
		return queryEndRegisterTimeStr;
	}
	public void setQueryEndRegisterTimeStr(String queryEndRegisterTimeStr) {
		this.queryEndRegisterTimeStr = queryEndRegisterTimeStr;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
}
