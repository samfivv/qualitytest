package com.midai.miya.sys.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midai.miya.sys.dao.VideoDao;
import com.midai.miya.sys.model.Approval;
import com.midai.miya.sys.model.Message;
import com.midai.miya.sys.model.Video;
import com.midai.miya.sys.service.VideoService;
import com.midai.miya.user.dao.UserExperienceLogDao;
import com.midai.miya.user.dao.UserOftenChangeInfoDao;
import com.midai.miya.user.model.UserExperienceLog;
import com.midai.miya.user.model.UserOftenChangeInfo;
import com.midai.miya.utils.NumberUtil;
import com.midai.miya.utils.PageUtil;
import com.midai.miya.utils.UUIDUtil;
@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
	private VideoDao videoDao;
    @Autowired
    private UserExperienceLogDao userExperienceLogDao;
    @Autowired
    private UserOftenChangeInfoDao userOftenChangeInfoDao;
	@Override
	public List<Video> findAllVideo(Video video, PageUtil pageUtil) {
		if(video.getVideoName()!=null){
			video.setVideoName(video.getVideoName().trim());
		}
		if(video.getUserName()!=null){
			video.setUserName(video.getUserName().trim());
		}
		List<Video> videos=videoDao.findAllVideo(video, pageUtil);
		return videos;
	}

	@Override
	public long findVideoCount(Video video) {
		if(video.getVideoName()!=null){
			video.setVideoName(video.getVideoName().trim());
		}
		if(video.getUserName()!=null){
			video.setUserName(video.getUserName().trim());
		}
		long count=videoDao.findVideoCount(video);
		return count;
	}

	@Override
	public void addApproval(Approval approval) {
		videoDao.addApproval(approval);
	}

	@Override
	public void updateVideoState(Video video) {
		Video video1=videoDao.findVideoById(video.getVideoId());
		Message message=new Message();
		if(video.getVideoState()!=0 && video1.getVideoState()!=video.getVideoState()){
			if(video.getVideoState()==1){
				message.setMessageContent(" 恭喜您！您上传的视频《"+video1.getVideoName()+"》通过了审核");
				UserOftenChangeInfo userOftenChangeInfo=userOftenChangeInfoDao.findById(video1.getVideoCreator());
				userOftenChangeInfo.setUserExperience(NumberUtil.add(userOftenChangeInfo.getUserExperience(), 10));
				userOftenChangeInfoDao.update(userOftenChangeInfo);
				UserExperienceLog userExperienceLog=new UserExperienceLog();
				userExperienceLog.setUserExperienceLogId(UUIDUtil.getUUID());
				userExperienceLog.setLogType(1);
				userExperienceLog.setCreateTime(new Date());
				userExperienceLog.setLogExperience(10);
				userExperienceLog.setLogDesc("上传视频通过了审核，经验+10");
				userExperienceLog.setUserId(video1.getVideoCreator());
				userExperienceLogDao.save(userExperienceLog);
				userOftenChangeInfoDao.updateUserLevel(video1.getVideoCreator());
			}else if(video.getVideoState()==2){
				message.setMessageContent("很遗憾！您上传的是视频《"+video1.getVideoName()+"》没有通过审核，原因是："+video1.getNotPassReason());
			}
			message.setMessageCreateTime(new Date());
			message.setMessageId(UUIDUtil.getUUID());
			message.setMessageState(0);
			message.setMessageType(0);
			message.setMessageUserId(video1.getVideoCreator());
			videoDao.addMessage(message);
			videoDao.updateVideoState(video);
		}
	}
	
	@Override
	public List<Approval> findApproval(Approval approval,PageUtil pageUtil) {
		if(approval.getVideoId()!=null){
			approval.setVideoId(approval.getVideoId().replace(" ", ""));
		}
		if(approval.getVideoName()!=null){
			approval.setVideoName(approval.getVideoName().replace(" ", ""));
		}
		if(approval.getOperatorName()!=null){
			approval.setOperatorName(approval.getOperatorName().replace(" ", ""));
		}
		List<Approval> list=videoDao.findApproval(approval,pageUtil);
		return list;
	}
	@Override
	public long findApprovalCount(Approval approval) {
		if(approval.getVideoId()!=null){
			approval.setVideoId(approval.getVideoId().replace(" ", ""));
		}
		if(approval.getVideoName()!=null){
			approval.setVideoName(approval.getVideoName().replace(" ", ""));
		}
		if(approval.getOperatorName()!=null){
			approval.setOperatorName(approval.getOperatorName().replace(" ", ""));
		}
		long count=videoDao.findApprovalCount(approval);
		return count;
	}
     
	@Override
	public Video findVideoById(String videoId) {
		Video video=videoDao.findVideoById(videoId);
		return video;
	}

	@Override
	public void update(Video video) {
		videoDao.update(video);
	}

}
