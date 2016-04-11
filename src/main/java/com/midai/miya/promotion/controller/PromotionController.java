package com.midai.miya.promotion.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
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
import com.midai.miya.promotion.model.Promotion;
import com.midai.miya.promotion.service.PromotionService;
import com.midai.miya.user.vo.Result;
import com.midai.miya.utils.FileHttp;
import com.midai.miya.utils.UUIDUtil;

@Controller
@RequestMapping("/promotion")
public class PromotionController extends BaseController {
 
	private static final long serialVersionUID = -7968523323054952398L;
    @Autowired
	private PromotionService promotionService;
    
    
    @RequestMapping("/findById")
    public String findById(String promotionId,HttpServletRequest request){
    	Promotion promotion=promotionService.findById(promotionId);
    	request.setAttribute("promotion", promotion);
    	return "/securityJsp/promotion/promotionForm";
    }
    
    @RequestMapping("/closePromotion")
    public @ResponseBody Result closePromotion(Promotion promotion,HttpServletRequest request){
    	Result result=new Result();
    	promotion.setPromotionState(2);
    	promotionService.update(promotion);
    	result.setSuccess(true);
    	result.setMsg("取消成功");
    	this.addLog(request, "取消促销", Constant.LOG_TYPE_UPDATE);
    	return result;
    }    
    @RequestMapping("/openPromotion")
    public @ResponseBody Result openPromotion(Promotion promotion,HttpServletRequest request){
    	Result result=new Result();
    	promotion.setPromotionState(1);
    	promotionService.update(promotion);
    	result.setSuccess(true);
    	result.setMsg("恢复成功");
    	this.addLog(request, "恢复促销", Constant.LOG_TYPE_UPDATE);
    	return result;
    }  
    
    @RequestMapping("/deletePromotion")
    public @ResponseBody Result deletePromotion(Promotion promotion,HttpServletRequest request){
    	Result result=new Result();
    	promotionService.delete(promotion);
    	result.setSuccess(true);
    	result.setMsg("删除成功");
    	this.addLog(request, "删除促销", Constant.LOG_TYPE_UPDATE);
    	return result;
    }    
    
    
    @RequestMapping("/findAll")
    public @ResponseBody Grid findAll(Promotion promotion,HttpServletRequest request){
    	this.addLog(request, "查询促销", Constant.LOG_TYPE_SELECT);
    	Grid grid=new Grid();
    	List<Promotion> promotions=promotionService.findByConditions(promotion,this.getPage(request));
    	long count=promotionService.findByConditionsCount(promotion);
    	grid.setRows(promotions);
    	grid.setTotal(count);
    	return grid;
    }
    @RequestMapping("/save")
    public @ResponseBody Result save(Promotion promotion,HttpServletRequest request){
    	Result result=new Result();
    	//SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	if("".equals(promotion.getPromotionTitle().replace(" ", ""))){
    		result.setSuccess(false);
    		result.setMsg("促销标题不能为空");
    		return result;
    	}
    	String promotionText=promotion.getPromotionText().replaceAll("&nbsp;", "").replaceAll("<br />", "").replaceAll(" ", "");
    	if("".equals(promotionText)){
    		result.setSuccess(false);
    		result.setMsg("促销内容不能为空");
    		return result;
    	}
    	int count=promotionService.findByPromotionTitle(promotion.getPromotionTitle(),promotion.getPromotionId());
    	if(count>0){
    		result.setSuccess(false);
    		result.setMsg("促销标题已存在");
    		return result;
    	} 
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	try {
    		promotion.setPromotionStartTime(sdf.parse(promotion.getPromotionStartTimeStr()));
 		} catch (ParseException e) {e.printStackTrace();}
    	
    	try {
    		promotion.setPromotionEffTime(sdf.parse(promotion.getPromotionEffTimeStr()));
 		} catch (ParseException e) {e.printStackTrace();}
    	
    	promotion.setPromotionId(UUIDUtil.getUUID());
    	promotion.setCreateTime(new Date());
    	promotion.setPromotionState(1);
    	promotionService.save(promotion);
    	result.setSuccess(true);
    	result.setMsg("保存成功");
    	this.addLog(request, "添加促销", Constant.LOG_TYPE_ADD);
    	return result;
    }
    @RequestMapping("/update")
    public @ResponseBody Result update(Promotion promotion,HttpServletRequest request){
    	//SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	Result result=new Result();
    	if("".equals(promotion.getPromotionTitle().replace(" ", ""))){
    		result.setSuccess(false);
    		result.setMsg("促销标题不能为空");
    		return result;
    	}
    	int count=promotionService.findByPromotionTitle(promotion.getPromotionTitle(),promotion.getPromotionId());
    	if(count>0){
    		result.setSuccess(false);
    		result.setMsg("促销标题已存在");
    		return result;
    	}
 
    	String s = promotion.getPromotionStartTimeStr();
    	Date date = null;
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
 		try {
			date = sdf.parse(promotion.getPromotionStartTimeStr()); 
		} catch (ParseException e1) {e1.printStackTrace();}
		promotion.setPromotionStartTime(date);
    	
		/*
		 这么写就会报错
    	try {
     		promotion.setPromotionEffTime(sdf.parse(promotion.getPromotionEffTimeStr()));
 		} catch (ParseException e) {e.printStackTrace();}
     	*/
		date = null;
 		try {
			date = sdf.parse(promotion.getPromotionEffTimeStr()); 
		} catch (ParseException e1) {e1.printStackTrace();}
		promotion.setPromotionEffTime(date);
		
    	promotionService.update(promotion);
    	result.setSuccess(true);
    	result.setMsg("修改成功");
    	this.addLog(request, "修改促销", Constant.LOG_TYPE_UPDATE);
    	return result;
    }


    @RequestMapping(value = "/exportPromotion", method = RequestMethod.POST)
	public void exportPromotion(Promotion promotion,HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		this.addLog(request, "导出促销信息", Constant.LOG_TYPE_EXPORT);
		List<Promotion> promotions = promotionService.findByConditions(promotion,this.getExportPage(request));
		super.doExport(request, response, promotions, "促销标题", "促销管理",
				this.getHeadForPromotion(), this.getColumnForPromotion());
	}
	private String[] getHeadForPromotion() {
		return new String[] { "促销标题", "促销排序","促销状态","创建时间"};
	}
	private String[] getColumnForPromotion() {
		return new String[] { "promotionTitle", "promotionSort","promotionState","promotionCreateTime"};
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