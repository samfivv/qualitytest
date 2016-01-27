package com.midai.miya.sys.controller;

import java.io.UnsupportedEncodingException;
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
import com.midai.miya.sys.model.SensitiveWord;
import com.midai.miya.sys.service.SensitiveWordService;
import com.midai.miya.user.vo.Result;
import com.midai.miya.utils.PageUtil;
import com.midai.miya.utils.UUIDUtil;
@Controller
@RequestMapping("/sensitive")
public class SensitiveController extends BaseController{

	private static final long serialVersionUID = 1149436168447408077L;
	@Autowired
	private SensitiveWordService  sensitiveService;
	/**
	 * 查询敏感词
	 * 王梦圆
	 * 2015年5月19日
	 */
	@RequestMapping("/findAll")
	public @ResponseBody Grid findAll(SensitiveWord sensitiveWord,HttpServletRequest request){
		PageUtil page=this.getPage(request);
		List<SensitiveWord> sensitiveWords=sensitiveService.findAll(sensitiveWord, page);
		long count=sensitiveService.findAllCount(sensitiveWord);
		Grid grid=new Grid();
		grid.setRows(sensitiveWords);
		grid.setTotal(count);
		return grid;
	}
	/**
	 * 添加敏感词
	 * 王梦圆
	 * 2015年5月19日
	 */
	@RequestMapping("/save")
	public @ResponseBody Result save(SensitiveWord sensitiveWord,HttpServletRequest request){
		this.addLog(request, "添加敏感词", Constant.LOG_TYPE_ADD);
		Result result=new Result();
		sensitiveWord.setSensitiveWord(sensitiveWord.getSensitiveWord().replace(" ", ""));
		if(sensitiveWord.getSensitiveWord().trim().equals("")){
			result.setMsg("名称不能为空");
			result.setSuccess(false);
		}else{
		int extis=sensitiveService.findCountByName(sensitiveWord);
		if(extis>=1){
			result.setMsg("名称重复");
			result.setSuccess(false);
		}else{
			sensitiveWord.setSensitiveWordId(UUIDUtil.getUUID());
			sensitiveWord.setSensitiveWordCreateTime(new Date());
			sensitiveService.save(sensitiveWord);
			result.setMsg("添加成功");
			result.setSuccess(true);
		}
		}
		return result;
	}
	/**
	 * 修改敏感词
	 * 王梦圆
	 * 2015年5月19日
	 */
	@RequestMapping("/update")
	public @ResponseBody Result update(SensitiveWord sensitiveWord,HttpServletRequest request){
		this.addLog(request, "修改敏感词", Constant.LOG_TYPE_UPDATE);
		Result result=new Result();
		sensitiveWord.setSensitiveWord(sensitiveWord.getSensitiveWord().replace(" ", ""));
		if(sensitiveWord.getSensitiveWord().trim().equals("")){
			result.setMsg("名称不能为空");
			result.setSuccess(false);
		}else{
		int extis=sensitiveService.findCountByName(sensitiveWord);
		if(extis>=1){
			result.setMsg("名称重复");
			result.setSuccess(false);
		}else{
			sensitiveService.update(sensitiveWord);
			result.setMsg("修改成功");
			result.setSuccess(true);
		}
		}
		return result;
	}
	/**
	 * 删除敏感词
	 * 王梦圆
	 * 2015年5月19日
	 */
	@RequestMapping("/delete")
	public @ResponseBody Result delete(String sensitiveWordId,HttpServletRequest request){
		this.addLog(request, "删除敏感词", Constant.LOG_TYPE_DELETE);
		sensitiveService.delete(sensitiveWordId);
		Result result=new Result();
		result.setMsg("删除成功");
		result.setSuccess(true);
		return result;
	}
	/**
	 * 根据id查询这一条信息
	 * 王梦圆
	 * 2015年5月19日
	 */
	@RequestMapping("/findById")
	public @ResponseBody SensitiveWord findById(String sensitiveWordId,HttpServletRequest request){
		SensitiveWord sensitiveWord=sensitiveService.findById(sensitiveWordId);
		return sensitiveWord;
	}
	/**
	 * 导出敏感词信息
	 * 王梦圆
	 * 2015年5月19日
	 */
	@RequestMapping(value = "/exportSensitive", method = RequestMethod.POST)
	public void exportRole(SensitiveWord sensitiveWord, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		this.addLog(request, "导出敏感词", Constant.LOG_TYPE_EXPORT);
		String sensitiveword=sensitiveWord.getSensitiveWord();
		sensitiveword=new String(sensitiveword.getBytes("iso-8859-1"),"utf-8");
		List<SensitiveWord> sensitiveWords=sensitiveService.findAll(sensitiveWord,this.getExportPage(request));
		super.doExport(request, response, sensitiveWords, "敏感词设置", "敏感词设置",
				this.getHeadForSensitive(), this.getColumnForSensitive());
	}
	private String[] getHeadForSensitive() {
		return new String[] { "敏感词名称", "创建时间"};
	}
	private String[] getColumnForSensitive() {
		return new String[] { "sensitiveWord", "sensitiveWordCreateTimeStr" };
	}

}
