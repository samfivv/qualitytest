package com.midai.miya.user.model;

import java.util.Date;
import java.io.Serializable;
public class ApprovalUser implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private String approvalUserId;
    /**
     * 视频ID
     */
    private String userId;
    /**
     * 审核人id
     */
    private String operatorId;
    /**
     * 审批结果  1审核通过 2 审核不通过
     */
    private Integer approvalState;
    /**
     * 不通过的原因
     */
    private String notPassReason;
    /**
     * 审核时间
     */
    private Date approvalTime;
    
    private Date approvalCreateTime;
    
    private String userNo;
    private String userSort;
    private String userDesc;
    private String userPhone;
    private String userEmail;
    private String userAddr;
    private String userCertNo;
    private int userType;
    private Date createTime;
    
    private String approvalTimeBeginStr;
    private String approvalTimeEndStr;
    
    private String approvalCreateTimeBeginStr;
    private String approvalCreateTimeEndStr;
    

	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getUserSort() {
		return userSort;
	}
	public void setUserSort(String userSort) {
		this.userSort = userSort;
	}
	public String getUserDesc() {
		return userDesc;
	}
	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserAddr() {
		return userAddr;
	}
	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}
	public String getUserCertNo() {
		return userCertNo;
	}
	public void setUserCertNo(String userCertNo) {
		this.userCertNo = userCertNo;
	}
	public String getApprovalUserId(){
        return approvalUserId;
    }
    public void setApprovalUserId(String approvalUserId){
        this.approvalUserId=approvalUserId;
    }
    public String getUserId(){
        return userId;
    }
    public void setUserId(String userId){
        this.userId=userId;
    }
    public String getOperatorId(){
        return operatorId;
    }
    public void setOperatorId(String operatorId){
        this.operatorId=operatorId;
    }
    public Integer getApprovalState(){
        return approvalState;
    }
    public void setApprovalState(Integer approvalState){
        this.approvalState=approvalState;
    }
    public String getNotPassReason(){
        return notPassReason;
    }
    public void setNotPassReason(String notPassReason){
        this.notPassReason=notPassReason;
    }
    public Date getApprovalTime(){
        return approvalTime;
    }
    public void setApprovalTime(Date approvalTime){
        this.approvalTime=approvalTime;
    }
	public Date getApprovalCreateTime() {
		return approvalCreateTime;
	}
	public void setApprovalCreateTime(Date approvalCreateTime) {
		this.approvalCreateTime = approvalCreateTime;
	}
	public String getApprovalTimeBeginStr() {
		return approvalTimeBeginStr;
	}
	public void setApprovalTimeBeginStr(String approvalTimeBeginStr) {
		this.approvalTimeBeginStr = approvalTimeBeginStr;
	}
	public String getApprovalTimeEndStr() {
		return approvalTimeEndStr;
	}
	public void setApprovalTimeEndStr(String approvalTimeEndStr) {
		this.approvalTimeEndStr = approvalTimeEndStr;
	}
	public String getApprovalCreateTimeBeginStr() {
		return approvalCreateTimeBeginStr;
	}
	public void setApprovalCreateTimeBeginStr(String approvalCreateTimeBeginStr) {
		this.approvalCreateTimeBeginStr = approvalCreateTimeBeginStr;
	}
	public String getApprovalCreateTimeEndStr() {
		return approvalCreateTimeEndStr;
	}
	public void setApprovalCreateTimeEndStr(String approvalCreateTimeEndStr) {
		this.approvalCreateTimeEndStr = approvalCreateTimeEndStr;
	}    
}