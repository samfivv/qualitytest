package com.midai.miya.sys.dao;

import com.midai.miya.sys.model.Partner;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.midai.miya.utils.PageUtil;

public interface PartnerDao {

     List<Partner> findByConditions(@Param("partner")Partner partner,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("partner")Partner partner);

     void save(@Param("partner")Partner partner);

     void update(@Param("partner")Partner partner);

     void delete(@Param("partner")Partner partner);
     
     Partner findById(@Param("partnerId")String partnerId);

}