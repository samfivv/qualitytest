package com.midai.miya.order.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.midai.miya.constant.Constant;
import com.midai.miya.controller.BaseController;
import com.midai.miya.easyui.Grid;
import com.midai.miya.item.service.ItemAddtitionalService;
import com.midai.miya.order.model.Order;
import com.midai.miya.order.model.OrderItem;
import com.midai.miya.order.model.OrderResult;
import com.midai.miya.order.service.OrderItemService;
import com.midai.miya.order.service.OrderService;
import com.midai.miya.utils.PageUtil;

@Controller
@RequestMapping("/order")
public class orderController extends BaseController{
	private static final long serialVersionUID = -7968523323054952398L;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderItemService orderItemService;
	@Autowired
	private ItemAddtitionalService itemAddtitionalService;
	
    @RequestMapping("/findByBarcode")
    public @ResponseBody Order findByBarcode(String materialBarcode,HttpServletRequest request)
    {
    	Order order=orderService.findByBarcode(materialBarcode);
    	//this.addLog(request, "查看检测报告详情", Constant.LOG_TYPE_SELECT);
    	return order;
    }
    
    @RequestMapping("/findByAllUnFinish")
    public @ResponseBody Grid findByAllUnFinish(Order order,HttpServletRequest request)
    {
    	Grid grid=new Grid();
    	PageUtil page = this.getPage(request);
    	if (page.getOrder() == null || "".equals(page.getOrder().toString())){
    		page.setOrder("resultDate");
    		page.setSort("DESC");
    	}
    	
    	if (order.getOrderState() == null){
    		order.setOrderState(5);
    	}
    	List<Order> list=orderService.findByConditions(order,page);
    	long count=orderService.findByConditionsCount(order);
    	this.addLog(request, "查询未检验完成的委托单", Constant.LOG_TYPE_SELECT);
    	grid.setRows(list);
    	grid.setTotal(count);
    	return grid;
    }
    
    //查委托单的检测项目
    @RequestMapping("/findItemByOrderId")
    public @ResponseBody Grid findItemByOrderId(String orderId,HttpServletRequest request)
    {
    	Grid grid=new Grid();
    	PageUtil page = this.getPage(request);
    	/*
    	if (page.getOrder() == null || "".equals(page.getOrder().toString())){
    		page.setOrder("ASC");
    		page.setSort("order_id");
    	}
    	*/
    	OrderItem orderItem = new OrderItem();
    	orderItem.setOrderId(orderId);
    	orderItem.setItemType(1);

    	List<OrderItem> list=orderItemService.findByConditions(orderItem,page);
    	long count=orderItemService.findByConditionsCount(orderItem);
    	
    	for(OrderItem oi : list ){
    		if (oi.getItemParentName() == null || "".equals(oi.getItemParentName().toString())){
    			oi.setItemParentName(oi.getItemName());
    			oi.setItemName("");
    		}
    	}
    	this.addLog(request, "查询委托单项目", Constant.LOG_TYPE_SELECT);
    	grid.setRows(list);
    	grid.setTotal(count);
    	return grid;
    }    
}
