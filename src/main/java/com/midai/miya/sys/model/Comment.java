package com.midai.miya.sys.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Comment implements Serializable {
	private static final long serialVersionUID = 4261607255535431813L;
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String commentId;
	private String commentContent;
	private Date commentCreateTime;
	private String commentCreateTimeStr;
	private String commentUserId;
	private Integer commentState;
	private String commentStateStr;
	private String videoId;
	private String videoName;
	private String commentPid;
	private String commentUserNickname;
	private String commentToUserNickname;
	private String commentToUserId;
	private String queryTimeBegin;
	private String queryTimeEnd;
	private String interestId;
	private String interestTitle;
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getCommentUserId() {
		return commentUserId;
	}
	public void setCommentUserId(String commentUserId) {
		this.commentUserId = commentUserId;
	}
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
	public String getCommentPid() {
		return commentPid;
	}
	public void setCommentPid(String commentPid) {
		this.commentPid = commentPid;
	}
	public String getCommentUserNickname() {
		return commentUserNickname;
	}
	public void setCommentUserNickname(String commentUserNickname) {
		this.commentUserNickname = commentUserNickname;
	}
	public String getCommentToUserNickname() {
		return commentToUserNickname;
	}
	public void setCommentToUserNickname(String commentToUserNickname) {
		this.commentToUserNickname = commentToUserNickname;
	}
	public String getCommentToUserId() {
		return commentToUserId;
	}
	public void setCommentToUserId(String commentToUserId) {
		this.commentToUserId = commentToUserId;
	}
	public Date getCommentCreateTime() {
		return commentCreateTime;
	}
	public void setCommentCreateTime(Date commentCreateTime) {
		this.commentCreateTime = commentCreateTime;
	}
	public String getCommentCreateTimeStr() {
		if(commentCreateTime==null){
			return null;
		}else{
			return sdf.format(commentCreateTime);
		}
	}
	public void setCommentCreateTimeStr(String commentCreateTimeStr) {
		this.commentCreateTimeStr = commentCreateTimeStr;
	}
	public String getCommentStateStr() {
		if(commentState!=null){
			if(commentState==1){
				return "通过";
			}else if(commentState==2){
				return "违规评论，不显示";
			}
		}else{
			return "通过";
		}
		return commentStateStr;
	}
	public void setCommentStateStr(String commentStateStr) {
		this.commentStateStr = commentStateStr;
	}
	public Integer getCommentState() {
		return commentState;
	}
	public void setCommentState(Integer commentState) {
		this.commentState = commentState;
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
	public String getInterestId() {
		return interestId;
	}
	public void setInterestId(String interestId) {
		this.interestId = interestId;
	}
	public String getInterestTitle() {
		return interestTitle;
	}
	public void setInterestTitle(String interestTitle) {
		this.interestTitle = interestTitle;
	}
	
	
}
