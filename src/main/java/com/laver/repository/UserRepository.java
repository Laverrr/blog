package com.laver.repository;

import com.laver.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by L on 2018/9/10.
 */
public interface UserRepository extends JpaRepository<User,Long> {
}
