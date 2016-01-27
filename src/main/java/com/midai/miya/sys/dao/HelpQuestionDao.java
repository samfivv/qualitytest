package com.midai.miya.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.midai.miya.sys.model.HelpQuestion;
import com.midai.miya.utils.PageUtil;

public interface HelpQuestionDao {
	
	List<HelpQuestion> findAll(@Param("help")HelpQuestion helpQuestion,@Param("page")PageUtil page);
	
	long findCount(@Param("help")HelpQuestion helpQuestion,@Param("page")PageUtil page);
	
	void update(HelpQuestion helpQuestion);
	
	HelpQuestion findById(String helpQuestionId);
}
