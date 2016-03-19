package com.midai.miya.user.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.midai.miya.user.dao.UserCertImgDao;
import com.midai.miya.user.service.UserCertImgService;
import com.midai.miya.user.model.UserCertImg;
import com.midai.miya.utils.PageUtil;

@Service
public class UserCertImgServiceImpl implements UserCertImgService {

     @Autowired
     private UserCertImgDao userCertImgDao;

     @Override
     public List<UserCertImg> findByConditions(UserCertImg userCertImg,PageUtil page) {
        List<UserCertImg> lists=userCertImgDao.findByConditions(userCertImg,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(UserCertImg userCertImg) {
        long count=userCertImgDao.findByConditionsCount(userCertImg);
        return count;
     }

     @Override
     public void save(UserCertImg userCertImg) {
        userCertImgDao.save(userCertImg);
     }

     @Override
     public void update(UserCertImg userCertImg) {
        userCertImgDao.update(userCertImg);
     }

     @Override
     public void delete(UserCertImg userCertImg) {
        userCertImgDao.delete(userCertImg);
     }
}

