package com.niit.PizzaService.service;

import com.niit.PizzaService.domain.Pizza;
import com.niit.PizzaService.domain.User;
import com.niit.PizzaService.exception.UserAlreadyExistException;
import com.niit.PizzaService.exception.UserNotFoundException;
import com.niit.PizzaService.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PizzaServiceImpl implements PizzaService{


    private PizzaRepository pizzaRepository;

    @Autowired
    public PizzaServiceImpl(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    public User registerUser(User user) throws UserAlreadyExistException {
        if(pizzaRepository.findById(user.getEmail()).isPresent())
        {
            throw new UserAlreadyExistException();
        }

        return pizzaRepository.save(user);
    }

    @Override
    public User savePizzaToUserList(Pizza pizza, String email) throws UserNotFoundException {
        if(pizzaRepository.findById(email).isEmpty()){
            throw new UserNotFoundException();
        }
        User result=pizzaRepository.findById(email).get();
        if(result.getPizza()!=null) {
            result.getPizza().add(pizza);
        }else{
            result.setPizza(new ArrayList<>());
            result.getPizza().add(pizza);
        }
        pizzaRepository.save(result);
        return result;
    }

    @Override
    public List<Pizza> getAllUserPizzas(String email) throws UserNotFoundException {

        if(pizzaRepository.findById(email).isEmpty()){
            throw new UserNotFoundException();
        }
        List<Pizza> allPizzas=pizzaRepository.findById(email).get().getPizza();
        return allPizzas;
    }
}
