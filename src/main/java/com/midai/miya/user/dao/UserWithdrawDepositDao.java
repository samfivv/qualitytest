package com.midai.miya.user.dao;

import com.midai.miya.user.model.UserWithdrawDeposit;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.midai.miya.utils.PageUtil;

public interface UserWithdrawDepositDao {

     List<UserWithdrawDeposit> findByConditions(@Param("userWithdrawDeposit")UserWithdrawDeposit userWithdrawDeposit,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("userWithdrawDeposit")UserWithdrawDeposit userWithdrawDeposit);

     void save(@Param("userWithdrawDeposit")UserWithdrawDeposit userWithdrawDeposit);

     void update(@Param("userWithdrawDeposit")UserWithdrawDeposit userWithdrawDeposit);

     void delete(@Param("userWithdrawDeposit")UserWithdrawDeposit userWithdrawDeposit);


   	 UserWithdrawDeposit findUserWithdrawDepositById(@Param("withdrawDepositId")String withdrawDepositId);
}