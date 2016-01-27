package com.midai.miya.user.service;

import com.midai.miya.user.model.UserBillChargeback;
import java.util.List;
import com.midai.miya.utils.PageUtil;

public interface UserBillChargebackService {

     List<UserBillChargeback> findByConditions(UserBillChargeback userBillChargeback,PageUtil page);

     long findByConditionsCount(UserBillChargeback userBillChargeback);

     void save(UserBillChargeback userBillChargeback);

     void update(UserBillChargeback userBillChargeback);

     void delete(UserBillChargeback userBillChargeback);


   	UserBillChargeback findBillChargebackByUserId(String userBillChargebackId);
}