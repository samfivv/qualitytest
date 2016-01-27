package com.midai.miya.user.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.Serializable;
public class UserUpgradeApply implements Serializable {
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final long serialVersionUID = 1L;
    /**
     * 用户升级申请
     */
    private String userUpgradeApplyId;
    /**
     * 申请类型 2签约特长人 3资深特长人 4特约特长人
     */
    private Integer userUpgradeApplyType;
    /**
     * 申请人Id
     */
    private String userId;
    /**
     * 申请时间
     */
    private Date createTime;
    /**
     * 1未审核 2审核通过 3审核不通过
     */
    private Integer auditState;
    /**
     * 审核时间
     */
    private Date auditTime;
    /**
     * 审核人Id
     */
    private String operatorId;
    /**
     * 不通过的原因
     */
    private String applyNotPassReason;
    /**
     * 申请人昵称
     */
    private String userNickname;
    /**
     * 申请人手机
     */
    private String userPhone;
    /**
     * 操作人
     */
    private String operatorName;
    
    private String queryTimeBegin;
    private String queryTimeEnd;
    private String createTimeStr;
    private String auditStateStr;
    private String userUpgradeApplyTypeStr;
    private String auditTimeStr;
    
    public String getUserUpgradeApplyId(){
        return userUpgradeApplyId;
    }
    public void setUserUpgradeApplyId(String userUpgradeApplyId){
        this.userUpgradeApplyId=userUpgradeApplyId;
    }
    public Integer getUserUpgradeApplyType(){
        return userUpgradeApplyType;
    }
    public void setUserUpgradeApplyType(Integer userUpgradeApplyType){
        this.userUpgradeApplyType=userUpgradeApplyType;
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
    public Integer getAuditState(){
        return auditState;
    }
    public void setAuditState(Integer auditState){
        this.auditState=auditState;
    }
    public Date getAuditTime(){
        return auditTime;
    }
    public void setAuditTime(Date auditTime){
        this.auditTime=auditTime;
    }
    public String getOperatorId(){
        return operatorId;
    }
    public void setOperatorId(String operatorId){
        this.operatorId=operatorId;
    }
    public String getApplyNotPassReason(){
        return applyNotPassReason;
    }
    public void setApplyNotPassReason(String applyNotPassReason){
        this.applyNotPassReason=applyNotPassReason;
    }
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getQueryTimeBegin() {
		return queryTimeBegin;
	}
	public void setQueryTimeBegin(String queryTimeBegin) {
		this.queryTimeBegin = queryTimeBegin;
	}
	public String getQueryTimeEnd() {
		return queryTimeEnd;
	}
	public void setQueryTimeEnd(String queryTimeEnd) {
		this.queryTimeEnd = queryTimeEnd;
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
	public String getAuditStateStr() {
		String str="";
		if(this.auditState!=null){
			if(this.auditState==1){
				str="未审核";
			}else if(this.auditState==2){
				str="审核通过";
			}else if(this.auditState==3){
				str="审核不通过";
			}
		} 
		return str;
	}
	public void setAuditStateStr(String auditStateStr) {
		this.auditStateStr = auditStateStr;
	}
	public String getUserUpgradeApplyTypeStr() {
		String str="";
		if(this.userUpgradeApplyType!=null){
			if(this.userUpgradeApplyType==2){
				str="签约特长人";
			}else if(this.userUpgradeApplyType==3){
				str="资深特长人";
			}else if(this.userUpgradeApplyType==4){
				str="特约特长人";
			}
		} 
		return str;
	}
	public void setUserUpgradeApplyTypeStr(String userUpgradeApplyTypeStr) {
		this.userUpgradeApplyTypeStr = userUpgradeApplyTypeStr;
	}
	public String getAuditTimeStr() {
		if(this.auditTime!=null){
			return sdf.format(auditTime);
		}else{
			return "";
		}
	}
	public void setAuditTimeStr(String auditTimeStr) {
		this.auditTimeStr = auditTimeStr;
	}
}