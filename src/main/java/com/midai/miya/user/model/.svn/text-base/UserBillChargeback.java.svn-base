package com.midai.miya.user.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.Serializable;
public class UserBillChargeback implements Serializable {
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    private String userBillChargebackId;
    /**
     * 约单Id
     */
    private String userBillId;
    /**
     * 用户Id
     */
    private String userId;
    /**
     * 
     */
    private Date createTime;
    /**
     * 1提交 2审核通过 3审核不通过
     */
    private Integer userBillChargebackState;
    /**
     * 不通过的原因 订单状态为不通过的时候有值
     */
    private String notPassReason;
    /**
     * 审批人Id
     */
    private String operatorId;
    /**
     * 申请人
     */
    private String fromCall;
    /**
     * 申请人手机
     */
    private String fromPhoneNumber;
    /**
     * 被约人姓名
     */
    private String userName;
    /**
     * 被约人手机
     */
    private String userPhone;
    /**
     * 订单状态
     */
    private Integer billState;
    /**
     * 审批人
     */
    private String operatorName;
    /**
     * 订单号
     */
    private String billNum;
    /**
     * 米芽财富值
     */
    private Double billPayMoney;
    /**
     * 人民币
     */
    private Double billPayMoneyRmb;
    
    private String queryTimeBegin;
	private String queryTimeEnd;
	
	private Date approvalTime;
	
	private String createTimeStr;
	
	private String approvalTimeStr;
	private String billStateStr;
	private String userBillChargebackStateStr;
    
    public String getUserBillChargebackId(){
        return userBillChargebackId;
    }
    public void setUserBillChargebackId(String userBillChargebackId){
        this.userBillChargebackId=userBillChargebackId;
    }
    public String getUserBillId(){
        return userBillId;
    }
    public void setUserBillId(String userBillId){
        this.userBillId=userBillId;
    }
    public String getUserId(){
        return userId;
    }
    public void setUserId(String userId){
        this.userId=userId;
    }
    public Date getCreateTime(){
        return createTime;
    }
    public void setCreateTime(Date createTime){
        this.createTime=createTime;
    }
    public String getNotPassReason(){
        return notPassReason;
    }
    public void setNotPassReason(String notPassReason){
        this.notPassReason=notPassReason;
    }
    public String getOperatorId(){
        return operatorId;
    }
    public void setOperatorId(String operatorId){
        this.operatorId=operatorId;
    }
	public String getFromCall() {
		return fromCall;
	}
	public void setFromCall(String fromCall) {
		this.fromCall = fromCall;
	}
	public String getFromPhoneNumber() {
		return fromPhoneNumber;
	}
	public void setFromPhoneNumber(String fromPhoneNumber) {
		this.fromPhoneNumber = fromPhoneNumber;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public Integer getBillState() {
		return billState;
	}
	public void setBillState(Integer billState) {
		this.billState = billState;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getBillNum() {
		return billNum;
	}
	public void setBillNum(String billNum) {
		this.billNum = billNum;
	}
	public Integer getUserBillChargebackState() {
		return userBillChargebackState;
	}
	public void setUserBillChargebackState(Integer userBillChargebackState) {
		this.userBillChargebackState = userBillChargebackState;
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
	public Double getBillPayMoney() {
		return billPayMoney;
	}
	public void setBillPayMoney(Double billPayMoney) {
		this.billPayMoney = billPayMoney;
	}
	public Double getBillPayMoneyRmb() {
		return billPayMoneyRmb;
	}
	public void setBillPayMoneyRmb(Double billPayMoneyRmb) {
		this.billPayMoneyRmb = billPayMoneyRmb;
	}
	public Date getApprovalTime() {
		return approvalTime;
	}
	public void setApprovalTime(Date approvalTime) {
		this.approvalTime = approvalTime;
	}
	public String getCreateTimeStr() {
		if(this.createTime!=null){
			return sdf.format(createTime);
		}else{
			return "";
		}
	}
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}
	public String getApprovalTimeStr() {
		if(this.approvalTime!=null){
			return sdf.format(approvalTime);
		}else{
			return "";
		}
	}
	public void setApprovalTimeStr(String approvalTimeStr) {
		this.approvalTimeStr = approvalTimeStr;
	}
	public String getBillStateStr() {
		String str="";
		if(this.billState!=null){
			 if(this.billState==1){
				 str="用户提交账单";
			 }else if(this.billState==2){
				 str="被约人同意见面";
			 }else if(this.billState==3){
				 str="被约人不同意见面";
			 }else if(this.billState==4){
				 str="约定人已付款到平台";
			 }else if(this.billState==5){
				 str="确定付款到被约人";
			 }else if(this.billState==6){
				 str="退款";
			 }else if(this.billState==7){
				 str="订单取消";
			 }
		} 
		return str;
	}
	public void setBillStateStr(String billStateStr) {
		this.billStateStr = billStateStr;
	}
	public String getUserBillChargebackStateStr() {
		String str="";
		if(this.userBillChargebackState!=null){
			if(this.userBillChargebackState==1){
				str="未审核";
			}else if(this.userBillChargebackState==2){
				str="审核通过";
			}else if(this.userBillChargebackState==3){
				str="审核不通过";
			}
		}
		return str;
	}
	public void setUserBillChargebackStateStr(String userBillChargebackStateStr) {
		this.userBillChargebackStateStr = userBillChargebackStateStr;
	}
}