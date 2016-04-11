package com.midai.miya.order.service;

import com.midai.miya.order.model.ApprovalOrder;
import java.util.List;
import com.midai.miya.utils.PageUtil;

public interface ApprovalOrderService {

     List<ApprovalOrder> findByConditions(ApprovalOrder approvalOrder,PageUtil page);

     long findByConditionsCount(ApprovalOrder approvalOrder);

     void save(ApprovalOrder approvalOrder);

     void update(ApprovalOrder approvalOrder);

     void delete(ApprovalOrder approvalOrder);
     
     //////////////////////////////////////////////////////////////
     ApprovalOrder findById(String approvalOrderId);

}