package com.midai.miya.sys.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.Serializable;
public class SubjectInterest implements Serializable {
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    private String subjectInterestId;
    /**
     * 
     */
    private String subjectId;
    /**
     * 
     */
    private String interestId;
    /**
     * 状态 1未通过审核 2通过审核
     */
    private Integer subjectInterestState;
    private String subjectInterestStateStr;
    /**
     * 排序
     */
    private Integer subjectInterestSort;
    /**
     * 
     */
    private Date createTime;
    /**
     * 兴趣标题
     */
    private String interestTitle;
    /**
     * 兴趣简介
     */
    private String interestDesc;
    /**
     * 昵称
     */
    private String userNickname;
    
    private String userMail;
    /**
     * 更新时间
     */
    private Date interestUpdateTime;
    /**
     * 专题标题
     */
    private String subjectTitle;
    private String queryTimeBegin;
    private String queryTimeEnd;
    private String createTimeStr;
    private String interestUpdateTimeStr;
    private String schoolNum;//学校编号
    private String schoolName;//学校名称
    private String clubNum;//社团编号
    private String clubName;//社团名称
    
    public String getSubjectInterestId(){
        return subjectInterestId;
    }
    public void setSubjectInterestId(String subjectInterestId){
        this.subjectInterestId=subjectInterestId;
    }
    public String getSubjectId(){
        return subjectId;
    }
    public void setSubjectId(String subjectId){
        this.subjectId=subjectId;
    }
    public String getInterestId(){
        return interestId;
    }
    public void setInterestId(String interestId){
        this.interestId=interestId;
    }
    public Integer getSubjectInterestState(){
        return subjectInterestState;
    }
    public void setSubjectInterestState(Integer subjectInterestState){
        this.subjectInterestState=subjectInterestState;
    }
    public Integer getSubjectInterestSort(){
        return subjectInterestSort;
    }
    public void setSubjectInterestSort(Integer subjectInterestSort){
        this.subjectInterestSort=subjectInterestSort;
    }
    public Date getCreateTime(){
        return createTime;
    }
    public void setCreateTime(Date createTime){
        this.createTime=createTime;
    }
	public String getInterestTitle() {
		return interestTitle;
	}
	public void setInterestTitle(String interestTitle) {
		this.interestTitle = interestTitle;
	}
	public String getInterestDesc() {
		return interestDesc;
	}
	public void setInterestDesc(String interestDesc) {
		this.interestDesc = interestDesc;
	}
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	public Date getInterestUpdateTime() {
		return interestUpdateTime;
	}
	public void setInterestUpdateTime(Date interestUpdateTime) {
		this.interestUpdateTime = interestUpdateTime;
	}
	public String getSubjectTitle() {
		return subjectTitle;
	}
	public void setSubjectTitle(String subjectTitle) {
		this.subjectTitle = subjectTitle;
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
	public String getInterestUpdateTimeStr() {
		if(interestUpdateTime!=null){
			return sdf.format(interestUpdateTime);
		}else{
			return "";
		}
	}
	public void setInterestUpdateTimeStr(String interestUpdateTimeStr) {
		this.interestUpdateTimeStr = interestUpdateTimeStr;
	}
	public String getSubjectInterestStateStr() {
		String str="";
		if(subjectInterestState!=null){
			if(subjectInterestState==1){
				str= "不通过";
			}else if(subjectInterestState==2){
				str= "通过";
			}
		}else{
			str="不通过";
		}
		return str;
	}
	public void setSubjectInterestStateStr(String subjectInterestStateStr) {
		this.subjectInterestStateStr = subjectInterestStateStr;
	}
	public String getSchoolNum() {
		return schoolNum;
	}
	public void setSchoolNum(String schoolNum) {
		this.schoolNum = schoolNum;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getClubNum() {
		return clubNum;
	}
	public void setClubNum(String clubNum) {
		this.clubNum = clubNum;
	}
	public String getClubName() {
		return clubName;
	}
	public void setClubName(String clubName) {
		this.clubName = clubName;
	}
	public String getUserMail() {
		return userMail;
	}
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
}