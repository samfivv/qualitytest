package com.midai.miya.questionnaire.model;

public class QuestionSurvey {
	/**
	 * 按天计算浏览量和ip数
	 */
	private String days;
	/**
	 * 浏览量
	 */
	private long viewCount;
	/**
	 * ip数
	 */
	private long ipCount;
	/**
	 * 填写完成率
	 */
	private String finishingRate;
	/**
	 * 平均用时
	 */
	private String avgTime;
	 
	public String getFinishingRate() {
		return finishingRate;
	}
	public void setFinishingRate(String finishingRate) {
		this.finishingRate = finishingRate;
	}
	public String getAvgTime() {
		return avgTime;
	}
	public void setAvgTime(String avgTime) {
		this.avgTime = avgTime;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public long getViewCount() {
		return viewCount;
	}
	public void setViewCount(long viewCount) {
		this.viewCount = viewCount;
	}
	public long getIpCount() {
		return ipCount;
	}
	public void setIpCount(long ipCount) {
		this.ipCount = ipCount;
	}
	
}
