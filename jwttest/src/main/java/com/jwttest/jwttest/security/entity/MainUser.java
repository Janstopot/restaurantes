package com.jwttest.jwttest.security.entity;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MainUser implements UserDetails {
    private Long id;

    private String email;
    private String name;
    private String password;
    private String image;
    private Collection<? extends GrantedAuthority> authorities;

    public MainUser(Long id, String email, String name, String password, String image, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.image = image;
        this.authorities = authorities;
    }

    public static MainUser build(User user){
        List<GrantedAuthority> authorities =
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRoleName().name())).collect(Collectors.toList());
        return new MainUser(user.getId(), user.getEmail(), user.getName(), user.getPassword(), user.getImage(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() { return id; }

    public String getImage() {
        return image;
    }
    public String getName() { return name; }
}
