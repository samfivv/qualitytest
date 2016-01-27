package com.midai.miya.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.midai.miya.sys.model.Comment;
import com.midai.miya.utils.PageUtil;

public interface CommentDao {
	
	List<Comment> findAll(@Param("comment")Comment comment,@Param("page")PageUtil page);
	
	long findAllCount(@Param("comment")Comment comment);
	
	void update(@Param("comment")Comment comment); 
	
}
