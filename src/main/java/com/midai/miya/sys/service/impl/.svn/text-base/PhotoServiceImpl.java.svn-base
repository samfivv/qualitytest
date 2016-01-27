package com.midai.miya.sys.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midai.miya.sys.dao.PhotoAlbumDao;
import com.midai.miya.sys.dao.PhotoDao;
import com.midai.miya.sys.dao.VideoDao;
import com.midai.miya.sys.service.PhotoService;
import com.midai.miya.sys.model.Message;
import com.midai.miya.sys.model.Photo;
import com.midai.miya.sys.model.PhotoAlbum;
import com.midai.miya.utils.NumberUtil;
import com.midai.miya.utils.PageUtil;
import com.midai.miya.utils.UUIDUtil;

@Service
public class PhotoServiceImpl implements PhotoService {

     @Autowired
     private PhotoDao photoDao;
     @Autowired
     private VideoDao videoDao;
     @Autowired
     private PhotoAlbumDao photoAlbumDao;

     @Override
     public List<Photo> findByConditions(Photo photo,PageUtil page) {
    	 if(photo.getPhotoName()!=null){
    		 photo.setPhotoName(photo.getPhotoName().replace(" ", ""));
    	 }
    	 if(photo.getUserNickname()!=null){
    		 photo.setUserNickname(photo.getUserNickname().replace(" ", ""));
    	 }
        List<Photo> lists=photoDao.findByConditions(photo,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(Photo photo) {
    	 if(photo.getPhotoName()!=null){
    		 photo.setPhotoName(photo.getPhotoName().replace(" ", ""));
    	 }
    	 if(photo.getUserNickname()!=null){
    		 photo.setUserNickname(photo.getUserNickname().replace(" ", ""));
    	 }
        long count=photoDao.findByConditionsCount(photo);
        return count;
     }

     @Override
     public void save(Photo photo) {
        photoDao.save(photo);
     }

     @Override
     public void update(Photo photo) {
    	 PhotoAlbum photoAlbum=photoAlbumDao.findPhotoAlbumById(photo.getPhotoAlbumId());
    	 Message message=new Message();
    	 message.setMessageId(UUIDUtil.getUUID());
    	 message.setMessageState(0);
    	 message.setMessageType(0);
    	 message.setMessageCreateTime(new Date());
    	 message.setMessageUserId(photo.getUserId());
    	 if(photo.getPhotoState()==1){
 			photo.setPhotoState(2);
 			message.setMessageContent("您好，您的图片不符合要求，已被移除，请重新选择！");
 			photoAlbum.setPhotoAlbumCount(photoAlbum.getPhotoAlbumCount()-1);
 		}else{
 			photo.setPhotoState(1);
 			message.setMessageContent("您好，您的图片已被恢复！");
 			if(photoAlbum.getPhotoAlbumCount()==null){
 				photoAlbum.setPhotoAlbumCount(1);
 			}else{
 			photoAlbum.setPhotoAlbumCount(photoAlbum.getPhotoAlbumCount()+1);
 			}
 		}
    	 photoAlbumDao.update(photoAlbum);
    	videoDao.addMessage(message);
        photoDao.update(photo);
     }

     @Override
     public void delete(Photo photo) {
        photoDao.delete(photo);
     }

	@Override
	public Photo findById(String photoId) {
		Photo photo=photoDao.findById(photoId);
		return photo;
	}
}

