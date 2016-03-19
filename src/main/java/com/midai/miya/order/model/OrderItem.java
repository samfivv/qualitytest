package com.midai.miya.order.model;

import java.io.Serializable;
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;
    private String orderItemId;
    private String orderId;
    private String itemAddtitionalId;
    private Integer itemType;
    private Double itemPrice;
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
}