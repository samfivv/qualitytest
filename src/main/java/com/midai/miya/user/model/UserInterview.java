package com.midai.miya.user.model;

import java.util.Date;
import java.io.Serializable;
public class UserInterview implements Serializable {

    private static final long serialVersionUID = 1L;
    private String userInterviewId;
    private String userInterviewTitle;
    private String userInterviewAbstract;
    private String userInterviewContent;
    private String coverImgUrl;
    private String createOperatorId;
    private Integer userInterviewState;
    private String updateOperatorId;
    private String createOperatorName;
    private String updateOperatorName;
    private Date createTime;
    private Date updateTime;
    private Integer userInterviewSort;
    public String getUserInterviewId(){
        return userInterviewId;
    }
    public void setUserInterviewId(String userInterviewId){
        this.userInterviewId=userInterviewId;
    }
    public String getUserInterviewTitle(){
        return userInterviewTitle;
    }
    public void setUserInterviewTitle(String userInterviewTitle){
        this.userInterviewTitle=userInterviewTitle;
    }
    public String getUserInterviewAbstract(){
        return userInterviewAbstract;
    }
    public void setUserInterviewAbstract(String userInterviewAbstract){
        this.userInterviewAbstract=userInterviewAbstract;
    }
    public String getUserInterviewContent(){
        return userInterviewContent;
    }
    public void setUserInterviewContent(String userInterviewContent){
        this.userInterviewContent=userInterviewContent;
    }
    public String getCoverImgUrl(){
        return coverImgUrl;
    }
    public void setCoverImgUrl(String coverImgUrl){
        this.coverImgUrl=coverImgUrl;
    }
    public String getCreateOperatorId(){
        return createOperatorId;
    }
    public void setCreateOperatorId(String createOperatorId){
        this.createOperatorId=createOperatorId;
    }
    public Integer getUserInterviewState(){
        return userInterviewState;
    }
    public void setUserInterviewState(Integer userInterviewState){
        this.userInterviewState=userInterviewState;
    }
    public String getUpdateOperatorId(){
        return updateOperatorId;
    }
    public void setUpdateOperatorId(String updateOperatorId){
        this.updateOperatorId=updateOperatorId;
    }
    public Date getCreateTime(){
        return createTime;
    }
    public void setCreateTime(Date createTime){
        this.createTime=createTime;
    }
    public Date getUpdateTime(){
        return updateTime;
    }
    public void setUpdateTime(Date updateTime){
        this.updateTime=updateTime;
    }
	public String getCreateOperatorName() {
		return createOperatorName;
	}
	public void setCreateOperatorName(String createOperatorName) {
		this.createOperatorName = createOperatorName;
	}
	public String getUpdateOperatorName() {
		return updateOperatorName;
	}
	public void setUpdateOperatorName(String updateOperatorName) {
		this.updateOperatorName = updateOperatorName;
	}
	public Integer getUserInterviewSort() {
		return userInterviewSort;
	}
	public void setUserInterviewSort(Integer userInterviewSort) {
		this.userInterviewSort = userInterviewSort;
	}
}