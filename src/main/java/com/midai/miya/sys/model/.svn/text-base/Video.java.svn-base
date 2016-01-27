package com.midai.miya.sys.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.midai.miya.filter.PropertieUtil;

public class Video implements Serializable {

	private static final long serialVersionUID = 3579786606930853713L;
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private String videoId;
	private String interestId;
	private String videoName;
	private String videoDesc;
	private String videoCreator;
	private Date videoCreatetime;
	private Date videoAudittime;
	private Integer videoState;
	private String polyVid;
	private String queryTimeBegin;
	private String queryTimeEnd;
	private String videoStateStr;
	private String notPassReason;
	private String notPassReasonState;
	private String videoCreatetimeStr;
	private String videoAudittimeStr;
	private String userName;
	private Integer videoLock;
	private String videoLockUserName;
	private String videoDuration;
	
	
	public String getVideoId() {
		return videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	public String getVideoDesc() {
		return videoDesc;
	}
	public void setVideoDesc(String videoDesc) {
		this.videoDesc = videoDesc;
	}
	public String getVideoCreator() {
		return videoCreator;
	}
	public void setVideoCreator(String videoCreator) {
		this.videoCreator = videoCreator;
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
	public String getPolyVid() {
		return polyVid;
	}
	public void setPolyVid(String polyVid) {
		this.polyVid = polyVid;
	}
	public String getVideoStateStr() {
		if(videoState!=null){
			if(videoState==0){
				return "未审核";
			}else if(videoState==1||videoState==3){
				return "审核通过";
			}else{
				return "审核不通过";
			}
		}else{
			return "审核不通过";
		}
	}
	public void setVideoStateStr(String videoStateStr) {
		this.videoStateStr = videoStateStr;
	}
	public Date getVideoCreatetime() {
		return videoCreatetime;
	}
	public void setVideoCreatetime(Date videoCreatetime) {
		this.videoCreatetime = videoCreatetime;
	}
	public Date getVideoAudittime() {
		return videoAudittime;
	}
	public void setVideoAudittime(Date videoAudittime) {
		this.videoAudittime = videoAudittime;
	}
	public Integer getVideoState() {
		return videoState;
	}
	public void setVideoState(Integer videoState) {
		this.videoState = videoState;
	}
	public String getNotPassReason() {
		if(notPassReasonState!=null){
			if(PropertieUtil.unPassReasonMap.get(notPassReasonState)!=null&&!"zzz".equals(notPassReasonState)){
				return PropertieUtil.unPassReasonMap.get(notPassReasonState);
			}else{
				return notPassReason;
			}
		}else{
			return notPassReason;
		}
	}
	public void setNotPassReason(String notPassReason) {
		this.notPassReason = notPassReason;
	}
	public String getVideoCreatetimeStr() {
		if(videoCreatetime==null){
			return null;
		}else{
		return sdf.format(videoCreatetime);
		}
	}
	public void setVideoCreatetimeStr(String videoCreatetimeStr) {
		this.videoCreatetimeStr = videoCreatetimeStr;
	}
	public String getVideoAudittimeStr() {
		if(videoAudittime==null){
			return null;
		}else{
		return sdf.format(videoAudittime);
		}
	}
	public void setVideoAudittimeStr(String videoAudittimeStr) {
		this.videoAudittimeStr = videoAudittimeStr;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNotPassReasonState() {
		return notPassReasonState;
	}
	public void setNotPassReasonState(String notPassReasonState) {
		this.notPassReasonState = notPassReasonState;
	}
	public Integer getVideoLock() {
		return videoLock;
	}
	public void setVideoLock(Integer videoLock) {
		this.videoLock = videoLock;
	}
	public String getVideoLockUserName() {
		return videoLockUserName;
	}
	public void setVideoLockUserName(String videoLockUserName) {
		this.videoLockUserName = videoLockUserName;
	}
	public String getInterestId() {
		return interestId;
	}
	public void setInterestId(String interestId) {
		this.interestId = interestId;
	}
	public String getVideoDuration() {
		return videoDuration;
	}
	public void setVideoDuration(String videoDuration) {
		this.videoDuration = videoDuration;
	}
	
}
