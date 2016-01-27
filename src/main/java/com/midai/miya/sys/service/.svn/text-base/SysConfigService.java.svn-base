package com.midai.miya.sys.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.midai.miya.sys.model.Log;
import com.midai.miya.sys.model.Operator;
import com.midai.miya.sys.model.SysConfig;
import com.midai.miya.utils.PageUtil;

@Service
public interface SysConfigService {
	
	public List<SysConfig> find(SysConfig sysConfig,PageUtil pageUtil);
	
	public int findCount(SysConfig sysConfig,PageUtil pageUtil);
	
	public void save(SysConfig sysConfig);
	
	public void delete(String sysConfigId);
	
	public void update(SysConfig sysConfig);
	/**
	 * 根据operator对象查询log信息
	 * 彭坤
	 * 2015年4月30日
	 */
	List<Log> findLogByOperatorId(Operator operator,Log log,PageUtil pageUtil);
	/**
	 * 分页
	 * 彭坤
	 * 2015年4月30日
	 */
	Long findCountLog(Operator operator,@Param("log")Log log);
	
	public int findConfigName(SysConfig sysConfig);
	/**
	 * 添加根据id查找
	 * 王梦圆
	 * 2015年4月30日
	 */
	SysConfig findConfigById(String sysConfigId);

}
