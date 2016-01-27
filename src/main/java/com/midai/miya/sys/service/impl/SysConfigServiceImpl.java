package com.midai.miya.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midai.miya.sys.dao.SysConfigDao;
import com.midai.miya.sys.model.Log;
import com.midai.miya.sys.model.Operator;
import com.midai.miya.sys.model.SysConfig;
import com.midai.miya.sys.service.SysConfigService;
import com.midai.miya.utils.PageUtil;

@Service
public class SysConfigServiceImpl implements SysConfigService {
	@Autowired
	private SysConfigDao sysConfigDao;

	@Override
	public List<SysConfig> find(SysConfig sysConfig, PageUtil pageUtil) {
		if (sysConfig.getSysConfigKey() != null) {
			sysConfig.setSysConfigKey(sysConfig.getSysConfigKey().replace(" ",
					""));
		}
		if (sysConfig.getSysConfigValue() != null) {
			sysConfig.setSysConfigValue(sysConfig.getSysConfigValue().replace(
					" ", ""));
		}
		if (sysConfig.getSysConfigDesc() != null) {
			sysConfig.setSysConfigDesc(sysConfig.getSysConfigDesc().replace(
					" ", ""));
		}
		List<SysConfig> lists = sysConfigDao.find(sysConfig, pageUtil);
		return lists;
	}

	@Override
	public int findCount(SysConfig sysConfig, PageUtil pageUtil) {
		if (sysConfig.getSysConfigKey() != null) {
			sysConfig.setSysConfigKey(sysConfig.getSysConfigKey().replace(" ",
					""));
		}
		if (sysConfig.getSysConfigValue() != null) {
			sysConfig.setSysConfigValue(sysConfig.getSysConfigValue().replace(
					" ", ""));
		}
		if (sysConfig.getSysConfigDesc() != null) {
			sysConfig.setSysConfigDesc(sysConfig.getSysConfigDesc().replace(
					" ", ""));
		}
		int count = sysConfigDao.findCount(sysConfig, pageUtil);
		return count;
	}

	@Override
	public void save(SysConfig sysConfig) {
		sysConfig.setSysConfigKey(sysConfig.getSysConfigKey().replace(" ", ""));
		sysConfig.setSysConfigDesc(sysConfig.getSysConfigDesc()
				.replace(" ", ""));
		sysConfig.setSysConfigValue(sysConfig.getSysConfigValue().replace(" ",
				""));
		sysConfigDao.save(sysConfig);

	}

	@Override
	public void delete(String sysConfigId) {
		sysConfigDao.delete(sysConfigId);

	}

	@Override
	public void update(SysConfig sysConfig) {
		sysConfigDao.update(sysConfig);

	}

	@Override
	public List<Log> findLogByOperatorId(Operator operator, Log log,
			PageUtil pageUtil) {
		String content = log.getLogContent();
		String name = operator.getOperatorName();
		if (name != null) {
			name = name.replace(" ", "");
			operator.setOperatorName(name);
		}
		if (content != null) {
			content = content.replace(" ", "");
			log.setLogContent(content);
		}
		List<Log> logs = sysConfigDao.findLogByOperatorId(operator, log,
				pageUtil);
		return logs;
	}

	@Override
	public int findConfigName(SysConfig sysConfig) {
		int exist = sysConfigDao.findWhetherExistConfigName(sysConfig);
		return exist;
	}

	@Override
	public Long findCountLog(Operator operator, Log log) {
		String name = operator.getOperatorName();
		if (name != null) {
			name = name.replace(" ", "");
			operator.setOperatorName(name);
		}

		return sysConfigDao.findCountLog(operator, log);
	}

	@Override
	public SysConfig findConfigById(String sysConfigId) {
		SysConfig sysConfig = sysConfigDao.findConfigById(sysConfigId);
		return sysConfig;
	}

}
