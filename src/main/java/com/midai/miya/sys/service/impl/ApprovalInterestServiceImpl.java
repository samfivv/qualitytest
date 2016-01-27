package com.midai.miya.sys.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.midai.miya.sys.dao.ApprovalInterestDao;
import com.midai.miya.sys.service.ApprovalInterestService;
import com.midai.miya.sys.model.ApprovalInterest;
import com.midai.miya.utils.PageUtil;

@Service
public class ApprovalInterestServiceImpl implements ApprovalInterestService {

     @Autowired
     private ApprovalInterestDao approvalInterestDao;

     @Override
     public List<ApprovalInterest> findByConditions(ApprovalInterest approvalInterest,PageUtil page) {
        List<ApprovalInterest> lists=approvalInterestDao.findByConditions(approvalInterest,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(ApprovalInterest approvalInterest) {
        long count=approvalInterestDao.findByConditionsCount(approvalInterest);
        return count;
     }

     @Override
     public void save(ApprovalInterest approvalInterest) {
        approvalInterestDao.save(approvalInterest);
     }

     @Override
     public void update(ApprovalInterest approvalInterest) {
        approvalInterestDao.update(approvalInterest);
     }

     @Override
     public void delete(ApprovalInterest approvalInterest) {
        approvalInterestDao.delete(approvalInterest);
     }
}

