package com.midai.miya.sys.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.midai.miya.constant.Constant;
import com.midai.miya.controller.BaseController;
import com.midai.miya.easyui.Grid;
import com.midai.miya.sys.model.Interest;
import com.midai.miya.sys.model.InterestBoutique;
import com.midai.miya.sys.service.InterestBoutiqueService;
import com.midai.miya.sys.service.InterestService;
import com.midai.miya.user.vo.Result;
import com.midai.miya.utils.FileHttp;
import com.midai.miya.utils.UUIDUtil;
@Controller
@RequestMapping("/interestBoutique")
public class InterestBoutiqueController extends BaseController {

	private static final long serialVersionUID = 2175428298079887910L;
	
	@Autowired
	private InterestBoutiqueService interestBoutiqueService;
	@Autowired
	private InterestService interestService;
	
	/**
	 * 查询所有的推荐攻略
	 * 王梦圆
	 * 2015年11月27日
	 */
	@RequestMapping("/findAllInterestBoutique")
	public @ResponseBody Grid findAllInterestBoutique(HttpServletRequest request,InterestBoutique interestBoutique){
		Grid grid=new Grid();
		List<InterestBoutique> interestBoutiques=interestBoutiqueService.findByConditions(interestBoutique, this.getPage(request));
		long count=interestBoutiqueService.findByConditionsCount(interestBoutique);
		grid.setRows(interestBoutiques);
		grid.setTotal(count);
		return grid;
	}
	/**
	 * 添加推荐攻略
	 * 王梦圆
	 * 2015年11月27日
	 */
	@RequestMapping("/saveInterestBoutique")
	public @ResponseBody Result saveInterestBoutique(HttpServletRequest request,InterestBoutique interestBoutique,String interestBigLongImgUrl,String interesttype){
		Result result=new Result();
		interestBoutique.setInterestBoutiqueId(UUIDUtil.getUUID());
		Integer type=Integer.parseInt(interesttype);
		interestBoutique.setProductionInterestType(type);
		interestBoutiqueService.save(interestBoutique);
		Interest interest=new Interest();
		interest.setInterestBigLongImgUrl(interestBigLongImgUrl);
		interest.setInterestId(interestBoutique.getInterestId());
		interestService.updateBigLongImgUrl(interest);
		result.setSuccess(true);
		result.setMsg("添加成功");
		return result;
	}
	/**
	 * 修改推荐攻略
	 * 王梦圆
	 * 2015年11月27日
	 */
	@RequestMapping("/updateInterestBoutique")
	public @ResponseBody Result updateInterestBoutique(HttpServletRequest request,String interestBigLongImgUrl,InterestBoutique interestBoutique){
		Result result=new Result();
		interestBoutiqueService.update(interestBoutique);
		Interest interest=new Interest();
		interest.setInterestBigLongImgUrl(interestBigLongImgUrl);
		interest.setInterestId(interestBoutique.getInterestId());
		interestService.updateBigLongImgUrl(interest);
		result.setSuccess(true);
		result.setMsg("修改成功");
		return result;
	}
	/**
	 * 根据id查询攻略
	 * 王梦圆
	 * 2015年11月27日
	 */
	@RequestMapping("/findInterestBoutiqueById")
	public @ResponseBody InterestBoutique findInterestBoutiqueById(HttpServletRequest request,String interestBoutiqueId){
		InterestBoutique interestBoutique=interestBoutiqueService.findInterestBoutiqueById(interestBoutiqueId);
		return interestBoutique;
	}
	/**
	 * 删除推荐攻略
	 * 王梦圆
	 * 2015年11月27日
	 */
	@RequestMapping("/deleteInterestBoutique")
	public @ResponseBody Result deleteInterestBoutique(HttpServletRequest request,InterestBoutique interestBoutique){
		Result result=new Result();
		interestBoutiqueService.delete(interestBoutique);
		result.setSuccess(true);
		result.setMsg("删除成功");
		return result;
	}
	/**
	 * 上传图片
	 * 王梦圆
	 * 2015年12月1日
	 */
	@RequestMapping("/load")
	public @ResponseBody Result loadapplicationHandIdentityPhoto(HttpServletRequest request,HttpServletResponse response) throws ParseException {
		Result result = new Result();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		if (multipartRequest == null) {
			result.setMsg("上传对象创建失败");
			result.setSuccess(false);
			return result;
		}
		String logoPathDir=null;
		String imagename=null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		logoPathDir = sdf.format(new Date());
	    imagename = UUIDUtil.getUUID() + ".jpg";
	
		MultipartFile multipartFile = multipartRequest.getFile("upload_file");
		if (multipartFile == null) {
			result.setMsg("获取图片失败");
			result.setSuccess(false);
			return result;
		}
		logger.info("文件名1：" + imagename);
		boolean uploadState=FileHttp.uploadFileWithHttpMime(logoPathDir, imagename,
				multipartFile);
		if(uploadState){
			String photoPath=Constant.GET_IMG_SERVER_ADDR+logoPathDir+ "/"+imagename;
			result.setSuccess(true);
			result.setData(photoPath);
		}
		return result;
	}

}
