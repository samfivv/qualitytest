package com.midai.miya.production.dao;

import com.midai.miya.production.model.Production;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.midai.miya.utils.PageUtil;

public interface ProductionDao {

     List<Production> findByConditions(@Param("production")Production production,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("production")Production production);

     void save(@Param("production")Production production);

     void update(@Param("production")Production production);

     void delete(@Param("production")Production production);

}