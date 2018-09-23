package com.laver.controller;

import com.laver.vo.Menu;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by L on 2018/9/15.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    /**
     * 获取后台管理主页面
     * @return
     */
    @GetMapping
    public ModelAndView listUsers(Model model) {
        List<Menu> list = new ArrayList<>();
        list.add(new Menu("用户管理", "/user/users"));
        model.addAttribute("list", list);
        return new ModelAndView("admins/index", "model", model);
    }
}
