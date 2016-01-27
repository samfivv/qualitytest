package com.midai.miya.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.midai.miya.sys.model.Search;
import com.midai.miya.sys.model.SearchCount;
import com.midai.miya.utils.PageUtil;

public interface SearchDao {
	
	List<Search> findAll(@Param("search")Search search,@Param("page")PageUtil pageutil);
	
	long findCount(@Param("search")Search search);
	
	List<SearchCount> findByCount(Search search);
}
