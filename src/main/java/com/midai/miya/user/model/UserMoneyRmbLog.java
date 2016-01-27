package com.midai.miya.user.model;

import java.util.Date;
import java.io.Serializable;
public class UserMoneyRmbLog implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    private String userMoneyRmbLog;
    /**
     * 发生时间
     */
    private Date createTime;
    /**
     * 日志类型 1 获得 2扣减
     */
    private Integer logType;
    /**
     * 人民币金额
     */
    private Double logMoneyRmb;
    /**
     * 备注
     */
    private String logDesc;
    /**
     * 用户Id
     */
    private String userId;
     
    private Double userBalance;
    
    public String getUserMoneyRmbLog(){
        return userMoneyRmbLog;
    }
    public void setUserMoneyRmbLog(String userMoneyRmbLog){
        this.userMoneyRmbLog=userMoneyRmbLog;
    }
    public Date getCreateTime(){
        return createTime;
    }
    public void setCreateTime(Date createTime){
        this.createTime=createTime;
    }
    public Integer getLogType(){
        return logType;
    }
    public void setLogType(Integer logType){
        this.logType=logType;
    }
    public String getLogDesc(){
        return logDesc;
    }
    public void setLogDesc(String logDesc){
        this.logDesc=logDesc;
    }
    public String getUserId(){
        return userId;
    }
    public void setUserId(String userId){
        this.userId=userId;
    }
	public Double getLogMoneyRmb() {
		return logMoneyRmb;
	}
	public void setLogMoneyRmb(Double logMoneyRmb) {
		this.logMoneyRmb = logMoneyRmb;
	}
	public Double getUserBalance() {
		return userBalance;
	}
	public void setUserBalance(Double userBalance) {
		this.userBalance = userBalance;
	}
}