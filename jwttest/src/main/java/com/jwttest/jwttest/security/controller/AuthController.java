package com.jwttest.jwttest.security.controller;


import com.jwttest.jwttest.model.Restaurant;
import com.jwttest.jwttest.security.dto.JwtDto;
import com.jwttest.jwttest.security.dto.LoginUser;
import com.jwttest.jwttest.security.dto.NewUser;
import com.jwttest.jwttest.security.entity.Role;
import com.jwttest.jwttest.security.entity.User;
import com.jwttest.jwttest.security.enums.RoleName;
import com.jwttest.jwttest.security.jwt.JwtProvider;
import com.jwttest.jwttest.security.jwt.JwtTokenFilter;
import com.jwttest.jwttest.security.repository.UserRepository;
import com.jwttest.jwttest.security.service.RoleService;
import com.jwttest.jwttest.security.service.UserService;
import com.jwttest.jwttest.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    Long userId;
    Long restaurantID;


    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;
    @Autowired
    RestaurantService restaurantService;

    @Autowired
    RoleService roleService;

    @Autowired
    JwtProvider jwtProvider;


    @PostMapping("new-user")
    public ResponseEntity<?> createUser (@Valid @RequestBody NewUser newUser, BindingResult bindingResult){
        //List<Restaurant> EmptyList = Collections.<Restaurant>emptyList();
        if(bindingResult.hasErrors())
            return new ResponseEntity("Incorrect input type", HttpStatus.BAD_REQUEST);
        if(userService.existsByEmail(newUser.getEmail()))
            return new ResponseEntity("User already exists", HttpStatus.BAD_REQUEST);
        User user = new User(newUser.getEmail(), newUser.getName(), passwordEncoder.encode(newUser.getPassword()), newUser.getImage());
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getByRoleName(RoleName.ROLE_USER).get());
        if(newUser.getRoles().contains("admin"))
            roles.add(roleService.getByRoleName(RoleName.ROLE_ADMIN).get());
        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity("User created", HttpStatus.OK);
        //LoginUser loginUser = new LoginUser(newUser.getEmail(), newUser.getPassword());
        //return login(loginUser, bindingResult);


    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login (@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult){
        if (bindingResult.hasErrors()) return new ResponseEntity("Incorrect credentials", HttpStatus.BAD_REQUEST);

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getEmail(), loginUser.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        System.out.println("AQUI SE GENERA EL TOKEN    " + jwt);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByName(userDetails.getUsername()).get();
        JwtDto jwtDto = new JwtDto(jwt, user.getId(), user.getEmail(), userDetails.getAuthorities(), user.getName(),  user.getImage());
        System.out.println("JWDTOOOOOOO    " + jwtDto.getToken());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public Optional<User> getUser(@Valid @RequestHeader String Authorization, @PathVariable Long id) {
        System.out.println("token = " + Authorization + ", id = " + id);
        return userService.getById(id);

    }

}
