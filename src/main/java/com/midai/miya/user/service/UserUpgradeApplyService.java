package com.midai.miya.user.service;

import com.midai.miya.user.model.UserUpgradeApply;
import java.util.List;
import com.midai.miya.utils.PageUtil;

public interface UserUpgradeApplyService {

     List<UserUpgradeApply> findByConditions(UserUpgradeApply userUpgradeApply,PageUtil page);

     long findByConditionsCount(UserUpgradeApply userUpgradeApply);

     void save(UserUpgradeApply userUpgradeApply);

     void update(UserUpgradeApply userUpgradeApply);

     void delete(UserUpgradeApply userUpgradeApply);


   	UserUpgradeApply findUpgradeApplyById(String userUpgradeApplyId);
}