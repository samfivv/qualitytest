package com.midai.miya.item.model;

import java.util.Date;
import java.io.Serializable;
public class ItemAddtitional implements Serializable {

    private static final long serialVersionUID = 1L;
    private String addtitionalId;
    private String itemId;
    private String addtitionalName;
    private Double addtitionalPrice;
    private Date createTime;
    
    private String itemName; //关联item的表item_name
    
    public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getAddtitionalId(){
        return addtitionalId;
    }
    public void setAddtitionalId(String addtitionalId){
        this.addtitionalId=addtitionalId;
    }
    public String getItemId(){
        return itemId;
    }
    public void setItemId(String itemId){
        this.itemId=itemId;
    }
    public String getAddtitionalName(){
        return addtitionalName;
    }
    public void setAddtitionalName(String addtitionalName){
        this.addtitionalName=addtitionalName;
    }
    public Double getAddtitionalPrice(){
        return addtitionalPrice;
    }
    public void setAddtitionalPrice(Double addtitionalPrice){
        this.addtitionalPrice=addtitionalPrice;
    }
    public Date getCreateTime(){
        return createTime;
    }
    public void setCreateTime(Date createTime){
        this.createTime=createTime;
    }
}