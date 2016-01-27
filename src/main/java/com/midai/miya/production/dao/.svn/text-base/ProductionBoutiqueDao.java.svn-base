package com.midai.miya.production.dao;

import com.midai.miya.production.model.ProductionBoutique;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.midai.miya.utils.PageUtil;

public interface ProductionBoutiqueDao {

     List<ProductionBoutique> findByConditions(@Param("productionBoutique")ProductionBoutique productionBoutique,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("productionBoutique")ProductionBoutique productionBoutique);

     void save(@Param("productionBoutique")ProductionBoutique productionBoutique);

     void update(@Param("productionBoutique")ProductionBoutique productionBoutique);

     void delete(@Param("productionBoutique")ProductionBoutique productionBoutique);


   	 ProductionBoutique findProductionBoutiqueByproductionId(String productionBoutiqueId);

   	 int findProductionBoutiqueCountByproductionId(@Param("productionBoutique")ProductionBoutique productionBoutique);
}