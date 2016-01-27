package com.midai.miya.sys.service;

import com.midai.miya.sys.model.ApprovalInterest;
import java.util.List;
import com.midai.miya.utils.PageUtil;

public interface ApprovalInterestService {

     List<ApprovalInterest> findByConditions(ApprovalInterest approvalInterest,PageUtil page);

     long findByConditionsCount(ApprovalInterest approvalInterest);

     void save(ApprovalInterest approvalInterest);

     void update(ApprovalInterest approvalInterest);

     void delete(ApprovalInterest approvalInterest);

}