package com.jwttest.jwttest.util;

import com.jwttest.jwttest.model.Restaurant;
import com.jwttest.jwttest.security.entity.Role;
import com.jwttest.jwttest.security.enums.RoleName;
import com.jwttest.jwttest.security.repository.RestaurantRepository;
import com.jwttest.jwttest.security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static java.lang.Long.valueOf;

@Component
public class CreateRoles implements CommandLineRunner {
    @Autowired
    RoleService roleService;
    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    public void run(String... args) throws Exception {
        Role adminRole = new Role(RoleName.ROLE_ADMIN);
        Role userRole = new Role(RoleName.ROLE_USER);
        roleService.save(userRole);
        roleService.save(adminRole);

        Restaurant restaurant1 = new Restaurant(41.360301747267776, 2.104658833868416, "Casa Paco", "https://i.imgur.com/LhjGnwL.jpeg", "Comida tradicional" , valueOf(10), true, valueOf(0));
        restaurant1.setImagesList("https://mdbcdn.b-cdn.net/img/Photos/Lightbox/Original/img%20(112).webp", "https://mdbcdn.b-cdn.net/img/Photos/Lightbox/Original/img%20(107).webp","https://mdbcdn.b-cdn.net/img/Photos/Lightbox/Original/img%20(108).webp");
        restaurantRepository.save(restaurant1);
        Restaurant restaurant2 = new Restaurant(41.36124391017526, 2.107029906637581, "Buen Gusto", "https://i.imgur.com/8hbOs29.jpeg", "comida de batalla" , valueOf(8), true, valueOf(0));
        restaurantRepository.save(restaurant2);
        Restaurant restaurant3 = new Restaurant(41.362033061771484, 2.101032487280281, "La Tagliatella", "https://i.imgur.com/Ze3nxNt.jpeg", "Comida italiana", valueOf(12), false, valueOf(0));
        restaurantRepository.save(restaurant3);
        Restaurant restaurant4 = new Restaurant(41.36158211917401, 2.1035430349182205, "Taqueria andale wey", "https://i.imgur.com/vTcwGPf.jpeg", "Mexican food", valueOf(12), true, valueOf(0));
        restaurantRepository.save(restaurant4);
        Restaurant restaurant5 = new Restaurant(41.35877171006715, 2.101407996542366, "Dominos", "https://i.imgur.com/WPqqOyf.jpeg", "Fast food chain", valueOf(9), false, valueOf(0));
        restaurantRepository.save(restaurant5);
        Restaurant restaurant6 = new Restaurant(41.35876365714462, 2.1054634965728836, "El Chino", "https://i.imgur.com/5blPJGI.jpeg", "Restaurante asiatico", valueOf(12), false, valueOf(0));
        restaurantRepository.save(restaurant6);

    }




}
