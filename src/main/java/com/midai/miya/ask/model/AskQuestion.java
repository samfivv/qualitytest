package com.midai.miya.ask.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.Serializable;
public class AskQuestion implements Serializable {
SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final long serialVersionUID = 1L;
    /**
     * 问题Id
     */
    private String questionId;
    /**
     * 问题标题
     */
    private String questionTitle;
    /**
     * 问题描述
     */
    private String questionDesc;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 状态  1通过 2不通过 3删除 默认1
     */
    private Integer questionState;
    private String questionStateStr;
    /**
     * 小类别Id
     */
    private String categoryId;
    /**
     * 提问时间
     */
    private Date questionCreateTime;
    private String questionCreateTimeStr;
    /**
     * 问题更新时间
     */
    private Date questionUpdateTime;
    private String questionUpdateTimeStr;
    /**
     * 标签 多个标签用,隔开
     */
    private String questionTag;
    /**
     * 是否已解决 1已解决 2未解决 默认2
     */
    private Integer questionIsSettle;
    private String questionIsSettleStr;
    /**
     * 是否悬赏 1悬赏 2不悬赏
     */
    private Integer questionIsOffer;
    private String questionIsOfferStr;
    /**
     * 悬赏数额
     */
    private Integer questionOfferCount;
    /**
     * 来源 1网页 2 安卓 3 苹果
     */
    private Integer questionFrom;
    private String questionFromStr;
    /**
     * 回复数量，只算首条回复，回复其他回复的不算
     */
    private Integer replyCount;
    /**
     * 浏览次数
     */
    private Integer questionWacthCount;
    /**
     * 推荐数量
     */
    private Integer questionRecommendCount;
    
    private String userNickname;
    private String categoryName;
    private String questionBeginTime;
    private String questionEndTime;
    public String getQuestionId(){
        return questionId;
    }
    public void setQuestionId(String questionId){
        this.questionId=questionId;
    }
    public String getQuestionTitle(){
        return questionTitle;
    }
    public void setQuestionTitle(String questionTitle){
        this.questionTitle=questionTitle;
    }
    public String getQuestionDesc(){
        return questionDesc;
    }
    public void setQuestionDesc(String questionDesc){
        this.questionDesc=questionDesc;
    }
    public String getUserId(){
        return userId;
    }
    public void setUserId(String userId){
        this.userId=userId;
    }
    public Integer getQuestionState(){
        return questionState;
    }
    public void setQuestionState(Integer questionState){
        this.questionState=questionState;
    }
    public String getCategoryId(){
        return categoryId;
    }
    public void setCategoryId(String categoryId){
        this.categoryId=categoryId;
    }
    public Date getQuestionCreateTime(){
        return questionCreateTime;
    }
    public void setQuestionCreateTime(Date questionCreateTime){
        this.questionCreateTime=questionCreateTime;
    }
    public Date getQuestionUpdateTime(){
        return questionUpdateTime;
    }
    public void setQuestionUpdateTime(Date questionUpdateTime){
        this.questionUpdateTime=questionUpdateTime;
    }
    public String getQuestionTag(){
        return questionTag;
    }
    public void setQuestionTag(String questionTag){
        this.questionTag=questionTag;
    }
    public Integer getQuestionIsSettle(){
        return questionIsSettle;
    }
    public void setQuestionIsSettle(Integer questionIsSettle){
        this.questionIsSettle=questionIsSettle;
    }
    public Integer getQuestionIsOffer(){
        return questionIsOffer;
    }
    public void setQuestionIsOffer(Integer questionIsOffer){
        this.questionIsOffer=questionIsOffer;
    }
    public Integer getQuestionOfferCount(){
        return questionOfferCount;
    }
    public void setQuestionOfferCount(Integer questionOfferCount){
        this.questionOfferCount=questionOfferCount;
    }
    public Integer getQuestionFrom(){
        return questionFrom;
    }
    public void setQuestionFrom(Integer questionFrom){
        this.questionFrom=questionFrom;
    }
    public Integer getReplyCount(){
        return replyCount;
    }
    public void setReplyCount(Integer replyCount){
        this.replyCount=replyCount;
    }
    public Integer getQuestionWacthCount(){
        return questionWacthCount;
    }
    public void setQuestionWacthCount(Integer questionWacthCount){
        this.questionWacthCount=questionWacthCount;
    }
    public Integer getQuestionRecommendCount(){
        return questionRecommendCount;
    }
    public void setQuestionRecommendCount(Integer questionRecommendCount){
        this.questionRecommendCount=questionRecommendCount;
    }
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
	public String getQuestionStateStr() {
		String questionStateStr="";
		if(questionState!=null){
			if(questionState==1){
				questionStateStr= "通过";
			}else if(questionState==2){
				questionStateStr= "不通过";
			}else if(questionState==3){
				questionStateStr= "删除";
			}
		} 
		return questionStateStr;
	}
	public void setQuestionStateStr(String questionStateStr) {
		this.questionStateStr = questionStateStr;
	}
	public String getQuestionCreateTimeStr() {
		String questionCreateTimeStr="";
		if(questionCreateTime!=null){
			questionCreateTimeStr=sdf.format(questionCreateTime);
		} 
		return questionCreateTimeStr;
	}
	public void setQuestionCreateTimeStr(String questionCreateTimeStr) {
		this.questionCreateTimeStr = questionCreateTimeStr;
	}
	public String getQuestionUpdateTimeStr() {
		String questionUpdateTimeStr="";
		if(questionUpdateTime!=null){
			questionUpdateTimeStr=sdf.format(questionUpdateTime);
		} 
		return questionUpdateTimeStr;
	}
	public void setQuestionUpdateTimeStr(String questionUpdateTimeStr) {
		this.questionUpdateTimeStr = questionUpdateTimeStr;
	}
	public String getQuestionIsSettleStr() {
		String questionIsSettleStr="";
		if(questionIsSettle!=null){
			if(questionIsSettle==1){
				questionIsSettleStr= "已解决";
			}else if(questionIsSettle==2){
				questionIsSettleStr= "未解决";
			} 
		} 
		return questionIsSettleStr;
	}
	public void setQuestionIsSettleStr(String questionIsSettleStr) {
		this.questionIsSettleStr = questionIsSettleStr;
	}
	public String getQuestionIsOfferStr() {
		String questionIsOfferStr="";
		if(questionIsOffer!=null){
			if(questionIsOffer==1){
				questionIsOfferStr= "悬赏";
			}else if(questionIsOffer==2){
				questionIsOfferStr= "不悬赏";
			} 
		} 
		return questionIsOfferStr;
	}
	public void setQuestionIsOfferStr(String questionIsOfferStr) {
		this.questionIsOfferStr = questionIsOfferStr;
	}
	public String getQuestionFromStr() {
		String questionFromStr="";
		if(questionFrom!=null){
			if(questionIsOffer==1){
				questionFromStr= "网页";
			}else if(questionIsOffer==2){
				questionFromStr= "安卓";
			}else if(questionIsOffer==3){
				questionFromStr= "苹果";
			}  
		} 
		return questionFromStr;
	}
	public void setQuestionFromStr(String questionFromStr) {
		this.questionFromStr = questionFromStr;
	}
}