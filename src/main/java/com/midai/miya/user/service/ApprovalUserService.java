package com.midai.miya.user.service;

import com.midai.miya.user.model.ApprovalUser;
import java.util.List;
import com.midai.miya.utils.PageUtil;

public interface ApprovalUserService {

     List<ApprovalUser> findByConditions(ApprovalUser approvalUser,PageUtil page);

     long findByConditionsCount(ApprovalUser approvalUser);

     void save(ApprovalUser approvalUser);

     void update(ApprovalUser approvalUser);

     void delete(ApprovalUser approvalUser);
     
     ////////////////////////////////////////////////////
     ApprovalUser findById(String approvalUserId);

}