package com.midai.miya.sys.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log implements Serializable{
	private static final long serialVersionUID = -3057814498290311243L;
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String logId;
	private String logContent;
	private String logCreator;
	private Date logCreateTime;
	private Integer logType;
	private String operatorName;
	private String queryTimeBegin;
	private String queryTimeEnd;
	private String createTimeStr;
	private String logTypeStr;
	
	public String getCreateTimeStr() {
		if(logCreateTime ==null){
			return null;
		}else{
			return sdf.format(logCreateTime);
		}
	}
	public void setCreateTimeStr(String createTimeStr) {
		
		this.createTimeStr = createTimeStr;
	}
	public String getQueryTimeBegin() {
		return queryTimeBegin;
	}
	public String getLogTypeStr() {
		if(logType !=null){
			if(logType==1){
				return"登录";
			}else if(logType==2){
				return"注销";
			}else if(logType==3){
				return"新增";
			}else if(logType==4){
				return"删除";
			}else if(logType==5){
				return"修改";
			}else if(logType==6){
				return"查询";
			}else if(logType==7){
				return"导出";
			}
		}
		return logTypeStr;
	}
	public void setLogTypeStr(String logTypeStr) {
		this.logTypeStr = logTypeStr;
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
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getLogId() {
		return logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}
	public String getLogContent() {
		return logContent;
	}
	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}
	public String getLogCreator() {
		return logCreator;
	}
	public void setLogCreator(String logCreator) {
		this.logCreator = logCreator;
	}
	
	public Integer getLogType() {
		return logType;
	}
	public void setLogType(Integer logType) {
		this.logType = logType;
	}
	public Date getLogCreateTime() {
		return logCreateTime;
	}
	public void setLogCreateTime(Date logCreateTime) {
		this.logCreateTime = logCreateTime;
	}
	
	
}
