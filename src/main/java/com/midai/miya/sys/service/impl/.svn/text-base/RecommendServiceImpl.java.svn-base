package com.midai.miya.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midai.miya.sys.dao.RecommendDao;
import com.midai.miya.sys.service.InterestService;
import com.midai.miya.sys.service.RecommendService;
import com.midai.miya.sys.model.Interest;
import com.midai.miya.sys.model.Recommend;
import com.midai.miya.utils.PageUtil;

@Service
public class RecommendServiceImpl implements RecommendService {

     @Autowired
     private RecommendDao recommendDao;
     @Autowired
     private InterestService interestService;

     @Override
     public List<Recommend> findByConditions(Recommend recommend,PageUtil page) {
        List<Recommend> lists=recommendDao.findByConditions(recommend,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(Recommend recommend) {
        long count=recommendDao.findByConditionsCount(recommend);
        return count;
     }

     @Override
     public void save(Recommend recommend) {
        recommendDao.save(recommend);
     }

     @Override
     public void update(Recommend recommend) {
        recommendDao.update(recommend);
     }

     @Override
     public void delete(Recommend recommend) {
        recommendDao.delete(recommend);
     }

	@Override
	public Recommend findById(String recommendId) {
		Recommend recommend=recommendDao.findById(recommendId);
		return recommend;
	}

	@Override
	public List<Interest> findInterestByConditions(Interest interest, PageUtil page) {
		if(interest.getInterestTitle()!=null){
			interest.setInterestTitle(interest.getInterestTitle().replace(" ", ""));
		}
		if(interest.getInterestDesc()!=null){
			interest.setInterestDesc(interest.getInterestDesc().replace(" ", ""));
		}
		if(interest.getUserNickname()!=null){
			interest.setUserNickname(interest.getUserNickname().replace(" ", ""));
		}
		List<Interest> interests=interestService.findByConditions(interest, page);
		return interests;
	}

	@Override
	public long findInterestByConditionsCount(Interest interest) {
		if(interest.getInterestTitle()!=null){
			interest.setInterestTitle(interest.getInterestTitle().replace(" ", ""));
		}
		if(interest.getInterestDesc()!=null){
			interest.setInterestDesc(interest.getInterestDesc().replace(" ", ""));
		}
		if(interest.getUserNickname()!=null){
			interest.setUserNickname(interest.getUserNickname().replace(" ", ""));
		}
		long count=interestService.findByConditionsCount(interest);
		return count;
	}
	
	@Override
	public Interest findInterestById(String interestId) {
		Interest interest=interestService.findInterestById(interestId);
		return interest;
	}

	@Override
	public long findByInterestId(Recommend recommend) {
		long count=recommendDao.findByInterestId(recommend);
		return count;
	}
}

