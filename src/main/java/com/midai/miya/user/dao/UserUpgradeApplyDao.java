package com.midai.miya.user.dao;

import com.midai.miya.user.model.UserUpgradeApply;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.midai.miya.utils.PageUtil;

public interface UserUpgradeApplyDao {

     List<UserUpgradeApply> findByConditions(@Param("userUpgradeApply")UserUpgradeApply userUpgradeApply,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("userUpgradeApply")UserUpgradeApply userUpgradeApply);

     void save(@Param("userUpgradeApply")UserUpgradeApply userUpgradeApply);

     void update(@Param("userUpgradeApply")UserUpgradeApply userUpgradeApply);

     void delete(@Param("userUpgradeApply")UserUpgradeApply userUpgradeApply);


   	 UserUpgradeApply findUpgradeApplyById(@Param("userUpgradeApplyId")String userUpgradeApplyId);
}