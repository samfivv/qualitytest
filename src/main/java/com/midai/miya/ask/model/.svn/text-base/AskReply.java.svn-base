package com.midai.miya.ask.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.Serializable;
public class AskReply implements Serializable {
SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final long serialVersionUID = 1L;
    /**
     * 回复Id
     */
    private String replyId;
    /**
     * 回复的问题Id
     */
    private String questionId;
    /**
     * 回复内容
     */
    private String replyContent;
    /**
     * 回复时间
     */
    private Date replyCreateTime;
    private String replyCreateTimeStr;
    /**
     * 回复状态 1 通过 2不通过 默认1
     */
    private Integer replyState;
    private String replyStateStr;
    /**
     * 回复人Id
     */
    private String replyFromUserId;
    /**
     * 被回复人的Id
     */
    private String replyToUserId;
    /**
     * 被回复人昵称
     */
    private String replyToNickname;
    /**
     * 回复人昵称
     */
    private String replyFromNickname;
    /**
     * 父Id 如果回复的是问题则为0
     */
    private String replyPid;
    /**
     * 赞数量
     */
    private Integer replyPraiseCount;
    /**
     * 踩数量
     */
    private Integer replyTrampleCount;
    private String questionBeginTime;
    private String questionEndTime;
    private String questionTitle;
    public String getReplyId(){
        return replyId;
    }
    public void setReplyId(String replyId){
        this.replyId=replyId;
    }
    public String getQuestionId(){
        return questionId;
    }
    public void setQuestionId(String questionId){
        this.questionId=questionId;
    }
    public String getReplyContent(){
        return replyContent;
    }
    public void setReplyContent(String replyContent){
        this.replyContent=replyContent;
    }
    public Date getReplyCreateTime(){
        return replyCreateTime;
    }
    public void setReplyCreateTime(Date replyCreateTime){
        this.replyCreateTime=replyCreateTime;
    }
    public Integer getReplyState(){
        return replyState;
    }
    public void setReplyState(Integer replyState){
        this.replyState=replyState;
    }
    public String getReplyFromUserId(){
        return replyFromUserId;
    }
    public void setReplyFromUserId(String replyFromUserId){
        this.replyFromUserId=replyFromUserId;
    }
    public String getReplyToUserId(){
        return replyToUserId;
    }
    public void setReplyToUserId(String replyToUserId){
        this.replyToUserId=replyToUserId;
    }
    public String getReplyToNickname(){
        return replyToNickname;
    }
    public void setReplyToNickname(String replyToNickname){
        this.replyToNickname=replyToNickname;
    }
    public String getReplyFromNickname(){
        return replyFromNickname;
    }
    public void setReplyFromNickname(String replyFromNickname){
        this.replyFromNickname=replyFromNickname;
    }
    public String getReplyPid(){
        return replyPid;
    }
    public void setReplyPid(String replyPid){
        this.replyPid=replyPid;
    }
    public Integer getReplyPraiseCount(){
        return replyPraiseCount;
    }
    public void setReplyPraiseCount(Integer replyPraiseCount){
        this.replyPraiseCount=replyPraiseCount;
    }
    public Integer getReplyTrampleCount(){
        return replyTrampleCount;
    }
    public void setReplyTrampleCount(Integer replyTrampleCount){
        this.replyTrampleCount=replyTrampleCount;
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
	public String getReplyCreateTimeStr() {
		String replyCreateTimeStr="";
		if(replyCreateTime!=null){
			replyCreateTimeStr=sdf.format(replyCreateTime);
		}
		return replyCreateTimeStr;
	}
	public void setReplyCreateTimeStr(String replyCreateTimeStr) {
		this.replyCreateTimeStr = replyCreateTimeStr;
	}
	public String getReplyStateStr() {
		String replyStateStr="";
		if(replyState!=null){
			if(replyState==1){
				replyStateStr="通过";
			}else if(replyState==2){
				replyStateStr="不通过";
			}
		}
		return replyStateStr;
	}
	public void setReplyStateStr(String replyStateStr) {
		this.replyStateStr = replyStateStr;
	}
	public String getQuestionTitle() {
		return questionTitle;
	}
	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
}