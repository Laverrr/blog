package com.laver.controller;

import com.laver.domain.Authority;
import com.laver.domain.User;
import com.laver.service.AuthorityService;
import com.laver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by L on 2018/9/14.
 */
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

    @GetMapping("/index")
    public String index(){
        return "index";
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
    public String registerUser(User user) {
        List<Authority> authorities = new ArrayList<>();
        authorities.add(authorityService.getAuthorityById(ROLE_USER_AUTHORITY_ID));
        user.setAuthorities(authorities);
        userService.saveUser(user);
        return "redirect:/login";
    }

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
