package com.midai.miya.sys.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.midai.miya.sys.model.Category;
import com.midai.miya.sys.model.SysConfig;
import com.midai.miya.utils.PageUtil;

public interface CategoryService {
	
	List<Category> findAllCategory(Category category,PageUtil pageUtil);
	public int findCount(Category category,PageUtil pageUtil);
	public void save(Category category);
	public void delete(String categoryId);
	public void update(Category category);
	public int findCategoryName(Category category);
	Category findCategoryById(String categoryId);

   	List<Category> findCategoryByParentId();

   	String findCategoryNameByParentId(String categoryId);
}