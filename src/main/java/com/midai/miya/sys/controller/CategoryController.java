package com.midai.miya.sys.controller;

import java.io.File;
import java.io.IOException;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;






import com.alibaba.fastjson.JSON;
import com.midai.miya.constant.Constant;
import com.midai.miya.controller.BaseController;
import com.midai.miya.easyui.Grid;
import com.midai.miya.sys.model.Category;
import com.midai.miya.sys.model.Operator;
import com.midai.miya.sys.service.CategoryService;
import com.midai.miya.user.vo.Result;
import com.midai.miya.utils.UUIDUtil;

@Controller
@RequestMapping("/category")
public class CategoryController extends BaseController{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8503244709665339796L;
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("/findCategorys")
	@ResponseBody
	public Grid findSysConfig(Category category,HttpServletRequest request){
		Grid grid=new Grid();
		List<Category> categorys=categoryService.findAllCategory(category,this.getPage(request));
		long total=categoryService.findCount(category, this.getPage(request));
		grid.setRows(categorys);
		grid.setTotal(total);
		return grid;
	}
	@RequestMapping("/save")   
	@ResponseBody
	public Result save(Category category,HttpServletRequest request){
		this.addLog(request, "添加类型", Constant.LOG_TYPE_ADD);
        Operator opeator=this.getCurrentOperator(request);
		Result result=new Result();
		if(category.getCategoryName()!=null){
			category.setCategoryName(category.getCategoryName().replace(" ", ""));
		}
		int exist=categoryService.findCategoryName(category);
		if(exist>=1){
			result.setSuccess(false);
			result.setMsg("用户名已存在");
		}else if(category.getCategoryName().trim().equals("")){
			result.setMsg("不能为空格");
			result.setSuccess(false);
		}else if(category.getCategoryImgUrl()==null){
			result.setMsg("选择一张图片");
			result.setSuccess(false);
		}else{  
		        category.setCategoryId(UUIDUtil.getUUID());
		  		category.setCategoryName(category.getCategoryName());
		  		category.setCategoryState(category.getCategoryState());
		  		category.setCategoryCretor(opeator.getOperatorName());
		  		category.setCategoryCreattime(new Date());
		  		categoryService.save(category);
		  		result.setMsg("添加成功");
		  		result.setSuccess(true);
				}
		
		return result;
		}
	@RequestMapping("/plupload")	
	public @ResponseBody Map<String, Object> plupload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		HttpSession session = request.getSession(); 
	      //目录命名格式如：*files2013-03-05-22
	      SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
	      //构建图片保存的目录
	      String logoPathDir = "/upload/images/category/" + dateformat.format(new Date());
	      //得到图片保存目录的真实路径
	      String logoRealPathDir = session.getServletContext().getRealPath("/")+logoPathDir;
	      //根据真实路径创建目录
	      File logoSaveFile = new File(logoRealPathDir);
	      if (!logoSaveFile.exists())
	          logoSaveFile.mkdirs();
	          try {
	        	String fileName = file.getOriginalFilename();  
	        	if(fileName!=null&&!"".equals(fileName)){
	        		String temp=fileName.substring(fileName.lastIndexOf("."));
	        		fileName=UUIDUtil.getUUID()+temp;
	        	}
	            System.out.println(logoRealPathDir);  
	            File targetFile = new File(logoRealPathDir, fileName);  
	            file.transferTo(targetFile);  
	            map.put("status", true);
	            map.put("fileUrl", logoPathDir + "/" + fileName);
			} catch (Exception e) {
				map.put("status", false);
				e.printStackTrace();
			}
		return map;
	}
	 
	@RequestMapping("/update")
	@ResponseBody
	public Result update(Category category,HttpServletRequest request){
		this.addLog(request, "修改类型", Constant.LOG_TYPE_UPDATE);
		Result result=new Result();
		if(category.getCategoryName()!=null){
			category.setCategoryName(category.getCategoryName().replace(" ", ""));
		}
		int exist=categoryService.findCategoryName(category);
		request.setAttribute("userName", getCurrentOperator(request));
		if(exist>=1){
			result.setSuccess(false);
			result.setMsg("用户名已存在");
		}else if(category.getCategoryName().trim().equals("")){
			result.setMsg("不能为空格");
			result.setSuccess(false);
		}else{
			 category.setCategoryName(category.getCategoryName());
			 category.setCategoryState(category.getCategoryState());
			 categoryService.update(category);
			 result.setMsg("修改成功");
			 result.setSuccess(true);
		}
		return result;
	}
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(String categoryId,HttpServletRequest request){
		this.addLog(request, "删除类型", Constant.LOG_TYPE_DELETE);
		Result result=new Result();
		categoryService.delete(categoryId);
		result.setSuccess(true);
		return result;
	}
	/**
	 * 根据id查看类别信息
	 * 王梦圆
	 * 2015年4月30日
	 */
	@RequestMapping("/findCategoryById")
	@ResponseBody
	public Category findCategoryById(String categoryId,HttpServletRequest request){
		this.addLog(request, "查看该类型信息", Constant.LOG_TYPE_SELECT);
		Category category=categoryService.findCategoryById(categoryId);
		return category;
	}
	
	/**
	 * 导出
	 * 王梦圆
	 * 2015年5月5日
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/exportCategory", method = RequestMethod.POST)
	public void exportCategory(Category category, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		this.addLog(request, "导出类型信息", Constant.LOG_TYPE_EXPORT);
		String categoryName=category.getCategoryName();
		String categoryCreator=category.getCategoryCretor();
		categoryName=new String(categoryName.getBytes("iso-8859-1"),"utf-8");
		categoryCreator=new String(categoryCreator.getBytes("iso-8859-1"),"utf-8");
		category.setCategoryName(categoryName);
		category.setCategoryCretor(categoryCreator);
		List<Category> categoryList = categoryService.findAllCategory(category, this.getExportPage(request));
		super.doExport(request, response, categoryList, "分类管理", "分类管理",
				this.getHeadForCategory(), this.getColumnForCategory());
	}

	private String[] getHeadForCategory() {
		return new String[] { "类型名称", "创建时间", "创建者", "类型状态","类型图片路径" };
	}

	private String[] getColumnForCategory() {
		return new String[] { "categoryName", "categoryCreattimeStr", "categoryCretor",
				"categoryStateStr","categoryImgUrl" };
	}
	/**
	 * 查询父Id为0的类别
	 * 王梦圆
	 * 2015年11月10日
	 */
	@RequestMapping("/findCategoryByParentId")
	public @ResponseBody List<Category> findCategoryByParentId(HttpServletRequest request){
		List<Category> categorys=categoryService.findCategoryByParentId();
		return categorys;
	}


}
