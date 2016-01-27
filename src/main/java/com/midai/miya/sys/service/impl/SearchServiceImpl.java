package com.midai.miya.sys.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midai.miya.sys.dao.SearchDao;
import com.midai.miya.sys.model.Search;
import com.midai.miya.sys.model.SearchCount;
import com.midai.miya.sys.service.SearchService;
import com.midai.miya.utils.PageUtil;
@Service
public class SearchServiceImpl implements SearchService {
	@Autowired
	private SearchDao searchDao;
	@Override
	public List<Search> findAll(Search search, PageUtil pageutil) {
		if(search.getSearchContent()!=null){
			search.setSearchContent(search.getSearchContent().replace(" ", ""));
		}
		if(search.getUserNickname()!=null){
			search.setUserNickname(search.getUserNickname().replace(" ", ""));
		}
		if(search.getSearchResultCount()!=null){
			search.setSearchResultCount(search.getSearchResultCount().replace(" ", ""));
		}
		List<Search> list=searchDao.findAll(search, pageutil);
		return list;
	}

	@Override
	public long findCount(Search search) {
		if(search.getSearchContent()!=null){
			search.setSearchContent(search.getSearchContent().replace(" ", ""));
		}
		if(search.getUserNickname()!=null){
			search.setUserNickname(search.getUserNickname().replace(" ", ""));
		}
		if(search.getSearchResultCount()!=null){
			search.setSearchResultCount(search.getSearchResultCount().replace(" ", ""));
		}
		long count=searchDao.findCount(search);
		return count;
	}

	@Override
	public List<SearchCount> findByCount( String queryBeginTimeStr,String queryEndTimeStr) {
		if(queryBeginTimeStr.equals("") ){
			 Calendar c=Calendar.getInstance();
			 int year=c.get(Calendar.YEAR);
			 int month=c.get(Calendar.MONTH)+1;
			 if(month<10){
				 queryBeginTimeStr=year+"-0"+month+"-01 00:00:00";
			 }else{
			 queryBeginTimeStr=year+"-"+month+"-01 00:00:00";
			 }
		 }
		 if(queryEndTimeStr.equals("")){
			 SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 queryEndTimeStr=sdf.format(new Date());
		 }
		 Search search=new Search();
		 search.setQueryBeginTimeStr(queryBeginTimeStr);
		 search.setQueryEndTimeStr(queryEndTimeStr);
		 List<SearchCount> searchs=searchDao.findByCount(search);
		 return searchs;
	}

}
