package com.midai.miya.order.model;

import java.io.Serializable;
public class OrderAddtitionalItem implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    private String orderAddtitionalItemId;
    /**
     * 委托单检测项目ID
     */
    private String orderItemId;
    /**
     * 附加项目ID
     */
    private String addtitionalId;
    public String getOrderAddtitionalItemId(){
        return orderAddtitionalItemId;
    }
    public void setOrderAddtitionalItemId(String orderAddtitionalItemId){
        this.orderAddtitionalItemId=orderAddtitionalItemId;
    }
    public String getOrderItemId(){
        return orderItemId;
    }
    public void setOrderItemId(String orderItemId){
        this.orderItemId=orderItemId;
    }
    public String getAddtitionalId(){
        return addtitionalId;
    }
    public void setAddtitionalId(String addtitionalId){
        this.addtitionalId=addtitionalId;
    }
}