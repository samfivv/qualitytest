package com.midai.miya.item.model;

import java.util.Date;
import java.io.Serializable;
public class ItemType implements Serializable {

    private static final long serialVersionUID = 1L;
    private String typeId;
    private String typeName;
    private Date createTime;
    public String getTypeId(){
        return typeId;
    }
    public void setTypeId(String typeId){
        this.typeId=typeId;
    }
    public String getTypeName(){
        return typeName;
    }
    public void setTypeName(String typeName){
        this.typeName=typeName;
    }
    public Date getCreateTime(){
        return createTime;
    }
    public void setCreateTime(Date createTime){
        this.createTime=createTime;
    }
}