package com.midai.miya.sys.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.midai.miya.sys.dao.InterestBoutiqueDao;
import com.midai.miya.sys.service.InterestBoutiqueService;
import com.midai.miya.sys.model.InterestBoutique;
import com.midai.miya.utils.PageUtil;

@Service
public class InterestBoutiqueServiceImpl implements InterestBoutiqueService {

     @Autowired
     private InterestBoutiqueDao interestBoutiqueDao;

     @Override
     public List<InterestBoutique> findByConditions(InterestBoutique interestBoutique,PageUtil page) {
        List<InterestBoutique> lists=interestBoutiqueDao.findByConditions(interestBoutique,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(InterestBoutique interestBoutique) {
        long count=interestBoutiqueDao.findByConditionsCount(interestBoutique);
        return count;
     }

     @Override
     public void save(InterestBoutique interestBoutique) {
        interestBoutiqueDao.save(interestBoutique);
     }

     @Override
     public void update(InterestBoutique interestBoutique) {
        interestBoutiqueDao.update(interestBoutique);
     }

     @Override
     public void delete(InterestBoutique interestBoutique) {
        interestBoutiqueDao.delete(interestBoutique);
     }

     @Override
     public InterestBoutique findInterestBoutiqueById(String interestBoutiqueId){
        InterestBoutique InterestBoutique = interestBoutiqueDao.findInterestBoutiqueById(interestBoutiqueId);
        return InterestBoutique;
     }
}