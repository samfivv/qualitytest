package com.midai.miya.order.model;

import java.io.Serializable;
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;
    private String orderItemId;
    private String orderId;
    private String itemAddtitionalId;
    private Integer itemType;
    private Double itemPrice;
    
    private String itemUnit;
    private String itemStandard;
    private String itemResult;
    private String itemConclusion;
    private String itemNote;
    private String itemName;
    private String itemParentName;
    
    private String addtitionalName;
   
    
    
    public String getOrderItemId(){
        return orderItemId;
    }
    public void setOrderItemId(String orderItemId){
        this.orderItemId=orderItemId;
    }
    public String getOrderId(){
        return orderId;
    }
    public void setOrderId(String orderId){
        this.orderId=orderId;
    }
    public String getItemAddtitionalId(){
        return itemAddtitionalId;
    }
    public void setItemAddtitionalId(String itemAddtitionalId){
        this.itemAddtitionalId=itemAddtitionalId;
    }
    public Integer getItemType(){
        return itemType;
    }
    public void setItemType(Integer itemType){
        this.itemType=itemType;
    }
    public Double getItemPrice(){
        return itemPrice;
    }
    public void setItemPrice(Double itemPrice){
        this.itemPrice=itemPrice;
    }
	public String getItemUnit() {
		return itemUnit;
	}
	public void setItemUnit(String itemUnit) {
		this.itemUnit = itemUnit;
	}
	public String getItemStandard() {
		return itemStandard;
	}
	public void setItemStandard(String itemStandard) {
		this.itemStandard = itemStandard;
	}
	public String getItemResult() {
		return itemResult;
	}
	public void setItemResult(String itemResult) {
		this.itemResult = itemResult;
	}
	public String getItemConclusion() {
		return itemConclusion;
	}
	public void setItemConclusion(String itemConclusion) {
		this.itemConclusion = itemConclusion;
	}
	public String getItemNote() {
		return itemNote;
	}
	public void setItemNote(String itemNote) {
		this.itemNote = itemNote;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemParentName() {
		return itemParentName;
	}
	public void setItemParentName(String itemParentName) {
		this.itemParentName = itemParentName;
	}
	public String getAddtitionalName() {
		return addtitionalName;
	}
	public void setAddtitionalName(String addtitionalName) {
		this.addtitionalName = addtitionalName;
	}
    
    
}