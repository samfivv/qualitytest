package com.midai.miya.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midai.miya.sys.dao.CommentDao;
import com.midai.miya.sys.model.Comment;
import com.midai.miya.sys.service.CommentService;
import com.midai.miya.utils.PageUtil;
@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentDao commentDao;
	@Override
	public List<Comment> findAll(Comment comment, PageUtil page) {
			if(comment.getCommentContent()!=null){
				comment.setCommentContent(comment.getCommentContent().replace(" ", ""));	
			}
			if(comment.getVideoId()!=null){
				comment.setVideoId(comment.getVideoId().replace(" ", ""));
			}
			if(comment.getCommentUserNickname()!=null){
				comment.setCommentUserNickname(comment.getCommentUserNickname().replace(" ", ""));
			}
			if(comment.getVideoName()!=null){
			comment.setVideoName(comment.getVideoName().replace(" ", ""));
		    }
		List<Comment> comments=commentDao.findAll(comment, page);
		return comments;
	}

	@Override
	public long findAllCount(Comment comment) {
		if(comment.getCommentContent()!=null){
			comment.setCommentContent(comment.getCommentContent().replace(" ", ""));	
		}
		if(comment.getVideoId()!=null){
			comment.setVideoId(comment.getVideoId().replace(" ", ""));
		}
		if(comment.getCommentUserNickname()!=null){
			comment.setCommentUserNickname(comment.getCommentUserNickname().replace(" ", ""));
		}
		if(comment.getVideoName()!=null){
		comment.setVideoName(comment.getVideoName().replace(" ", ""));
	    }
		long count=commentDao.findAllCount(comment);
		return count;
	}

	@Override
	public void update(Comment comment) {
		commentDao.update(comment);
	}

}
