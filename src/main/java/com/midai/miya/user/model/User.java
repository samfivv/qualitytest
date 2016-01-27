package com.midai.miya.user.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class User implements Serializable{
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -2126272309054399733L;
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String userId;

	private String userName;

	private String userPassword;

	private String userMail;

	private String userQq;

	private Integer userState;
	private String userStateStr;
	private Integer userIsauthor;
	private Integer userSex;
	private String userSexStr;
	private Integer userMailState;
	private String userMailStateStr;
	private String userUuid;
	private String userHeadPortrait;
	private String userNickname;
	private Date userLastLoginTime;
	private Date userRegisterTime;
	private String userLastLoginTimeStr;
	private String userRegisterTimeStr;
	private String lastLoginTimeBegin;
	private String lastLoginTimeEnd;
	private String queryBeginRegisterTimeStr;
	private String queryEndRegisterTimeStr;
	private String userSignature;
	private String userSkills;
	private Integer userType;
	private String clubNum;
	private String schoolNum;
	private Integer isInside;
	private String content;
	private String userLists;
	private Integer checkState;
	private String userMailTitle;
	private String userPhoneLists;
	private String userPhone;
	private Integer registerFrom;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserMail() {
		return userMail;
	}
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
	public String getUserQq() {
		return userQq;
	}
	public void setUserQq(String userQq) {
		this.userQq = userQq;
	}
	public Integer getUserState() {
		return userState;
	}
	public void setUserState(Integer userState) {
		this.userState = userState;
	}
	public Integer getUserSex() {
		return userSex;
	}
	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}
	public Integer getUserMailState() {
		return userMailState;
	}
	public void setUserMailState(Integer userMailState) {
		this.userMailState = userMailState;
	}
	public String getUserUuid() {
		return userUuid;
	}
	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}
	public String getUserHeadPortrait() {
		return userHeadPortrait;
	}
	public void setUserHeadPortrait(String userHeadPortrait) {
		this.userHeadPortrait = userHeadPortrait;
	}
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	public String getQueryBeginRegisterTimeStr() {
		return queryBeginRegisterTimeStr;
	}
	public void setQueryBeginRegisterTimeStr(String queryBeginRegisterTimeStr) {
		this.queryBeginRegisterTimeStr = queryBeginRegisterTimeStr;
	}
	public String getQueryEndRegisterTimeStr() {
		return queryEndRegisterTimeStr;
	}
	public void setQueryEndRegisterTimeStr(String queryEndRegisterTimeStr) {
		this.queryEndRegisterTimeStr = queryEndRegisterTimeStr;
	}
	
	public String getUserStateStr() {
		if(userState!=null){
			if(userState==1){
				return "正常";
			}else{
				return "黑名单";
			}
		}else{
			return "黑名单";
		}
	}
	public void setUserStateStr(String userStateStr) {
		this.userStateStr = userStateStr;
	}
	public String getUserMailStateStr() {
		if(userMailState!=null){
			if(userMailState==1){
				return "未验证";
			}else{
				return "已验证";
			}
		}else{
			return "未验证";
		}
	}
	public void setUserMailStateStr(String userMailStateStr) {
		this.userMailStateStr = userMailStateStr;
	}
	public String getUserSexStr() {
		if(userSex!=null){
			if(userSex==0){
				return "未填写";
			}else if(userSex==1){
				return "男";
			}else {
				return "女";
			}
		}else{
			return "未填写";
		}
	}
	public void setUserSexStr(String userSexStr) {
		this.userSexStr = userSexStr;
	}
	public Date getUserLastLoginTime() {
		return userLastLoginTime;
	}
	public void setUserLastLoginTime(Date userLastLoginTime) {
		this.userLastLoginTime = userLastLoginTime;
	}
	public Date getUserRegisterTime() {
		return userRegisterTime;
	}
	public void setUserRegisterTime(Date userRegisterTime) {
		this.userRegisterTime = userRegisterTime;
	}
	public String getUserLastLoginTimeStr() {
		if(userLastLoginTime==null){
			return null;
		}else{
			return sdf.format(userLastLoginTime);
		}
	}
	public void setUserLastLoginTimeStr(String userLastLoginTimeStr) {
		this.userLastLoginTimeStr = userLastLoginTimeStr;
	}
	public String getUserRegisterTimeStr() {
		if(userRegisterTime==null){
			return null;
		}else{
			return sdf.format(userRegisterTime);
		}
	}
	public void setUserRegisterTimeStr(String userRegisterTimeStr) {
		this.userRegisterTimeStr = userRegisterTimeStr;
	}
	public String getLastLoginTimeEnd() {
		return lastLoginTimeEnd;
	}
	public void setLastLoginTimeEnd(String lastLoginTimeEnd) {
		this.lastLoginTimeEnd = lastLoginTimeEnd;
	}
	public String getLastLoginTimeBegin() {
		return lastLoginTimeBegin;
	}
	public void setLastLoginTimeBegin(String lastLoginTimeBegin) {
		this.lastLoginTimeBegin = lastLoginTimeBegin;
	}
	public Integer getUserIsauthor() {
		return userIsauthor;
	}
	public void setUserIsauthor(Integer userIsauthor) {
		this.userIsauthor = userIsauthor;
	}
	public String getUserSignature() {
		return userSignature;
	}
	public void setUserSignature(String userSignature) {
		this.userSignature = userSignature;
	}
	public String getUserSkills() {
		return userSkills;
	}
	public void setUserSkills(String userSkills) {
		this.userSkills = userSkills;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public String getClubNum() {
		return clubNum;
	}
	public void setClubNum(String clubNum) {
		this.clubNum = clubNum;
	}
	public String getSchoolNum() {
		return schoolNum;
	}
	public void setSchoolNum(String schoolNum) {
		this.schoolNum = schoolNum;
	}
	public Integer getIsInside() {
		return isInside;
	}
	public void setIsInside(Integer isInside) {
		this.isInside = isInside;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getCheckState() {
		return checkState;
	}
	public void setCheckState(Integer checkState) {
		this.checkState = checkState;
	}
	public String getUserMailTitle() {
		return userMailTitle;
	}
	public void setUserMailTitle(String userMailTitle) {
		this.userMailTitle = userMailTitle;
	}
	public String getUserLists() {
		return userLists;
	}
	public void setUserLists(String userLists) {
		this.userLists = userLists;
	}
	public String getUserPhoneLists() {
		return userPhoneLists;
	}
	public void setUserPhoneLists(String userPhoneLists) {
		this.userPhoneLists = userPhoneLists;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public Integer getRegisterFrom() {
		return registerFrom;
	}
	public void setRegisterFrom(Integer registerFrom) {
		this.registerFrom = registerFrom;
	}

}