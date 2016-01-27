package com.midai.miya.production.service;

import com.midai.miya.production.model.ProductionBoutique;
import java.util.List;
import com.midai.miya.utils.PageUtil;

public interface ProductionBoutiqueService {

     List<ProductionBoutique> findByConditions(ProductionBoutique productionBoutique,PageUtil page);

     long findByConditionsCount(ProductionBoutique productionBoutique);

     void save(ProductionBoutique productionBoutique);

     void update(ProductionBoutique productionBoutique);

     void delete(ProductionBoutique productionBoutique);


   	ProductionBoutique findProductionBoutiqueByproductionId(String productionBoutiqueId);

   	int findProductionBoutiqueCountByproductionId(ProductionBoutique productionBoutique);
}