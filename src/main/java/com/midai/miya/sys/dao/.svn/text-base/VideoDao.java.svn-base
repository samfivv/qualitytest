package com.midai.miya.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.midai.miya.sys.model.Approval;
import com.midai.miya.sys.model.Message;
import com.midai.miya.sys.model.Video;
import com.midai.miya.utils.PageUtil;

public interface VideoDao {
	
	List<Video> findAllVideo(@Param("video")Video video,@Param("page")PageUtil pageUtil );
	
	long findVideoCount(@Param("video")Video video);
	
	Video findVideoById(@Param("videoId")String videoId);
	
	void addApproval(Approval approval);
	
	void updateVideoState(Video video);
	
	void update(Video video);
	
    List<Approval> findApproval(@Param("approval")Approval approval,@Param("page")PageUtil pageUtil);
	
	long findApprovalCount(@Param("approval")Approval approval);
	
	void addMessage(Message message);
	
	/**
	 * 根据攻略Id查询攻略下面的视频
	 *  黄扬仲
	 *  2015年12月9日
	 */
	List<Video> findVideoByInterestId(@Param("interestId")String interestId);
	/**
	 *  根据攻略Id更新视频状态
	 *  黄扬仲
	 *  2015年12月9日
	 */
	void updateVideoStateByInterestId(@Param("interestId")String interestId,@Param("videoState")int videoState);
}
