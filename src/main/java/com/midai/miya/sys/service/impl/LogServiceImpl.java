package com.midai.miya.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midai.miya.sys.dao.LogDao;
import com.midai.miya.sys.model.Log;
import com.midai.miya.sys.service.LogService;
@Service("logServiceImpl")
public class LogServiceImpl implements LogService{
	@Autowired
	private LogDao logDao;
	@Override
	public void addLog(Log log) {
		logDao.addLog(log);
	}

}
