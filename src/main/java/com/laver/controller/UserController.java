package com.laver.controller;

import com.laver.domain.User;
import com.laver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by L on 2018/9/10.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public UserRepository userRepository;

    @GetMapping("/users")
    public ModelAndView list(Model model){
        List<User> users = userRepository.findAll();
        model.addAttribute("users",users);
        model.addAttribute("title","用户管理");
        return new ModelAndView("users/list","model",model);
    }

    @GetMapping("/{id}")
    public ModelAndView list(Model model, @PathVariable Long id){
        User user = userRepository.getOne(id);
        model.addAttribute("user",user);
        model.addAttribute("title","查看用户");
        return new ModelAndView("users/view","model",model);
    }

    @GetMapping("/form")
    public ModelAndView createForm(Model model){
        User user = new User();
        model.addAttribute("user",user);
        model.addAttribute("title","创建用户");
        return new ModelAndView("users/form","model",model);
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(Model model,@PathVariable Long id){
        userRepository.deleteById(id);
        return new ModelAndView("redirect:/user/users");
    }

    @GetMapping("/modify/{id}")
    public ModelAndView modify(Model model,@PathVariable Long id){
        User user = userRepository.getOne(id);
        model.addAttribute("user",user);
        model.addAttribute("title","修改用户");
        return new ModelAndView("users/form","model",model);
    }

    @PostMapping
    public ModelAndView saveOrUpdate(User user){
        userRepository.save(user);
        return new ModelAndView("redirect:/user/users");
    }
}
