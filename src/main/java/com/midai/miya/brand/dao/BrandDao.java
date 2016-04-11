package com.midai.miya.brand.dao;

import com.midai.miya.brand.model.Brand;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.midai.miya.utils.PageUtil;

public interface BrandDao {

     List<Brand> findByConditions(@Param("brand")Brand brand,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("brand")Brand brand);

     void save(@Param("brand")Brand brand);

     void update(@Param("brand")Brand brand);

     void delete(@Param("brand")Brand brand);

     ///////////////////////////////////////////////
     Brand findById(@Param("brandId")String brandId);
     int findByBrandTitle(@Param("brand")Brand brand);
}