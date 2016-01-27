package com.midai.miya.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midai.miya.sys.dao.PartnerDao;
import com.midai.miya.sys.service.PartnerService;
import com.midai.miya.sys.model.Partner;
import com.midai.miya.utils.PageUtil;

@Service
public class PartnerServiceImpl implements PartnerService {

     @Autowired
     private PartnerDao partnerDao;

     @Override
     public List<Partner> findByConditions(Partner partner,PageUtil page) {
    	 if(partner.getPartnerName()!=null){
    		 partner.setPartnerName(partner.getPartnerName().replace(" ", ""));
    	 }
        List<Partner> lists=partnerDao.findByConditions(partner,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(Partner partner) {
    	 if(partner.getPartnerName()!=null){
    		 partner.setPartnerName(partner.getPartnerName().replace(" ", ""));
    	 }
        long count=partnerDao.findByConditionsCount(partner);
        return count;
     }

     @Override
     public void save(Partner partner) {
    	 if(partner.getPartnerName()!=null){
    		 partner.setPartnerName(partner.getPartnerName().trim());
    	 }
    	 if(partner.getPartnerUrl()!=null){
    		 partner.setPartnerUrl(partner.getPartnerUrl().trim());
    	 }
    	 if(partner.getPartnerImgUrl()!=null){
    		 partner.setPartnerImgUrl(partner.getPartnerImgUrl().trim());
    	 }
        partnerDao.save(partner);
     }

     @Override
     public void update(Partner partner) {
    	 if(partner.getPartnerName()!=null){
    		 partner.setPartnerName(partner.getPartnerName().trim());
    	 }
    	 if(partner.getPartnerUrl()!=null){
    		 partner.setPartnerUrl(partner.getPartnerUrl().trim());
    	 }
    	 if(partner.getPartnerImgUrl()!=null){
    		 partner.setPartnerImgUrl(partner.getPartnerImgUrl().trim());
    	 }
        partnerDao.update(partner);
     }

     @Override
     public void delete(Partner partner) {
        partnerDao.delete(partner);
     }

	@Override
	public Partner findById(String partnerId) {
		Partner partner=partnerDao.findById(partnerId);
		return partner;
	}
}

