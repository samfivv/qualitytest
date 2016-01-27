package com.midai.miya.sys.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midai.miya.sys.dao.OperatorDao;
import com.midai.miya.sys.model.Operator;
import com.midai.miya.sys.service.OperatorService;
import com.midai.miya.utils.PageUtil;

@Service("operatorService")
public class OperatorServiceImpl implements OperatorService {
	@Autowired
	OperatorDao operatorDao;
	@Override
	public List<Operator> findAll(PageUtil pageUtil) {
		List<Operator> operators = operatorDao.findAll(pageUtil);
		return operators;
	}
	@Override
	public Operator findById(String id) {
		Operator operator=operatorDao.findById(id);
		return operator;
	}
	@Override
	public void update(Operator operator) {
		if(operator !=null){
			if(operator.getOperatorName() !=null){
				operator.setOperatorName(operator.getOperatorName().replace(" ", ""));
			}
			if(operator.getOperatorDept() !=null){
				operator.setOperatorDept(operator.getOperatorDept().replace(" ", ""));
			}
			if(operator.getOperatorMail() !=null){
			operator.setOperatorMail(operator.getOperatorMail().replace(" ", ""));
			}
			if(operator.getOperatorMobile() !=null){
			operator.setOperatorMobile(operator.getOperatorMobile().replace(" ", ""));
			}
			if(operator.getOperatorPosition() != null){
			operator.setOperatorPosition(operator.getOperatorPosition().replace(" ", ""));
			}
			if(operator.getOperatorRealName() !=null){
			operator.setOperatorRealName(operator.getOperatorRealName().replace(" ", ""));
			}
			operatorDao.update(operator);
		}
	}
	@Override
	public void save(Operator operator) {
		if(operator !=null){
			if(operator.getOperatorName() !=null){
				operator.setOperatorName(operator.getOperatorName().replace(" ", ""));
			}
			if(operator.getOperatorDept() !=null){
				operator.setOperatorDept(operator.getOperatorDept().replace(" ", ""));
			}
			if(operator.getOperatorMail() !=null){
			operator.setOperatorMail(operator.getOperatorMail().replace(" ", ""));
			}
			if(operator.getOperatorMobile() !=null){
			operator.setOperatorMobile(operator.getOperatorMobile().replace(" ", ""));
			}
			if(operator.getOperatorPosition() != null){
			operator.setOperatorPosition(operator.getOperatorPosition().replace(" ", ""));
			}
			if(operator.getOperatorRealName() !=null){
			operator.setOperatorRealName(operator.getOperatorRealName().replace(" ", ""));
			}
			operatorDao.save(operator);
		}
	}
	@Override
	public void delete(String operatorId) {
		operatorDao.delete(operatorId);
	}
	@Override
	public long findAllCount(Operator operator) {
		String operatorName=operator.getOperatorName();
		String operatorRealName=operator.getOperatorRealName();
		if(operatorName != null && operatorRealName != null){
			operatorName=operatorName.replace(" ", "");
			operator.setOperatorName(operatorName);
			operatorRealName=operatorRealName.replace(" ", "");
			operator.setOperatorRealName(operatorRealName);
		}
		long count=operatorDao.findAllCount(operator);
		return count;
	}
	@Override
	public List<Operator> findOperatorByName(Operator operator,
			PageUtil pageUtil) {
		String operatorName=operator.getOperatorName();
		String operatorRealName=operator.getOperatorRealName();
		if(operatorRealName != null){
			operatorRealName=operatorRealName.replace(" ", "");
			operator.setOperatorRealName(operatorRealName);
		}
		if(operatorName != null){
			operatorName=operatorName.replace(" ", "");
			operator.setOperatorName(operatorName);
		}
		return operatorDao.findByOperatorName(operator, pageUtil);
	}
	@Override
	public Operator findOperatorByName(Operator operator) {
		List<Operator> operators= operatorDao.findOperatorByName(operator);
		if(operators!=null&&operators.size()>0){
			return operators.get(0);
		}
		return null;
	}
	@Override
	public Operator findOperatorForLogin(String operatorName) {
		List<Operator> operators=operatorDao.findOperatorForLogin(operatorName);
		
		if(operators!=null&&operators.size()>0){
			return operators.get(0);
		}
		return null;
	}
	@Override
	public void updatePwd(String newPwd,String operatorId) {
		operatorDao.updatePwd(newPwd,operatorId);
	}
}
