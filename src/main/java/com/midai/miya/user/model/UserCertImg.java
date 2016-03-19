package com.midai.miya.user.model;

import java.util.Date;
import java.io.Serializable;
public class UserCertImg implements Serializable {

    private static final long serialVersionUID = 1L;
    private String userImgId;
    private String userId;
    private String bigImgUrl;
    private String smallImgUrl;
    private Date createTime;
    public String getUserImgId(){
        return userImgId;
    }
    public void setUserImgId(String userImgId){
        this.userImgId=userImgId;
    }
    public String getUserId(){
        return userId;
    }
    public void setUserId(String userId){
        this.userId=userId;
    }
    public String getBigImgUrl(){
        return bigImgUrl;
    }
    public void setBigImgUrl(String bigImgUrl){
        this.bigImgUrl=bigImgUrl;
    }
    public String getSmallImgUrl(){
        return smallImgUrl;
    }
    public void setSmallImgUrl(String smallImgUrl){
        this.smallImgUrl=smallImgUrl;
    }
    public Date getCreateTime(){
        return createTime;
    }
    public void setCreateTime(Date createTime){
        this.createTime=createTime;
    }
}