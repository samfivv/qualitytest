package com.midai.miya.production.service;

import com.midai.miya.production.model.Production;
import java.util.List;
import com.midai.miya.utils.PageUtil;

public interface ProductionService {

     List<Production> findByConditions(Production production,PageUtil page);

     long findByConditionsCount(Production production);

     void save(Production production);

     void update(Production production);

     void delete(Production production);

}