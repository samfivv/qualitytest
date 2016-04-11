package com.midai.miya.standard.service;

import com.midai.miya.standard.model.Standard;

import java.util.List;

import com.midai.miya.utils.PageUtil;

public interface StandardService {

     List<Standard> findByConditions(Standard standard,PageUtil page);

     long findByConditionsCount(Standard standard);

     void save(Standard standard);

     void update(Standard standard);

     void delete(Standard standard);
     
     //////////////////////////////////////////////////
     Standard findById(String standardId);
     int findByStandardTitle(String standardTitle,String standardId);

}