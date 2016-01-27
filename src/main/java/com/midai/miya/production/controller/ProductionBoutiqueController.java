package com.midai.miya.production.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.midai.miya.controller.BaseController;
import com.midai.miya.easyui.Grid;
import com.midai.miya.production.model.Production;
import com.midai.miya.production.model.ProductionBoutique;
import com.midai.miya.production.service.ProductionBoutiqueService;
import com.midai.miya.production.service.ProductionService;
import com.midai.miya.user.vo.Result;
import com.midai.miya.utils.UUIDUtil;
@Controller
@RequestMapping("/production")
public class ProductionBoutiqueController extends BaseController {

	private static final long serialVersionUID = 6223998803057026043L;
	
	@Autowired
	private ProductionBoutiqueService productionBoutiqueService;
	@Autowired
	private ProductionService productionService;
	/**
	 * 推荐作品
	 * 王梦圆
	 * 2015年11月26日
	 */
	@RequestMapping("/findAllProductionBoutique")
	public @ResponseBody Grid findAllProductionBoutique(HttpServletRequest request,ProductionBoutique productionBoutique){
		Grid grid=new Grid();
		List<ProductionBoutique> lists=productionBoutiqueService.findByConditions(productionBoutique, this.getPage(request));
		long count=productionBoutiqueService.findByConditionsCount(productionBoutique);
		grid.setRows(lists);
		grid.setTotal(count);
		return grid;
	}
	/**
	 * 所有作品
	 * 王梦圆
	 * 2015年11月26日
	 */
	@RequestMapping("/findAllProduction")
	public @ResponseBody Grid findAllProduction(HttpServletRequest request,Production production){
		Grid grid=new Grid();
		production.setProductionState(2);
		List<Production> lists=productionService.findByConditions(production, this.getPage(request));
		long count=productionService.findByConditionsCount(production);
		grid.setRows(lists);
		grid.setTotal(count);
		return grid;
	}
	/**
	 * 添加推荐作品
	 * 王梦圆
	 * 2015年11月26日
	 */
	@RequestMapping("/saveProductionBoutique")
	public @ResponseBody Result saveProductionBoutique(HttpServletRequest request,ProductionBoutique productionBoutique){
		Result result=new Result();
		int count=productionBoutiqueService.findProductionBoutiqueCountByproductionId(productionBoutique);
		if(count>0){
			result.setSuccess(false);
			result.setMsg("该作品已存在");
			return result;
		}
		productionBoutique.setProductionBoutiqueId(UUIDUtil.getUUID());
		productionBoutiqueService.save(productionBoutique);
		result.setSuccess(true);
		result.setMsg("添加成功");
		return result;
	}
	/**
	 * 修改推荐作品
	 * 王梦圆
	 * 2015年11月26日
	 */
	@RequestMapping("/updateProductionBoutique")
	public @ResponseBody Result updateProductionBoutique(HttpServletRequest request,ProductionBoutique productionBoutique){
		Result result=new Result();
		int count=productionBoutiqueService.findProductionBoutiqueCountByproductionId(productionBoutique);
		if(count>0){
			result.setSuccess(false);
			result.setMsg("该作品已存在");
			return result;
		}
		productionBoutiqueService.update(productionBoutique);
		result.setSuccess(true);
		result.setMsg("修改成功");
		return result;
	}
	
	/**
	 * 根据推荐作品的id查询
	 * 王梦圆
	 * 2015年11月26日
	 */
	@RequestMapping("/findProductionBoutiqueById")
	public @ResponseBody ProductionBoutique findProductionBoutiqueById(HttpServletRequest request,String productionBoutiqueId){
		ProductionBoutique productionBoutique=productionBoutiqueService.findProductionBoutiqueByproductionId(productionBoutiqueId);
		return productionBoutique;
	}
	/**
	 * 删除推荐的作品
	 * 王梦圆
	 * 2015年11月26日
	 */
	@RequestMapping("/deleteProductionBoutique")
	public @ResponseBody Result deleteProductionBoutique(HttpServletRequest request,ProductionBoutique productionBoutique){
		Result result =new Result();
		productionBoutiqueService.delete(productionBoutique);
		result.setSuccess(true);
		result.setMsg("删除成功");
		return result;
	}

}
