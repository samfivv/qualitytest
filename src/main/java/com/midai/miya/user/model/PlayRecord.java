package com.midai.miya.user.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayRecord implements Serializable {
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final long serialVersionUID = -438801003196262583L;
	private String playId;
	private String videoId;
	private String userId;
	private String playIp;
	private Integer playFrom;
	private String playFormStr;
	private String interestId;
	private Date playBeginTime;
	private String playBeginTimeStr;
	private String playEndTimeStr;
	private Date playEndTime;
	private String videoName;
	private String interesetTitle;
	private String userNickname;
	private String playTimeBegin;
	private String playTimeEnd;
	private String endTimeStart;
	private String endTimeEnd;
	public String getPlayId() {
		return playId;
	}
	public void setPlayId(String playId) {
		this.playId = playId;
	}
	public String getVideoId() {
		return videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPlayIp() {
		return playIp;
	}
	public void setPlayIp(String playIp) {
		this.playIp = playIp;
	}
	public String getInterestId() {
		return interestId;
	}
	public void setInterestId(String interestId) {
		this.interestId = interestId;
	}
	public Date getPlayBeginTime() {
		return playBeginTime;
	}
	public void setPlayBeginTime(Date playBeginTime) {
		this.playBeginTime = playBeginTime;
	}
	public Date getPlayEndTime() {
		return playEndTime;
	}
	public void setPlayEndTime(Date playEndTime) {
		this.playEndTime = playEndTime;
	}
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	public String getInteresetTitle() {
		return interesetTitle;
	}
	public void setInteresetTitle(String interesetTitle) {
		this.interesetTitle = interesetTitle;
	}
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	public String getPlayFormStr() {
		if(playFrom!=null){
			if(playFrom==1){
				return "网页";
			}else if(playFrom==2){
				return "安卓";
			}else {
				return "苹果";
			} 
		}else{
			return "网页";
		}
	}
	public void setPlayFormStr(String playFormStr) {
		this.playFormStr = playFormStr;
	}
	public String getPlayBeginTimeStr() {
		if(playBeginTime!=null){
			return sdf.format(playBeginTime);
		}else{
			return null;
		}
	}
	public void setPlayBeginTimeStr(String playBeginTimeStr) {
		this.playBeginTimeStr = playBeginTimeStr;
	}
	public String getPlayEndTimeStr() {
		if(playEndTime!=null){
			return sdf.format(playEndTime);
		}else{
			return null;
		}
	}
	public void setPlayEndTimeStr(String playEndTimeStr) {
		this.playEndTimeStr = playEndTimeStr;
	}
	public String getPlayTimeBegin() {
		return playTimeBegin;
	}
	public void setPlayTimeBegin(String playTimeBegin) {
		this.playTimeBegin = playTimeBegin;
	}
	public String getPlayTimeEnd() {
		return playTimeEnd;
	}
	public void setPlayTimeEnd(String playTimeEnd) {
		this.playTimeEnd = playTimeEnd;
	}
	public Integer getPlayFrom() {
		return playFrom;
	}
	public void setPlayFrom(Integer playFrom) {
		this.playFrom = playFrom;
	}
	public String getEndTimeStart() {
		 return endTimeStart;
	}
	public void setEndTimeStart(String endTimeStart) {
		this.endTimeStart = endTimeStart;
	}
	public String getEndTimeEnd() {
		return endTimeEnd;
	}
	public void setEndTimeEnd(String endTimeEnd) {
		this.endTimeEnd = endTimeEnd;
	}
	
}
