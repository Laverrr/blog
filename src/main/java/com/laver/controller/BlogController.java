package com.laver.controller;

import com.laver.domain.Blog;
import com.laver.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Created by L on 2018/9/12.
 */
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    BlogRepository blogRepository;

    @RequestMapping("/blogs")
    public List<Blog> list(@RequestParam(value = "title") String title,
                           @RequestParam(value = "summary") String summary,
                           @RequestParam(value = "content") String content,
                           @RequestParam(value = "pageIndex",defaultValue = "0") int  pageIndex,
                           @RequestParam(value = "pageSize",defaultValue = "10") int  pageSize){
        PageRequest pageRequest = new PageRequest(pageIndex, pageSize);
        Page<Blog> blogs = blogRepository.findDistinctByTitleContainingOrSummaryContainingOrContentContaining(title, summary, content, pageRequest);
        return blogs.getContent();
    }
}
