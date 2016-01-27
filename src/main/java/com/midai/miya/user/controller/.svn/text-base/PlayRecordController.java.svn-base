package com.midai.miya.user.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.midai.miya.user.model.Play;
import com.midai.miya.user.model.PlayRecord;
import com.midai.miya.user.service.PlayService;
import com.midai.miya.utils.CalendarUtil;
@Controller
@RequestMapping("/playRecord")
public class PlayRecordController extends BaseController {
 
	private static final long serialVersionUID = -8561378081294666435L;
	@Autowired
	private PlayService playService;
	@RequestMapping("/findPlayRecord")
	public @ResponseBody Grid findPlayRecord(PlayRecord playRecord,HttpServletRequest request){
		Grid grid=new Grid();
		List<PlayRecord> records=playService.findPlayRecord(playRecord,this.getPage(request));
		long count=playService.findCount(playRecord, this.getPage(request));
		grid.setRows(records);
		grid.setTotal(count);
		return grid;
		}
	
	@RequestMapping(value = "/exportPlayRecord" , method = RequestMethod.POST)
	public void exportPlayRecord(PlayRecord playRecord,HttpServletRequest request, HttpServletResponse response){
		this.addLog(request, "导出播放记录", Constant.LOG_TYPE_EXPORT);
		List<PlayRecord> records=playService.findPlayRecord(playRecord,this.getExportPage(request));
		 super.doExport(request, response, records,"播放记录","播放记录",this.getHeadForPlayRecord(),this.getColumnForPlayRecord());
	}
	private String[] getHeadForPlayRecord(){
		         return new String[]{
		        		 "播放IP","兴趣标题","视频ID","视频名称","用户昵称","来源","播放时间","结束时间"
		};
	}
	private String[] getColumnForPlayRecord(){
		return new String[]{
				"playIp","interesetTitle","videoId","videoName","userNickname","playFormStr","playBeginTimeStr","playEndTimeStr"
		};
	}
	
	/**
	 * 播放记录折线图
	 * 王梦圆
	 * 2015年6月18日
	 */
	@RequestMapping("/findCount")
	public @ResponseBody List<Play> findCount(String queryBeginTimeStr,String queryEndTimeStr,Integer playFrom){
		 if(queryBeginTimeStr.equals("")){
			 Calendar c=Calendar.getInstance();
			 int year=c.get(Calendar.YEAR);
			 int month=c.get(Calendar.MONTH)+1;
			 if(month<10){
				 queryBeginTimeStr=year+"-0"+month+"-01 00:00:00";
			 }else{
			 queryBeginTimeStr=year+"-"+month+"-01 00:00:00";
			 }
		 }
		 if(queryEndTimeStr.equals("")){
			 SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 queryEndTimeStr=sdf.format(new Date());
		 }
		Play play=new Play();
		play.setPlayFrom(playFrom);
		play.setQueryBeginTimeStr(queryBeginTimeStr);
		play.setQueryEndTimeStr(queryEndTimeStr);
		List<Play> plays=playService.findCountAll(play);
		List<Play> countIps=playService.findCountByIp(play);
		for(int i=0;i<plays.size();i++){
			plays.get(i).setCountIp(countIps.get(i).getCountIp());
		}
		List<String> dates=CalendarUtil.getBtDates(queryBeginTimeStr, queryEndTimeStr);
		List<Play> playAll=new ArrayList<Play>();
		if(dates!=null){
			for(String date:dates){
				Play play1=new Play();
				play1.setCountAll(0);
				play1.setCountIp(0);
				play1.setDays(date);
				for(Play p:plays){
					if(date.equals(p.getDays())){
						play1.setCountAll(p.getCountAll());
						play1.setCountIp(p.getCountIp());
					}
				}
				playAll.add(play1);
			}
		}
		return playAll;
	}
}
