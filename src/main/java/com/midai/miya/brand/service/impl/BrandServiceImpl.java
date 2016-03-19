package com.midai.miya.brand.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.midai.miya.brand.dao.BrandDao;
import com.midai.miya.brand.service.BrandService;
import com.midai.miya.brand.model.Brand;
import com.midai.miya.utils.PageUtil;

@Service
public class BrandServiceImpl implements BrandService {

     @Autowired
     private BrandDao brandDao;

     @Override
     public List<Brand> findByConditions(Brand brand,PageUtil page) {
        List<Brand> lists=brandDao.findByConditions(brand,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(Brand brand) {
        long count=brandDao.findByConditionsCount(brand);
        return count;
     }

     @Override
     public void save(Brand brand) {
        brandDao.save(brand);
     }

     @Override
     public void update(Brand brand) {
        brandDao.update(brand);
     }

     @Override
     public void delete(Brand brand) {
        brandDao.delete(brand);
     }
}

