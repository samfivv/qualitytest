package com.midai.miya.sys.model;

import java.util.Date;
import java.io.Serializable;
public class BottomPage implements Serializable {

    private static final long serialVersionUID = 1L;
    private String bottomPageId;
    private Integer horizontalPosition;
    private Integer verticalPosition;
    private String pageTitle;
    private String pageContent;
    private Date createTime;
    public String getBottomPageId(){
        return bottomPageId;
    }
    public void setBottomPageId(String bottomPageId){
        this.bottomPageId=bottomPageId;
    }
    public Integer getHorizontalPosition(){
        return horizontalPosition;
    }
    public void setHorizontalPosition(Integer horizontalPosition){
        this.horizontalPosition=horizontalPosition;
    }
    public Integer getVerticalPosition(){
        return verticalPosition;
    }
    public void setVerticalPosition(Integer verticalPosition){
        this.verticalPosition=verticalPosition;
    }
    public String getPageTitle(){
        return pageTitle;
    }
    public void setPageTitle(String pageTitle){
        this.pageTitle=pageTitle;
    }
    public String getPageContent(){
        return pageContent;
    }
    public void setPageContent(String pageContent){
        this.pageContent=pageContent;
    }
    public Date getCreateTime(){
        return createTime;
    }
    public void setCreateTime(Date createTime){
        this.createTime=createTime;
    }
}