package com.midai.miya.production.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.midai.miya.production.dao.ProductionBoutiqueDao;
import com.midai.miya.production.service.ProductionBoutiqueService;
import com.midai.miya.production.model.ProductionBoutique;
import com.midai.miya.utils.PageUtil;

@Service
public class ProductionBoutiqueServiceImpl implements ProductionBoutiqueService {

     @Autowired
     private ProductionBoutiqueDao productionBoutiqueDao;

     @Override
     public List<ProductionBoutique> findByConditions(ProductionBoutique productionBoutique,PageUtil page) {
        List<ProductionBoutique> lists=productionBoutiqueDao.findByConditions(productionBoutique,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(ProductionBoutique productionBoutique) {
        long count=productionBoutiqueDao.findByConditionsCount(productionBoutique);
        return count;
     }

     @Override
     public void save(ProductionBoutique productionBoutique) {
        productionBoutiqueDao.save(productionBoutique);
     }

     @Override
     public void update(ProductionBoutique productionBoutique) {
        productionBoutiqueDao.update(productionBoutique);
     }

     @Override
     public void delete(ProductionBoutique productionBoutique) {
        productionBoutiqueDao.delete(productionBoutique);
     }

     @Override
     public ProductionBoutique findProductionBoutiqueByproductionId(String productionBoutiqueId){
        ProductionBoutique ProductionBoutique = productionBoutiqueDao.findProductionBoutiqueByproductionId(productionBoutiqueId);
        return ProductionBoutique;
     }

     @Override
     public int findProductionBoutiqueCountByproductionId(ProductionBoutique productionBoutique){
        int ProductionBoutique = productionBoutiqueDao.findProductionBoutiqueCountByproductionId(productionBoutique);
        return ProductionBoutique;
     }
}