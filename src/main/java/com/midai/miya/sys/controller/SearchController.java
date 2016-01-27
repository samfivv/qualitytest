package com.midai.miya.sys.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.midai.miya.constant.Constant;
import com.midai.miya.controller.BaseController;
import com.midai.miya.easyui.Grid;
import com.midai.miya.sys.model.Search;
import com.midai.miya.sys.model.SearchCount;
import com.midai.miya.sys.service.SearchService;
@Controller
@RequestMapping("/search")
public class SearchController extends BaseController {
	@Autowired
	private SearchService searchService;
	private static final long serialVersionUID = -755209043445589325L;
	
	/**
	 * 查询搜索记录
	 * 王梦圆
	 * 2015年6月15日
	 */
	@RequestMapping("/findAll")
	public @ResponseBody Grid findAll(Search search,HttpServletRequest request){
		Grid grid=new Grid();
		List<Search> list=searchService.findAll(search, this.getPage(request));
		long count=searchService.findCount(search);
		grid.setRows(list);
		grid.setTotal(count);
		return grid;
	}
	
	/**
	 * 导出搜索
	 * 王梦圆
	 * 2015年6月15日
	 */
	@RequestMapping(value = "/exportSearch", method = RequestMethod.POST)
	public void exportSearch(Search search, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		this.addLog(request, "导出搜索", Constant.LOG_TYPE_EXPORT);
		String searchContent=search.getSearchContent();
		searchContent=new String(searchContent.getBytes("iso-8859-1"),"utf-8");
		String userNickname=search.getUserNickname();
		userNickname=new String(userNickname.getBytes("iso-8859-1"),"utf-8");
		List<Search> searchs=searchService.findAll(search,this.getExportPage(request));
		super.doExport(request, response, searchs, "搜索关键词", "搜索关键词",
				this.getHeadForSearch(), this.getColumnForSearch());
	}
	private String[] getHeadForSearch() {
		return new String[] { "搜索关键词", "搜索结果条数","用户昵称","搜索时间"};
	}
	private String[] getColumnForSearch() {
		return new String[] { "searchContent", "searchResultCount","userNickname","searchTimeStr" };
	}
	
	/**
	 * 热门搜索词
	 * 王梦圆
	 * 2015年6月16日
	 */
	@RequestMapping("/findByCount")
	public @ResponseBody List<SearchCount> findByCount(String queryBeginTimeStr,String queryEndTimeStr){
		List<SearchCount> searchs=searchService.findByCount(queryBeginTimeStr,queryEndTimeStr);
		return searchs;
	}
}
