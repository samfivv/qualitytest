package com.midai.miya.sys.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.midai.miya.sys.dao.BottomPageDao;
import com.midai.miya.sys.service.BottomPageService;
import com.midai.miya.sys.model.BottomPage;
import com.midai.miya.utils.PageUtil;

@Service
public class BottomPageServiceImpl implements BottomPageService {

     @Autowired
     private BottomPageDao bottomPageDao;

     @Override
     public List<BottomPage> findByConditions(BottomPage bottomPage,PageUtil page) {
        List<BottomPage> lists=bottomPageDao.findByConditions(bottomPage,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(BottomPage bottomPage) {
        long count=bottomPageDao.findByConditionsCount(bottomPage);
        return count;
     }

     @Override
     public void save(BottomPage bottomPage) {
        bottomPageDao.save(bottomPage);
     }

     @Override
     public void update(BottomPage bottomPage) {
        bottomPageDao.update(bottomPage);
     }

     @Override
     public void delete(BottomPage bottomPage) {
        bottomPageDao.delete(bottomPage);
     }

     @Override
     public BottomPage findBottomPageById(String bottomPageId){
        BottomPage BottomPage = bottomPageDao.findBottomPageById(bottomPageId);
        return BottomPage;
     }

     @Override
     public int findBottomPageCountByName(BottomPage bottomPage){
        int BottomPage = bottomPageDao.findBottomPageCountByName(bottomPage);
        return BottomPage;
     }
}