package com.laver.repository;

import com.laver.domain.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by L on 2018/9/12.
 */
public interface BlogRepository extends ElasticsearchRepository<Blog,String> {
    Page<Blog> findDistinctByTitleContainingOrSummaryContainingOrContentContaining(String title, String summary, String content, Pageable pageable);
}
