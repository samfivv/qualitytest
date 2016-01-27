package com.midai.miya.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.midai.miya.sys.model.SensitiveWord;
import com.midai.miya.utils.PageUtil;

public interface SensitiveWordDao {
	
	void save(SensitiveWord sensitiveWord);
	
	void update(SensitiveWord sensitiveWord);
	
	void delete(String sensitiveWordId);
	
	List<SensitiveWord> findAll(@Param("sensitiveWord")SensitiveWord sensitiveWord,@Param("page")PageUtil page);
	
	long findAllCount(@Param("sensitiveWord")SensitiveWord sensitiveWord);
	
	SensitiveWord findById(String sensitiveWordId);
	
	int findCountByName(SensitiveWord sensitiveWord);
}
