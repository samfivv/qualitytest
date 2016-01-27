package com.midai.miya.user.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midai.miya.sys.dao.VideoDao;
import com.midai.miya.sys.model.Message;
import com.midai.miya.user.dao.UserBillChargebackDao;
import com.midai.miya.user.dao.UserMoneyLogDao;
import com.midai.miya.user.dao.UserMoneyRmbLogDao;
import com.midai.miya.user.dao.UserOftenChangeInfoDao;
import com.midai.miya.user.service.UserBillChargebackService;
import com.midai.miya.user.service.UserOftenChangeInfoService;
import com.midai.miya.user.model.UserBillChargeback;
import com.midai.miya.user.model.UserMoneyLog;
import com.midai.miya.user.model.UserMoneyRmbLog;
import com.midai.miya.user.model.UserOftenChangeInfo;
import com.midai.miya.utils.NumberUtil;
import com.midai.miya.utils.PageUtil;
import com.midai.miya.utils.UUIDUtil;

@Service
public class UserBillChargebackServiceImpl implements UserBillChargebackService {

     @Autowired
     private UserBillChargebackDao userBillChargebackDao;
     @Autowired
     private VideoDao videoDao;
     @Autowired
     private UserMoneyLogDao userMoneyLogDao;
     @Autowired
     private UserMoneyRmbLogDao userMoneyRmbLogDao;
     @Autowired
     private UserOftenChangeInfoDao userOftenChangeInfoDao;
     @Override
     public List<UserBillChargeback> findByConditions(UserBillChargeback userBillChargeback,PageUtil page) {
    	 if(userBillChargeback.getBillNum()!=null){
    		 userBillChargeback.setBillNum(userBillChargeback.getBillNum().replace(" ", ""));
    	 }
    	 if(userBillChargeback.getFromPhoneNumber()!=null){
    		 userBillChargeback.setFromPhoneNumber(userBillChargeback.getFromPhoneNumber().replace(" ", ""));
    	 }
        List<UserBillChargeback> lists=userBillChargebackDao.findByConditions(userBillChargeback,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(UserBillChargeback userBillChargeback) {
    	 if(userBillChargeback.getBillNum()!=null){
    		 userBillChargeback.setBillNum(userBillChargeback.getBillNum().replace(" ", ""));
    	 }
    	 if(userBillChargeback.getFromPhoneNumber()!=null){
    		 userBillChargeback.setFromPhoneNumber(userBillChargeback.getFromPhoneNumber().replace(" ", ""));
    	 }
        long count=userBillChargebackDao.findByConditionsCount(userBillChargeback);
        return count;
     }

     @Override
     public void save(UserBillChargeback userBillChargeback) {
        userBillChargebackDao.save(userBillChargeback);
     }

     @Override
     public void update(UserBillChargeback userBillChargeback) {
    	 Message message=new Message();
		 message.setMessageId(UUIDUtil.getUUID());
		 message.setMessageUserId(userBillChargeback.getUserId());
		 message.setMessageType(0);
		 message.setMessageCreateTime(new Date());
		 message.setMessageState(0);
    	 if(userBillChargeback.getUserBillChargebackState()==3){
    	 message.setMessageContent("很遗憾，订单号为："+userBillChargeback.getBillNum()+"的退款申请没有通过！原因是："+userBillChargeback.getNotPassReason());
    	 }else{
    		 UserOftenChangeInfo userOftenChangeIfo=userOftenChangeInfoDao.findById(userBillChargeback.getUserId());
    		 userOftenChangeIfo.setUserId(userBillChargeback.getUserId());
    		 if(userBillChargeback.getBillPayMoney()!=null&&userBillChargeback.getBillPayMoney()>0){
        		 UserMoneyLog userMoneyLog=new UserMoneyLog();
        		 userMoneyLog.setUserMoneyLogId(UUIDUtil.getUUID());
        		 userMoneyLog.setLogType(1);
        		 userMoneyLog.setCreateTime(new Date());
        		 userMoneyLog.setLogDesc("来自"+userBillChargeback.getBillNum()+"的退款");
        		 userMoneyLog.setLogMoney(userBillChargeback.getBillPayMoney());
        		 userMoneyLogDao.save(userMoneyLog);
        		 if(userOftenChangeIfo.getUserMoney()!=null){
        			 userOftenChangeIfo.setUserMoney(NumberUtil.add(userOftenChangeIfo.getUserMoney(), userBillChargeback.getBillPayMoney()));
        		 }else{
        			 userOftenChangeIfo.setUserMoney(userBillChargeback.getBillPayMoney());
        		 }
        	 }
    		 if(userBillChargeback.getBillPayMoneyRmb()!=null&&userBillChargeback.getBillPayMoneyRmb()>0){
        		 UserMoneyRmbLog userMoneyRmbLog=new UserMoneyRmbLog();
        		 userMoneyRmbLog.setUserMoneyRmbLog(UUIDUtil.getUUID());
        		 userMoneyRmbLog.setCreateTime(new Date());
        		 userMoneyRmbLog.setLogMoneyRmb(userBillChargeback.getBillPayMoneyRmb());
        		 userMoneyRmbLog.setLogType(1);
        		 userMoneyRmbLog.setLogDesc("来自"+userBillChargeback.getBillNum()+"的退款");
        		 userMoneyRmbLogDao.save(userMoneyRmbLog);
        		 if(userOftenChangeIfo.getUserMoneyRmb()!=null){
        			 userOftenChangeIfo.setUserMoneyRmb(NumberUtil.add(userOftenChangeIfo.getUserMoneyRmb(), userBillChargeback.getBillPayMoneyRmb()));
        		 }else{
        			 userOftenChangeIfo.setUserMoneyRmb(userBillChargeback.getBillPayMoneyRmb());
        		 }
        	 }
    		 userOftenChangeInfoDao.update(userOftenChangeIfo);
    		message.setMessageContent("恭喜你，订单号为："+userBillChargeback.getBillNum()+"退款申请已通过！");
    		userBillChargeback.setNotPassReason("");
    	 } 
    	 videoDao.addMessage(message);
    	 userBillChargeback.setApprovalTime(new Date());
        userBillChargebackDao.update(userBillChargeback);
     }

     @Override
     public void delete(UserBillChargeback userBillChargeback) {
        userBillChargebackDao.delete(userBillChargeback);
     }

     @Override
     public UserBillChargeback findBillChargebackByUserId(String userBillChargebackId){
        UserBillChargeback UserBillChargeback = userBillChargebackDao.findBillChargebackByUserId(userBillChargebackId);
        return UserBillChargeback;
     }
}