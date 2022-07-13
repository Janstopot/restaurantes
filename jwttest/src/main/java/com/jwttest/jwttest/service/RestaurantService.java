package com.jwttest.jwttest.service;

import com.jwttest.jwttest.model.Restaurant;
import com.jwttest.jwttest.security.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    public List<Restaurant> getAllRestaurants() { return restaurantRepository.findAll(); }

    public Restaurant getRestaurantById(Long id){
        if(restaurantRepository.findById(id).isPresent()){
            System.out.println("A VER QUE ESTOY ENVIANDO AL FRONT: " + restaurantRepository.findById(id).get().getOwner());
            return restaurantRepository.findById(id).get();
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    public void save(Restaurant restaurant){ restaurantRepository.save(restaurant); }
}
