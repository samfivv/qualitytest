package com.midai.miya.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midai.miya.user.dao.ApprovalUserDao;
import com.midai.miya.user.service.ApprovalUserService;
import com.midai.miya.user.model.ApprovalUser;
import com.midai.miya.utils.PageUtil;

@Service
public class ApprovalUserServiceImpl implements ApprovalUserService {

     @Autowired
     private ApprovalUserDao approvalUserDao;

     @Override
     public List<ApprovalUser> findByConditions(ApprovalUser approvalUser,PageUtil page) {
        List<ApprovalUser> lists=approvalUserDao.findByConditions(approvalUser,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(ApprovalUser approvalUser) {
        long count=approvalUserDao.findByConditionsCount(approvalUser);
        return count;
     }

     @Override
     public void save(ApprovalUser approvalUser) {
        approvalUserDao.save(approvalUser);
     }

     @Override
     public void update(ApprovalUser approvalUser) {
        approvalUserDao.update(approvalUser);
     }

     @Override
     public void delete(ApprovalUser approvalUser) {
        approvalUserDao.delete(approvalUser);
     }

     ////////////////////////////////////////////////////////////////
	@Override
	public ApprovalUser findById(String approvalUserId) {
		return approvalUserDao.findById(approvalUserId);
	}
}

