package com.midai.miya.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midai.miya.user.dao.RewardDao;
import com.midai.miya.user.service.RewardService;
import com.midai.miya.user.model.Reward;
import com.midai.miya.utils.PageUtil;

@Service
public class RewardServiceImpl implements RewardService {

     @Autowired
     private RewardDao rewardDao;

     @Override
     public List<Reward> findByConditions(Reward reward,PageUtil page) {
        List<Reward> lists=rewardDao.findByConditions(reward,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(Reward reward) {
        long count=rewardDao.findByConditionsCount(reward);
        return count;
     }

     @Override
     public void save(Reward reward) {
        rewardDao.save(reward);
     }

     @Override
     public void update(Reward reward) {
        rewardDao.update(reward);
     }

     @Override
     public void delete(Reward reward) {
        rewardDao.delete(reward);
     }
}

