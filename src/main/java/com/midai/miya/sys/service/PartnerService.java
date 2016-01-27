package com.midai.miya.sys.service;

import com.midai.miya.sys.model.Partner;

import java.util.List;

import com.midai.miya.utils.PageUtil;

public interface PartnerService {

     List<Partner> findByConditions(Partner partner,PageUtil page);

     long findByConditionsCount(Partner partner);

     void save(Partner partner);

     void update(Partner partner);

     void delete(Partner partner);
     
     Partner findById(String partnerId);

}