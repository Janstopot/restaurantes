package com.jwttest.jwttest.security.service;

import com.jwttest.jwttest.security.entity.User;
import com.jwttest.jwttest.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;
    public Optional<User> getByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public Optional<User> getById(Long id) { return userRepository.findById(id); }
    public Optional<User> getByName(String name) { return userRepository.findByName(name); }
    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }



    public void save(User user){
        userRepository.save(user);
    }
}
