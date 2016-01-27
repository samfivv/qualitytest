package com.midai.miya.sys.dao;

import com.midai.miya.sys.model.OrganizationApplication;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.midai.miya.utils.PageUtil;

public interface OrganizationApplicationDao {

     List<OrganizationApplication> findByConditions(@Param("organizationApplication")OrganizationApplication organizationApplication,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("organizationApplication")OrganizationApplication organizationApplication);

     void save(@Param("organizationApplication")OrganizationApplication organizationApplication);

     void update(@Param("organizationApplication")OrganizationApplication organizationApplication);

     void delete(@Param("organizationApplication")OrganizationApplication organizationApplication);


   	 OrganizationApplication findById(@Param("organizationApplicationId")String organizationApplicationId);
}