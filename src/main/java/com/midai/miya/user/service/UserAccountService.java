package com.midai.miya.user.service;

import com.midai.miya.user.model.UserAccount;
import java.util.List;
import com.midai.miya.utils.PageUtil;

public interface UserAccountService {

     List<UserAccount> findByConditions(UserAccount userAccount,PageUtil page);

     long findByConditionsCount(UserAccount userAccount);

     void save(UserAccount userAccount);

     void update(UserAccount userAccount);

     void delete(UserAccount userAccount);

}