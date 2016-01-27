package com.midai.miya.user.model;

import java.util.Date;
import java.io.Serializable;
public class UserWithdrawDeposit implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 提现申请id
     */
    private String withdrawDepositId;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 金额
     */
    private Double money;
    /**
     * 电话号码
     */
    private String phoneNum;
    /**
     * 身份证号码
     */
    private String identityCard;
    /**
     * 银行卡号
     */
    private String bankCardNum;
    /**
     * 开户行
     */
    private String bankName;
    /**
     * 支付宝号
     */
    private String alipayNum;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 申请时间
     */
    private Date createTime;
    /**
     * 用户邮箱
     */
    private String userMail;
    /**
     * 备注
     */
    private String remak;
    /**
     * 处理状态1未处理 2已处理 3驳回申请 
     */
    private Integer dealState;
    /**
     * 处理时间
     */
    private Date dealTime;
    /**
     * 处理人id
     */
    private String dealOperatorId;
    /**
     * 处理人姓名
     */
    private String operatorName;
    
    private String queryDealTimeBegin;
    
    private String queryDealTimeEnd;
    
    private String queryTimeBegin;
    
    private String queryTimeEnd;
    
    private Double userBalance;
    
    public String getWithdrawDepositId(){
        return withdrawDepositId;
    }
    public void setWithdrawDepositId(String withdrawDepositId){
        this.withdrawDepositId=withdrawDepositId;
    }
    public String getUserId(){
        return userId;
    }
    public void setUserId(String userId){
        this.userId=userId;
    }
    public String getPhoneNum(){
        return phoneNum;
    }
    public void setPhoneNum(String phoneNum){
        this.phoneNum=phoneNum;
    }
    public String getIdentityCard(){
        return identityCard;
    }
    public void setIdentityCard(String identityCard){
        this.identityCard=identityCard;
    }
    public String getBankCardNum(){
        return bankCardNum;
    }
    public void setBankCardNum(String bankCardNum){
        this.bankCardNum=bankCardNum;
    }
    public String getBankName(){
        return bankName;
    }
    public void setBankName(String bankName){
        this.bankName=bankName;
    }
    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName){
        this.userName=userName;
    }
    public Date getCreateTime(){
        return createTime;
    }
    public void setCreateTime(Date createTime){
        this.createTime=createTime;
    }
    public String getUserMail(){
        return userMail;
    }
    public void setUserMail(String userMail){
        this.userMail=userMail;
    }
    public String getRemak(){
        return remak;
    }
    public void setRemak(String remak){
        this.remak=remak;
    }
    public Integer getDealState(){
        return dealState;
    }
    public void setDealState(Integer dealState){
        this.dealState=dealState;
    }
    public Date getDealTime(){
        return dealTime;
    }
    public void setDealTime(Date dealTime){
        this.dealTime=dealTime;
    }
    public String getDealOperatorId(){
        return dealOperatorId;
    }
    public void setDealOperatorId(String dealOperatorId){
        this.dealOperatorId=dealOperatorId;
    }
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getQueryDealTimeBegin() {
		return queryDealTimeBegin;
	}
	public void setQueryDealTimeBegin(String queryDealTimeBegin) {
		this.queryDealTimeBegin = queryDealTimeBegin;
	}
	public String getQueryDealTimeEnd() {
		return queryDealTimeEnd;
	}
	public void setQueryDealTimeEnd(String queryDealTimeEnd) {
		this.queryDealTimeEnd = queryDealTimeEnd;
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
	public Double getUserBalance() {
		return userBalance;
	}
	public void setUserBalance(Double userBalance) {
		this.userBalance = userBalance;
	}
	public String getAlipayNum() {
		return alipayNum;
	}
	public void setAlipayNum(String alipayNum) {
		this.alipayNum = alipayNum;
	}
}