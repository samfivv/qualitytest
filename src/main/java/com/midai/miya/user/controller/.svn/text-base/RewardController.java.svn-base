package com.midai.miya.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.midai.miya.controller.BaseController;
import com.midai.miya.easyui.Grid;
import com.midai.miya.user.model.Reward;
import com.midai.miya.user.service.RewardService;
@Controller
@RequestMapping("/reward")
public class RewardController extends BaseController {

	private static final long serialVersionUID = 768576517366081080L;
	
	@Autowired
	private RewardService rewardService;
	
	@RequestMapping("/findAllReward")
	public @ResponseBody Grid findAllReward(HttpServletRequest request,Reward reward){
		Grid grid=new Grid();
		List<Reward> rewards=rewardService.findByConditions(reward,this.getPage(request));
		long count=rewardService.findByConditionsCount(reward);
		grid.setRows(rewards);
		grid.setTotal(count);
		return grid;
	}

}
