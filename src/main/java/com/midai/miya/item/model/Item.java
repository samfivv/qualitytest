package com.midai.miya.item.model;

import java.util.Date;
import java.io.Serializable;
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;
    private String itemId;
    private String typeId;
    private String itemName;
    private String parentId;
    private Double itemPrice;
    private Integer itemSort;
    private String itemNote;
    private String itemCreator;
    private Date createTime;
    public String getItemId(){
        return itemId;
    }
    public void setItemId(String itemId){
        this.itemId=itemId;
    }
    public String getTypeId(){
        return typeId;
    }
    public void setTypeId(String typeId){
        this.typeId=typeId;
    }
    public String getItemName(){
        return itemName;
    }
    public void setItemName(String itemName){
        this.itemName=itemName;
    }
    public String getParentId(){
        return parentId;
    }
    public void setParentId(String parentId){
        this.parentId=parentId;
    }
    public Double getItemPrice(){
        return itemPrice;
    }
    public void setItemPrice(Double itemPrice){
        this.itemPrice=itemPrice;
    }
    public Integer getItemSort(){
        return itemSort;
    }
    public void setItemSort(Integer itemSort){
        this.itemSort=itemSort;
    }
    public String getItemNote(){
        return itemNote;
    }
    public void setItemNote(String itemNote){
        this.itemNote=itemNote;
    }
    public String getItemCreator(){
        return itemCreator;
    }
    public void setItemCreator(String itemCreator){
        this.itemCreator=itemCreator;
    }
    public Date getCreateTime(){
        return createTime;
    }
    public void setCreateTime(Date createTime){
        this.createTime=createTime;
    }
}