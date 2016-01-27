package com.midai.miya.sys.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Category {
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String categoryId;
	private String categoryName;
	private String categoryCretor;
	private String categoryImgUrl;
	private Date categoryCreattime;
	private String categoryCreattimeStr;
	private Integer categoryState;
	private String categoryStateStr;
	private String queryBeginCreateTimeStr;
	private String queryEndCreateTimeStr;
	private String categoryParentId;
	
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryCretor() {
		return categoryCretor;
	}
	public void setCategoryCretor(String categoryCretor) {
		this.categoryCretor = categoryCretor;
	}
	
	 
	public Integer getCategoryState() {
		return categoryState;
	}
	public void setCategoryState(Integer categoryState) {
		this.categoryState = categoryState;
	}
	public String getCategoryImgUrl() {
		return categoryImgUrl;
	}
	public void setCategoryImgUrl(String categoryImgUrl) {
		this.categoryImgUrl = categoryImgUrl;
	}
	public String getQueryBeginCreateTimeStr() {
		return queryBeginCreateTimeStr;
	}
	public void setQueryBeginCreateTimeStr(String queryBeginCreateTimeStr) {
		this.queryBeginCreateTimeStr = queryBeginCreateTimeStr;
	}
	public String getQueryEndCreateTimeStr() {
		return queryEndCreateTimeStr;
	}
	public void setQueryEndCreateTimeStr(String queryEndCreateTimeStr) {
		this.queryEndCreateTimeStr = queryEndCreateTimeStr;
	}
	 
	public void setCategoryCreattime(Date date) {
		this.categoryCreattime = date;
	}
	public String getCategoryStateStr() {
		if(categoryState!=null){
			 if(categoryState==1){
				return "有效";
			}else{
				return "失效";
			}
		}else{
			return "失效";
		}
	}
	public void setCategoryStateStr(String categoryStateStr) {
		this.categoryStateStr = categoryStateStr;
	}
	public String getCategoryCreattimeStr() {
		if(categoryCreattime==null){
			return null;
		}else{
		return sdf.format(categoryCreattime);
		}
	}
	public void setCategoryCreattimeStr(String categoryCreattimeStr) {
		this.categoryCreattimeStr = categoryCreattimeStr;
	}
	public Date getCategoryCreattime() {
		return categoryCreattime;
	}
	public String getCategoryParentId() {
		return categoryParentId;
	}
	public void setCategoryParentId(String categoryParentId) {
		this.categoryParentId = categoryParentId;
	}
	

}
