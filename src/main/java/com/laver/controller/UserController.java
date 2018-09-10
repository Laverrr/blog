package com.laver.controller;

import com.laver.domain.User;
import com.laver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

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
        return new ModelAndView("users/list","userModel",model);
    }

    @GetMapping("/{id}")
    public ModelAndView list(Model model,@PathVariable Long id){
        Optional<User> userOptional = userRepository.findById(id);
        User user = userOptional.get();
        model.addAttribute("user",user);
        model.addAttribute("title","查看用户");
        return new ModelAndView("users/view","userModel",model);
    }

    @GetMapping("/form")
    public ModelAndView createForm(Model model){
        User user = new User();
        model.addAttribute("user",user);
        model.addAttribute("title","创建用户");
        return new ModelAndView("users/form","userModel",model);
    }

    @PostMapping
    public ModelAndView saveOrUpdate(User user){
        userRepository.save(user);
        return new ModelAndView("redirect:/users");
    }
}
