package com.midai.miya.copywriter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midai.miya.copywriter.dao.CopywriterDao;
import com.midai.miya.copywriter.service.CopywriterService;
import com.midai.miya.copywriter.model.Copywriter;
import com.midai.miya.utils.PageUtil;

@Service
public class CopywriterServiceImpl implements CopywriterService {

     @Autowired
     private CopywriterDao copywriterDao;

     @Override
     public List<Copywriter> findByConditions(Copywriter copywriter,PageUtil page) {
        List<Copywriter> lists=copywriterDao.findByConditions(copywriter,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(Copywriter copywriter) {
        long count=copywriterDao.findByConditionsCount(copywriter);
        return count;
     }

     @Override
     public void save(Copywriter copywriter) {
        copywriterDao.save(copywriter);
     }

     @Override
     public void update(Copywriter copywriter) {
        copywriterDao.update(copywriter);
     }

     @Override
     public void delete(Copywriter copywriter) {
        copywriterDao.delete(copywriter);
     }

	@Override
	public long findByName(Copywriter copywriter) {
		long count=copywriterDao.findByName(copywriter);
		return count;
	}

	@Override
	public Copywriter findById(String copywriterId) {
		return copywriterDao.findById(copywriterId);
	}
}

