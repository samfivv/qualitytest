package com.midai.miya.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midai.miya.sys.dao.HobbyDao;
import com.midai.miya.sys.model.Hobby;
import com.midai.miya.sys.service.HobbyService;
import com.midai.miya.utils.PageUtil;
@Service
public class HobbyServiceImpl implements HobbyService {
	@Autowired
	private HobbyDao hobbyDao;
	@Override
	public List<Hobby> findAllHobby(Hobby hobby,PageUtil pageUtil) {
		if(hobby.getHobbyName()!=null){
			hobby.setHobbyName(hobby.getHobbyName().replace(" ", ""));
		}
		List<Hobby> happys=hobbyDao.findAllHobby(hobby,pageUtil);
		return happys;
	}
	@Override
	public void addHobby(Hobby hobby) {
		hobby.setHobbyName(hobby.getHobbyName().replace(" ", ""));
		hobbyDao.addHobby(hobby);
	}
	@Override
	public Hobby findHobbyById(String hobbyId) {
		Hobby hobby=hobbyDao.findHobbyById(hobbyId);
		return hobby;
	}
	@Override
	public void updateHobby(Hobby hobby) {
		hobby.setHobbyName(hobby.getHobbyName().replace(" ", ""));
		hobbyDao.updateHobby(hobby);
	}
	@Override
	public long findCountHobby(Hobby hobby) {
		if(hobby.getHobbyName()!=null){
			hobby.setHobbyName(hobby.getHobbyName().replace(" ", ""));
		}
		long count=hobbyDao.findCountHobby(hobby);
		return count;
	}
	@Override
	public long findHobbyByName(Hobby hobby) {
		long count=hobbyDao.findHobbyByName(hobby);
		return count;
	}

}
