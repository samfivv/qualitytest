package com.midai.miya.sys.dao;

import com.midai.miya.sys.model.Photo;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.midai.miya.utils.PageUtil;

public interface PhotoDao {

     List<Photo> findByConditions(@Param("photo")Photo photo,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("photo")Photo photo);

     void save(@Param("photo")Photo photo);

     void update(@Param("photo")Photo photo);

     void delete(@Param("photo")Photo photo);
     
     Photo findById(@Param("photoId")String photoId);
     
}