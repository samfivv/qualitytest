package com.midai.miya.circle.model;

import java.util.Date;
import java.util.List;
import java.io.Serializable;
public class CircleTopic implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 圈子话题Id
     */
    private String circleTopicId;
    /**
     * 圈子Id
     */
    private String circleId;
    /**
     * 圈子名称
     */
    private String circleName;
    
    /**
     * 标题
     */
    private String circleTopicTitle;
    /**
     * 内容
     */
    private String circleTopicContent;
    /**
     * 创建时间
     */
    private Date circleTopicCreateTime;
    /**
     * 创建人Id
     */
    private String userId;
    /**
     * 顶的数量
     */
    private Integer topCount;
    /**
     * 回复数量
     */
    private Integer replyCount;
    /**
     * 是否置顶 1 否  2 是
     */
    private Integer isTop;
    /**
     * 置顶时间
     */
    private Date toTopTime;
    /**
     * 加精高亮 1否 2加精高亮
     */
    private Integer isHighlight;
    /**
     * 是否显示 1是 2否
     */
    private Integer isShow;
    
    private List<CircleTopicImg> circleTopicImgLists;
    
    private String userNickname;//昵称
    
    private String questionBeginTime;
    
    private String questionEndTime;
    
    public String getCircleTopicId(){
        return circleTopicId;
    }
    public void setCircleTopicId(String circleTopicId){
        this.circleTopicId=circleTopicId;
    }
    public String getCircleTopicTitle(){
        return circleTopicTitle;
    }
    public void setCircleTopicTitle(String circleTopicTitle){
        this.circleTopicTitle=circleTopicTitle;
    }
    public String getCircleTopicContent(){
        return circleTopicContent;
    }
    public void setCircleTopicContent(String circleTopicContent){
        this.circleTopicContent=circleTopicContent;
    }
    public Date getCircleTopicCreateTime(){
        return circleTopicCreateTime;
    }
    public void setCircleTopicCreateTime(Date circleTopicCreateTime){
        this.circleTopicCreateTime=circleTopicCreateTime;
    }
    public String getUserId(){
        return userId;
    }
    public void setUserId(String userId){
        this.userId=userId;
    }
    public Integer getTopCount(){
        return topCount;
    }
    public void setTopCount(Integer topCount){
        this.topCount=topCount;
    }
    public Integer getReplyCount(){
        return replyCount;
    }
    public void setReplyCount(Integer replyCount){
        this.replyCount=replyCount;
    }
    public Integer getIsTop(){
        return isTop;
    }
    public void setIsTop(Integer isTop){
        this.isTop=isTop;
    }
    public Date getToTopTime(){
        return toTopTime;
    }
    public void setToTopTime(Date toTopTime){
        this.toTopTime=toTopTime;
    }
    public Integer getIsHighlight(){
        return isHighlight;
    }
    public void setIsHighlight(Integer isHighlight){
        this.isHighlight=isHighlight;
    }
    public Integer getIsShow(){
        return isShow;
    }
    public void setIsShow(Integer isShow){
        this.isShow=isShow;
    }
	public List<CircleTopicImg> getCircleTopicImgLists() {
		return circleTopicImgLists;
	}
	public void setCircleTopicImgLists(List<CircleTopicImg> circleTopicImgLists) {
		this.circleTopicImgLists = circleTopicImgLists;
	}
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	public String getQuestionBeginTime() {
		return questionBeginTime;
	}
	public void setQuestionBeginTime(String questionBeginTime) {
		this.questionBeginTime = questionBeginTime;
	}
	public String getQuestionEndTime() {
		return questionEndTime;
	}
	public void setQuestionEndTime(String questionEndTime) {
		this.questionEndTime = questionEndTime;
	}
	public String getCircleId() {
		return circleId;
	}
	public void setCircleId(String circleId) {
		this.circleId = circleId;
	}
	public String getCircleName() {
		return circleName;
	}
	public void setCircleName(String circleName) {
		this.circleName = circleName;
	}
}