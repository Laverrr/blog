package com.laver.service.Impl;

import com.laver.domain.User;
import com.laver.repository.UserRepository;
import com.laver.service.UserService;
import com.laver.util.EncodePwd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

/**
 * Created by L on 2018/9/16.
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public User saveUser(User user) {
        user.setPassword(EncodePwd.getEncodePassword(user.getPassword()));
        User save = userRepository.save(user);
        return save;
    }

    @Override
    public User updateUser(User user) {
        User save = userRepository.save(user);
        return save;
    }

    @Override
    public void removeUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void removeUsersInBatch(List<User> users) {
        userRepository.deleteAll(users);
    }

    @Override
    public User getUserById(Long id) {
        User user = userRepository.getOne(id);
        return user;
    }

    @Override
    public List<User> listUsers() {
        List<User> userList = userRepository.findAll();
        return userList;
    }

    @Override
    public Page<User> listUsersByNameLike(String name, Pageable pageable) {
        // 模糊查询
        name = "%" + name + "%";
        Page<User> users = userRepository.findByNameLike(name, pageable);
        return users;
    }

    /**
     * 重写UserDetailsService的方法
     * User已经继承了UserDetails接口
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> listUsersByUsernames(Collection<String> usernames) {
        return userRepository.findByUsernameIn(usernames);
    }
}
