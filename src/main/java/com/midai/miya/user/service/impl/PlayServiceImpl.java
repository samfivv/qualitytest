package com.midai.miya.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midai.miya.user.dao.playDao;
import com.midai.miya.user.model.Play;
import com.midai.miya.user.model.PlayRecord;
import com.midai.miya.user.service.PlayService;
import com.midai.miya.utils.PageUtil;
@Service
public class PlayServiceImpl implements PlayService {
	@Autowired
	private playDao playDao;
	@Override
	public List<PlayRecord> findPlayRecord(PlayRecord playRecord, PageUtil page) {
		String interestTitle=playRecord.getInteresetTitle();
		String videoId=playRecord.getVideoId();
		String videoName=playRecord.getVideoName();
		String userNickname=playRecord.getUserNickname();
		if(interestTitle!=null){
			playRecord.setInteresetTitle(interestTitle.replace(" ", ""));
		}
		if(videoId!=null){
			playRecord.setVideoId(videoId.replace(" ", ""));
		}
		if(videoName!=null){
			playRecord.setVideoName(videoName.replace(" ", ""));
		}
		if(userNickname!=null){
			playRecord.setUserNickname(userNickname.replace(" ", ""));
		}
		List<PlayRecord> records=playDao.findPlayRecord(playRecord, page);
		return records;
	}

	@Override
	public long findCount(PlayRecord playRecord, PageUtil page) {
		String interestTitle=playRecord.getInteresetTitle();
		String videoId=playRecord.getVideoId();
		String videoName=playRecord.getVideoName();
		String userNickname=playRecord.getUserNickname();
		if(interestTitle!=null){
			playRecord.setInteresetTitle(interestTitle.replace(" ", ""));
		}
		if(videoId!=null){
			playRecord.setVideoId(videoId.replace(" ", ""));
		}
		if(videoName!=null){
			playRecord.setVideoName(videoName.replace(" ", ""));
		}
		if(userNickname!=null){
			playRecord.setUserNickname(userNickname.replace(" ", ""));
		}
		long count=playDao.findCount(playRecord, page);
		return count;
	}

	@Override
	public List<Play> findCountByIp(Play play) {
		List<Play> countIp=playDao.findCountByIp(play);
		return countIp;
	}

	@Override
	public List<Play> findCountAll(Play play) {
		List<Play> countAll=playDao.findCountAll(play);
		return countAll;
	}

}
