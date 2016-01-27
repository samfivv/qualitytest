package com.midai.miya.sys.service;

import java.util.List;




import com.midai.miya.sys.model.HelpQuestion;
import com.midai.miya.utils.PageUtil;

public interface HelpQuestionService {
    
	List<HelpQuestion> findAll(HelpQuestion helpQuestion,PageUtil page);
	
	long findCount(HelpQuestion helpQuestion,PageUtil page);
	
	void update(HelpQuestion helpQuestion);
	
	HelpQuestion findById(String helpQuestionId);
}
