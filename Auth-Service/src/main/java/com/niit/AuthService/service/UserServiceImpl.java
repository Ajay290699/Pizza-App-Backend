package com.niit.AuthService.service;

import com.niit.AuthService.domain.User;
import com.niit.AuthService.exception.UserAlreadyException;
import com.niit.AuthService.exception.UserNotFoundException;
import com.niit.AuthService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User addUser(User user) throws UserAlreadyException {
        if(userRepository.findById(user.getEmail()).isPresent()) {
            throw new UserAlreadyException();
        }
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public String deleteUser(String email) throws UserNotFoundException {
        if(userRepository.findById(email).isEmpty()) {
            throw new UserNotFoundException();
        }
        userRepository.deleteById(email);
        return "user deleted successfully";
    }

    @Override
    public User updateUser(User user) {
        if(userRepository.findById(user.getEmail()).isEmpty()) {
            return null;
        }
        User tempUser = userRepository.findById(user.getEmail()).get();
        tempUser.setFirstName(user.getFirstName());
        tempUser.setLastName(user.getLastName());
        tempUser.setPassword(user.getPassword());
        return userRepository.save(tempUser);
    }

    @Override
    public User loginCheck(String email, String password) {
        if(userRepository.findById(email).isPresent()) {
            User user = userRepository.findById(email).get();
            if(user.getPassword().equals(password)) {
                return user;
            }
            else {
                return null;
            }
        }
        else {
            return null;
        }
    }
}
