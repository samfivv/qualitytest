package com.midai.miya.sys.service;

import java.util.List;

import com.midai.miya.sys.model.Search;
import com.midai.miya.sys.model.SearchCount;
import com.midai.miya.utils.PageUtil;

public interface SearchService {
	
	List<Search> findAll(Search search,PageUtil pageutil);
	
	long findCount(Search search);
	
	List<SearchCount> findByCount( String queryBeginTimeStr,String queryEndTimeStr);
}
