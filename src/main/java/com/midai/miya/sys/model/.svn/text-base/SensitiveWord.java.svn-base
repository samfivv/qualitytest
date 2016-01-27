package com.midai.miya.sys.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SensitiveWord implements Serializable {
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final long serialVersionUID = 3028736885180464958L;
	private String sensitiveWordId;
	private String sensitiveWord;
	private Date sensitiveWordCreateTime;
	private String sensitiveWordCreateTimeStr;
	public String getSensitiveWordId() {
		return sensitiveWordId;
	}
	public void setSensitiveWordId(String sensitiveWordId) {
		this.sensitiveWordId = sensitiveWordId;
	}
	public String getSensitiveWord() {
		return sensitiveWord;
	}
	public void setSensitiveWord(String sensitiveWord) {
		this.sensitiveWord = sensitiveWord;
	}
	public Date getSensitiveWordCreateTime() {
		return sensitiveWordCreateTime;
	}
	public void setSensitiveWordCreateTime(Date sensitiveWordCreateTime) {
		this.sensitiveWordCreateTime = sensitiveWordCreateTime;
	}
	public String getSensitiveWordCreateTimeStr() {
		if(sensitiveWordCreateTime==null){
			return null;
		}else{
		return sdf.format(sensitiveWordCreateTime);
		}
	}
	public void setSensitiveWordCreateTimeStr(String sensitiveWordCreateTimeStr) {
		this.sensitiveWordCreateTimeStr = sensitiveWordCreateTimeStr;
	}
}
