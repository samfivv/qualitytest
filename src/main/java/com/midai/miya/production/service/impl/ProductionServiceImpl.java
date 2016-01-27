package com.midai.miya.production.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.midai.miya.production.dao.ProductionDao;
import com.midai.miya.production.service.ProductionService;
import com.midai.miya.production.model.Production;
import com.midai.miya.utils.PageUtil;

@Service
public class ProductionServiceImpl implements ProductionService {

     @Autowired
     private ProductionDao productionDao;

     @Override
     public List<Production> findByConditions(Production production,PageUtil page) {
        List<Production> lists=productionDao.findByConditions(production,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(Production production) {
        long count=productionDao.findByConditionsCount(production);
        return count;
     }

     @Override
     public void save(Production production) {
        productionDao.save(production);
     }

     @Override
     public void update(Production production) {
        productionDao.update(production);
     }

     @Override
     public void delete(Production production) {
        productionDao.delete(production);
     }
}

