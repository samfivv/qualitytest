package com.midai.miya.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midai.miya.sys.dao.CategoryDao;
import com.midai.miya.sys.model.Category;
import com.midai.miya.sys.service.CategoryService;
import com.midai.miya.utils.PageUtil;
@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;

	@Override
	public List<Category> findAllCategory(Category category,PageUtil pageUtil) {
		if(category.getCategoryName()!=null){
			category.setCategoryName(category.getCategoryName().replace(" ", ""));
		}
		if(category.getCategoryCretor()!=null){
			category.setCategoryCretor(category.getCategoryCretor().replace(" ", ""));
		}
		List<Category> lists=categoryDao.findAll(category,pageUtil);
		return lists;
	}

	@Override
	public int findCount(Category category, PageUtil pageUtil) {
		if(category.getCategoryName()!=null){
			category.setCategoryName(category.getCategoryName().replace(" ", ""));
		}
		if(category.getCategoryCretor()!=null){
			category.setCategoryCretor(category.getCategoryCretor().replace(" ", ""));
		}
		int count=categoryDao.findCount(category, pageUtil);
		return count;
	}

	@Override
	public void save(Category category) {
		category.setCategoryCretor(category.getCategoryCretor().replace(" ", ""));
		category.setCategoryName(category.getCategoryName().replace(" ", ""));
		categoryDao.save(category);
		
	}

	@Override
	public void delete(String categoryId) {
		categoryDao.delete(categoryId);
		
	}

	@Override
	public void update(Category category) {
		categoryDao.update(category);
		
	}

	@Override
	public int findCategoryName(Category category) {
		int exist=categoryDao.findWhetherExistCategoryName(category);
		return exist;
	}

	@Override
	public Category findCategoryById(String categoryId) {
		Category category=categoryDao.findCategoryById(categoryId);
		return category;
	}


     @Override
     public List<Category> findCategoryByParentId(){
        List<Category> Category = categoryDao.findCategoryByParentId();
        return Category;
     }

     @Override
     public String findCategoryNameByParentId(String categoryId){
        String Category = categoryDao.findCategoryNameByParentId(categoryId);
        return Category;
     }
}