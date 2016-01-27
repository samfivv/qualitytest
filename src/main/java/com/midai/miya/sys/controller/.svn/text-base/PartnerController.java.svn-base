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
import com.midai.miya.sys.model.Partner;
import com.midai.miya.sys.service.PartnerService;
import com.midai.miya.user.vo.Result;
import com.midai.miya.utils.UUIDUtil;
@Controller
@RequestMapping("/partner")
public class PartnerController extends BaseController{
	@Autowired
	private PartnerService partnerService;

	private static final long serialVersionUID = 9160652162256241335L;
	@RequestMapping("/findByConditions")
	public @ResponseBody Grid findByConditions(Partner partner,HttpServletRequest request){
		this.addLog(request, "查询合作伙伴", Constant.LOG_TYPE_SELECT);
		Grid grid=new Grid();
		List<Partner> lists=partnerService.findByConditions(partner, this.getPage(request));
		long count=partnerService.findByConditionsCount(partner);
		grid.setRows(lists);
		grid.setTotal(count);
		return grid;
	}
	
	@RequestMapping("/savePartner")
	public @ResponseBody Result savePartner(Partner partner,HttpServletRequest request){
		Result result=new Result();
		if("".equals(partner.getPartnerName().replace(" ", ""))){
			result.setSuccess(false);
			result.setMsg("名称不能为空");
			return result;
		}
		if(partner.getPartnerType()==2){
			if("".equals(partner.getPartnerImgUrl().replace(" ", ""))){
				result.setSuccess(false);
				result.setMsg("图片地址不能为空");
				return result;
			}
		}
		if("".equals(partner.getPartnerUrl().replace(" ", ""))){
			result.setSuccess(false);
			result.setMsg("链接不能为空");
			return result;
		}
		partner.setPartnerId(UUIDUtil.getUUID());
		partner.setPartnerCreateTime(new Date());
		partnerService.save(partner);
		result.setSuccess(true);
		result.setMsg("保存成功");
		this.addLog(request, "添加合作伙伴", Constant.LOG_TYPE_ADD);
		return result;
	}
	
	@RequestMapping("/updatePartner")
	public @ResponseBody Result updatePartner(Partner partner,HttpServletRequest request){
		Result result=new Result();
		if("".equals(partner.getPartnerName().replace(" ", ""))){
			result.setSuccess(false);
			result.setMsg("名称不能为空");
			return result;
		}
		if("".equals(partner.getPartnerImgUrl().replace(" ", ""))){
			result.setSuccess(false);
			result.setMsg("图片地址不能为空");
			return result;
		}
		if("".equals(partner.getPartnerUrl().replace(" ", ""))){
			result.setSuccess(false);
			result.setMsg("链接不能为空");
			return result;
		}
		partnerService.update(partner);
		result.setSuccess(true);
		result.setMsg("修改成功");
		this.addLog(request, "修改合作伙伴", Constant.LOG_TYPE_UPDATE);
		return result;
	}
	
	@RequestMapping("/findById")
	public @ResponseBody Partner findById(String partnerId,HttpServletRequest request){
		Partner partner=partnerService.findById(partnerId);
		this.addLog(request, "查看合作伙伴详情", Constant.LOG_TYPE_SELECT);
		return partner;
	}
	
	@RequestMapping("/deletePartner")
	public @ResponseBody Result deletePartner(String partnerId,HttpServletRequest request){
		this.addLog(request, "删除合作伙伴", Constant.LOG_TYPE_DELETE);
		Result result=new Result();
		Partner partner=new Partner();
		partner.setPartnerId(partnerId);
		partnerService.delete(partner);
		result.setSuccess(true);
		return result;
	}
	
	@RequestMapping(value = "/exportPartner", method = RequestMethod.POST)
	public void exportPartner(Partner partner,HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		this.addLog(request, "导出合作伙伴信息", Constant.LOG_TYPE_EXPORT);
		List<Partner> partners=partnerService.findByConditions(partner,this.getExportPage(request));
		super.doExport(request, response, partners, "合作伙伴", "合作伙伴",
				this.getHeadForPartner(), this.getColumnForPartner());
	}
	private String[] getHeadForPartner() {
		return new String[] {  "合作伙伴名称", "合作伙伴链接","合作伙伴图片地址","合作伙伴类型","创建时间"};
	}
	private String[] getColumnForPartner() {
		return new String[] {  "partnerName", "partnerUrl","partnerImgUrl","partnerTypeStr","partnerCreateTimeStr"};
	}
}
