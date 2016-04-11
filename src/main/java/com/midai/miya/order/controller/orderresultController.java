package com.midai.miya.order.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.midai.miya.constant.Constant;
import com.midai.miya.controller.BaseController;
import com.midai.miya.easyui.Grid;
import com.midai.miya.item.model.ItemType;
import com.midai.miya.order.model.Order;
import com.midai.miya.order.model.OrderItem;
import com.midai.miya.order.model.OrderResult;
import com.midai.miya.order.model.OrderResultItem;
import com.midai.miya.order.service.OrderItemService;
import com.midai.miya.order.service.OrderResultItemService;
import com.midai.miya.order.service.OrderResultService;
import com.midai.miya.order.service.OrderService;
import com.midai.miya.user.vo.Result;
import com.midai.miya.utils.PageUtil;
import com.midai.miya.utils.UUIDUtil;

@Controller
@RequestMapping("/orderresult")
public class orderresultController extends BaseController{
	private static final long serialVersionUID = -7968523323054952398L;
	private static final boolean OrderItem = false;
	@Autowired
	private OrderResultService orderResultService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderItemService orderItemService;
	@Autowired
	private OrderResultItemService orderResultItemService;	
	
	
    @RequestMapping("/findAll")
    public @ResponseBody Grid findAll(OrderResult orderResult,HttpServletRequest request){
    	this.addLog(request, "查询检测结果报告单", Constant.LOG_TYPE_SELECT);
    	Grid grid=new Grid();
    	PageUtil page = this.getPage(request);
    	if (page.getOrder() == null || "".equals(page.getOrder().toString())){
    		page.setOrder("resultDate");
    		page.setSort("DESC");
    	}
    	List<OrderResult> orderResults=orderResultService.findByConditions(orderResult,page);
    	long count=orderResultService.findByConditionsCount(orderResult);
    	grid.setRows(orderResults);
    	grid.setTotal(count);
    	return grid;
    }
    
    @RequestMapping("/findById")
    public @ResponseBody OrderResult findById(String resultId,HttpServletRequest request)
    {
    	OrderResult orderResult=orderResultService.findById(resultId);
    	this.addLog(request, "查看检测报告详情", Constant.LOG_TYPE_SELECT);
    	return orderResult;
    }
    @RequestMapping("/addOrderResult")
    public @ResponseBody Result addOrderResult(OrderResult orderResult,HttpServletRequest request)
    {
    	Result result=new Result();
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	
    	if("".equals(orderResult.getOrderNo().replace(" ", ""))){
    		result.setSuccess(false);
    		result.setMsg("合同编号或条码不能为空");
    		return result;
    	}
    	
    	Order order = orderService.findByBarcode(orderResult.getOrderNo());
    	if (order == null){
    		order = orderService.findByNo(orderResult.getOrderNo());
    		if (order == null){
        		result.setSuccess(false);
        		result.setMsg("合同编号或条码不存在");
        		return result;
    		}
    	}
    	
    	if (orderResult.getResultDate() == null){
    		orderResult.setResultDate(new Date());
    	}
    	
    	orderResult.setCreateTime(new Date());
    	orderResult.setOrderId(order.getOrderId());
    	orderResult.setResultId(UUIDUtil.getUUID());
    	
    	orderResultService.save(orderResult);
    	
    	OrderItem orderItem = new OrderItem();
    	orderItem.setOrderId(orderResult.getOrderId());
    	
    	List<OrderItem> orderItemList = orderItemService.findByOrderId(orderResult.getOrderId());
    	for(OrderItem oi : orderItemList){
    		OrderResultItem orderResultItem = new OrderResultItem();
    		orderResultItem.setResultItemId(UUIDUtil.getUUID());
    		orderResultItem.setResultId(orderResult.getResultId());
    		orderResultItem.setOrderId(orderResult.getOrderId());
    		orderResultItem.setItemId(oi.getOrderItemId());
    		orderResultItem.setItemType(oi.getItemType());
    		orderResultItemService.save(orderResultItem);
    	}
    	
    	//在此修改委托单状态
    	
    	result.setSuccess(true);
    	result.setMsg("添加成功");
    	this.addLog(request, "增加结果报告", Constant.LOG_TYPE_UPDATE);
    	return result;
    }    	
}
