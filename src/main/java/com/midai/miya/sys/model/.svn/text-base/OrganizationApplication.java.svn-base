package com.midai.miya.sys.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.midai.miya.filter.PropertieUtil;
public class OrganizationApplication implements Serializable {
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final long serialVersionUID = 1L;
    /**
     * 机构认证Id
     */
    private String organizationApplicationId;
    /**
     * 用户Id
     */
    private String userId;
    /**
     * 认证领域
     */
    private String categoryId;
    /**
     * 机构全称
     */
    private String organizationName;
    /**
     * 机构简介
     */
    private String organizationDesc;
    /**
     * 机构网址
     */
    private String organizationUrl;
    /**
     * 联系人
     */
    private String organizationLinkman;
    /**
     * 联系电话
     */
    private String linkmanPhone;
    /**
     * 联系邮箱
     */
    private String linkmanMail;
    /**
     * 上传条形LOGO
     */
    private String organizationLog;
    /**
     * 企业法人营业执照
     */
    private String organizationBusinessLicense;
    
    private String userMail;
    
    private String categoryIdStr;
    /**
     * 状态
     */
    private Integer organizationState;
    private String organizationStateStr;
    /**
     * 审批时间
     */
    private Date applicationApproveTime;
    private String applicationApproveTimeStr;
    private String notPassReasonState;
    /**
     * 不通过原因
     */
    private String applicationNotPassReason;
    /**
     * 创建时间
     */
    private Date createTime;
    private String createTimeStr;
    private String queryTimeBegin;
    
  	private String queryTimeEnd;
  	
  	private String queryTimeBeginStr;
  	    
  	private String queryTimeEndStr;
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
    /**
     * 身份证号
     */
    private String applicationIdentityCard;
    public String getOrganizationApplicationId(){
        return organizationApplicationId;
    }
    public void setOrganizationApplicationId(String organizationApplicationId){
        this.organizationApplicationId=organizationApplicationId;
    }
    public String getUserId(){
        return userId;
    }
    public void setUserId(String userId){
        this.userId=userId;
    }
    public String getCategoryId(){
        return categoryId;
    }
    public void setCategoryId(String categoryId){
        this.categoryId=categoryId;
    }
    public String getOrganizationName(){
        return organizationName;
    }
    public void setOrganizationName(String organizationName){
        this.organizationName=organizationName;
    }
    public String getOrganizationDesc(){
        return organizationDesc;
    }
    public void setOrganizationDesc(String organizationDesc){
        this.organizationDesc=organizationDesc;
    }
    public String getOrganizationUrl(){
        return organizationUrl;
    }
    public void setOrganizationUrl(String organizationUrl){
        this.organizationUrl=organizationUrl;
    }
    public String getOrganizationLinkman(){
        return organizationLinkman;
    }
    public void setOrganizationLinkman(String organizationLinkman){
        this.organizationLinkman=organizationLinkman;
    }
    public String getLinkmanPhone(){
        return linkmanPhone;
    }
    public void setLinkmanPhone(String linkmanPhone){
        this.linkmanPhone=linkmanPhone;
    }
    public String getLinkmanMail(){
        return linkmanMail;
    }
    public void setLinkmanMail(String linkmanMail){
        this.linkmanMail=linkmanMail;
    }
    public String getOrganizationLog(){
        return organizationLog;
    }
    public void setOrganizationLog(String organizationLog){
        this.organizationLog=organizationLog;
    }
    public String getOrganizationBusinessLicense(){
        return organizationBusinessLicense;
    }
    public void setOrganizationBusinessLicense(String organizationBusinessLicense){
        this.organizationBusinessLicense=organizationBusinessLicense;
    }
	public String getUserMail() {
		return userMail;
	}
	public void setUserMail(String userMail) {
		this.userMail = userMail;
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
	public Integer getOrganizationState() {
		return organizationState;
	}
	public void setOrganizationState(Integer organizationState) {
		this.organizationState = organizationState;
	}
	public Date getApplicationApproveTime() {
		return applicationApproveTime;
	}
	public void setApplicationApproveTime(Date applicationApproveTime) {
		this.applicationApproveTime = applicationApproveTime;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
	public String getNotPassReasonState() {
		return notPassReasonState;
	}
	public void setNotPassReasonState(String notPassReasonState) {
		this.notPassReasonState = notPassReasonState;
	}
	public String getOrganizationStateStr() {
		if(organizationState!=null){
			if(organizationState==0){
				organizationStateStr="审批中";
			}else if(organizationState==1){
				organizationStateStr="审批通过";
			}else if(organizationState==2){
				organizationStateStr="审批不通过";
			}
		}
		return organizationStateStr;
	}
	public void setOrganizationStateStr(String organizationStateStr) {
		this.organizationStateStr = organizationStateStr;
	}
	public String getCreateTimeStr() {
		if(createTime!=null){
			return sdf.format(createTime);
		}else{
		return "";
		}
	}
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
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
	public String getApplicationIdentityCard() {
		return applicationIdentityCard;
	}
	public void setApplicationIdentityCard(String applicationIdentityCard) {
		this.applicationIdentityCard = applicationIdentityCard;
	}
}