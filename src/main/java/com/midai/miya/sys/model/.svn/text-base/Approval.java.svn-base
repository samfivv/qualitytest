package com.midai.miya.sys.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.midai.miya.filter.PropertieUtil;

public class Approval implements Serializable {
	private static final long serialVersionUID = -8350147896221825498L;
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private String approvalId;
	private String videoId;
	private String operatorId;
	private Integer approvalState;
	private String notPassReasonState;
	private String notPassReason;
	private Date approvalTime;
	private String queryTimeBegin;
	private String queryTimeEnd;
	private String videoName;
	private String videoDesc;
	private Integer videoState;
	private String operatorName;
	private String approvalStateStr;
	private String approvalTimeStr;
	public String getApprovalId() {
		return approvalId;
	}
	public void setApprovalId(String approvalId) {
		this.approvalId = approvalId;
	}
	 
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public String getNotPassReason() {
		if(notPassReasonState!=null){
			String value=PropertieUtil.unPassReasonMap.get(notPassReasonState);
			if(value!=null&&"zzz".equals(notPassReasonState)){
				return notPassReason;
			}else{
				return value;
			}
		}else{
			return notPassReason;
		}
	}
	public void setNotPassReason(String notPassReason) {
		this.notPassReason = notPassReason;
	}
	public Date getApprovalTime() {
		return approvalTime;
	}
	public void setApprovalTime(Date approvalTime) {
		this.approvalTime = approvalTime;
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
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public Integer getApprovalState() {
		return approvalState;
	}
	public void setApprovalState(Integer approvalState) {
		this.approvalState = approvalState;
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
	public String getVideoDesc() {
		return videoDesc;
	}
	public void setVideoDesc(String videoDesc) {
		this.videoDesc = videoDesc;
	}
	public String getApprovalStateStr() {
		if(approvalState!=null){
			if(approvalState==1||approvalState==3){
				return "审核通过";
			}else{
				return "审核不通过";
			}
		}else{
			return "审核不通过";
		}
	}
	public void setApprovalStateStr(String approvalStateStr) {
		this.approvalStateStr = approvalStateStr;
	}
	public String getApprovalTimeStr() {
		if(approvalTime==null){
			return null;
		}else{
			return sdf.format(approvalTime);
		}
		
	}
	public void setApprovalTimeStr(String approvalTimeStr) {
		this.approvalTimeStr = approvalTimeStr;
	}
	public Integer getVideoState() {
		return videoState;
	}
	public void setVideoState(Integer videoState) {
		this.videoState = videoState;
	}
	public String getNotPassReasonState() {
		return notPassReasonState;
	}
	public void setNotPassReasonState(String notPassReasonState) {
		this.notPassReasonState = notPassReasonState;
	}
	
	

}
