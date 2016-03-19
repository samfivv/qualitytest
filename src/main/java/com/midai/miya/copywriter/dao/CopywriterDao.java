package com.midai.miya.copywriter.dao;

import com.midai.miya.copywriter.model.Copywriter;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.midai.miya.utils.PageUtil;

public interface CopywriterDao {

     List<Copywriter> findByConditions(@Param("copywriter")Copywriter copywriter,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("copywriter")Copywriter copywriter);

     void save(@Param("copywriter")Copywriter copywriter);

     void update(@Param("copywriter")Copywriter copywriter);

     void delete(@Param("copywriter")Copywriter copywriter);

     long findByName(@Param("copywriter")Copywriter copywriter);
     
     Copywriter findById(@Param("copywriterId")String copywriterId);
}