package com.midai.miya.user.model;

import java.util.Date;
import java.io.Serializable;
public class School implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 学校编号
     */
    private String schoolNum;
    /**
     * 学校名称
     */
    private String schoolName;
    /**
     * 排序
     */
    private Integer schoolSort;
    /**
     * 
     */
    private Date createTime;
    
    private String oldSchoolNum;
    public String getSchoolNum(){
        return schoolNum;
    }
    public void setSchoolNum(String schoolNum){
        this.schoolNum=schoolNum;
    }
    public String getSchoolName(){
        return schoolName;
    }
    public void setSchoolName(String schoolName){
        this.schoolName=schoolName;
    }
    public Integer getSchoolSort(){
        return schoolSort;
    }
    public void setSchoolSort(Integer schoolSort){
        this.schoolSort=schoolSort;
    }
    public Date getCreateTime(){
        return createTime;
    }
    public void setCreateTime(Date createTime){
        this.createTime=createTime;
    }
	public String getOldSchoolNum() {
		return oldSchoolNum;
	}
	public void setOldSchoolNum(String oldSchoolNum) {
		this.oldSchoolNum = oldSchoolNum;
	}
}