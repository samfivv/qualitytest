package com.midai.miya.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.midai.miya.user.model.Play;
import com.midai.miya.user.model.PlayRecord;
import com.midai.miya.utils.PageUtil;

public interface playDao {
	
	List<PlayRecord> findPlayRecord(@Param("playRecord")PlayRecord playRecord,@Param("page")PageUtil page);
	
	long findCount(@Param("playRecord")PlayRecord playRecord,@Param("page")PageUtil page);
	
	List<Play> findCountByIp(Play play);
	
	List<Play> findCountAll(Play play);
}
