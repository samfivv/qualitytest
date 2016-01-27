package com.midai.miya.user.model;

import java.io.Serializable;

public class Play implements Serializable {

	private static final long serialVersionUID = -5307435400485282658L;
	
	private long countIp;
	private long countAll;
	private String days;
	private String queryBeginTimeStr;
	private String queryEndTimeStr;
	private Integer playFrom;
	public long getCountIp() {
		return countIp;
	}
	public void setCountIp(long countIp) {
		this.countIp = countIp;
	}
	public long getCountAll() {
		return countAll;
	}
	public void setCountAll(long countAll) {
		this.countAll = countAll;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getQueryBeginTimeStr() {
		return queryBeginTimeStr;
	}
	public void setQueryBeginTimeStr(String queryBeginTimeStr) {
		this.queryBeginTimeStr = queryBeginTimeStr;
	}
	public String getQueryEndTimeStr() {
		return queryEndTimeStr;
	}
	public void setQueryEndTimeStr(String queryEndTimeStr) {
		this.queryEndTimeStr = queryEndTimeStr;
	}
	public Integer getPlayFrom() {
		return playFrom;
	}
	public void setPlayFrom(Integer playFrom) {
		this.playFrom = playFrom;
	}
	
	

}
