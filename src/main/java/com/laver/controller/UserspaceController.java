package com.laver.controller;

import com.laver.domain.User;
import com.laver.service.UserService;
import com.laver.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by L on 2018/9/15.
 */
@Controller
@RequestMapping("/space")
public class UserspaceController {

    @Qualifier("userServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

//    @Autowired
//    private BlogService blogService;

    @Value("${file.server.url}")
    private String fileServerUrl;

    @GetMapping("/{username}/profile")
    //判断是否是当前验证用户
    @PreAuthorize("authentication.name.equals(#username)")
    public ModelAndView profile(@PathVariable("username") String username, Model model) {
        User  user = (User)userDetailsService.loadUserByUsername(username);
        model.addAttribute("user", user);
        model.addAttribute("fileServerUrl",fileServerUrl);
        return new ModelAndView("/userspace/profile", "userModel", model);
    }

    /**
     * 保存个人设置
     * @param user
     * @return
     */
    @PostMapping("/{username}/profile")
    @PreAuthorize("authentication.name.equals(#username)")
    public String saveProfile(@PathVariable("username") String username,User user) {
        User originalUser = userService.getUserById(user.getId());
        originalUser.setEmail(user.getEmail());
        originalUser.setName(user.getName());

        // 判断密码是否做了变更
        String rawPassword = originalUser.getPassword();
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePasswd = encoder.encode(user.getPassword());
        boolean isMatch = encoder.matches(rawPassword, encodePasswd);
        if (!isMatch) {
            originalUser.setEncodePassword(user.getPassword());
        }

        userService.saveOrUpdateUser(originalUser);
        return "redirect:/space/" + username + "/profile";
    }

    /**
     * 获取编辑头像的界面
     * @param username
     * @param model
     * @return
     */
    @GetMapping("/{username}/avatar")
    @PreAuthorize("authentication.name.equals(#username)")
    public ModelAndView avatar(@PathVariable("username") String username, Model model) {
        User  user = (User)userDetailsService.loadUserByUsername(username);
        model.addAttribute("user", user);
        return new ModelAndView("/userspace/avatar", "userModel", model);
    }

    /**
     * 保存头像
     * @param username
     * @return
     */
    @PostMapping("/{username}/avatar")
    @PreAuthorize("authentication.name.equals(#username)")
    public ResponseEntity<Response> saveAvatar(@PathVariable("username") String username, @RequestBody User user) {
        String avatarUrl = user.getAvatar();

        User originalUser = userService.getUserById(user.getId());
        originalUser.setAvatar(avatarUrl);
        userService.saveOrUpdateUser(originalUser);

        return ResponseEntity.ok().body(new Response(true, "处理成功", avatarUrl));
    }


    @GetMapping("/{username}")
    public String userSpace(@PathVariable("username") String username) {
        System.out.println("username" + username);
        return "/space";
    }

    @GetMapping("/{username}/blogs")
    public String listBlogsByOrder(@PathVariable("username") String username,
                                   @RequestParam(value="order",required=false,defaultValue="new") String order,
                                   @RequestParam(value="category",required=false ) Long category,
                                   @RequestParam(value="keyword",required=false ) String keyword) {

        if (category != null) {

            System.out.print("category:" +category );
            System.out.print("selflink:" + "redirect:/space/"+ username +"/blogs?category="+category);
            return "/space";

        } else if (keyword != null && keyword.isEmpty() == false) {

            System.out.print("keyword:" +keyword );
            System.out.print("selflink:" + "redirect:/space/"+ username +"/blogs?keyword="+keyword);
            return "/space";
        }

        System.out.print("order:" +order);
        System.out.print("selflink:" + "redirect:/space/"+ username +"/blogs?order="+order);
        return "/space";
    }

    @GetMapping("/{username}/blogs/{id}")
    public String listBlogsByOrder(@PathVariable("id") Long id) {

        System.out.print("blogId:" + id);
        return "/blog";
    }


    @GetMapping("/{username}/blogs/edit")
    public String editBlog() {

        return "/blogedit";
    }
}
