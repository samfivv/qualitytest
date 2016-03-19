package com.midai.miya.copywriter.model;

import java.io.Serializable;
public class Copywriter implements Serializable {

    private static final long serialVersionUID = 1L;
    private String copywriterId;
    private String copywriterUrl;
    private String copywriterDesc;
    private String copywriterImgUrl;
    private Integer copywriterSort;
    public String getCopywriterId(){
        return copywriterId;
    }
    public void setCopywriterId(String copywriterId){
        this.copywriterId=copywriterId;
    }
    public String getCopywriterUrl(){
        return copywriterUrl;
    }
    public void setCopywriterUrl(String copywriterUrl){
        this.copywriterUrl=copywriterUrl;
    }
    public String getCopywriterDesc(){
        return copywriterDesc;
    }
    public void setCopywriterDesc(String copywriterDesc){
        this.copywriterDesc=copywriterDesc;
    }
    public String getCopywriterImgUrl(){
        return copywriterImgUrl;
    }
    public void setCopywriterImgUrl(String copywriterImgUrl){
        this.copywriterImgUrl=copywriterImgUrl;
    }
    public Integer getCopywriterSort(){
        return copywriterSort;
    }
    public void setCopywriterSort(Integer copywriterSort){
        this.copywriterSort=copywriterSort;
    }
}