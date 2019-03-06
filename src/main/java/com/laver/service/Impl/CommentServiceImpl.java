package com.laver.service.Impl;

import javax.transaction.Transactional;

import com.laver.domain.Comment;
import com.laver.repository.CommentRepository;
import com.laver.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Comment 服务.
 *
 */
@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Override
	@Transactional
	public void removeComment(Long id) {
		commentRepository.deleteById(id);
	}

	@Override
	public Comment getCommentById(Long id) {
		return commentRepository.getOne(id);
	}

}
