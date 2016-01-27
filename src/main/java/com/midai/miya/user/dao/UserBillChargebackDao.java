package com.midai.miya.user.dao;

import com.midai.miya.user.model.UserBillChargeback;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.midai.miya.utils.PageUtil;

public interface UserBillChargebackDao {

     List<UserBillChargeback> findByConditions(@Param("userBillChargeback")UserBillChargeback userBillChargeback,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("userBillChargeback")UserBillChargeback userBillChargeback);

     void save(@Param("userBillChargeback")UserBillChargeback userBillChargeback);

     void update(@Param("userBillChargeback")UserBillChargeback userBillChargeback);

     void delete(@Param("userBillChargeback")UserBillChargeback userBillChargeback);


   	 UserBillChargeback findBillChargebackByUserId(String userBillChargebackId);
}