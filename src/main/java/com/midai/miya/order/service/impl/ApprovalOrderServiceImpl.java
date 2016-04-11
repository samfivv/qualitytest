package com.midai.miya.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midai.miya.order.dao.ApprovalOrderDao;
import com.midai.miya.order.service.ApprovalOrderService;
import com.midai.miya.order.model.ApprovalOrder;
import com.midai.miya.utils.PageUtil;

@Service
public class ApprovalOrderServiceImpl implements ApprovalOrderService {

     @Autowired
     private ApprovalOrderDao approvalOrderDao;

     @Override
     public List<ApprovalOrder> findByConditions(ApprovalOrder approvalOrder,PageUtil page) {
        List<ApprovalOrder> lists=approvalOrderDao.findByConditions(approvalOrder,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(ApprovalOrder approvalOrder) {
        long count=approvalOrderDao.findByConditionsCount(approvalOrder);
        return count;
     }

     @Override
     public void save(ApprovalOrder approvalOrder) {
        approvalOrderDao.save(approvalOrder);
     }

     @Override
     public void update(ApprovalOrder approvalOrder) {
        approvalOrderDao.update(approvalOrder);
     }

     @Override
     public void delete(ApprovalOrder approvalOrder) {
        approvalOrderDao.delete(approvalOrder);
     }

     //////////////////////////////////////////////////////////////////////////
	@Override
	public ApprovalOrder findById(String approvalOrderId) {
		return approvalOrderDao.findById(approvalOrderId);
	}
}

