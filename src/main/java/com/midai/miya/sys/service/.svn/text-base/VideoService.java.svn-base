package com.midai.miya.sys.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.midai.miya.sys.model.Approval;
import com.midai.miya.sys.model.Video;
import com.midai.miya.utils.PageUtil;

public interface VideoService {
	
    List<Video> findAllVideo(@Param("video")Video video,@Param("page")PageUtil pageUtil );
	
	long findVideoCount(@Param("video")Video video);
	
	Video findVideoById(@Param("videoId")String videoId);
	
	void addApproval(Approval approval);
	
	void updateVideoState(Video video);
	
	void update(Video video);
	
    List<Approval> findApproval(Approval approval,PageUtil pageUtil);
	
	long findApprovalCount(Approval approval);
}
