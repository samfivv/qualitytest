package com.midai.miya.sys.controller;

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
import com.midai.miya.sys.model.Subject;
import com.midai.miya.sys.model.SubjectInterest;
import com.midai.miya.sys.service.SubjectInterestService;
import com.midai.miya.sys.service.SubjectService;
import com.midai.miya.user.vo.Result;
import com.midai.miya.utils.FileHttp;
import com.midai.miya.utils.UUIDUtil;
@Controller
@RequestMapping("/subject")
public class SubjectController extends BaseController {
 
	private static final long serialVersionUID = -7968523323054952398L;
    @Autowired
	private SubjectService subjectService;
    @Autowired
    private SubjectInterestService subjectInterestService;
    
    @RequestMapping("/findAll")
    public @ResponseBody Grid findAll(Subject subject,HttpServletRequest request){
    	this.addLog(request, "查询活动", Constant.LOG_TYPE_SELECT);
    	Grid grid=new Grid();
    	List<Subject> subjects=subjectService.findByConditions(subject,this.getPage(request));
    	long count=subjectService.findByConditionsCount(subject);
    	grid.setRows(subjects);
    	grid.setTotal(count);
    	return grid;
    }
    @RequestMapping("/save")
    public @ResponseBody Result save(Subject subject,HttpServletRequest request){
    	Result result=new Result();
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	if("".equals(subject.getSubjectTitle().replace(" ", ""))){
    		result.setSuccess(false);
    		result.setMsg("活动标题不能为空");
    		return result;
    	}
    	if("".equals(subject.getSubjectAward().replace(" ", ""))){
    		result.setSuccess(false);
    		result.setMsg("奖励不能为空");
    		return result;
    	}
    	String subjectText=subject.getSubjectText().replaceAll("&nbsp;", "").replaceAll("<br />", "").replaceAll(" ", "");
    	if("".equals(subjectText)){
    		result.setSuccess(false);
    		result.setMsg("活动内容不能为空");
    		return result;
    	}
    	int count=subjectService.findBySubjectTitle(subject.getSubjectTitle(),subject.getSubjectId());
    	if(count>0){
    		result.setSuccess(false);
    		result.setMsg("活动标题已存在");
    		return result;
    	} 
    	subject.setSubjectId(UUIDUtil.getUUID());
    	subject.setSubjectCreateTime(new Date());
    	subject.setSubjectState(1);
    	subject.setSubjectParticipantsNumber(0);
    	try {
			subject.setSubjectStartTime(sdf.parse(subject.getSubjectStartTimeStr()));
			subject.setSubjectEndTime(sdf.parse(subject.getSubjectEndTimeStr()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	if(subject.getSubjectNeedSettingclub()==null||"".equals(subject.getSubjectNeedSettingclub())){
    		subject.setSubjectNeedSettingclub(2);
    	}
    	subjectService.save(subject);
    	result.setSuccess(true);
    	result.setMsg("保存成功");
    	this.addLog(request, "添加活动", Constant.LOG_TYPE_ADD);
    	return result;
    }
    @RequestMapping("/update")
    public @ResponseBody Result update(Subject subject,HttpServletRequest request){
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	Result result=new Result();
    	if("".equals(subject.getSubjectTitle().replace(" ", ""))){
    		result.setSuccess(false);
    		result.setMsg("活动标题不能为空");
    		return result;
    	}
    	int count=subjectService.findBySubjectTitle(subject.getSubjectTitle(),subject.getSubjectId());
    	if(count>0){
    		result.setSuccess(false);
    		result.setMsg("活动标题已存在");
    		return result;
    	}
    	try {
			subject.setSubjectStartTime(sdf.parse(subject.getSubjectStartTimeStr()));
			subject.setSubjectEndTime(sdf.parse(subject.getSubjectEndTimeStr()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	if(subject.getSubjectNeedSettingclub()==null||"".equals(subject.getSubjectNeedSettingclub())){
    		subject.setSubjectNeedSettingclub(2);
    	}
    	subjectService.update(subject);
    	result.setSuccess(true);
    	result.setMsg("修改成功");
    	this.addLog(request, "修改活动", Constant.LOG_TYPE_UPDATE);
    	return result;
    }
    @RequestMapping("/openSubject")
    public @ResponseBody Result openSubject(Subject subject,HttpServletRequest request){
    	Result result=new Result();
    	subject.setSubjectState(1);
    	subjectService.update(subject);
    	result.setSuccess(true);
    	result.setMsg("恢复成功");
    	this.addLog(request, "恢复活动", Constant.LOG_TYPE_UPDATE);
    	return result;
    }
    @RequestMapping("/deleteSubject")
    public @ResponseBody Result delete(String subjectId,HttpServletRequest request){
    	Result result=new Result();
    	Subject subject=new Subject();
    	subject.setSubjectId(subjectId);
    	subject.setSubjectState(2); 
    	subjectService.update(subject);
    	result.setSuccess(true);
    	result.setMsg("取消成功");
    	this.addLog(request, "取消活动", Constant.LOG_TYPE_UPDATE);
    	return result;
    }
    @RequestMapping("/findById")
    public String findById(String subjectId,HttpServletRequest request){
    	Subject subject=subjectService.findById(subjectId);
    	request.setAttribute("subject", subject);
    	return "/securityJsp/subject/subjectForm";
    }
    @RequestMapping(value = "/exportSubject", method = RequestMethod.POST)
	public void exportSubject(Subject subject,HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		this.addLog(request, "导出活动信息", Constant.LOG_TYPE_EXPORT);
		List<Subject> subjects=subjectService.findByConditions(subject,this.getExportPage(request));
		super.doExport(request, response, subjects, "活动管理", "活动管理",
				this.getHeadForSubject(), this.getColumnForSubject());
	}
	private String[] getHeadForSubject() {
		return new String[] { "活动标题", "活动排序","活动状态","创建时间"};
	}
	private String[] getColumnForSubject() {
		return new String[] { "subjectTitle", "subjectSort","subjectStateStr","subjectCreateTimeStr"};
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
	      String logoPathDir = "/upload/images/subject/" + dateformat.format(new Date());
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
	
	@RequestMapping("/findSubjectInterest")
	public @ResponseBody Grid findSubjectInterest(SubjectInterest subjectInterest,HttpServletRequest request){
		Grid grid=new Grid();
		List<SubjectInterest> subjectInterests=subjectInterestService.findByConditions(subjectInterest,this.getPage(request));
		long count=subjectInterestService.findByConditionsCount(subjectInterest);
		grid.setRows(subjectInterests);
		grid.setTotal(count);
		return grid;
	}
	@RequestMapping("/findSubjectInterestById")
	public @ResponseBody SubjectInterest findSubjectInterestById(String subjectInterestId,HttpServletRequest request){
		SubjectInterest subjectInterest=subjectInterestService.findSubjectInterestById(subjectInterestId);
		return subjectInterest;
	}
	@RequestMapping("/updateSubjectInterest")
	public @ResponseBody Result updateSubjectInterest(SubjectInterest subjectInterest,HttpServletRequest request){
		Result result = new Result();
		subjectInterestService.update(subjectInterest);
		result.setSuccess(true);
		result.setMsg("修改成功");
		return result;
	}
	@RequestMapping(value = "/exportSubjectInterest", method = RequestMethod.POST)
	public void exportSubjectInterest(SubjectInterest subjectInterest,HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		this.addLog(request, "导出活动信息", Constant.LOG_TYPE_EXPORT);
		List<SubjectInterest> subjects=subjectInterestService.findByConditions(subjectInterest,this.getExportPage(request));
		super.doExport(request, response, subjects, "活动管理", "活动管理",
				this.getHeadForSubjectInterest(), this.getColumnForSubjectInterest());
	}
	private String[] getHeadForSubjectInterest() {
		return new String[] { "活动标题","审核状态","排序","兴趣标题","兴趣简介","上传人昵称","更新时间","创建时间"};
	}
	private String[] getColumnForSubjectInterest() {
		return new String[] { "subjectTitle","subjectInterestStateStr", "subjectInterestSort","interestTitle",
				"interestDesc","userNickname","interestUpdateTimeStr","createTimeStr"};
	}
}
