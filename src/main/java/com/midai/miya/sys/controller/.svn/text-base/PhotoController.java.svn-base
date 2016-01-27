package com.midai.miya.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.midai.miya.constant.Constant;
import com.midai.miya.controller.BaseController;
import com.midai.miya.sys.model.Photo;
import com.midai.miya.sys.service.PhotoService;
import com.midai.miya.user.vo.Result;
import com.midai.miya.utils.PageUtil;
@Controller
@RequestMapping("/photo")
public class PhotoController extends BaseController {
 
	private static final long serialVersionUID = -5022541410743155925L;
	@Autowired
	private PhotoService photoService;
	
	@RequestMapping("/findAllPhoto")
	public @ResponseBody Result findAllPhoto(String photoName,Integer photoState,String userNickname,Integer page,Integer row,HttpServletRequest request){
		this.addLog(request, "查看用户相片", Constant.LOG_TYPE_SELECT);
		Result result=new Result();
		Map<String, Object> map=new HashMap<String, Object>();
		Photo photo=new Photo();
		photo.setPhotoName(photoName);
		photo.setPhotoState(photoState);
		photo.setUserNickname(userNickname);
		int count=(int) photoService.findByConditionsCount(photo);
		PageUtil pageUtil=new PageUtil();
		pageUtil.setPage(page);
		pageUtil.setRows(row);
		List<Photo> photos=photoService.findByConditions(photo, pageUtil);
		map.put("photos", photos);
		map.put("count", count);
		result.setData(map);
		result.setSuccess(true);
		return result;
	}
	@RequestMapping("/update")
	public @ResponseBody Result update(String photoId,HttpServletRequest request){
		this.addLog(request, "修改用户相片", Constant.LOG_TYPE_UPDATE);
		Result result=new Result();
		Photo photo=photoService.findById(photoId);
		photoService.update(photo);
		result.setSuccess(true);
		if(photo.getPhotoState()==1){
			result.setMsg("恢复成功");
		}else{
			result.setMsg("删除成功");
		}
		return result;
	}

}
