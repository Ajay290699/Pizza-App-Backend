package com.niit.PizzaService.service;

import com.niit.PizzaService.domain.Pizza;
import com.niit.PizzaService.domain.User;
import com.niit.PizzaService.exception.UserAlreadyExistException;
import com.niit.PizzaService.exception.UserNotFoundException;

import java.util.List;

public interface PizzaService {

    User registerUser(User user) throws UserAlreadyExistException;
    User savePizzaToUserList(Pizza pizza, String email) throws UserNotFoundException;
    List<Pizza> getAllUserPizzas(String email) throws UserNotFoundException;
}
