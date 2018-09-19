package com.laver.service.Impl;

import com.laver.domain.User;
import com.laver.repository.UserRepository;
import com.laver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public User saveOrUpdateUser(User user) {
        user.setEncodePassword(user.getPassword());
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
        name="%"+name+"%";
        Page<User> userPage = userRepository.findByNameLike(name, pageable);
        return userPage;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
