package com.midai.miya.sys.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Subject implements Serializable {
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
    private static final long serialVersionUID = 1L;
    /**
     * 专题Id
     */
    private String subjectId;
    /**
     * 专题标题
     */
    private String subjectTitle;
    /**
     * 专题内容
     */
    private String subjectText;
    /**
     * 专题排序
     */
    private Integer subjectSort;
    /**
     * 状态 1正常 2取消
     */
    private Integer subjectState;
    private String subjectStateStr;
 
    /**
     * 创建时间
     */
    private Date subjectCreateTime;
    private String subjectCreateTimeStr;
    
    private String queryTimeBegin;
    private String queryTimeEnd;
    
    private Date subjectStartTime;
    private Date subjectEndTime;
    private String subjectStartTimeStr;
    private String subjectEndTimeStr;
    private String subjectAward;
    private String subjectImgUrl;
    private Integer subjectParticipantsNumber;
    /**
     * 是否需要设置学校和社团
     */
    private Integer subjectNeedSettingclub;
    public String getSubjectId(){
        return subjectId;
    }
    public void setSubjectId(String subjectId){
        this.subjectId=subjectId;
    }
    public String getSubjectTitle(){
        return subjectTitle;
    }
    public void setSubjectTitle(String subjectTitle){
        this.subjectTitle=subjectTitle;
    }
    public String getSubjectText(){
        return subjectText;
    }
    public void setSubjectText(String subjectText){
        this.subjectText=subjectText;
    }
    public Integer getSubjectSort(){
        return subjectSort;
    }
    public void setSubjectSort(Integer subjectSort){
        this.subjectSort=subjectSort;
    }
    public Integer getSubjectState(){
        return subjectState;
    }
    public void setSubjectState(Integer subjectState){
        this.subjectState=subjectState;
    }
    public Date getSubjectCreateTime(){
        return subjectCreateTime;
    }
    public void setSubjectCreateTime(Date subjectCreateTime){
        this.subjectCreateTime=subjectCreateTime;
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
	public String getSubjectStateStr() {
		if(subjectState==1){
			return "正常";
		}else{
			return "取消";
		}
	}
	public void setSubjectStateStr(String subjectStateStr) {
		this.subjectStateStr = subjectStateStr;
	}
	public String getSubjectCreateTimeStr() {
		if(subjectCreateTime!=null){
			return sdf.format(subjectCreateTime);
		}else{
			return "";
		}
	}
	public void setSubjectCreateTimeStr(String subjectCreateTimeStr) {
		this.subjectCreateTimeStr = subjectCreateTimeStr;
	}
	public Date getSubjectStartTime() {
		return subjectStartTime;
	}
	public void setSubjectStartTime(Date subjectStartTime) {
		this.subjectStartTime = subjectStartTime;
	}
	public Date getSubjectEndTime() {
		return subjectEndTime;
	}
	public void setSubjectEndTime(Date subjectEndTime) {
		this.subjectEndTime = subjectEndTime;
	}
	public String getSubjectAward() {
		return subjectAward;
	}
	public void setSubjectAward(String subjectAward) {
		this.subjectAward = subjectAward;
	}
	public String getSubjectImgUrl() {
		return subjectImgUrl;
	}
	public void setSubjectImgUrl(String subjectImgUrl) {
		this.subjectImgUrl = subjectImgUrl;
	}
	public Integer getSubjectParticipantsNumber() {
		return subjectParticipantsNumber;
	}
	public void setSubjectParticipantsNumber(Integer subjectParticipantsNumber) {
		this.subjectParticipantsNumber = subjectParticipantsNumber;
	}
	public String getSubjectStartTimeStr() {
		return subjectStartTimeStr;
	}
	public void setSubjectStartTimeStr(String subjectStartTimeStr) {
		this.subjectStartTimeStr = subjectStartTimeStr;
	}
	public String getSubjectEndTimeStr() {
		return subjectEndTimeStr;
	}
	public void setSubjectEndTimeStr(String subjectEndTimeStr) {
		this.subjectEndTimeStr = subjectEndTimeStr;
	}
	public Integer getSubjectNeedSettingclub() {
		return subjectNeedSettingclub;
	}
	public void setSubjectNeedSettingclub(Integer subjectNeedSettingclub) {
		this.subjectNeedSettingclub = subjectNeedSettingclub;
	}
}