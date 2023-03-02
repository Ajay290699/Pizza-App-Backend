package com.niit.AuthService.security;

import com.niit.AuthService.domain.User;

import java.util.Map;

public interface SecurityTokenGenerator {

    public Map<String, String> tokenGenrator(User user);
}
