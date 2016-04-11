package com.midai.miya.order.model;

import java.io.Serializable;
public class OrderResultItem implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    private String resultItemId;
    /**
     * 合同ID
     */
    private String resultId;
    /**
     * 合同不ID
     */
    private String orderId;
    /**
     * 项目ID
     */
    private String itemId;
    /**
     * 1检测项目，2附加项目
     */
    private Integer itemType;
    /**
     * 检测费用
     */
    private String itemUnit;
    /**
     * 标准要求
     */
    private String itemStandard;
    /**
     * 实测结果
     */
    private String itemResult;
    /**
     * 单项结论
     */
    private String itemConclusion;
    /**
     * 备注
     */
    private String itemNote;
    public String getResultItemId(){
        return resultItemId;
    }
    public void setResultItemId(String resultItemId){
        this.resultItemId=resultItemId;
    }
    public String getResultId(){
        return resultId;
    }
    public void setResultId(String resultId){
        this.resultId=resultId;
    }
    public String getOrderId(){
        return orderId;
    }
    public void setOrderId(String orderId){
        this.orderId=orderId;
    }
    public String getItemId(){
        return itemId;
    }
    public void setItemId(String itemId){
        this.itemId=itemId;
    }
    public Integer getItemType(){
        return itemType;
    }
    public void setItemType(Integer itemType){
        this.itemType=itemType;
    }
    public String getItemUnit(){
        return itemUnit;
    }
    public void setItemUnit(String itemUnit){
        this.itemUnit=itemUnit;
    }
    public String getItemStandard(){
        return itemStandard;
    }
    public void setItemStandard(String itemStandard){
        this.itemStandard=itemStandard;
    }
    public String getItemResult(){
        return itemResult;
    }
    public void setItemResult(String itemResult){
        this.itemResult=itemResult;
    }
    public String getItemConclusion(){
        return itemConclusion;
    }
    public void setItemConclusion(String itemConclusion){
        this.itemConclusion=itemConclusion;
    }
    public String getItemNote(){
        return itemNote;
    }
    public void setItemNote(String itemNote){
        this.itemNote=itemNote;
    }
}