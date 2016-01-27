package com.midai.miya.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midai.miya.sys.dao.SensitiveWordDao;
import com.midai.miya.sys.model.SensitiveWord;
import com.midai.miya.sys.service.SensitiveWordService;
import com.midai.miya.utils.PageUtil;
@Service
public class SensitiveWordServiceImpl implements SensitiveWordService {
	@Autowired
	private SensitiveWordDao sensitiveWordDao;
	@Override
	public void save(SensitiveWord sensitiveWord) {
		sensitiveWordDao.save(sensitiveWord);
	}

	@Override
	public void update(SensitiveWord sensitiveWord) {
		sensitiveWordDao.update(sensitiveWord);
	}

	@Override
	public void delete(String sensitiveWordId) {
		sensitiveWordDao.delete(sensitiveWordId);
	}

	@Override
	public List<SensitiveWord> findAll(SensitiveWord sensitiveWord,
			PageUtil page) {
		if(sensitiveWord.getSensitiveWord()!=null){
			sensitiveWord.setSensitiveWord(sensitiveWord.getSensitiveWord().replace(" ", ""));
		}
		List<SensitiveWord> sensitiveWords=sensitiveWordDao.findAll(sensitiveWord, page);
		return sensitiveWords;
	}

	@Override
	public long findAllCount(SensitiveWord sensitiveWord) {
		if(sensitiveWord.getSensitiveWord()!=null){
			sensitiveWord.setSensitiveWord(sensitiveWord.getSensitiveWord().replace(" ", ""));
		}
		long count=sensitiveWordDao.findAllCount(sensitiveWord);
		return count;
	}

	@Override
	public SensitiveWord findById(String sensitiveWordId) {
		SensitiveWord sensitiveWord=sensitiveWordDao.findById(sensitiveWordId);
		return sensitiveWord;
	}

	@Override
	public int findCountByName(SensitiveWord sensitiveWord) {
		int count=sensitiveWordDao.findCountByName(sensitiveWord);
		return count;
	}

}
