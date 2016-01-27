package com.midai.miya.questionnaire.model;

import java.io.Serializable;
import java.util.Date;
public class QuestionnaireRecord implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 问卷调查记录ID
     */
    private String questionnaireRecordId;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 用户IP
     */
    private String userIp;
    /**
     * 开始回答时间
     */
    private Date userBeginTime;
    /**
     * 结束时间
     */
    private Date userEndTime;
    /**
     * 手机号码
     */
    private String userPhone;
    /**
     * 用户姓名
     */
    private String userName;
    public String getQuestionnaireRecordId(){
        return questionnaireRecordId;
    }
    public void setQuestionnaireRecordId(String questionnaireRecordId){
        this.questionnaireRecordId=questionnaireRecordId;
    }
    public String getUserId(){
        return userId;
    }
    public void setUserId(String userId){
        this.userId=userId;
    }
    public String getUserIp(){
        return userIp;
    }
    public void setUserIp(String userIp){
        this.userIp=userIp;
    }
    public Date getUserBeginTime(){
        return userBeginTime;
    }
    public void setUserBeginTime(Date userBeginTime){
        this.userBeginTime=userBeginTime;
    }
    public Date getUserEndTime(){
        return userEndTime;
    }
    public void setUserEndTime(Date userEndTime){
        this.userEndTime=userEndTime;
    }
    public String getUserPhone(){
        return userPhone;
    }
    public void setUserPhone(String userPhone){
        this.userPhone=userPhone;
    }
    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName){
        this.userName=userName;
    }
}