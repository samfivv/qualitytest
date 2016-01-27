package com.midai.miya.user.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midai.miya.user.dao.UserMoneyRmbLogDao;
import com.midai.miya.user.dao.UserOftenChangeInfoDao;
import com.midai.miya.user.dao.UserWithdrawDepositDao;
import com.midai.miya.user.model.UserMoneyRmbLog;
import com.midai.miya.user.model.UserOftenChangeInfo;
import com.midai.miya.user.model.UserWithdrawDeposit;
import com.midai.miya.user.service.UserWithdrawDepositService;
import com.midai.miya.utils.NumberUtil;
import com.midai.miya.utils.PageUtil;
import com.midai.miya.utils.UUIDUtil;

@Service
public class UserWithdrawDepositServiceImpl implements UserWithdrawDepositService {

     @Autowired
     private UserWithdrawDepositDao userWithdrawDepositDao;
     
     @Autowired
 	private UserMoneyRmbLogDao userMoneyRmbLogDao;
 	@Autowired
 	private UserOftenChangeInfoDao userOftenChangeInfoDao;

     @Override
     public List<UserWithdrawDeposit> findByConditions(UserWithdrawDeposit userWithdrawDeposit,PageUtil page) {
        List<UserWithdrawDeposit> lists=userWithdrawDepositDao.findByConditions(userWithdrawDeposit,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(UserWithdrawDeposit userWithdrawDeposit) {
        long count=userWithdrawDepositDao.findByConditionsCount(userWithdrawDeposit);
        return count;
     }

     @Override
     public void save(UserWithdrawDeposit userWithdrawDeposit) {
        userWithdrawDepositDao.save(userWithdrawDeposit);
     }

     @Override
     public void update(UserWithdrawDeposit userWithdrawDeposit) {
    	 UserWithdrawDeposit userWithdrawDeposit1=userWithdrawDepositDao.findUserWithdrawDepositById(userWithdrawDeposit.getWithdrawDepositId());
 		UserOftenChangeInfo userOftenChangeInfo=userOftenChangeInfoDao.findById(userWithdrawDeposit.getUserId());
 		if(userWithdrawDeposit.getDealState()==3){
 			userOftenChangeInfo.setUserMoneyRmb(NumberUtil.add(Double.valueOf(userOftenChangeInfo.getUserMoneyRmb()), Double.valueOf(userWithdrawDeposit.getMoney())));
 			userOftenChangeInfoDao.update(userOftenChangeInfo);
 			UserMoneyRmbLog userMoneyRmbLog=new UserMoneyRmbLog();
 			userMoneyRmbLog.setUserMoneyRmbLog(UUIDUtil.getUUID());
 			userMoneyRmbLog.setCreateTime(new Date());
 			userMoneyRmbLog.setLogMoneyRmb(userWithdrawDeposit.getMoney());
 			userMoneyRmbLog.setLogType(1);
 			userMoneyRmbLog.setUserId(userWithdrawDeposit.getUserId());
 			userMoneyRmbLog.setLogDesc("驳回申请，钱已退回余额");
 			userMoneyRmbLog.setUserBalance(userOftenChangeInfo.getUserMoneyRmb());
 			userMoneyRmbLogDao.save(userMoneyRmbLog);
 			userWithdrawDeposit.setUserBalance(NumberUtil.add(userWithdrawDeposit1.getUserBalance(), userWithdrawDeposit.getMoney()));
 		} 
        userWithdrawDepositDao.update(userWithdrawDeposit);
     }

     @Override
     public void delete(UserWithdrawDeposit userWithdrawDeposit) {
        userWithdrawDepositDao.delete(userWithdrawDeposit);
     }

     @Override
     public UserWithdrawDeposit findUserWithdrawDepositById(String withdrawDepositId){
        UserWithdrawDeposit UserWithdrawDeposit = userWithdrawDepositDao.findUserWithdrawDepositById(withdrawDepositId);
        return UserWithdrawDeposit;
     }
}