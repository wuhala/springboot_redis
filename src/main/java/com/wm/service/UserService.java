package com.wm.service;

import com.wm.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findAllById(int id);

    User updateUserById(User user);

    User deleteUserById(int id);

}
