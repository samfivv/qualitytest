package com.midai.miya.user.service;

import com.midai.miya.user.model.UserCertImg;
import java.util.List;
import com.midai.miya.utils.PageUtil;

public interface UserCertImgService {

     List<UserCertImg> findByConditions(UserCertImg userCertImg,PageUtil page);

     long findByConditionsCount(UserCertImg userCertImg);

     void save(UserCertImg userCertImg);

     void update(UserCertImg userCertImg);

     void delete(UserCertImg userCertImg);

}