package com.midai.miya.ask.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.midai.miya.ask.dao.AskWhistleBlowingDao;
import com.midai.miya.ask.service.AskWhistleBlowingService;
import com.midai.miya.ask.model.AskWhistleBlowing;
import com.midai.miya.utils.PageUtil;

@Service
public class AskWhistleBlowingServiceImpl implements AskWhistleBlowingService {

     @Autowired
     private AskWhistleBlowingDao askWhistleBlowingDao;

     @Override
     public List<AskWhistleBlowing> findByConditions(AskWhistleBlowing askWhistleBlowing,PageUtil page) {
        List<AskWhistleBlowing> lists=askWhistleBlowingDao.findByConditions(askWhistleBlowing,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(AskWhistleBlowing askWhistleBlowing) {
        long count=askWhistleBlowingDao.findByConditionsCount(askWhistleBlowing);
        return count;
     }

     @Override
     public void save(AskWhistleBlowing askWhistleBlowing) {
        askWhistleBlowingDao.save(askWhistleBlowing);
     }

     @Override
     public void update(AskWhistleBlowing askWhistleBlowing) {
        askWhistleBlowingDao.update(askWhistleBlowing);
     }

     @Override
     public void delete(AskWhistleBlowing askWhistleBlowing) {
        askWhistleBlowingDao.delete(askWhistleBlowing);
     }

     @Override
     public AskWhistleBlowing findAskWhistleBlowingById(String whistleBlowing){
        AskWhistleBlowing AskWhistleBlowing = askWhistleBlowingDao.findAskWhistleBlowingById(whistleBlowing);
        return AskWhistleBlowing;
     }

	@Override
	public List<AskWhistleBlowing> findReplyByConditions(
			AskWhistleBlowing askWhistleBlowing, PageUtil page) {
		List<AskWhistleBlowing> lists=askWhistleBlowingDao.findReplyByConditions(askWhistleBlowing, page);
		return lists;
	}

     @Override
     public AskWhistleBlowing findReplyById(String whistleBlowing){
        AskWhistleBlowing AskWhistleBlowing = askWhistleBlowingDao.findReplyById(whistleBlowing);
        return AskWhistleBlowing;
     }
}