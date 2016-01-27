package com.midai.miya.sys.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.Serializable;

import com.midai.miya.filter.PropertieUtil;
public class UserApplication implements Serializable {
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private String applicationId;
    /**
     * 用户Id
     */
    private String userId;
    /**
     * 姓名
     */
    private String applicationName;
    /**
     * 身份证号码
     */
    private String applicationIdentityCard;
    /**
     * 身份证到期时间
     */
    private Date applicationDueTime;
    
    private String applicationDueTimeStr;
    /**
     * 手持身份证照片
     */
    private String applicationHandIdentityPhoto;
    /**
     * 身份证正面
     */
    private String applicationFrontIdentityPhoto;
    /**
     * 身份证背面
     */
    private String applicationBackIdentityPhoto;
    /**
     * 创建时间
     */
    private Date applicationCreateTime;
    private String applicationCreateTimeStr;
    /**
     * 审批时间
     */
    private Date applicationApproveTime;
    private String applicationApproveTimeStr;
    /**
     * 0审批中 1审批通过 2审批不通过
     */
    private Integer applicationState;
    
    private String applicationStateStr;
    
    private String queryTimeBegin;
    
	private String queryTimeEnd;
	
	private String queryTimeBeginStr;
	    
	private String queryTimeEndStr;
	
	private String notPassReasonState;
	/**
	 * 不通过原因
	 */
	private String applicationNotPassReason;
	/**
	 * 电话号码
	 */
	private String applicationPhoneNumber;
	
	private String userMail;
	/**
	 * 个人简介
	 */
	private String applicationResume;
	
	private String categoryId;
	
	private String categoryIdStr;
	 /**
     * 详细地址
     */
    private String userAddr;
    /**
     * 省
     */
    private String userAddrProvince;
    /**
     * 市
     */
    private String userAddrCity;
    /**
     * 区
     */
    private String userAddrDistrict;
    /**
     * 攻略链接
     */
    private String interestUrl;
    /**
     * 微信号
     */
    private String wechatNum;
    /**
     * 微博号
     */
    private String weiboNum;
    /**
     * 媒体报道链接
     */
    private String medaiReportUrl;
    /**
     * 代表作链接
     */
    private String masterWorkUrl;
    
    private String categoryName;
    public String getApplicationId(){
        return applicationId;
    }
    public void setApplicationId(String applicationId){
        this.applicationId=applicationId;
    }
    public String getUserId(){
        return userId;
    }
    public void setUserId(String userId){
        this.userId=userId;
    }
    public String getApplicationName(){
        return applicationName;
    }
    public void setApplicationName(String applicationName){
        this.applicationName=applicationName;
    }
    public String getApplicationIdentityCard(){
        return applicationIdentityCard;
    }
    public void setApplicationIdentityCard(String applicationIdentityCard){
        this.applicationIdentityCard=applicationIdentityCard;
    }
    public Date getApplicationDueTime(){
        return applicationDueTime;
    }
    public void setApplicationDueTime(Date applicationDueTime){
        this.applicationDueTime=applicationDueTime;
    }
    public String getApplicationHandIdentityPhoto(){
        return applicationHandIdentityPhoto;
    }
    public void setApplicationHandIdentityPhoto(String applicationHandIdentityPhoto){
        this.applicationHandIdentityPhoto=applicationHandIdentityPhoto;
    }
    public String getApplicationFrontIdentityPhoto(){
        return applicationFrontIdentityPhoto;
    }
    public void setApplicationFrontIdentityPhoto(String applicationFrontIdentityPhoto){
        this.applicationFrontIdentityPhoto=applicationFrontIdentityPhoto;
    }
    public String getApplicationBackIdentityPhoto(){
        return applicationBackIdentityPhoto;
    }
    public void setApplicationBackIdentityPhoto(String applicationBackIdentityPhoto){
        this.applicationBackIdentityPhoto=applicationBackIdentityPhoto;
    }
    public Date getApplicationCreateTime(){
        return applicationCreateTime;
    }
    public void setApplicationCreateTime(Date applicationCreateTime){
        this.applicationCreateTime=applicationCreateTime;
    }
    public Date getApplicationApproveTime(){
        return applicationApproveTime;
    }
    public void setApplicationApproveTime(Date applicationApproveTime){
        this.applicationApproveTime=applicationApproveTime;
    }
    public Integer getApplicationState(){
        return applicationState;
    }
    public void setApplicationState(Integer applicationState){
        this.applicationState=applicationState;
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
	public String getApplicationStateStr() {
		if(applicationState!=null){
			if(applicationState==0){
				applicationStateStr="审批中";
			}else if(applicationState==1){
				applicationStateStr="审批通过";
			}else if(applicationState==2){
				applicationStateStr="审批不通过";
			}
		}
		return applicationStateStr;
	}
	public void setApplicationStateStr(String applicationStateStr) {
		this.applicationStateStr = applicationStateStr;
	}
	public String getNotPassReasonState() {
		return notPassReasonState;
	}
	public void setNotPassReasonState(String notPassReasonState) {
		this.notPassReasonState = notPassReasonState;
	}
	public String getApplicationNotPassReason() {
		if(notPassReasonState!=null){
			if(PropertieUtil.userunPassReasonMap.get(notPassReasonState)!=null&&!"zzz".equals(notPassReasonState)){
				return PropertieUtil.userunPassReasonMap.get(notPassReasonState);
			}else{
				return applicationNotPassReason;
			}
		}else{
			return applicationNotPassReason;
		}
	}
	public void setApplicationNotPassReason(String applicationNotPassReason) {
		this.applicationNotPassReason = applicationNotPassReason;
	}
	public String getApplicationPhoneNumber() {
		return applicationPhoneNumber;
	}
	public void setApplicationPhoneNumber(String applicationPhoneNumber) {
		this.applicationPhoneNumber = applicationPhoneNumber;
	}
	public String getQueryTimeBeginStr() {
		return queryTimeBeginStr;
	}
	public void setQueryTimeBeginStr(String queryTimeBeginStr) {
		this.queryTimeBeginStr = queryTimeBeginStr;
	}
	public String getQueryTimeEndStr() {
		return queryTimeEndStr;
	}
	public void setQueryTimeEndStr(String queryTimeEndStr) {
		this.queryTimeEndStr = queryTimeEndStr;
	}
	public String getApplicationCreateTimeStr() {
		if(applicationCreateTime!=null){
			return sdf.format(applicationCreateTime);
		}else{
		return "";
		}
	}
	public void setApplicationCreateTimeStr(String applicationCreateTimeStr) {
		this.applicationCreateTimeStr = applicationCreateTimeStr;
	}
	public String getApplicationApproveTimeStr() {
		if(applicationApproveTime!=null){
			return sdf.format(applicationApproveTime);
		}else{
			return "";
		}
	}
	public void setApplicationApproveTimeStr(String applicationApproveTimeStr) {
		this.applicationApproveTimeStr = applicationApproveTimeStr;
	}
	public String getApplicationDueTimeStr() {
		if(applicationDueTime!=null){
			return sdf.format(applicationDueTime);
		}else{
			return "";
		}
	}
	public void setApplicationDueTimeStr(String applicationDueTimeStr) {
		this.applicationDueTimeStr = applicationDueTimeStr;
	}
	public String getUserMail() {
		return userMail;
	}
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
	public String getApplicationResume() {
		return applicationResume;
	}
	public void setApplicationResume(String applicationResume) {
		this.applicationResume = applicationResume;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryIdStr() {
		if(categoryId!=null){
			if(categoryId.equals("1")){
				categoryIdStr="艺术";
			}else if(categoryId.equals("2")){
				categoryIdStr="生活";
			}else if(categoryId.equals("3")){
				categoryIdStr="运动";
			}else if(categoryId.equals("4")){
				categoryIdStr="时尚";
			}else if(categoryId.equals("5")){
				categoryIdStr="美食";
			}else if(categoryId.equals("6")){
				categoryIdStr="娱乐";
			}else if(categoryId.equals("7")){
				categoryIdStr="语言";
			}else if(categoryId.equals("8")){
				categoryIdStr="职场";
			}
		}
		return categoryIdStr;
	}
	public void setCategoryIdStr(String categoryIdStr) {
		this.categoryIdStr = categoryIdStr;
	}
	public String getUserAddr() {
		return userAddr;
	}
	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}
	public String getUserAddrProvince() {
		return userAddrProvince;
	}
	public void setUserAddrProvince(String userAddrProvince) {
		this.userAddrProvince = userAddrProvince;
	}
	public String getUserAddrCity() {
		return userAddrCity;
	}
	public void setUserAddrCity(String userAddrCity) {
		this.userAddrCity = userAddrCity;
	}
	public String getUserAddrDistrict() {
		return userAddrDistrict;
	}
	public void setUserAddrDistrict(String userAddrDistrict) {
		this.userAddrDistrict = userAddrDistrict;
	}
	public String getInterestUrl() {
		return interestUrl;
	}
	public void setInterestUrl(String interestUrl) {
		this.interestUrl = interestUrl;
	}
	public String getWechatNum() {
		return wechatNum;
	}
	public void setWechatNum(String wechatNum) {
		this.wechatNum = wechatNum;
	}
	public String getWeiboNum() {
		return weiboNum;
	}
	public void setWeiboNum(String weiboNum) {
		this.weiboNum = weiboNum;
	}
	public String getMedaiReportUrl() {
		return medaiReportUrl;
	}
	public void setMedaiReportUrl(String medaiReportUrl) {
		this.medaiReportUrl = medaiReportUrl;
	}
	public String getMasterWorkUrl() {
		return masterWorkUrl;
	}
	public void setMasterWorkUrl(String masterWorkUrl) {
		this.masterWorkUrl = masterWorkUrl;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}