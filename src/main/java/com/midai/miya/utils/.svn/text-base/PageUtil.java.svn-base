package com.midai.miya.utils;

import java.io.Serializable;

public class PageUtil implements Serializable{
	private static final long serialVersionUID = 8969385927759455138L;
	private String order;	
	private String sort;
	private int page=1;	
	private int rows=10;
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public String getSort() {
		if("seq".equals(sort)) return null;
		if(sort!=null&&!"".equals(sort)){
			StringBuffer buffer=new StringBuffer();
			for(int i = 0; i < sort.length(); i++)
			  {
			   char c = sort.charAt(i);
			   if (Character.isLowerCase(c))
			   {
				   buffer.append(c);
			   }else{
				   buffer.append("_"+c);
			   }
			  }
			return buffer.toString();
		}
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}	
}
