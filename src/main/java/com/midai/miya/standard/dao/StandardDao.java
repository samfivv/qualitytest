package com.midai.miya.standard.dao;

import com.midai.miya.standard.model.Standard;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.midai.miya.utils.PageUtil;

public interface StandardDao {

     List<Standard> findByConditions(@Param("standard")Standard standard,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("standard")Standard standard);

     void save(@Param("standard")Standard standard);

     void update(@Param("standard")Standard standard);

     void delete(@Param("standard")Standard standard);
     
     /////////////////////////////////////////////////
     Standard findById(@Param("standardId")String standardId);
     int findByStandardTitle(@Param("standardTitle")String standardTitle,@Param("standardId")String standardId);

}