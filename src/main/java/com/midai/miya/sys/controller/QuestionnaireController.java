package com.midai.miya.sys.controller;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
 


import com.midai.miya.controller.BaseController;
import com.midai.miya.easyui.Grid;
import com.midai.miya.questionnaire.model.Question;
import com.midai.miya.questionnaire.model.QuestionSurvey;
import com.midai.miya.questionnaire.model.Questionnaire;
import com.midai.miya.questionnaire.model.QuestionnaireAnswer;
import com.midai.miya.questionnaire.model.QuestionnaireOption;
import com.midai.miya.questionnaire.model.QuestionnaireQuestion;
import com.midai.miya.questionnaire.service.QuestionnaireService;
import com.midai.miya.user.vo.Result;
import com.midai.miya.utils.CalendarUtil;
import com.midai.miya.utils.DateUtil;
import com.midai.miya.utils.UUIDUtil;

@Controller
@RequestMapping("/questionnaireController")
public class QuestionnaireController extends BaseController{
 
	private static final long serialVersionUID = 6956957924272255895L;
	@Autowired
	private QuestionnaireService questionnaireService;
	
	@RequestMapping("/save")
	public @ResponseBody Result save(Questionnaire questionnaire,HttpServletRequest request){
		Result result=new Result();
		if(questionnaire.getQuestionnaireId()!=null&&!"".equals(questionnaire.getQuestionnaireId())){
			questionnaireService.update(questionnaire);
		}else{
			questionnaire.setQuestionnaireId(UUIDUtil.getUUID());
			questionnaire.setQuestionnaireState(1);
			questionnaireService.save(questionnaire);
		}
		result.setData(questionnaire);
		result.setSuccess(true);
		result.setMsg("保存成功");
		return result;
	}
	
	@RequestMapping("/saveQuestion/{questionnaireId}")
	public  String saveQuestion(@PathVariable String questionnaireId,QuestionnaireQuestion questionnaireQuestion,HttpServletRequest request){
		questionnaireQuestion.setQuestionnaireId(questionnaireId);
		questionnaireService.saveQuestionAndOption(questionnaireQuestion);
		List<QuestionnaireQuestion> lists=questionnaireService.findQuestionnaireQuestion(questionnaireId);
		request.setAttribute("lists", lists);
		request.setAttribute("questionnaireId", questionnaireId);
		return "securityJsp/questionnaire/nextquestion";
	}
	
	@RequestMapping("/findQuestionnaireQuestion/{questionnaireId}")
	public  String findQuestionnaireQuestion(@PathVariable String questionnaireId,HttpServletRequest request){
		List<QuestionnaireQuestion> lists=questionnaireService.findQuestionnaireQuestion(questionnaireId);
		request.setAttribute("lists", lists);
		request.setAttribute("questionnaireId", questionnaireId);
		return "securityJsp/questionnaire/nextquestion";
	}
	@RequestMapping("/publishSearch/{questionnaireId}")
	public String publishSearch(@PathVariable String questionnaireId,HttpServletRequest request){
		request.setAttribute("questionnaireId", questionnaireId);
		Questionnaire questionnaire=new Questionnaire();
		questionnaire.setQuestionnaireId(questionnaireId);
		questionnaire.setQuestionnaireState(3);
		questionnaireService.update(questionnaire);
		return "securityJsp/questionnaire/questionsuccess";
	}
	
	@RequestMapping("/findQuestionnaire/{questionnaireId}")
	public  String findQuestionnaire(@PathVariable String questionnaireId,HttpServletRequest request){
		Questionnaire questionnaire=questionnaireService.findById(questionnaireId);
		request.setAttribute("questionnaire", questionnaire);
		return "securityJsp/questionnaire/question";
	}
	
	@RequestMapping("/findQuestionnaireResult/{questionnaireId}")
	public  String findQuestionnaireResult(@PathVariable String questionnaireId,HttpServletRequest request){
		Questionnaire questionnaire=questionnaireService.findById(questionnaireId);
		request.setAttribute("questionnaire", questionnaire);
		List<QuestionnaireQuestion> lists=questionnaireService.findQuestionnaireQuestion(questionnaireId);
		request.setAttribute("lists", lists);
		return "securityJsp/questionnaire/questionnaireresult";
	}
	/**
	 * 查出所有列表
	 * 王梦圆
	 * 2015年7月9日
	 */
	@RequestMapping("/findAll")
	public @ResponseBody Grid findAll(Questionnaire questionnaire ,HttpServletRequest request){
		Grid grid=new Grid();
		List<Questionnaire> lists=questionnaireService.findAll(questionnaire,this.getPage(request));
		long count=questionnaireService.findAllCount(questionnaire);
		grid.setRows(lists);
		grid.setTotal(count);
		return grid;
	}
	/**
	 * 根据id查找信息
	 * 王梦圆
	 * 2015年7月9日
	 */
	@RequestMapping("/findById")
	public @ResponseBody Questionnaire findById(String questionnaireId,HttpServletRequest request){
		Questionnaire questionnaire=questionnaireService.findById(questionnaireId); 
		return questionnaire;
	}
	/**
	 * 修改问卷信息
	 * 王梦圆
	 * 2015年7月9日
	 */
	@RequestMapping("/deleted")
	public @ResponseBody Result deleted(String questionnaireId,HttpServletRequest request){
		  Result result=new Result();
		  Questionnaire questionnaire=questionnaireService.findById(questionnaireId);
		  questionnaire.setQuestionnaireState(2);
		  questionnaireService.update(questionnaire);
		  result.setSuccess(true);
		  return result;
	}
	/**
	 * 遍历某个问卷
	 * 王梦圆
	 * 2015年7月14日
	 */
	@RequestMapping("/findQuestion")
	public @ResponseBody Question findQuestion(String questionnaireId){
		Question question=new Question();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date=sdf.format(new Date());
		question.setNewDate(date);
		long count=questionnaireService.selectCount(questionnaireId);
		question.setCount(count);
		List<QuestionnaireQuestion> lists=questionnaireService.findQuestionnaireQuestion(questionnaireId);
		Map<String, Integer> map1=new HashMap<String, Integer>();
		Map<String,Integer> map2=new HashMap<String, Integer>();
		for(QuestionnaireQuestion questionnaire:lists){
			int sum=0;
			List<QuestionnaireOption> questionnaireOptionList=questionnaire.getQuestionnaireOptionList();
			for(QuestionnaireOption questionnaireOption:questionnaireOptionList){
				int person=0;
				List<QuestionnaireAnswer> questionnaireAnswerList=questionnaireService.findCountByOption(questionnaireId);
				for(QuestionnaireAnswer questionnaireAnswer:questionnaireAnswerList){
					if(questionnaireOption.getQuestionnaireOptionId().equals(questionnaireAnswer.getQuestionnaireOptionId())){
						person++;
						sum++;
					}
				}
				map1.put(questionnaireOption.getQuestionnaireOptionId(), person);
			}
			map2.put(questionnaire.getQuestionnaireQuestionId(), sum);
		}
		for(QuestionnaireQuestion questionnaire:lists){
			int number = 0;
			String percent="";
			 for (Map.Entry<String, Integer> entry : map2.entrySet()) {  
				 if(entry.getKey().equals(questionnaire.getQuestionnaireQuestionId())){
					 number = entry.getValue();   
				 }
		        } 
			List<QuestionnaireOption> questionnaireOptionList=questionnaire.getQuestionnaireOptionList();
			for(QuestionnaireOption questionnaireOption:questionnaireOptionList){
				int person;
				 for (Map.Entry<String, Integer> entry : map1.entrySet()) {  
					 if(entry.getKey().equals(questionnaireOption.getQuestionnaireOptionId())){
						  person = entry.getValue();
						  if(number!=0){
						  NumberFormat numberFormat = NumberFormat.getInstance();  
					        numberFormat.setMaximumFractionDigits(2);  
					        percent = numberFormat.format((float) person / (float) number * 100);
						  }
						  questionnaireOption.setPerson(person);
						  questionnaireOption.setPercent(percent);
					 }
			        } 
			}
		}
		question.setQuestionnaireQuestionList(lists);
		return question;
	}
	
	@RequestMapping("/questionnaireAnalysis/{questionnaireId}")
	public  String questionnaireAnalysis(@PathVariable String questionnaireId,HttpServletRequest request) throws ParseException{
		Questionnaire questionnaire=questionnaireService.findById(questionnaireId);
		if(questionnaire.getQuestionnaireEndTime()!=null){
			questionnaire.setQuestionnaireEndTime(new Date(questionnaire.getQuestionnaireEndTime().getTime()+24*60*60*1000-1));	
		}
		request.setAttribute("questionnaire", questionnaire);
		QuestionSurvey questionSurvey=new QuestionSurvey();
		long viewCount=questionnaireService.selectViewCount(questionnaireId, 0);
		questionSurvey.setViewCount(viewCount);
		long ipCount=questionnaireService.selectIp(questionnaireId, 0);
		questionSurvey.setIpCount(ipCount);
		long finishingCount=questionnaireService.selectFinishingRate(questionnaireId, 0);
		String finishingRate="";
		if(viewCount!=0){
			  NumberFormat numberFormat = NumberFormat.getInstance();  
		        numberFormat.setMaximumFractionDigits(2);  
		        finishingRate = numberFormat.format((float) finishingCount / (float) viewCount * 100);
			  } else{
				  finishingRate="0"; 
			  }
		questionSurvey.setFinishingRate(finishingRate);
		Double avgTime1=questionnaireService.selectAvg(questionnaireId, 0);
		String avgTime="";
		if(avgTime1!=null){
			int avgTimes=Integer.parseInt(new DecimalFormat("0").format(avgTime1));
			avgTime=DateUtil.secToTime(avgTimes);
		}else{
			avgTime="0";
		}
		questionSurvey.setAvgTime(avgTime);
		request.setAttribute("questionSurvey", questionSurvey);
		QuestionSurvey questionSurvey2=new QuestionSurvey();
		long viewCount2=questionnaireService.selectViewCount(questionnaireId, 1);
		questionSurvey2.setViewCount(viewCount2);
		long ipCount2=questionnaireService.selectIp(questionnaireId,1);
		questionSurvey2.setIpCount(ipCount2);
		long finishingCount2=questionnaireService.selectFinishingRate(questionnaireId, 1);
		String finishingRate2="";
		if(viewCount2!=0){
			  NumberFormat numberFormat = NumberFormat.getInstance();  
		        numberFormat.setMaximumFractionDigits(2);  
		        finishingRate2 = numberFormat.format((float) finishingCount2 / (float) viewCount2 * 100);
			  } else{
				  finishingRate2="0"; 
			  }
		questionSurvey2.setFinishingRate(finishingRate2);
		Double avgTime2=questionnaireService.selectAvg(questionnaireId, 1);
		String avgTime3="";
		if(avgTime2!=null){
			int avgTimes1=Integer.parseInt(new DecimalFormat("0").format(avgTime2));
		    avgTime3=DateUtil.secToTime(avgTimes1);
		}else{
			avgTime3="0";
		}
		questionSurvey2.setAvgTime(avgTime3);
		request.setAttribute("questionSurveys", questionSurvey2);
		request.setAttribute("questionnaireId", questionnaireId);
		return "securityJsp/questionnaire/questionnairesurvey";
	}
	
	@RequestMapping("/getChar")
	public @ResponseBody Result getChar( String questionnaireId){
		Questionnaire questionnaire=questionnaireService.findById(questionnaireId);
		if(questionnaire.getQuestionnaireEndTime()!=null){
			questionnaire.setQuestionnaireEndTime(new Date(questionnaire.getQuestionnaireEndTime().getTime()+24*60*60*1000-1));
		}
		List<QuestionSurvey> questionSurvey1=questionnaireService.findViewCountByDay(questionnaire);
		List<QuestionSurvey> questionSurvey3=questionnaireService.findIpCountByDay(questionnaire);
		String questionnaireCreateTime1=DateUtil.dateToString(questionnaire.getQuestionnaireCreateTime(), "yyyy-MM-dd HH:mm:ss");
		String questionnaireEndTime2=DateUtil.dateToString(questionnaire.getQuestionnaireEndTime(), "yyyy-MM-dd HH:mm:ss");
		List<String> dates=CalendarUtil.getBtDates(questionnaireCreateTime1,questionnaireEndTime2);
		Result result=new Result();
		List<QuestionSurvey> questionSurveyList=new ArrayList<QuestionSurvey>();
		for(String date:dates){
			QuestionSurvey questionSurveyStr=new QuestionSurvey();
			questionSurveyStr.setViewCount(0);
			questionSurveyStr.setIpCount(0);
			questionSurveyStr.setDays(date);
			for(QuestionSurvey questionSurveys:questionSurvey1){
				if(questionSurveys.getDays().equals(date)){
					questionSurveyStr.setViewCount(questionSurveys.getViewCount());
				}
			}
			for(QuestionSurvey questionSurvey4:questionSurvey3){
				if(questionSurvey4.getDays().equals(date)){
					questionSurveyStr.setIpCount(questionSurvey4.getIpCount());
				}
			}
			questionSurveyList.add(questionSurveyStr);
		}
		result.setData(questionSurveyList);
		return result;
	}
	
}
