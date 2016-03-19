package com.midai.miya.user.dao;

import com.midai.miya.user.model.UserCertImg;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.midai.miya.utils.PageUtil;

public interface UserCertImgDao {

     List<UserCertImg> findByConditions(@Param("userCertImg")UserCertImg userCertImg,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("userCertImg")UserCertImg userCertImg);

     void save(@Param("userCertImg")UserCertImg userCertImg);

     void update(@Param("userCertImg")UserCertImg userCertImg);

     void delete(@Param("userCertImg")UserCertImg userCertImg);

}