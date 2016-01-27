package com.midai.miya.sys.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Search implements Serializable {
 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH：mm:ss");
	private static final long serialVersionUID = 150202519135443203L;
	private String searchId;
	private String searchContent;
	private String userId;
	private Date searchTime;
	private String searchTimeStr;
	private String queryBeginTimeStr;
	private String queryEndTimeStr;
	private String searchResultCount;
	private String userNickname;
	public String getSearchId() {
		return searchId;
	}
	public void setSearchId(String searchId) {
		this.searchId = searchId;
	}
	public String getSearchContent() {
		return searchContent;
	}
	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getSearchTime() {
		return searchTime;
	}
	public void setSearchTime(Date searchTime) {
		this.searchTime = searchTime;
	}
	public String getSearchResultCount() {
		return searchResultCount;
	}
	public void setSearchResultCount(String searchResultCount) {
		this.searchResultCount = searchResultCount;
	}
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	public String getQueryBeginTimeStr() {
		return queryBeginTimeStr;
	}
	public void setQueryBeginTimeStr(String queryBeginTimeStr) {
		this.queryBeginTimeStr = queryBeginTimeStr;
	}
	public String getQueryEndTimeStr() {
		return queryEndTimeStr;
	}
	public void setQueryEndTimeStr(String queryEndTimeStr) {
		this.queryEndTimeStr = queryEndTimeStr;
	}
	public String getSearchTimeStr() {
		if(searchTime!=null){
			return sdf.format(searchTime);
		}else{
			return null;
		}
	}
	public void setSearchTimeStr(String searchTimeStr) {
		this.searchTimeStr = searchTimeStr;
	}
	
}
