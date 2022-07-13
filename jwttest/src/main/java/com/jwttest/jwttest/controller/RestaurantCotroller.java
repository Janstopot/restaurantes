package com.jwttest.jwttest.controller;



import com.jwttest.jwttest.model.Restaurant;
import com.jwttest.jwttest.security.entity.User;
import com.jwttest.jwttest.security.jwt.JwtProvider;
import com.jwttest.jwttest.security.jwt.JwtTokenFilter;
import com.jwttest.jwttest.security.entity.MainUser;
import com.jwttest.jwttest.security.service.UserService;
import com.jwttest.jwttest.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
public class RestaurantCotroller {

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    UserService userService;

    @Autowired
    JwtProvider jwtProvider;

    @GetMapping("restaurants")
    @CrossOrigin()
    public List<Restaurant> getAllRestaurants() { return restaurantService.getAllRestaurants(); }

    @GetMapping("restaurants/{id}")
    @CrossOrigin()
    public Restaurant getOneRestaurant(@PathVariable Long id){ return restaurantService.getRestaurantById(id); }

    @PostMapping("new-restaurant/{id}")
    @CrossOrigin()
    public void createRestaurant(@PathVariable Long id, @RequestBody Restaurant restaurant){

        System.out.println("FROM RESTARUATN = " + id);

        System.out.println("restaurant = " + restaurant);
        Restaurant newRestaurant = new Restaurant(restaurant.getLat(), restaurant.getLng(), restaurant.getName(), restaurant.getDescription(), restaurant.getImage(), restaurant.getPrice());
        System.out.println("NEW RESTAURANT = " + newRestaurant);
        User owner = userService.getById(id).get();
        newRestaurant.setOwner(owner);
        System.out.println("CREATING RESTAURANT  " + newRestaurant);
        restaurantService.save(newRestaurant);

        owner.setCreatedRestaurantsList(newRestaurant);
        userService.save(owner);
    }

    @PostMapping("restaurants/{id}")
    @CrossOrigin()
    public void addImageToRestaurant(@PathVariable Long id, @RequestBody String image){
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        restaurant.setImagesList(image);
        restaurantService.save(restaurant);
    }
    @PutMapping("user/{id}")
    @CrossOrigin()
    public User updateProfileImage(@PathVariable Long id, @RequestBody String image){
        User user = userService.getById(id).get();
        user.setImage(image);
        userService.save(user);
        return user;
    }



}
