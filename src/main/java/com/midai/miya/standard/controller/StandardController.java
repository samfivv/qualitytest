package com.midai.miya.standard.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.midai.miya.constant.Constant;
import com.midai.miya.controller.BaseController;
import com.midai.miya.easyui.Grid;
import com.midai.miya.standard.model.Standard;
import com.midai.miya.standard.service.StandardService;
import com.midai.miya.user.vo.Result;
import com.midai.miya.utils.FileHttp;
import com.midai.miya.utils.UUIDUtil;

@Controller
@RequestMapping("/standard")
public class StandardController extends BaseController {
 
	private static final long serialVersionUID = -7968523323054952398L;
    @Autowired
	private StandardService standardService;
    
    
    @RequestMapping("/findById")
    public String findById(String standardId,HttpServletRequest request){
    	Standard standard=standardService.findById(standardId);
    	request.setAttribute("standard", standard);
    	return "/securityJsp/standard/standardForm";
    }
    
    @RequestMapping("/closeStandard")
    public @ResponseBody Result closeStandard(Standard standard,HttpServletRequest request){
    	Result result=new Result();
    	standard.setStandardState(2);
    	standardService.update(standard);
    	result.setSuccess(true);
    	result.setMsg("取消成功");
    	this.addLog(request, "取消标准", Constant.LOG_TYPE_UPDATE);
    	return result;
    }    
    @RequestMapping("/openStandard")
    public @ResponseBody Result openStandard(Standard standard,HttpServletRequest request){
    	Result result=new Result();
    	standard.setStandardState(1);
    	standardService.update(standard);
    	result.setSuccess(true);
    	result.setMsg("恢复成功");
    	this.addLog(request, "恢复标准", Constant.LOG_TYPE_UPDATE);
    	return result;
    }  
    
    @RequestMapping("/deleteStandard")
    public @ResponseBody Result deleteStandard(Standard standard,HttpServletRequest request){
    	Result result=new Result();
    	standardService.delete(standard);
    	result.setSuccess(true);
    	result.setMsg("删除成功");
    	this.addLog(request, "删除标准", Constant.LOG_TYPE_UPDATE);
    	return result;
    }    
    
    
    @RequestMapping("/findAll")
    public @ResponseBody Grid findAll(Standard standard,HttpServletRequest request){
    	this.addLog(request, "查询标准", Constant.LOG_TYPE_SELECT);
    	Grid grid=new Grid();
    	List<Standard> standards=standardService.findByConditions(standard,this.getPage(request));
    	long count=standardService.findByConditionsCount(standard);
    	grid.setRows(standards);
    	grid.setTotal(count);
    	return grid;
    }
    @RequestMapping("/save")
    public @ResponseBody Result save(Standard standard,HttpServletRequest request){
    	Result result=new Result();
    	//SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	if("".equals(standard.getStandardTitle().replace(" ", ""))){
    		result.setSuccess(false);
    		result.setMsg("标准标题不能为空");
    		return result;
    	}
    	String standardText=standard.getStandardText().replaceAll("&nbsp;", "").replaceAll("<br />", "").replaceAll(" ", "");
    	if("".equals(standardText)){
    		result.setSuccess(false);
    		result.setMsg("标准内容不能为空");
    		return result;
    	}
    	int count=standardService.findByStandardTitle(standard.getStandardTitle(),standard.getStandardId());
    	if(count>0){
    		result.setSuccess(false);
    		result.setMsg("标准标题已存在");
    		return result;
    	} 
    	standard.setStandardId(UUIDUtil.getUUID());
    	standard.setCreateTime(new Date());
    	standard.setStandardState(1);
    	standardService.save(standard);
    	result.setSuccess(true);
    	result.setMsg("保存成功");
    	this.addLog(request, "添加标准", Constant.LOG_TYPE_ADD);
    	return result;
    }
    @RequestMapping("/update")
    public @ResponseBody Result update(Standard standard,HttpServletRequest request){
    	//SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	Result result=new Result();
    	if("".equals(standard.getStandardTitle().replace(" ", ""))){
    		result.setSuccess(false);
    		result.setMsg("标准标题不能为空");
    		return result;
    	}
    	long count=standardService.findByStandardTitle(standard.getStandardTitle(),standard.getStandardId());
    	if(count>0){
    		result.setSuccess(false);
    		result.setMsg("标准标题已存在");
    		return result;
    	}
    	
    	standardService.update(standard);
    	result.setSuccess(true);
    	result.setMsg("修改成功");
    	this.addLog(request, "修改标准", Constant.LOG_TYPE_UPDATE);
    	return result;
    }


    @RequestMapping(value = "/exportStandard", method = RequestMethod.POST)
	public void exportStandard(Standard standard,HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		this.addLog(request, "导出标准信息", Constant.LOG_TYPE_EXPORT);
		List<Standard> standards = standardService.findByConditions(standard,this.getExportPage(request));
		super.doExport(request, response, standards, "标准标题", "标准管理",
				this.getHeadForStandard(), this.getColumnForStandard());
	}
	private String[] getHeadForStandard() {
		return new String[] { "标准标题", "标准排序","标准状态","创建时间"};
	}
	private String[] getColumnForStandard() {
		return new String[] { "standardTitle", "standardSort","standardState","standardCreateTime"};
	}
	
	@RequestMapping("/plupload")	
	public @ResponseBody Map<String, Object> plupload( HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		HttpSession session = request.getSession(); 
		 MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
   		Iterator item = multipartRequest.getFileNames();
   		MultipartFile files = null;
   		while (item.hasNext()) {
   		String fileNames = (String) item.next();
   		files = multipartRequest.getFile(fileNames);
   		  long maxSize = 1000000;  
   		// 检查文件大小
   		if (files.getSize() > maxSize) {
   			map.put("error", 1);
   			map.put("message", "上传文件大小超过限制。");
   		    return map;
   		}
   		}
	      //目录命名格式如：*files2013-03-05-22
	      SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
	      //构建图片保存的目录
	      String logoPathDir = "/upload/images/copywriter/" + dateformat.format(new Date());
	      //得到图片保存目录的真实路径
	      String logoRealPathDir =session.getServletContext().getRealPath("/")+logoPathDir;
	      //根据真实路径创建目录  本地临时文件夹
	    /*  File logoSaveFile = new File(logoRealPathDir);
	      if (!logoSaveFile.exists())
	          logoSaveFile.mkdirs();*/
			//创建文件夹
	          try {
	        	String fileName = files.getOriginalFilename();
	        	if(fileName!=null&&!"".equals(fileName)){
	        		String temp=fileName.substring(fileName.lastIndexOf("."));
	        		fileName=UUIDUtil.getUUID()+temp;
	        	}
	            System.out.println(logoRealPathDir);  
	            //File targetFile = new File(logoRealPathDir, fileName);  
	            //file.transferTo(targetFile);  
	            boolean uploadState=FileHttp.uploadFileWithHttpMime(logoPathDir, fileName,files);//上传到服务器
	            if(uploadState){
	            	 map.put("error", 0);
	 	            map.put("url", Constant.GET_IMG_SERVER_ADDR+logoPathDir + "/" + fileName);
	            }
			} catch (Exception e) {
				map.put("error", 1);
				map.put("message", "上传失败");
				e.printStackTrace();
			}
		return map;
	}
	@RequestMapping("/upload")	
	public @ResponseBody Map<String, Object> plupload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		HttpSession session = request.getSession(); 
	      //目录命名格式如：*files2013-03-05-22
	      SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
	      //构建图片保存的目录
	      String logoPathDir = "/upload/images/copywriter/" + dateformat.format(new Date());
	      //得到图片保存目录的真实路径
	      String logoRealPathDir =session.getServletContext().getRealPath("/")+logoPathDir;
	      //根据真实路径创建目录  本地临时文件夹
	    /*  File logoSaveFile = new File(logoRealPathDir);
	      if (!logoSaveFile.exists())
	          logoSaveFile.mkdirs();*/
	          try {
	        	String fileName = file.getOriginalFilename();  
	        	if(fileName!=null&&!"".equals(fileName)){
	        		String temp=fileName.substring(fileName.lastIndexOf("."));
	        		fileName=UUIDUtil.getUUID()+temp;
	        	}
	            System.out.println(logoRealPathDir);  
	            //File targetFile = new File(logoRealPathDir, fileName);  
	            //file.transferTo(targetFile);  
	            boolean uploadState=FileHttp.uploadFileWithHttpMime(logoPathDir, fileName,file);//上传到服务器
	            if(uploadState){
	            	 map.put("status", true);
	 	            map.put("fileUrl", Constant.GET_IMG_SERVER_ADDR+logoPathDir + "/" + fileName);
	            }
			} catch (Exception e) {
				map.put("status", false);
				e.printStackTrace();
			}
		return map;
	}
}
