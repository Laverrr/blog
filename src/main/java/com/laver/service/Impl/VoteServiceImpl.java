package com.laver.service.Impl;

import javax.transaction.Transactional;

import com.laver.domain.Vote;
import com.laver.repository.VoteRepository;
import com.laver.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * Vote 服务.
 *
 */
@Service
public class VoteServiceImpl implements VoteService {

	@Autowired
	private VoteRepository voteRepository;
	/* (non-Javadoc)
	 * @see com.waylau.spring.boot.blog.service.VoteService#removeVote(java.lang.Long)
	 */
	@Override
	@Transactional
	public void removeVote(Long id) {
		voteRepository.deleteById(id);
	}
	@Override
	public Vote getVoteById(Long id) {
		return voteRepository.getOne(id);
	}

}
