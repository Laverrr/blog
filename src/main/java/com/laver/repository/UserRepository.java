package com.laver.repository;

import com.laver.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by L on 2018/9/10.
 */
public interface UserRepository extends JpaRepository<User,Long> {

    Page<User> findByNameLike(String name,Pageable pageable);

    User findByUsername(String username);
}
