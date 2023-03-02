package com.niit.PizzaService.controller;

import com.niit.PizzaService.domain.Pizza;
import com.niit.PizzaService.domain.User;
import com.niit.PizzaService.exception.UserAlreadyExistException;
import com.niit.PizzaService.exception.UserNotFoundException;
import com.niit.PizzaService.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/pizza-service")
public class PizzaController {


    private PizzaService pizzaService;
    private ResponseEntity<?> responseEntity;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @PostMapping("/user")
    public ResponseEntity<?> registerUser(@RequestBody User user) throws UserAlreadyExistException {
        try {
            responseEntity =  new ResponseEntity<>(pizzaService.registerUser(user), HttpStatus.CREATED);
        }
        catch(UserAlreadyExistException e)
        {
            throw new UserAlreadyExistException();
        }
        return responseEntity;
    }

    //http://localhost:65001/api/movieservice/user/xyz@gmail.com/movie -- post -- movie
    @PostMapping("/user/{email}")
    public ResponseEntity<?> savePizzaToUserList(@RequestBody Pizza pizza, @PathVariable String email) throws UserNotFoundException {
        try {
            responseEntity = new ResponseEntity<>(pizzaService.savePizzaToUserList(pizza, email), HttpStatus.CREATED);
        }
        catch (UserNotFoundException e)
        {
            throw new UserNotFoundException();
        }
        return responseEntity;
    }

    @GetMapping("/user/{mail}")
    public ResponseEntity<?> getAllUserPizzas(@PathVariable String mail) throws UserNotFoundException {
        try {
            responseEntity = new ResponseEntity<>(pizzaService.getAllUserPizzas(mail),HttpStatus.OK);
        }
        catch (UserNotFoundException e)
        {
            throw new UserNotFoundException();
        }
        return responseEntity;
    }
}
