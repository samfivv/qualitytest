package com.midai.miya.sys.service;

import java.util.List;


import com.midai.miya.sys.model.SensitiveWord;
import com.midai.miya.utils.PageUtil;

public interface SensitiveWordService {
	
	void save(SensitiveWord sensitiveWord);
			
	void update(SensitiveWord sensitiveWord);
	
	void delete(String sensitiveWordId);
	
	List<SensitiveWord> findAll(SensitiveWord sensitiveWord,PageUtil page);
	
	long findAllCount(SensitiveWord sensitiveWord);
	
	SensitiveWord findById(String sensitiveWordId);
	
	int findCountByName(SensitiveWord sensitiveWord);
}
