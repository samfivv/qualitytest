package com.midai.miya.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.midai.miya.sys.model.Category;
import com.midai.miya.utils.PageUtil;

public interface CategoryDao {
	
	List<Category> findAll(@Param("category")Category category,@Param("pageUtil")PageUtil pageUtil);
	
	int findCount(@Param("category")Category category,@Param("pageUtil")PageUtil pageUtil);
	void save(@Param("category")Category category);
	void delete(String categoryId);
	void update(@Param("category")Category category);
	int findWhetherExistCategoryName(@Param("category")Category category);
	Category findCategoryById(@Param("categoryId")String categoryId);


   	 List<Category> findCategoryByParentId();

   	 String findCategoryNameByParentId(String categoryId);
}