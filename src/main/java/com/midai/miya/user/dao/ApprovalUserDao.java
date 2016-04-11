package com.midai.miya.user.dao;

import com.midai.miya.user.model.ApprovalUser;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.midai.miya.utils.PageUtil;

public interface ApprovalUserDao {

     List<ApprovalUser> findByConditions(@Param("approvalUser")ApprovalUser approvalUser,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("approvalUser")ApprovalUser approvalUser);

     void save(@Param("approvalUser")ApprovalUser approvalUser);

     void update(@Param("approvalUser")ApprovalUser approvalUser);

     void delete(@Param("approvalUser")ApprovalUser approvalUser);
     /////////////////////////////////////////////////////////////////////
     ApprovalUser findById(@Param("approvalUserId")String approvalUserId);
}