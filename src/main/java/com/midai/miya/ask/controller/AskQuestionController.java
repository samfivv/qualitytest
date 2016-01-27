package com.midai.miya.ask.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.midai.miya.ask.model.AskQuestion;
import com.midai.miya.ask.model.AskReply;
import com.midai.miya.ask.service.AskQuestionService;
import com.midai.miya.ask.service.AskReplyService;
import com.midai.miya.constant.Constant;
import com.midai.miya.controller.BaseController;
import com.midai.miya.easyui.Grid;
import com.midai.miya.questionnaire.model.Question;
import com.midai.miya.sys.model.Category;
import com.midai.miya.user.vo.Result;
@Controller
@RequestMapping("/ask")
public class AskQuestionController extends BaseController {

	private static final long serialVersionUID = -3453169990464238269L;
	@Autowired
	private AskQuestionService askQuestionService;
	@Autowired
	private AskReplyService askReplyService;
	@RequestMapping("/findAll")
	public @ResponseBody Grid findAll(AskQuestion askQuestion,HttpServletRequest request){
		this.addLog(request, "查询问题管理", Constant.LOG_TYPE_SELECT);
		Grid grid=new Grid();
		List<AskQuestion> askQuestions=askQuestionService.findByConditions(askQuestion, this.getPage(request));
		long count=askQuestionService.findByConditionsCount(askQuestion);
		grid.setRows(askQuestions);
		grid.setTotal(count);
		return grid;
	}
	@RequestMapping("/disableQuestion")
	public @ResponseBody Result disableQuestion(String questionId,HttpServletRequest request){
		this.addLog(request, "屏蔽问题", Constant.LOG_TYPE_UPDATE);
		Result result=new Result();
		AskQuestion askQuestion=askQuestionService.findById(questionId);
		askQuestion.setQuestionState(2);
		askQuestionService.update(askQuestion);
		result.setSuccess(true);
		result.setMsg("屏蔽成功");
		return result;
	}
	@RequestMapping("/findQuestionById")
	public @ResponseBody AskQuestion findQuestionById(String questionId,HttpServletRequest request){
		AskQuestion question=askQuestionService.findById(questionId);
		return question;
	}
	
	@RequestMapping("/openQuestion")
	public @ResponseBody Result openQuestion(String questionId,HttpServletRequest request){
		this.addLog(request, "取消屏蔽问题", Constant.LOG_TYPE_UPDATE);
		Result result=new Result();
		AskQuestion askQuestion=askQuestionService.findById(questionId);
		askQuestion.setQuestionState(1);
		askQuestionService.update(askQuestion);
		result.setSuccess(true);
		result.setMsg("取消屏蔽成功");
		return result;
	}
	@RequestMapping(value = "/exportAskQuestion", method = RequestMethod.POST)
	public void exportAskQuestion(AskQuestion askQuestion, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		this.addLog(request, "导出问题信息", Constant.LOG_TYPE_EXPORT);
		List<AskQuestion> askQuestions = askQuestionService.findByConditions(askQuestion,this.getExportPage(request));
		super.doExport(request, response, askQuestions, "问题管理", "问题管理",
				this.getHeadForAskQuestion(), this.getColumnForAskQuestion());
	}

	private String[] getHeadForAskQuestion() {
		return new String[] { "问题标题", "问题描述", "昵称", "类别","标签","状态","是否已解决","是否悬赏","悬赏数额","来源","回复数量",
				"浏览次数","推荐数量","提问时间","问题更新时间" };
	}

	private String[] getColumnForAskQuestion() {
		return new String[] { "questionTitle", "questionDesc", "userNickname","categoryName","questionTag","questionStateStr",
				"questionIsSettleStr","questionIsOfferStr","questionOfferCount","questionFromStr","replyCount","questionWacthCount",
				"questionRecommendCount","questionCreateTimeStr","questionUpdateTimeStr"};
	}
	@RequestMapping("/findAllReply")
	public @ResponseBody Grid findAllReply(AskReply askReply,HttpServletRequest request){
		this.addLog(request, "查询回复", Constant.LOG_TYPE_SELECT);
		Grid grid=new Grid();
		List<AskReply> askReplys=askReplyService.findByConditions(askReply, this.getPage(request));
		long count=askReplyService.findByConditionsCount(askReply);
		grid.setRows(askReplys);
		grid.setTotal(count);
		return grid;
	}
	
	@RequestMapping("/findAskReplyById")
	public @ResponseBody AskReply findAskReplyById(String replyId,HttpServletRequest request){
		AskReply askReply=askReplyService.findAskReplyById(replyId);
		return askReply;
	}
	
	@RequestMapping(value = "/exportAskReply", method = RequestMethod.POST)
	public void exportAskReply(AskReply askReply, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		this.addLog(request, "导出回复信息", Constant.LOG_TYPE_EXPORT);
		List<AskReply> askReplys = askReplyService.findByConditions(askReply,this.getExportPage(request));
		super.doExport(request, response, askReplys, "回复管理", "回复管理",
				this.getHeadForAskReply(), this.getColumnForAskReply());
	}

	private String[] getHeadForAskReply() {
		return new String[] { "回复内容", "被回复人昵称", "回复人昵称", "赞数量","踩数量","回复状态 ","回复时间" };
	}

	private String[] getColumnForAskReply() {
		return new String[] { "replyContent", "replyToNickname", "replyFromNickname","replyPraiseCount","replyTrampleCount",
				"replyStateStr","replyCreateTimeStr" };
	}
	@RequestMapping("/disableReply")
	public @ResponseBody Result disableReply(String replyId,HttpServletRequest request){
		this.addLog(request, "屏蔽回复", Constant.LOG_TYPE_UPDATE);
		Result result=new Result();
		AskReply askReply=new AskReply();
		askReply.setReplyState(2);
		askReply.setReplyId(replyId);
		askReplyService.update(askReply);
		result.setSuccess(true);
		result.setMsg("屏蔽成功");
		return result;
	}
	@RequestMapping("/openReply")
	public @ResponseBody Result openReply(String replyId,HttpServletRequest request){
		this.addLog(request, "取消屏蔽回复", Constant.LOG_TYPE_UPDATE);
		Result result=new Result();
		AskReply askReply=new AskReply();
		askReply.setReplyState(1);
		askReply.setReplyId(replyId);
		askReplyService.update(askReply);
		result.setSuccess(true);
		result.setMsg("取消屏蔽成功");
		return result;
	}
}
