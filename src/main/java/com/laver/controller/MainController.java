package com.laver.controller;

import com.laver.domain.Authority;
import com.laver.domain.User;
import com.laver.service.AuthorityService;
import com.laver.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by L on 2018/9/14.
 */
@Slf4j
@Controller
//继承ErrorController可以自定义错误返回页面 implements ErrorController
public class MainController  {

    //权限ID
    private static final Long ROLE_USER_AUTHORITY_ID = 2L;

    @Autowired
    AuthorityService authorityService;

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String root(){
        return "redirect:/index";
    }

    //将首页重定向 进行数据初始化
    @GetMapping("/index")
    public String index(){
        return "redirect:/blogs";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/login-error")
    public String loginError(Model model){
        model.addAttribute("loginError",true);
        model.addAttribute("errorMsg","login failed,username or password wrong!!");
        return "login";
    }

    @GetMapping("/search")
    public String search() {
        return "search";
    }

    /**
     * 注册用户
     * @param user
     * @return
     */
    @PostMapping("/register")
    public ModelAndView registerUser(User user, Model model) {
        List<Authority> authorities = new ArrayList<>();
        authorities.add(authorityService.getAuthorityById(ROLE_USER_AUTHORITY_ID));
        user.setAuthorities(authorities);
        try {
            userService.saveUser(user);
        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("errorMsg",e.getMessage());
            return new ModelAndView("/register", "model", model);
        }
        return new ModelAndView("/login", "model", model);
    }

    //此方法给spring security调用
    @GetMapping("/404")
    public String notFoundPage() {
        return "404";
    }

//    @Override
//    public String getErrorPath() {
//        //返回错误页面的URL
//        return "/error";
//    }
//
//    @RequestMapping("/error")
//    public String errorPage() {
//        return "404";
//    }
}
