package com.midai.miya.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.midai.miya.sys.model.Hobby;
import com.midai.miya.utils.PageUtil;

public interface HobbyDao {
	
	void addHobby(Hobby hobby);
	
	List<Hobby> findAllHobby(@Param("hobby")Hobby hobby,@Param("page")PageUtil pageUtil);
	
	Hobby findHobbyById(@Param("hobbyId")String hobbyId);//根据id查看爱好信息
	
	void updateHobby(@Param("hobby")Hobby hobby);
	
	long findCountHobby(@Param("hobby")Hobby hobby);
	
	long findHobbyByName(@Param("hobby")Hobby hobby);
}
