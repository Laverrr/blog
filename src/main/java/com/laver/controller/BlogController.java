package com.laver.controller;

import com.laver.domain.Blog;
import com.laver.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Created by L on 2018/9/12.
 */
@Controller
@RequestMapping("/blog")
public class BlogController {

    @GetMapping("list")
    public String listBlogs(@RequestParam(value="order",required=false,defaultValue="new") String order,
                            @RequestParam(value="keyword",required=false) String keyword) {
        System.out.print("order:" +order + ";keyword:" +keyword );
        return "redirect:/index?order="+order+"&keyword="+keyword;
    }
}
