package com.jwttest.jwttest.security.controller;

import com.jwttest.jwttest.model.Restaurant;
import com.jwttest.jwttest.security.entity.User;
import com.jwttest.jwttest.security.service.UserService;
import com.jwttest.jwttest.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    RestaurantService restaurantService;

    @PostMapping("/restaurant/{id}")
    public void addToFavorites(@Valid @PathVariable Long id, @RequestBody Long req){
        System.out.println("userId = " + req + "restID = " + id);
        User user = this.userService.getById(req).get();
        Restaurant restaurant = this.restaurantService.getRestaurantById(id);
        System.out.println("USER " + user);
        restaurant.setUserList(user);
        user.setFavoritesList(restaurant);
        userService.save(user);
        restaurantService.save(restaurant);

    }
}
