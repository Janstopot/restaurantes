package com.jwttest.jwttest.security.service;

import com.jwttest.jwttest.security.entity.MainUser;
import com.jwttest.jwttest.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        System.out.println("IN THE DETAILS SERVICEEEE");
        User user = userService.getByEmail(name).get();
        System.out.println("IN THE DETAILS SERVICEEEE     " + user.getId());
        return MainUser.build(user);
    }

    public User getUserByEmail(String email){
        User user = userService.getByEmail(email).get();
        return user;
    }

    public User getUserByName(String name){
        User user = userService.getByName(name).get();
        return user;
    }
}
