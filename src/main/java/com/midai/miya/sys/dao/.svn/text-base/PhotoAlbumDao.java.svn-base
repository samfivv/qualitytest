package com.midai.miya.sys.dao;

import com.midai.miya.sys.model.PhotoAlbum;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.midai.miya.utils.PageUtil;

public interface PhotoAlbumDao {

     List<PhotoAlbum> findByConditions(@Param("photoAlbum")PhotoAlbum photoAlbum,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("photoAlbum")PhotoAlbum photoAlbum);

     void save(@Param("photoAlbum")PhotoAlbum photoAlbum);

     void update(@Param("photoAlbum")PhotoAlbum photoAlbum);

     void delete(@Param("photoAlbum")PhotoAlbum photoAlbum);


   	 PhotoAlbum findPhotoAlbumById(@Param("photoAlbumId")String photoAlbumId);
}