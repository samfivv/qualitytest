package com.midai.miya.copywriter.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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

import com.midai.miya.constant.Constant;
import com.midai.miya.controller.BaseController;
import com.midai.miya.copywriter.model.Copywriter;
import com.midai.miya.copywriter.service.CopywriterService;
import com.midai.miya.easyui.Grid;
import com.midai.miya.user.vo.Result;
import com.midai.miya.utils.FileHttp;
import com.midai.miya.utils.UUIDUtil;
@Controller
@RequestMapping("/copywriter")
public class CopyWriterController extends BaseController {

	private static final long serialVersionUID = 7355508944473521645L;
	@Autowired
	private CopywriterService copywriterService;
	@RequestMapping("/findAll")
	public @ResponseBody Grid findAll(Copywriter copywriter,HttpServletRequest request){
		this.addLog(request, "查询文案信息", Constant.LOG_TYPE_SELECT);
		Grid grid=new Grid();
		List<Copywriter> copywriters=copywriterService.findByConditions(copywriter, this.getPage(request));
		long count=copywriterService.findByConditionsCount(copywriter);
		grid.setRows(copywriters);
		grid.setTotal(count);
		return grid;
	}
	
	@RequestMapping("/save")
	public @ResponseBody Result save(Copywriter copywriter,HttpServletRequest request){
		this.addLog(request, "保存文案", Constant.LOG_TYPE_ADD);
		Result result=new Result();
		if(copywriter.getCopywriterImgUrl()==null){
			result.setSuccess(false);
			result.setMsg("请选择一张图片");
			return result;
		} 
		 if(copywriter.getCopywriterUrl().length()>7){
		 if(!("http://".equals(copywriter.getCopywriterUrl().substring(0, 7))||"https://".equals(copywriter.getCopywriterUrl().substring(0, 8)))){
			 result.setSuccess(false);
			 result.setMsg("链接不合法");
			 return result;
		 }
		 }else{
			 result.setSuccess(false);
			 result.setMsg("链接不合法");
			 return result;
		 }
		long count=copywriterService.findByName(copywriter);
		if(count>0){
			result.setMsg("文案描述重复");
			result.setSuccess(false);
			return result;
		}
		copywriter.setCopywriterId(UUIDUtil.getUUID());
		copywriterService.save(copywriter);
		result.setSuccess(true);
		result.setMsg("保存成功");
		this.addLog(request, "添加文案", Constant.LOG_TYPE_ADD);

		return result;
	}
	@RequestMapping("/update")
	public @ResponseBody Result update(Copywriter copywriter,HttpServletRequest request){
		this.addLog(request, "修改文案", Constant.LOG_TYPE_UPDATE);
		Result result=new Result();
	
		if(copywriter.getCopywriterUrl().length()>7){
			 if(!("http://".equals(copywriter.getCopywriterUrl().substring(0, 7))||"https://".equals(copywriter.getCopywriterUrl().substring(0, 8)))){
				 result.setSuccess(false);
				 result.setMsg("链接不合法");
				 return result;
			 }
			 }else{
				 result.setSuccess(false);
				 result.setMsg("链接不合法");
				 return result;
			 }
		long count=copywriterService.findByName(copywriter);
		if(count>0){
			result.setMsg("文案描述重复");
			result.setSuccess(false);
			return result;
		} 
		copywriterService.update(copywriter);
		result.setSuccess(true);
		result.setMsg("修改成功");
		this.addLog(request, "修改文案", Constant.LOG_TYPE_UPDATE);
		return result;
	}
	@RequestMapping("/delete")
	public @ResponseBody Result delete(String copywriterId,HttpServletRequest request){
		this.addLog(request, "删除文案", Constant.LOG_TYPE_DELETE);
		Result result=new Result();
		Copywriter copywriter=new Copywriter();
		copywriter.setCopywriterId(copywriterId);
		copywriterService.delete(copywriter);
		result.setMsg("删除成功");
		result.setSuccess(true);
		this.addLog(request, "删除文案", Constant.LOG_TYPE_DELETE);
		return result;
	}
	@RequestMapping("/findById")
	public @ResponseBody Copywriter findById(String copywriterId,HttpServletRequest request){
		Copywriter copywriter=copywriterService.findById(copywriterId);
		this.addLog(request, "查看文案详情", Constant.LOG_TYPE_SELECT);
		return copywriter;
	}
	@RequestMapping("/plupload")	
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
	@RequestMapping(value = "/exportCopywriter", method = RequestMethod.POST)
	public void exportCopywriter(Copywriter copywriter,HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		this.addLog(request, "导出文案信息", Constant.LOG_TYPE_EXPORT);
		List<Copywriter> lists=copywriterService.findByConditions(copywriter, this.getExportPage(request));
		super.doExport(request, response, lists, "文案设置", "文案设置",
				this.getHeadForCopywriter(), this.getColumnForCopywriter());
	}
	private String[] getHeadForCopywriter() {
		return new String[] { "文案ID", "文案跳转链接", "文案描述","文案图片链接","文案顺序"};
	}
	private String[] getColumnForCopywriter() {
		return new String[] { "copywriterId", "copywriterUrl", "copywriterDesc","copywriterImgUrl","copywriterSort"};
	}
}
