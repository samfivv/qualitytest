package com.midai.miya.sys.service;

import com.midai.miya.sys.model.OrganizationApplication;
import java.util.List;
import com.midai.miya.utils.PageUtil;

public interface OrganizationApplicationService {

     List<OrganizationApplication> findByConditions(OrganizationApplication organizationApplication,PageUtil page);

     long findByConditionsCount(OrganizationApplication organizationApplication);

     void save(OrganizationApplication organizationApplication);

     void update(OrganizationApplication organizationApplication);

     void delete(OrganizationApplication organizationApplication);


   	OrganizationApplication findById(String organizationApplicationId);
}