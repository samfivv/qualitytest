package com.midai.miya.sys.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midai.miya.sys.dao.ApprovalInterestDao;
import com.midai.miya.sys.dao.InterestDao;
import com.midai.miya.sys.dao.VideoDao;
import com.midai.miya.sys.service.InterestService;
import com.midai.miya.sys.service.SubjectInterestService;
import com.midai.miya.sys.model.ApprovalInterest;
import com.midai.miya.sys.model.Interest;
import com.midai.miya.sys.model.Message;
import com.midai.miya.sys.model.SubjectInterest;
import com.midai.miya.sys.model.Video;
import com.midai.miya.utils.DateUtil;
import com.midai.miya.utils.PageUtil;
import com.midai.miya.utils.UUIDUtil;

@Service
public class InterestServiceImpl implements InterestService {

	@Autowired
	private InterestDao interestDao;
	@Autowired
	private ApprovalInterestDao approvalInterestDao;
	@Autowired
	private SubjectInterestService subjectInterestService;
	@Autowired
	private VideoDao videoDao;

	@Override
	public List<Interest> findByConditions(Interest interest, PageUtil page) {
		List<Interest> lists = interestDao.findByConditions(interest, page);
		return lists;
	}

	@Override
	public long findByConditionsCount(Interest interest) {
		long count = interestDao.findByConditionsCount(interest);
		return count;
	}

	@Override
	public void save(Interest interest) {
		interestDao.save(interest);
	}

	@Override
	public void update(Interest interest) {
		String interestId = interest.getInterestId();
		ApprovalInterest approvalInterest = new ApprovalInterest();
		approvalInterest.setApprovalInterestId(UUIDUtil.getUUID());
		approvalInterest.setApprovalTime(new Date());
		approvalInterest.setApprovalState(interest.getInterestState());
		approvalInterest.setInterestId(interestId);
		approvalInterest.setNotPassReason(interest.getNotPassReason());
		approvalInterest.setOperatorId(interest.getApprovalUserName());
		SubjectInterest subjectInterest = subjectInterestService
				.findSubjectInterestCountById(interestId);
		if (subjectInterest != null) {
			subjectInterest
					.setSubjectInterestState(interest.getInterestState());
			subjectInterestService.update(subjectInterest);
		}
		approvalInterestDao.save(approvalInterest);
		interestDao.update(interest);
		Interest oldInterest = interestDao.findInterestById(interestId);
		// 插入系统消息
		if(interest.getInterestState()==1||interest.getInterestState()==2){
			interest.setInterestTitle(oldInterest.getInterestTitle());
			addMessage(interest);
		}
		// 如果是视频类型的攻略，则修改攻略的视频数量和时长 并且修改视频的状态  
		if (oldInterest.getInterestType() != null
				&& oldInterest.getInterestType() == 1) {
			List<Video> videos = videoDao.findVideoByInterestId(interestId);
			if (videos != null && videos.size() > 0) {
				String interestVideoTimelong = "00:00:00";
				for (Video video : videos) {
					long a = DateUtil.getSecond(interestVideoTimelong)
							+ DateUtil.getSecond(video.getVideoDuration());
					interestVideoTimelong = DateUtil.formatSecond(a);
				}
				interestDao.updateInterestOftenChange(videos.size(),
						interestId, interestVideoTimelong);
				// 修改视频状态
				videoDao.updateVideoStateByInterestId(interestId,
						interest.getInterestState());
			}

		}
	}

	/**
	 * 插入系统消息 
	 * 黄扬仲 2015年12月9日
	 */
	public void addMessage(Interest interest) {
		Message message = new Message();
		if(interest.getInterestState()==1){
			message.setMessageContent(" 恭喜您！您发布的攻略《" + interest.getInterestTitle()
					+ "》通过了审核");
		}else if(interest.getInterestState()==2){
			message.setMessageContent("很遗憾！您发布的攻略《" + interest.getInterestTitle()
					+ "》没有通过审核，原因是：" + interest.getNotPassReason());
		}
		message.setMessageCreateTime(new Date());
		message.setMessageId(UUIDUtil.getUUID());
		message.setMessageState(0);
		message.setMessageType(0);
		message.setMessageUserId(interest.getInterestCreator());
		videoDao.addMessage(message);
	}

	@Override
	public void delete(Interest interest) {
		interestDao.delete(interest);
	}

	@Override
	public Interest findInterestById(String interestId) {
		Interest interest = interestDao.findInterestById(interestId);
		return interest;
	}

	@Override
	public List<Interest> findAllInterest(Interest interest, PageUtil page) {
		List<Interest> Interest = interestDao.findAllInterest(interest, page);
		return Interest;
	}

	@Override
	public long findAllInterestCount(Interest interest) {
		long Interest = interestDao.findAllInterestCount(interest);
		return Interest;
	}

	@Override
	public Interest findInterestByInterestId(String interestId) {
		Interest Interest = interestDao.findInterestByInterestId(interestId);
		return Interest;
	}

	@Override
	public List<Interest> findAllInterestByUser(Interest interest, Integer type) {
		List<Interest> Interest = interestDao.findAllInterestByUser(interest,
				type);
		return Interest;
	}

	@Override
	public long findAllInterestCountByUser(Interest interest, Integer type) {
		long Interest = interestDao.findAllInterestCountByUser(interest, type);
		return Interest;
	}

	@Override
	public void updateBigLongImgUrl(Interest interest) {
		interestDao.update(interest);
		
	}
}