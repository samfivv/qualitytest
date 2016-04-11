package com.midai.miya.brand.controller;

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

import com.midai.miya.brand.model.Brand;
import com.midai.miya.brand.service.BrandService;
import com.midai.miya.constant.Constant;
import com.midai.miya.controller.BaseController;
import com.midai.miya.easyui.Grid;
import com.midai.miya.user.vo.Result;
import com.midai.miya.utils.FileHttp;
import com.midai.miya.utils.UUIDUtil;
@Controller
@RequestMapping("/brand")
public class BrandController extends BaseController {
 
	private static final long serialVersionUID = -7968523323054952398L;
    @Autowired
	private BrandService brandService;
    
    
    @RequestMapping("/findById")
    public String findById(String brandId,HttpServletRequest request){
    	Brand brand=brandService.findById(brandId);
    	request.setAttribute("brand", brand);
    	return "/securityJsp/brand/brandForm";
    }
    
    @RequestMapping("/closeBrand")
    public @ResponseBody Result closeBrand(Brand brand,HttpServletRequest request){
    	Result result=new Result();
    	brand.setBrandState(2);
    	brandService.update(brand);
    	result.setSuccess(true);
    	result.setMsg("取消成功");
    	this.addLog(request, "取消品牌", Constant.LOG_TYPE_UPDATE);
    	return result;
    }    
    
    @RequestMapping("/openBrand")
    public @ResponseBody Result openBrand(Brand brand,HttpServletRequest request){
    	Result result=new Result();
    	brand.setBrandState(1);
    	brandService.update(brand);
    	result.setSuccess(true);
    	result.setMsg("恢复成功");
    	this.addLog(request, "恢复品牌", Constant.LOG_TYPE_UPDATE);
    	return result;
    }  
    
    @RequestMapping("/deleteBrand")
    public @ResponseBody Result deleteBrand(Brand brand,HttpServletRequest request){
    	Result result=new Result();
    	brandService.delete(brand);
    	result.setSuccess(true);
    	result.setMsg("删除成功");
    	this.addLog(request, "删除品牌", Constant.LOG_TYPE_UPDATE);
    	return result;
    }    
    
    
    @RequestMapping("/findAll")
    public @ResponseBody Grid findAll(Brand brand,HttpServletRequest request){
    	this.addLog(request, "查询品牌", Constant.LOG_TYPE_SELECT);
    	Grid grid=new Grid();
    	List<Brand> brands=brandService.findByConditions(brand,this.getPage(request));
    	long count=brandService.findByConditionsCount(brand);
    	grid.setRows(brands);
    	grid.setTotal(count);
    	return grid;
    }
    @RequestMapping("/save")
    public @ResponseBody Result save(Brand brand,HttpServletRequest request){
    	Result result=new Result();
    	//SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	if("".equals(brand.getBrandTitle().replace(" ", ""))){
    		result.setSuccess(false);
    		result.setMsg("品牌名称不能为空");
    		return result;
    	}
    	String brandText=brand.getBrandText().replaceAll("&nbsp;", "").replaceAll("<br />", "").replaceAll(" ", "");
    	if("".equals(brandText)){
    		result.setSuccess(false);
    		result.setMsg("品牌描述不能为空");
    		return result;
    	}
    	int count=brandService.findByBrandTitle(brand);
    	if(count>0){
    		result.setSuccess(false);
    		result.setMsg("品牌已存在");
    		return result;
    	}
    	if("".equals(brand.getUserId().replace(" ", ""))){
    		result.setSuccess(false);
    		result.setMsg("品牌所属会员不能为空");
    		return result;
    	}
    	
    	brand.setBrandId(UUIDUtil.getUUID());
    	brand.setCreateTime(new Date());
    	brand.setBrandState(1);
    	brandService.save(brand);
    	result.setSuccess(true);
    	result.setMsg("保存成功");
    	this.addLog(request, "添加品牌", Constant.LOG_TYPE_ADD);
    	return result;
    }
    @RequestMapping("/update")
    public @ResponseBody Result update(Brand brand,HttpServletRequest request){
    	//SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	Result result=new Result();
    	if("".equals(brand.getBrandTitle().replace(" ", ""))){
    		result.setSuccess(false);
    		result.setMsg("品牌名称不能为空");
    		return result;
    	}
    	
    	String brandText=brand.getBrandText().replaceAll("&nbsp;", "").replaceAll("<br />", "").replaceAll(" ", "");
    	if("".equals(brandText)){
    		result.setSuccess(false);
    		result.setMsg("品牌描述不能为空");
    		return result;
    	}
    	int count=brandService.findByBrandTitle(brand);
    	if(count>0){
    		result.setSuccess(false);
    		result.setMsg("品牌已存在");
    		return result;
    	}
    	if("".equals(brand.getUserId().replace(" ", ""))){
    		result.setSuccess(false);
    		result.setMsg("品牌所属会员不能为空");
    		return result;
    	}    	
    	brandService.update(brand);
    	result.setSuccess(true);
    	result.setMsg("修改成功");
    	this.addLog(request, "修改品牌", Constant.LOG_TYPE_UPDATE);
    	return result;
    }


    @RequestMapping(value = "/exportBrand", method = RequestMethod.POST)
	public void exportBrand(Brand brand,HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		this.addLog(request, "导出品牌信息", Constant.LOG_TYPE_EXPORT);
		List<Brand> brands = brandService.findByConditions(brand,this.getExportPage(request));
		super.doExport(request, response, brands, "品牌名称", "品牌管理",
				this.getHeadForBrand(), this.getColumnForBrand());
	}
	private String[] getHeadForBrand() {
		return new String[] { "品牌名称", "品牌排序","生产企业","所属会员","品牌状态","创建时间"};
	}
	private String[] getColumnForBrand() {
		return new String[] { "brandTitle", "brandSort","brandEnterprise","userDesc","brandState","brandCreateTime"};
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
	      String logoPathDir = "/upload/images/brand/" + dateformat.format(new Date());
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
	      String logoPathDir = "/upload/images/brand/" + dateformat.format(new Date());
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