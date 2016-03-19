package com.midai.miya.standard.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.midai.miya.standard.dao.StandardDao;
import com.midai.miya.standard.service.StandardService;
import com.midai.miya.standard.model.Standard;
import com.midai.miya.utils.PageUtil;

@Service
public class StandardServiceImpl implements StandardService {

     @Autowired
     private StandardDao standardDao;

     @Override
     public List<Standard> findByConditions(Standard standard,PageUtil page) {
        List<Standard> lists=standardDao.findByConditions(standard,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(Standard standard) {
        long count=standardDao.findByConditionsCount(standard);
        return count;
     }

     @Override
     public void save(Standard standard) {
        standardDao.save(standard);
     }

     @Override
     public void update(Standard standard) {
        standardDao.update(standard);
     }

     @Override
     public void delete(Standard standard) {
        standardDao.delete(standard);
     }
}

