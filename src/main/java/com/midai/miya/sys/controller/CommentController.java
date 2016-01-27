package com.midai.miya.sys.controller;

import java.io.UnsupportedEncodingException;
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
import com.midai.miya.sys.model.Comment;
import com.midai.miya.sys.service.CommentService;
import com.midai.miya.user.vo.Result;
@Controller
@RequestMapping("/comment")
public class CommentController extends BaseController {
	private static final long serialVersionUID = -6174875853457109765L;
	@Autowired
	private CommentService commentService;
	/**
	 * 查询评论内容
	 * 王梦圆
	 * 2015年5月19日
	 */
	@RequestMapping("/findAll")
	public @ResponseBody Grid findAll(Comment comment,HttpServletRequest request){
		this.addLog(request, "查询评论", Constant.LOG_TYPE_SELECT);
		Grid grid=new Grid();
		List<Comment> comments=commentService.findAll(comment, this.getPage(request));
		long count=commentService.findAllCount(comment);
		grid.setRows(comments);
		grid.setTotal(count);
		return grid;
	}
	/**
	 * 屏蔽评论
	 * 王梦圆
	 * 2015年5月19日
	 */
	@RequestMapping("/disableComment")
	public @ResponseBody Result disableComment(Comment comment,HttpServletRequest request){
		this.addLog(request, "屏蔽评论", Constant.LOG_TYPE_UPDATE);
		comment.setCommentState(2);
		commentService.update(comment);
		Result result=new Result();
		result.setMsg("屏蔽成功");
		result.setSuccess(true);
		return result;
	} 
	/**
	 * 取消屏蔽评论
	 * 王梦圆
	 * 2015年5月19日
	 */
	@RequestMapping("/openComment")
	public @ResponseBody Result openComment(Comment comment,HttpServletRequest request){
		this.addLog(request, "取消屏蔽评论", Constant.LOG_TYPE_UPDATE);
		comment.setCommentState(1);
		commentService.update(comment);
		Result result=new Result();
		result.setMsg("取消屏蔽成功");
		result.setSuccess(true);
		return result;
	} 
	/**
	 * 导出评论信息
	 * 王梦圆
	 * 2015年5月19日
	 */
	@RequestMapping(value = "/exportComment", method = RequestMethod.POST)
	public void exportCategory(Comment comment, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		this.addLog(request, "导出评论信息", Constant.LOG_TYPE_EXPORT);
		String commentContent=comment.getCommentContent();
		String commentUserNickname=comment.getCommentUserNickname();
		String videoName=comment.getVideoName();
		commentContent=new String(commentContent.getBytes("iso-8859-1"),"utf-8");
		commentUserNickname=new String(commentUserNickname.getBytes("iso-8859-1"),"utf-8");
		videoName=new String(videoName.getBytes("iso-8859-1"),"utf-8");
		comment.setCommentContent(commentContent);
		comment.setCommentUserNickname(commentUserNickname);
		comment.setVideoName(videoName);
		List<Comment> commentList = commentService.findAll(comment, this.getExportPage(request));
		super.doExport(request, response, commentList, "评论审核", "评论审核",
				this.getHeadForComment(), this.getColumnForComment());
	}
	private String[] getHeadForComment() {
		return new String[] { "视频ID", "视频名称", "评论内容", "评论人","评论状态","评论时间" };
	}
	private String[] getColumnForComment() {
		return new String[] { "videoId", "videoName", "commentContent",
				"commentUserNickname","commentStateStr","commentCreateTimeStr" };
	}
}
