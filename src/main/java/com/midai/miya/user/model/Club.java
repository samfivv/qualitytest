package com.midai.miya.user.model;

import java.util.Date;
import java.io.Serializable;
public class Club implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 社团编号
     */
    private String clubNum;
    /**
     * 所属学校
     */
    private String schoolNum;
    /**
     * 社团名称
     */
    private String clubName;
    /**
     * 排序
     */
    private Integer clubSort;
    /**
     * 
     */
    private Date createTime;
    
    private String schoolName;
    
    private String oldClubNum;
    
    public String getClubNum(){
        return clubNum;
    }
    public void setClubNum(String clubNum){
        this.clubNum=clubNum;
    }
    public String getSchoolNum(){
        return schoolNum;
    }
    public void setSchoolNum(String schoolNum){
        this.schoolNum=schoolNum;
    }
    public String getClubName(){
        return clubName;
    }
    public void setClubName(String clubName){
        this.clubName=clubName;
    }
    public Integer getClubSort(){
        return clubSort;
    }
    public void setClubSort(Integer clubSort){
        this.clubSort=clubSort;
    }
    public Date getCreateTime(){
        return createTime;
    }
    public void setCreateTime(Date createTime){
        this.createTime=createTime;
    }
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getOldClubNum() {
		return oldClubNum;
	}
	public void setOldClubNum(String oldClubNum) {
		this.oldClubNum = oldClubNum;
	}
}