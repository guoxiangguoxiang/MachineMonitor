package com.zju.dao;

import com.zju.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,String> {
    User findByName(String name);

    User findByIdAndPassword(Long id, String oldPassword);

    User findById(Long id);
}