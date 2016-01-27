package com.midai.miya.sys.service;

import java.util.List;
import com.midai.miya.sys.model.Operator;
import com.midai.miya.utils.PageUtil;
public interface OperatorService {
	public List<Operator> findAll(PageUtil pageUtil);
	
	long findAllCount(Operator operator);
	
	public Operator findById(String id);
	
	public void update(Operator operator);
	
	public void save(Operator operator);
	
	public void delete(String id);
	
	List<Operator> findOperatorByName(Operator operator,PageUtil pageUtil);
	
	Operator findOperatorByName(Operator operator);
	
	/**
	 *  根据用户名查询登录用户
	 *  @param operatorName
	 *  @return
	 */
	Operator findOperatorForLogin(String operatorName);
	
	void updatePwd(String newPwd,String operatorId);
}
