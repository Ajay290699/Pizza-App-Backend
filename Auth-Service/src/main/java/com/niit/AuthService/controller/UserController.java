package com.niit.AuthService.controller;

import com.niit.AuthService.domain.User;
import com.niit.AuthService.exception.UserAlreadyException;
import com.niit.AuthService.exception.UserNotFoundException;
import com.niit.AuthService.security.SecurityTokenGenerator;
import com.niit.AuthService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/authService")
public class UserController {


    // how to inject userService with manual syntax


    UserService userService;
    SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    public UserController(UserService userService, SecurityTokenGenerator securityTokenGenerator) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }

    //http://localhost:8081/api/authservice/register --- post
    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws UserAlreadyException {
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    //http://localhost:8081/api/authservice/user/sam@gmail.com
    @DeleteMapping("/user/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable String email) throws UserNotFoundException {
        return new ResponseEntity<>(userService.deleteUser(email), HttpStatus.OK);
    }

    @PutMapping("/user")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginCheck(@RequestBody User user) {
        User result = userService.loginCheck(user.getEmail(), user.getPassword());
        if(result!=null) {
            //valid user
            //generate token
            Map<String, String> map = securityTokenGenerator.tokenGenrator(result);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        else {
            //invalid user or user does not exist
            return new ResponseEntity<>("invalid user or user does not exist", HttpStatus.NOT_FOUND);
        }
    }
}
