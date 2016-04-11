package com.midai.miya.order.dao;

import com.midai.miya.order.model.ApprovalOrder;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.midai.miya.utils.PageUtil;

public interface ApprovalOrderDao {

     List<ApprovalOrder> findByConditions(@Param("approvalOrder")ApprovalOrder approvalOrder,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("approvalOrder")ApprovalOrder approvalOrder);

     void save(@Param("approvalOrder")ApprovalOrder approvalOrder);

     void update(@Param("approvalOrder")ApprovalOrder approvalOrder);

     void delete(@Param("approvalOrder")ApprovalOrder approvalOrder);

     //////////////////////////////////////////////////////////////////////
     ApprovalOrder findById(@Param("approvalOrderId")String approvalOrderId);
}