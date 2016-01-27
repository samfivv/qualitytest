package com.midai.miya.sys.dao;

import com.midai.miya.sys.model.ApprovalInterest;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.midai.miya.utils.PageUtil;

public interface ApprovalInterestDao {

     List<ApprovalInterest> findByConditions(@Param("approvalInterest")ApprovalInterest approvalInterest,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("approvalInterest")ApprovalInterest approvalInterest);

     void save(@Param("approvalInterest")ApprovalInterest approvalInterest);

     void update(@Param("approvalInterest")ApprovalInterest approvalInterest);

     void delete(@Param("approvalInterest")ApprovalInterest approvalInterest);

}