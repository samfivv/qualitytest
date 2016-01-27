package com.midai.miya.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midai.miya.sys.dao.RecommendUserDao;
import com.midai.miya.sys.service.RecommendUserService;
import com.midai.miya.sys.model.RecommendUser;
import com.midai.miya.utils.PageUtil;

@Service
public class RecommendUserServiceImpl implements RecommendUserService {

     @Autowired
     private RecommendUserDao recommendUserDao;

     @Override
     public List<RecommendUser> findByConditions(RecommendUser recommendUser,PageUtil page) {
        List<RecommendUser> lists=recommendUserDao.findByConditions(recommendUser,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(RecommendUser recommendUser) {
        long count=recommendUserDao.findByConditionsCount(recommendUser);
        return count;
     }

     @Override
     public void save(RecommendUser recommendUser) {
        recommendUserDao.save(recommendUser);
     }

     @Override
     public void update(RecommendUser recommendUser) {
        recommendUserDao.update(recommendUser);
     }

     @Override
     public void delete(RecommendUser recommendUser) {
        recommendUserDao.delete(recommendUser);
     }

	@Override
	public RecommendUser findById(String recommendUserId) {
		RecommendUser recommendUser=recommendUserDao.findById(recommendUserId);
		return recommendUser;
	}

	@Override
	public long findByUserId(String userId) {
		long count= recommendUserDao.findByUserId(userId);
		return count;
	}
}

