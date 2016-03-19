package com.midai.miya.user.dao;

import com.midai.miya.user.model.UserAccount;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.midai.miya.utils.PageUtil;

public interface UserAccountDao {

     List<UserAccount> findByConditions(@Param("userAccount")UserAccount userAccount,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("userAccount")UserAccount userAccount);

     void save(@Param("userAccount")UserAccount userAccount);

     void update(@Param("userAccount")UserAccount userAccount);

     void delete(@Param("userAccount")UserAccount userAccount);

}