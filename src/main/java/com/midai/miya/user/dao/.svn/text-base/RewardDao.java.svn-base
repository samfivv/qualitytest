package com.midai.miya.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.midai.miya.user.model.Reward;
import com.midai.miya.utils.PageUtil;

public interface RewardDao {

     List<Reward> findByConditions(@Param("reward")Reward reward,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("reward")Reward reward);

     void save(@Param("reward")Reward reward);

     void update(@Param("reward")Reward reward);

     void delete(@Param("reward")Reward reward);

}