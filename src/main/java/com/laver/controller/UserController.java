package com.laver.controller;

import com.laver.domain.Authority;
import com.laver.domain.User;
import com.laver.service.AuthorityService;
import com.laver.service.UserService;
import com.laver.util.ConstrainViolationExceptionHandler;
import com.laver.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by L on 2018/9/10.
 */
@RestController
@RequestMapping("/user")
public class UserController {

//    @Autowired
//    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorityService authorityService;

    @GetMapping("/users")
    public ModelAndView list(@RequestParam(value="async",required=false) boolean async,
                             @RequestParam(value="pageIndex",required=false,defaultValue="0") int pageIndex,
                             @RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize,
                             @RequestParam(value="name",required=false,defaultValue="") String name,
                             Model model) {

        Pageable pageable = new PageRequest(pageIndex, pageSize);
        Page<User> page = userService.listUsersByNameLike(name, pageable);
        List<User> users = page.getContent();	// 当前所在页面数据列表
        model.addAttribute("page", page);
        model.addAttribute("users", users);
        return new ModelAndView(async==true?"users/list :: #mainContainerRepleace":"users/list", "model", model);
    }

//    @GetMapping("/{id}")
//    public ModelAndView list(Model model, @PathVariable Long id){
//        User user = userRepository.getOne(id);
//        model.addAttribute("user",user);
//        model.addAttribute("title","查看用户");
//        return new ModelAndView("users/view","model",model);
//    }

//    @GetMapping("/form")
//    public ModelAndView createForm(Model model){
//        User user = new User();
//        model.addAttribute("user",user);
//        model.addAttribute("title","创建用户");
//        return new ModelAndView("users/form","model",model);
//    }

//    @GetMapping("/delete/{id}")
//    public ModelAndView delete(Model model,@PathVariable Long id){
//        userRepository.deleteById(id);
//        return new ModelAndView("redirect:/user/users");
//    }
//
//    @GetMapping("/modify/{id}")
//    public ModelAndView modify(Model model,@PathVariable Long id){
//        User user = userRepository.getOne(id);
//        model.addAttribute("user",user);
//        model.addAttribute("title","修改用户");
//        return new ModelAndView("users/form","model",model);
//    }

//    @PostMapping
//    public ModelAndView saveOrUpdate(User user){
//        userRepository.save(user);
//        return new ModelAndView("redirect:/user/users");
//    }

    /**
     * 获取 form 表单页面
     * @return
     */
    @GetMapping("/add")
    public ModelAndView createForm(Model model) {
        model.addAttribute("user", new User());
        return new ModelAndView("users/add", "userModel", model);
    }

    /**
     * 获取修改用户的界面，及数据
     * @return
     */

    @GetMapping(value = "/edit/{id}")
    public ModelAndView modifyForm(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return new ModelAndView("users/edit", "userModel", model);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable("id") Long id, Model model) {
        try {
            userService.removeUser(id);
        } catch (Exception e) {
            return  ResponseEntity.ok().body( new Response(false, e.getMessage()));
        }
        return  ResponseEntity.ok().body( new Response(true, "处理成功"));
    }

    /**
     * 新建用户
     * @param user
     * @return
     */
    @PostMapping
    public ResponseEntity<Response> create(User user, Long authorityId) {
        List<Authority> authorities = new ArrayList<>();
        authorities.add(authorityService.getAuthorityById(authorityId));
        user.setAuthorities(authorities);

        if(user.getId() == null) {
            user.setEncodePassword(user.getPassword()); // 加密密码
        }else {
            // 判断密码是否做了变更
            User originalUser = userService.getUserById(user.getId());
            String rawPassword = originalUser.getPassword();
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            String encodePasswd = encoder.encode(user.getPassword());
            boolean isMatch = encoder.matches(rawPassword, encodePasswd);
            if (!isMatch) {
                user.setEncodePassword(user.getPassword());
            }else {
                user.setPassword(user.getPassword());
            }
        }

        try {
            userService.saveOrUpdateUser(user);
        }  catch (ConstraintViolationException e)  {
            return ResponseEntity.ok().body(new Response(false, ConstrainViolationExceptionHandler.getMessage(e)));
        }

        return ResponseEntity.ok().body(new Response(true, "处理成功", user));
    }
}
