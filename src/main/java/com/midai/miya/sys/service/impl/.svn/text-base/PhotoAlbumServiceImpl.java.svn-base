package com.midai.miya.sys.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.midai.miya.sys.dao.PhotoAlbumDao;
import com.midai.miya.sys.service.PhotoAlbumService;
import com.midai.miya.sys.model.PhotoAlbum;
import com.midai.miya.utils.PageUtil;

@Service
public class PhotoAlbumServiceImpl implements PhotoAlbumService {

     @Autowired
     private PhotoAlbumDao photoAlbumDao;

     @Override
     public List<PhotoAlbum> findByConditions(PhotoAlbum photoAlbum,PageUtil page) {
        List<PhotoAlbum> lists=photoAlbumDao.findByConditions(photoAlbum,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(PhotoAlbum photoAlbum) {
        long count=photoAlbumDao.findByConditionsCount(photoAlbum);
        return count;
     }

     @Override
     public void save(PhotoAlbum photoAlbum) {
        photoAlbumDao.save(photoAlbum);
     }

     @Override
     public void update(PhotoAlbum photoAlbum) {
        photoAlbumDao.update(photoAlbum);
     }

     @Override
     public void delete(PhotoAlbum photoAlbum) {
        photoAlbumDao.delete(photoAlbum);
     }

     @Override
     public PhotoAlbum findPhotoAlbumById(String photoAlbumId){
        PhotoAlbum PhotoAlbum = photoAlbumDao.findPhotoAlbumById(photoAlbumId);
        return PhotoAlbum;
     }
}