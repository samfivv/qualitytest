package com.midai.miya.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.midai.miya.sys.model.Log;
import com.midai.miya.sys.model.Operator;
import com.midai.miya.sys.model.SysConfig;
import com.midai.miya.utils.PageUtil;

public interface SysConfigDao {
	
	List<SysConfig> find(@Param("sysConfig")SysConfig sysConfig,@Param("pageUtil")PageUtil pageUtil);
	
	int findCount(@Param("sysConfig")SysConfig sysConfig,@Param("pageUtil")PageUtil pageUtil);
	
	void save(@Param("sysConfig")SysConfig sysConfig);
	
	void delete(String sysConfigId);
	
	void update(@Param("sysConfig")SysConfig sysConfig);
	
	List<Log>findLogByOperatorId(@Param("operator")Operator operator,@Param("log")Log log,@Param("page")PageUtil pageUtil);
	
	int findWhetherExistConfigName(@Param("sysConfig")SysConfig sysConfig);
	
	long findCountLog(@Param("operator")Operator operator,@Param("log")Log log);
	
	SysConfig findConfigById(@Param("sysConfigId")String sysConfigId);
}
