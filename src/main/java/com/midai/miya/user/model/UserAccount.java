package com.midai.miya.user.model;

import java.util.Date;
import java.io.Serializable;
public class UserAccount implements Serializable {

    private static final long serialVersionUID = 1L;
    private String accountLogId;
    private String userId;
    private Date accountLogTime;
    private Double accountLogAmout;
    private String accountLogDesc;
    private Integer plusOrMinus;
    private String orderId;
    public String getAccountLogId(){
        return accountLogId;
    }
    public void setAccountLogId(String accountLogId){
        this.accountLogId=accountLogId;
    }
    public String getUserId(){
        return userId;
    }
    public void setUserId(String userId){
        this.userId=userId;
    }
    public Date getAccountLogTime(){
        return accountLogTime;
    }
    public void setAccountLogTime(Date accountLogTime){
        this.accountLogTime=accountLogTime;
    }
    public Double getAccountLogAmout(){
        return accountLogAmout;
    }
    public void setAccountLogAmout(Double accountLogAmout){
        this.accountLogAmout=accountLogAmout;
    }
    public String getAccountLogDesc(){
        return accountLogDesc;
    }
    public void setAccountLogDesc(String accountLogDesc){
        this.accountLogDesc=accountLogDesc;
    }
    public Integer getPlusOrMinus(){
        return plusOrMinus;
    }
    public void setPlusOrMinus(Integer plusOrMinus){
        this.plusOrMinus=plusOrMinus;
    }
    public String getOrderId(){
        return orderId;
    }
    public void setOrderId(String orderId){
        this.orderId=orderId;
    }
}