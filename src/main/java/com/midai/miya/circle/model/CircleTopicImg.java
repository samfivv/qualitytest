package com.midai.miya.circle.model;

import java.util.Date;
import java.io.Serializable;
public class CircleTopicImg implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 圈子图片Id
     */
    private String circleImgId;
    /**
     * 类型 1话题 2回复
     */
    private Integer circleImgType;
    /**
     * 话题Id或回复Id
     */
    private String circleTopicOrReplyId;
    /**
     * 图片URL
     */
    private String circleImgUrl;
    /**
     * 图片名称
     */
    private String circleImgName;
    /**
     * 创建时间
     */
    private Date createTime;
    private String circleBigImgUrl;
    
    private String circleSmallImgUrl;
    public String getCircleImgId(){
        return circleImgId;
    }
    public void setCircleImgId(String circleImgId){
        this.circleImgId=circleImgId;
    }
    public Integer getCircleImgType(){
        return circleImgType;
    }
    public void setCircleImgType(Integer circleImgType){
        this.circleImgType=circleImgType;
    }
    public String getCircleTopicOrReplyId(){
        return circleTopicOrReplyId;
    }
    public void setCircleTopicOrReplyId(String circleTopicOrReplyId){
        this.circleTopicOrReplyId=circleTopicOrReplyId;
    }
    public String getCircleImgUrl(){
        return circleImgUrl;
    }
    public void setCircleImgUrl(String circleImgUrl){
        this.circleImgUrl=circleImgUrl;
    }
    public String getCircleImgName(){
        return circleImgName;
    }
    public void setCircleImgName(String circleImgName){
        this.circleImgName=circleImgName;
    }
    public Date getCreateTime(){
        return createTime;
    }
    public void setCreateTime(Date createTime){
        this.createTime=createTime;
    }
	public String getCircleBigImgUrl() {
		return circleBigImgUrl;
	}
	public void setCircleBigImgUrl(String circleBigImgUrl) {
		this.circleBigImgUrl = circleBigImgUrl;
	}
	public String getCircleSmallImgUrl() {
		return circleSmallImgUrl;
	}
	public void setCircleSmallImgUrl(String circleSmallImgUrl) {
		this.circleSmallImgUrl = circleSmallImgUrl;
	}
}