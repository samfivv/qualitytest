package com.midai.miya.user.model;

import java.io.Serializable;
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private String userId;
    private String userrNo;
    private String userrSort;
    private String userrDesc;
    private String userPhone;
    private String userEmail;
    private String userPassword;
    private String userAddr;
    private Integer userType;
    private String userCertNo;
    private String userTel;
    private String userFax;
    private Integer userState;
    private Boolean userVip;
    private Integer userGrade;
    private Double userBalance;
    public String getUserId(){
        return userId;
    }
    public void setUserId(String userId){
        this.userId=userId;
    }
    public String getUserrNo(){
        return userrNo;
    }
    public void setUserrNo(String userrNo){
        this.userrNo=userrNo;
    }
    public String getUserrSort(){
        return userrSort;
    }
    public void setUserrSort(String userrSort){
        this.userrSort=userrSort;
    }
    public String getUserrDesc(){
        return userrDesc;
    }
    public void setUserrDesc(String userrDesc){
        this.userrDesc=userrDesc;
    }
    public String getUserPhone(){
        return userPhone;
    }
    public void setUserPhone(String userPhone){
        this.userPhone=userPhone;
    }
    public String getUserEmail(){
        return userEmail;
    }
    public void setUserEmail(String userEmail){
        this.userEmail=userEmail;
    }
    public String getUserPassword(){
        return userPassword;
    }
    public void setUserPassword(String userPassword){
        this.userPassword=userPassword;
    }
    public String getUserAddr(){
        return userAddr;
    }
    public void setUserAddr(String userAddr){
        this.userAddr=userAddr;
    }
    public Integer getUserType(){
        return userType;
    }
    public void setUserType(Integer userType){
        this.userType=userType;
    }
    public String getUserCertNo(){
        return userCertNo;
    }
    public void setUserCertNo(String userCertNo){
        this.userCertNo=userCertNo;
    }
    public String getUserTel(){
        return userTel;
    }
    public void setUserTel(String userTel){
        this.userTel=userTel;
    }
    public String getUserFax(){
        return userFax;
    }
    public void setUserFax(String userFax){
        this.userFax=userFax;
    }
    public Integer getUserState(){
        return userState;
    }
    public void setUserState(Integer userState){
        this.userState=userState;
    }
    public Boolean getUserVip(){
        return userVip;
    }
    public void setUserVip(Boolean userVip){
        this.userVip=userVip;
    }
    public Integer getUserGrade(){
        return userGrade;
    }
    public void setUserGrade(Integer userGrade){
        this.userGrade=userGrade;
    }
    public Double getUserBalance(){
        return userBalance;
    }
    public void setUserBalance(Double userBalance){
        this.userBalance=userBalance;
    }
}