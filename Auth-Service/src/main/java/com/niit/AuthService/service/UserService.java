package com.niit.AuthService.service;

import com.niit.AuthService.domain.User;
import com.niit.AuthService.exception.UserAlreadyException;
import com.niit.AuthService.exception.UserNotFoundException;

import java.util.List;

public interface UserService {

    public User addUser(User user) throws UserAlreadyException;
    public List<User> getAllUser();
    public String deleteUser(String email) throws UserNotFoundException;
    public User updateUser(User user);
    public User loginCheck(String email, String password);
}
