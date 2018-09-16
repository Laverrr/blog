package com.laver.service;

import com.laver.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by L on 2018/9/16.
 */
public interface UserService {
    /**
     * 保存用户
     * @return
     */
    User saveOrUpdateUser(User user);

    /**
     * 删除用户
     * @return
     */
    void removeUser(Long id);

    /**
     * 删除列表里面的用户
     * @return
     */
    void removeUsersInBatch(List<User> users);

    /**
     * 根据id获取用户
     * @return
     */
    User getUserById(Long id);

    /**
     * 获取用户列表
     * @return
     */
    List<User> listUsers();

    /**
     * 根据用户名进行分页模糊查询
     * @return
     */
    Page<User> listUsersByNameLike(String name, Pageable pageable);
}
