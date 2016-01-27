package com.midai.miya.sys.model;

import java.util.Date;

public class SysConfig {
	
	private String sysConfigId;
	private String sysConfigKey;
	private String sysConfigValue;
	private String sysConfigDesc;
	private Date sysConfigCreateTime;
	
	public String getSysConfigId() {
		return sysConfigId;
	}
	public void setSysConfigId(String sysConfigId) {
		this.sysConfigId = sysConfigId;
	}
	public String getSysConfigKey() {
		return sysConfigKey;
	}
	public void setSysConfigKey(String sysConfigKey) {
		this.sysConfigKey = sysConfigKey;
	}
	public String getSysConfigDesc() {
		return sysConfigDesc;
	}
	public void setSysConfigDesc(String sysConfigDesc) {
		this.sysConfigDesc = sysConfigDesc;
	}
	public String getSysConfigValue() {
		return sysConfigValue;
	}
	public void setSysConfigValue(String sysConfigValue) {
		this.sysConfigValue = sysConfigValue;
	}
	public Date getSysConfigCreateTime() {
		return sysConfigCreateTime;
	}
	public void setSysConfigCreateTime(Date sysConfigCreateTime) {
		this.sysConfigCreateTime = sysConfigCreateTime;
	}

}
