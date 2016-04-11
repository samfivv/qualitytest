package com.midai.miya.order.controller;

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
import com.midai.miya.order.model.ApprovalOrder;
import com.midai.miya.order.model.Order;
import com.midai.miya.order.service.ApprovalOrderService;
import com.midai.miya.order.service.OrderService;
import com.midai.miya.user.model.ApprovalUser;
import com.midai.miya.user.model.User;
import com.midai.miya.user.vo.Result;
import com.midai.miya.utils.PageUtil;


@Controller
@RequestMapping("/approvalorder")
public class approvalorderController extends BaseController {
	 
	private static final long serialVersionUID = -7968523323054952398L;
	@Autowired
	private ApprovalOrderService approvalOrderService;
	@Autowired
	private OrderService orderService;
	
    @RequestMapping("/findAllApprovalOrder")
    public @ResponseBody Grid findAllApprovalOrder(ApprovalOrder approvalOrder,HttpServletRequest request){
    	this.addLog(request, "查询待批准委托单", Constant.LOG_TYPE_SELECT);
    	Grid grid=new Grid();
    	PageUtil page = this.getPage(request);
    	if (page.getOrder() == null || "".equals(page.getOrder().toString())){
    		page.setOrder("approvalCreateTime");
    		page.setSort("DESC");
    	}
    	List<ApprovalOrder> approvalOrders=approvalOrderService.findByConditions(approvalOrder,page);
    	long count=approvalOrderService.findByConditionsCount(approvalOrder);
    	grid.setRows(approvalOrders);
    	grid.setTotal(count);
    	return grid;
    }	

    @RequestMapping("/findById")
    public @ResponseBody ApprovalOrder findById(String approvalOrderId,HttpServletRequest request){
    	ApprovalOrder approvalOrder=approvalOrderService.findById(approvalOrderId);
    	return approvalOrder;
    }
    
	@RequestMapping("/update")
	public @ResponseBody Result update(HttpServletRequest request,ApprovalOrder approvalOrder){
		
		Result result=new Result();
		approvalOrder.setApprovalTime(new Date());
		approvalOrderService.update(approvalOrder);
		
		Order order = new Order();
		order.setOrderId(approvalOrder.getOrderId()); 
		if (approvalOrder.getApprovalState() == 1)
		{
			order.setOrderState(3);
			order.setOrderApproveDate(new Date());
		}else
			order.setOrderState(2);
		orderService.update(order);
		result.setSuccess(true);
		result.setMsg("审批成功");
		this.addLog(request, "审批委托申请书", Constant.LOG_TYPE_UPDATE);
		return result;
	}     
    
}
