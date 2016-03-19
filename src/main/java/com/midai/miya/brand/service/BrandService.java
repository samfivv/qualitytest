package com.midai.miya.brand.service;

import com.midai.miya.brand.model.Brand;
import java.util.List;
import com.midai.miya.utils.PageUtil;

public interface BrandService {

     List<Brand> findByConditions(Brand brand,PageUtil page);

     long findByConditionsCount(Brand brand);

     void save(Brand brand);

     void update(Brand brand);

     void delete(Brand brand);

}