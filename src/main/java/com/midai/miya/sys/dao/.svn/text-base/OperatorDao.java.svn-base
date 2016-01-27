package com.midai.miya.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.midai.miya.sys.model.Operator;
import com.midai.miya.sys.model.Role;
import com.midai.miya.utils.PageUtil;


public interface OperatorDao {
	List<Operator> findAll(@Param("page")PageUtil pageUtil);
	Operator findById(String id);
	void update(Operator operator);
	void save(Operator operator);
	void delete(String id);
	long findAllCount(@Param("operator")Operator operator);
	List<Operator> findByOperatorName(@Param("operator")Operator operator,@Param("page")PageUtil pageUtil);
	List<Role> findAllRoleByName();
	
	List<Operator> findOperatorByName(@Param("operator")Operator operator);
	
	List<Operator> findOperatorForLogin(String operatorName);
	
	void updatePwd(@Param("newPwd")String newPwd,@Param("operatorId")String operatorId);
}
