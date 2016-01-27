package com.midai.miya.ask.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.Serializable;
public class AskWhistleBlowing implements Serializable {
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final long serialVersionUID = 1L;
    /**
     * 举报Id
     */
    private String whistleBlowing;
    /**
     * 举报人Id
     */
    private String userId;
    /**
     * 举报时间
     */
    private Date createTime;
    /**
     * 举报原因
     */
    private String whistleBlowingContent;
    /**
     * 举报类型 1 问题 2 回复
     */
    private Integer whistleBlowingType;
    /**
     * 问题或者回复Id
     */
    private String questionOrReplyId;
    /**
     * 问题Id
     */
    private String questionId;
    /**
     * 1未读 2已读
     */
    private Integer whistleState;
    
    private String userNickname;
    
    private String questionTitle;
    
    private String replyContent;
    
    private Integer questionState;
    
    private String createTimeStr;
    private String questionStateStr;
    
    public String getWhistleBlowing(){
        return whistleBlowing;
    }
    public void setWhistleBlowing(String whistleBlowing){
        this.whistleBlowing=whistleBlowing;
    }
    public String getUserId(){
        return userId;
    }
    public void setUserId(String userId){
        this.userId=userId;
    }
    public Date getCreateTime(){
        return createTime;
    }
    public void setCreateTime(Date createTime){
        this.createTime=createTime;
    }
    public String getWhistleBlowingContent(){
        return whistleBlowingContent;
    }
    public void setWhistleBlowingContent(String whistleBlowingContent){
        this.whistleBlowingContent=whistleBlowingContent;
    }
    public Integer getWhistleBlowingType(){
        return whistleBlowingType;
    }
    public void setWhistleBlowingType(Integer whistleBlowingType){
        this.whistleBlowingType=whistleBlowingType;
    }
    public String getQuestionOrReplyId(){
        return questionOrReplyId;
    }
    public void setQuestionOrReplyId(String questionOrReplyId){
        this.questionOrReplyId=questionOrReplyId;
    }
    public String getQuestionId(){
        return questionId;
    }
    public void setQuestionId(String questionId){
        this.questionId=questionId;
    }
    public Integer getWhistleState(){
        return whistleState;
    }
    public void setWhistleState(Integer whistleState){
        this.whistleState=whistleState;
    }
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	public String getQuestionTitle() {
		return questionTitle;
	}
	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public Integer getQuestionState() {
		return questionState;
	}
	public void setQuestionState(Integer questionState) {
		this.questionState = questionState;
	}
	public String getCreateTimeStr() {
		if(this.createTime!=null){
			return sdf.format(createTime);
		}else{
			return "";
		}
	}
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}
	public String getQuestionStateStr() {
		String str="";
		if(this.questionState!=null){
			if(this.questionState==1){
				str="通过";
			}else if(this.questionState==2){
				str="不通过";
			} 
		}
		return str;
	}
	public void setQuestionStateStr(String questionStateStr) {
		this.questionStateStr = questionStateStr;
	}
}