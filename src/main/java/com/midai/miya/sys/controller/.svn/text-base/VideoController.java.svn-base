package com.midai.miya.sys.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.midai.miya.filter.PropertieUtil;
import com.midai.miya.sys.dao.InterestDao;
import com.midai.miya.sys.model.Approval;
import com.midai.miya.sys.model.Interest;
import com.midai.miya.sys.model.Operator;
import com.midai.miya.sys.model.Video;
import com.midai.miya.sys.service.InterestService;
import com.midai.miya.sys.service.VideoService;
import com.midai.miya.user.vo.Result;
import com.midai.miya.utils.DateUtil;
import com.midai.miya.utils.UUIDUtil;
import com.midai.miya.utils.UnPassReasonMap;
@Controller
@RequestMapping("/videoController")
public class VideoController extends BaseController {

	private static final long serialVersionUID = 2628731742416087008L;
	@Autowired
	private VideoService videoService;
	@Autowired
	private InterestService interestService;
	@Autowired
	private InterestDao interestDao;
	/**
	 * 查看上传视频信息
	 * 王梦圆
	 * 2015年4月28日
	 */
	@RequestMapping("/findAllVideo")
	public @ResponseBody Grid findAllVideo(Video video,HttpServletRequest request){
		if(video.getVideoState()==null||"".equals(video.getVideoState())){
			video.setVideoState(0);
		}
		List<Video> videos=videoService.findAllVideo(video, this.getPage(request));
		long total=videoService.findVideoCount(video);
		Grid grid=new Grid();
		grid.setTotal(total);
		grid.setRows(videos);
		return grid;
	}
	/**
	 * 查看是否被锁定
	 * 王梦圆
	 * 2015年6月8日
	 */
	@RequestMapping("/findById")
	public @ResponseBody Result findById(String videoId,HttpServletRequest request){
		Result result=new Result();
		Video video=videoService.findVideoById(videoId);
		Operator operator=this.getCurrentOperator(request);
		if(video.getVideoLock()!=null&&video.getVideoLock()==1&&video.getVideoLockUserName()!=null){
				if(!operator.getOperatorName().equals(video.getVideoLockUserName())){
					result.setMsg("该操作已被"+video.getVideoLockUserName()+"锁定");
					result.setSuccess(false);
				}else{
					result.setSuccess(true);
				}
			}else{
			video.setVideoLock(1);
			video.setVideoLockUserName(operator.getOperatorName());
			videoService.update(video);
			result.setSuccess(true);
		}
		return result;
	}
	/**
	 * 修改锁的状态
	 * 王梦圆
	 * 2015年6月8日
	 */
	@RequestMapping("/update")
	public @ResponseBody Result update(String videoId,HttpServletRequest request){
		Result result=new Result();
		Video video=videoService.findVideoById(videoId);
		if(video.getVideoLock()!=null&&video.getVideoLock()==1){
			video.setVideoLock(0);
		}else{
			video.setVideoLock(1);
		}
			videoService.update(video);
			result.setSuccess(true);
		return result;
	}
	
	/**
	 * 根据id查看视频审核结果
	 * 王梦圆
	 * 2015年5月4日
	 */
	@RequestMapping("/findVideoById")
	public @ResponseBody Video findVideoById(String videoId,String videoLock,HttpServletRequest request){
		this.addLog(request, "查看视频", Constant.LOG_TYPE_SELECT);
		Video video=videoService.findVideoById(videoId);
		Map<String,String> map=PropertieUtil.unPassReasonMap;
		 for(String key:map.keySet()){
			 if(key!=null&&!"".equals(key)){
				 if(map.get(key)!=null){
					 if(video!=null&&map.get(key).equals(video.getNotPassReason())){
							video.setNotPassReasonState(key);
							break;
						}else{
							video.setNotPassReasonState("zzz");
						}
				 }
			 }
			
		 }
		
		return video;
	}
	/**
	 * 给审核不通过的原因加一个下拉框
	 * 王梦圆
	 * 2015年6月2日
	 */
	@RequestMapping("/findNotPassReason")
	public @ResponseBody List<UnPassReasonMap> findNotPassReason(){
		List<UnPassReasonMap> maps=new ArrayList<UnPassReasonMap>();
		Map<String,String> map=PropertieUtil.unPassReasonMap;
		 for(String key:map.keySet()){
			 if(key!=null&&!"".equals(key)){
				 if(map.get(key)!=null){
					 UnPassReasonMap unPassReasonMap=new UnPassReasonMap();
					 unPassReasonMap.setKey(key);
					 unPassReasonMap.setValue(map.get(key));
					 maps.add(unPassReasonMap);
				 }
			 }
			
		 }
		return maps;
	}
	/**
	 * 导出视频审核信息
	 * 王梦圆
	 * 2015年4月28日
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/exportVideo" , method = RequestMethod.POST)
	public void exportVideo(Video video,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		this.addLog(request, "导出视频审核信息", Constant.LOG_TYPE_EXPORT);
		String videoName=video.getVideoName();
		String userName=video.getUserName();
		videoName=new String(videoName.getBytes("iso-8859-1"),"utf-8");
		userName=new String(userName.getBytes("iso-8859-1"),"utf-8");
		video.setVideoName(videoName);
		video.setUserName(userName);
		List<Video> videoList = videoService.findAllVideo(video,this.getExportPage(request));
		
		 super.doExport(request, response, videoList,"视频审核","视频审核",this.getHeadForVideo(), this.getColumnForVideo());
	}
	private String[] getHeadForVideo(){
		return new String[]{
				"视频ID","视频名称","视频简介","创建人","创建时间","审核状态","审核时间",
		};
	}
	private String[] getColumnForVideo(){
		return new String[]{
				"videoId","videoName","videoDesc","userName","videoCreatetimeStr","videoStateStr","videoAudittimeStr"
		};
	}
	/**
	 * 审核结果添加到审核日志
	 * 王梦圆
	 * 2015年4月28日
	 */
	@RequestMapping("/addApproval")
	@ResponseBody
	public Result addApproval(Approval approval,HttpServletRequest request){
		this.addLog(request, "视频审核", Constant.LOG_TYPE_UPDATE);
		approval.setApprovalId(UUIDUtil.getUUID());
		approval.setApprovalTime(new Date());
		Operator operator=this.getCurrentOperator(request);
		approval.setOperatorId(operator.getOperatorId());
		approval.setApprovalState(approval.getVideoState());
		if(approval.getVideoState()==1){
			approval.setNotPassReason("");
			approval.setNotPassReasonState("");
			Video video=videoService.findVideoById(approval.getVideoId());
			if(video.getVideoState()==0||video.getVideoState()==2){
			String interestVideoTimelong=interestDao.findInterestOftenChange(video.getInterestId());
			Integer interestVideoCount=interestDao.findInterestOftenChangeVideoCount(video.getInterestId());
			if(interestVideoTimelong==null||"".equals(interestVideoTimelong)){
				long a =DateUtil.getSecond(video.getVideoDuration());
				interestVideoTimelong=DateUtil.formatSecond(a);
			}else{
				long a =DateUtil.getSecond(interestVideoTimelong) + DateUtil.getSecond(video.getVideoDuration());
				interestVideoTimelong=DateUtil.formatSecond(a);
			} 
			if(interestVideoCount!=null){
			interestDao.updateInterestOftenChange(interestVideoCount+1,video.getInterestId(), interestVideoTimelong);
			}else{
			interestDao.updateInterestOftenChange(1,video.getInterestId(), interestVideoTimelong);
			}
			}
		}else{
			Video video=videoService.findVideoById(approval.getVideoId());
			if(video.getVideoState()==1){
				String interestVideoTimelong=interestDao.findInterestOftenChange(video.getInterestId());
				Integer interestVideoCount=interestDao.findInterestOftenChangeVideoCount(video.getInterestId());
				if(interestVideoTimelong!=null){
					long a =DateUtil.getSecond(interestVideoTimelong) - DateUtil.getSecond(video.getVideoDuration());
					interestVideoTimelong=DateUtil.formatSecond(a);
				}
				if(interestVideoCount!=null){
				interestDao.updateInterestOftenChange(interestVideoCount-1,video.getInterestId(), interestVideoTimelong);
				}else{
					interestDao.updateInterestOftenChange(0,video.getInterestId(), interestVideoTimelong);
				}
			}
		} 
		videoService.addApproval(approval);
		Video video=new Video();
		video.setVideoId(approval.getVideoId());
		video.setVideoState(approval.getApprovalState());
		video.setVideoAudittime(new Date());
		video.setVideoLock(0);
		videoService.updateVideoState(video);
		Result result=new Result();
		result.setSuccess(true);
		return result;
	}
	/**
	 * 审核日志查询
	 * 王梦圆
	 * 2015年4月28日
	 */
	@RequestMapping("/findApproval")
	public @ResponseBody Grid findApproval(Approval approval,HttpServletRequest request){
		 this.addLog(request, "审核日志", Constant.LOG_TYPE_SELECT);
		 List<Approval> approvals=videoService.findApproval(approval,this.getPage(request));
		 long count=videoService.findApprovalCount(approval);
		 Grid grid=new Grid();
		 grid.setRows(approvals);
		 grid.setTotal(count);
		 return grid;
	} 
	/**
	 * 导出审核日志信息
	 * 王梦圆
	 * 2015年4月28日
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/exportApproval" , method = RequestMethod.POST)
	public void exportApproval(Approval approval,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		this.addLog(request, "导出审核日志信息", Constant.LOG_TYPE_EXPORT);
		String videoName=approval.getVideoName();
		String operatorName=approval.getOperatorName();
		videoName=new String(videoName.getBytes("iso-8859-1"),"utf-8");
		operatorName=new String(operatorName.getBytes("iso-8859-1"),"utf-8");
		approval.setVideoName(videoName);
		approval.setOperatorName(operatorName);
		List<Approval> approvalList = videoService.findApproval(approval,this.getExportPage(request));
		super.doExport(request, response, approvalList,"审核日志","审核日志",this.getHeadForApproval(), this.getColumnForApproval());
	}
	private String[] getHeadForApproval(){
		return new String[]{
				"视频ID","视频名称","视频简介","审核人","审核时间","审核结果","不通过的原因"
		};
	}
	private String[] getColumnForApproval(){
		return new String[]{
				"videoId","videoName","videoDesc","operatorName","approvalTimeStr","approvalStateStr","notPassReason"
		};
	}

}
