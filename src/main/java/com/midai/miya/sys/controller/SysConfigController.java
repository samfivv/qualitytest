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
import com.midai.miya.sys.model.Log;
import com.midai.miya.sys.model.Operator;
import com.midai.miya.sys.model.SysConfig;
import com.midai.miya.sys.service.SysConfigService;
import com.midai.miya.user.vo.Result;
import com.midai.miya.utils.UUIDUtil;

@Controller
@RequestMapping("/sysConfig")
public class SysConfigController extends BaseController {
	private static final long serialVersionUID = -7642954000913884440L;
	@Autowired
	private SysConfigService sysConfigService;

	@RequestMapping("/findSysConfig")
	@ResponseBody
	public Grid findSysConfig(SysConfig sysConfig, HttpServletRequest request) {
		Grid grid = new Grid();
		List<SysConfig> sysConfigList = sysConfigService.find(sysConfig,
				this.getPage(request));
		long total = sysConfigService.findCount(sysConfig,
				this.getPage(request));
		grid.setRows(sysConfigList);
		grid.setTotal(total);
		return grid;
	}
	
	@RequestMapping("/findConfigById")
	@ResponseBody
	public SysConfig findConfigById(String sysConfigId,HttpServletRequest request){
		this.addLog(request, "查看该系统参数信息", Constant.LOG_TYPE_DELETE);
		SysConfig sysConfig=sysConfigService.findConfigById(sysConfigId);
		return sysConfig;
	}
	
	/**
	 *  导出系统参数
	 *  黄扬仲
	 *  2015年4月28日
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/exportSysConfig" , method = RequestMethod.POST)
	public void exportSysConfig(SysConfig sysConfig,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		this.addLog(request, "导出系统参数", Constant.LOG_TYPE_EXPORT);
		String sysConfigKey=sysConfig.getSysConfigKey();
		String sysConfigValue=sysConfig.getSysConfigValue();
		String sysConfigDesc=sysConfig.getSysConfigDesc();
		sysConfigKey=new String(sysConfigKey.getBytes("iso-8859-1"),"utf-8");
		sysConfigValue=new String(sysConfigValue.getBytes("iso-8859-1"),"utf-8");
		sysConfigDesc=new String(sysConfigDesc.getBytes("iso-8859-1"),"utf-8");
		sysConfig.setSysConfigKey(sysConfigKey);
		sysConfig.setSysConfigValue(sysConfigValue);
		sysConfig.setSysConfigDesc(sysConfigDesc);
		List<SysConfig> sysConfigList = sysConfigService.find(sysConfig,
				this.getExportPage(request));
		 super.doExport(request, response, sysConfigList,"系统参数设置","系统参数设置",this.getHeadForSysConfig(), this.getColumnForSysConfig());
	}
	private String[] getHeadForSysConfig(){
		return new String[]{
				"参数名称","参数值","参数描述"
		};
	}
	private String[] getColumnForSysConfig(){
		return new String[]{
				"sysConfigKey","sysConfigValue","sysConfigDesc"
		};
	}
	
	/**
	 * 根据operator导出log表中的信息
	 * 彭坤
	 * 2015年4月29日
	 */
	@RequestMapping(value = "/exportSysLog" , method = RequestMethod.POST)
	public void exportSysLog(Operator operator,Log log,HttpServletRequest request, HttpServletResponse response)throws UnsupportedEncodingException{
		this.addLog(request, "导出操作日志信息", Constant.LOG_TYPE_EXPORT);
		String logContent=log.getLogContent();
		logContent=new String(logContent.getBytes("iso-8859-1"),"utf-8");
		log.setLogContent(logContent);
		List<Log> logList = sysConfigService.findLogByOperatorId(operator, log, this.getExportPage(request));
		 super.doExport(request, response, logList,"操作日志","操作日志",this.getHeadForSysLog(), this.getColumnForSysLog());
	}
	private String[] getHeadForSysLog(){
		return new String[]{
				"登录名", "操作时间","操作","日志内容"
		};
	}
	private String[] getColumnForSysLog(){
		return new String[]{
				"operatorName","createTimeStr","logTypeStr","logContent"
		};
	}

	@RequestMapping("/save")
	@ResponseBody
	public Result save(SysConfig sysConfig,HttpServletRequest request) {
		this.addLog(request, "添加系统参数", Constant.LOG_TYPE_ADD);
		Result result = new Result();
		if(sysConfig.getSysConfigKey()!=null){
			sysConfig.setSysConfigKey(sysConfig.getSysConfigKey().replace(" ", ""));
		}
		int exist=sysConfigService.findConfigName(sysConfig);
		if(exist>=1){
			result.setSuccess(false);
			result.setMsg("参数名已存在");
		}else if(sysConfig.getSysConfigKey().trim().equals("")||sysConfig.getSysConfigValue().trim().equals("")
					||sysConfig.getSysConfigDesc().trim().equals("")){
				result.setMsg("不能为空格");
				result.setSuccess(false);
		}else{
			sysConfig.setSysConfigId(UUIDUtil.getUUID());
			sysConfig.setSysConfigCreateTime(new Date());
			sysConfigService.save(sysConfig);
			result.setMsg("添加成功");
			result.setSuccess(true);
		}
		return result;
	}

	@RequestMapping("/update")
	@ResponseBody
	public Result update(SysConfig sysConfig,HttpServletRequest request) {
		this.addLog(request, "修改系统参数", Constant.LOG_TYPE_UPDATE);
		Result result = new Result();
		if(sysConfig.getSysConfigKey()!=null){
			sysConfig.setSysConfigKey(sysConfig.getSysConfigKey().replace(" ", ""));
		}
		int exist=sysConfigService.findConfigName(sysConfig);
		if(exist>=1){
			result.setSuccess(false);
			result.setMsg("用户名已存在");
		}else if(sysConfig.getSysConfigKey().trim().equals("")||sysConfig.getSysConfigValue().trim().equals("")
				||sysConfig.getSysConfigDesc().trim().equals("")){
			result.setMsg("不能为空格");
			result.setSuccess(false);
		}else{
			sysConfigService.update(sysConfig);
			result.setMsg("修改成功");
			result.setSuccess(true);
		}
		return result;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(String sysConfigId,HttpServletRequest request) {
		this.addLog(request, "删除系统参数", Constant.LOG_TYPE_DELETE);
		Result result = new Result();
		sysConfigService.delete(sysConfigId);
		result.setSuccess(true);
		return result;
	}
/**
 * 根据operator对象查询Log
 * 彭坤
 * 2015年4月28日
 */
	@RequestMapping("/findLogByOperatorId")
	@ResponseBody
	public Grid findLogByOperatorId(Operator operator,Log log, HttpServletRequest request) {
		List<Log> logs = sysConfigService.findLogByOperatorId(operator,log,this.getPage(request));
		Long count=sysConfigService.findCountLog(operator, log);
		Grid grid = new Grid();
		grid.setTotal(count);
		grid.setRows(logs);
		return grid;
	}

}
