package com.midai.miya.news.controller;

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
import com.midai.miya.news.model.News;
import com.midai.miya.news.service.NewsService;
import com.midai.miya.user.vo.Result;
import com.midai.miya.utils.FileHttp;
import com.midai.miya.utils.UUIDUtil;
@Controller
@RequestMapping("/news")
public class NewsController extends BaseController {
 
	private static final long serialVersionUID = -7968523323054952398L;
    @Autowired
	private NewsService newsService;
    
    
    @RequestMapping("/findById")
    public String findById(String newsId,HttpServletRequest request){
    	News news=newsService.findById(newsId);
    	request.setAttribute("news", news);
    	return "/securityJsp/news/newsForm";
    }
    
    @RequestMapping("/closeNews")
    public @ResponseBody Result closeNews(News news,HttpServletRequest request){
    	Result result=new Result();
    	news.setNewsState(2);
    	newsService.update(news);
    	result.setSuccess(true);
    	result.setMsg("取消成功");
    	this.addLog(request, "取消新闻", Constant.LOG_TYPE_UPDATE);
    	return result;
    }    
    @RequestMapping("/openNews")
    public @ResponseBody Result openNews(News news,HttpServletRequest request){
    	Result result=new Result();
    	news.setNewsState(1);
    	newsService.update(news);
    	result.setSuccess(true);
    	result.setMsg("恢复成功");
    	this.addLog(request, "恢复新闻", Constant.LOG_TYPE_UPDATE);
    	return result;
    }  
    
    @RequestMapping("/deleteNews")
    public @ResponseBody Result deleteNews(News news,HttpServletRequest request){
    	Result result=new Result();
    	newsService.delete(news);
    	result.setSuccess(true);
    	result.setMsg("删除成功");
    	this.addLog(request, "删除新闻", Constant.LOG_TYPE_UPDATE);
    	return result;
    }    
    
    
    @RequestMapping("/findAll")
    public @ResponseBody Grid findAll(News news,HttpServletRequest request){
    	this.addLog(request, "查询新闻", Constant.LOG_TYPE_SELECT);
    	Grid grid=new Grid();
    	List<News> newslist=newsService.findByConditions(news,this.getPage(request));
    	long count=newsService.findByConditionsCount(news);
    	grid.setRows(newslist);
    	grid.setTotal(count);
    	return grid;
    }
    @RequestMapping("/save")
    public @ResponseBody Result save(News news,HttpServletRequest request){
    	Result result=new Result();
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	if("".equals(news.getNewsTitle().replace(" ", ""))){
    		result.setSuccess(false);
    		result.setMsg("新闻标题不能为空");
    		return result;
    	}
    	String newText=news.getNewsText().replaceAll("&nbsp;", "").replaceAll("<br />", "").replaceAll(" ", "");
    	if("".equals(newText)){
    		result.setSuccess(false);
    		result.setMsg("新闻内容不能为空");
    		return result;
    	}
    	int count=newsService.findByNewsTitle(news.getNewsTitle(),news.getNewsId());
    	if(count>0){
    		result.setSuccess(false);
    		result.setMsg("新闻标题已存在");
    		return result;
    	} 
    	news.setNewsId(UUIDUtil.getUUID());
    	news.setCreateTime(new Date());
    	news.setNewsState(1);
    	newsService.save(news);
    	result.setSuccess(true);
    	result.setMsg("保存成功");
    	this.addLog(request, "添加新闻", Constant.LOG_TYPE_ADD);
    	return result;
    }
    @RequestMapping("/update")
    public @ResponseBody Result update(News news,HttpServletRequest request){
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	Result result=new Result();
    	if("".equals(news.getNewsTitle().replace(" ", ""))){
    		result.setSuccess(false);
    		result.setMsg("新闻标题不能为空");
    		return result;
    	}
    	long count=newsService.findByNewsTitle(news.getNewsTitle(),news.getNewsId());
    	if(count>0){
    		result.setSuccess(false);
    		result.setMsg("新闻标题已存在");
    		return result;
    	}
    	
    	newsService.update(news);
    	result.setSuccess(true);
    	result.setMsg("修改成功");
    	this.addLog(request, "修改新闻", Constant.LOG_TYPE_UPDATE);
    	return result;
    }


    @RequestMapping(value = "/exportNews", method = RequestMethod.POST)
	public void exportNews(News news,HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		this.addLog(request, "导出新闻信息", Constant.LOG_TYPE_EXPORT);
		List<News> newss = newsService.findByConditions(news,this.getExportPage(request));
		super.doExport(request, response, newss, "新闻标题", "新闻管理",
				this.getHeadForNews(), this.getColumnForNews());
	}
	private String[] getHeadForNews() {
		return new String[] { "新闻标题", "新闻排序","新闻状态","创建时间"};
	}
	private String[] getColumnForNews() {
		return new String[] { "newsTitle", "newsSort","newsState","newsCreateTime"};
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
	      String logoPathDir = "/upload/images/news/" + dateformat.format(new Date());
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

