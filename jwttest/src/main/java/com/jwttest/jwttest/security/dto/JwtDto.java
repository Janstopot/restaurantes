package com.jwttest.jwttest.security.dto;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtDto {

    private String token;
    private String bearer = "Bearer";
    private Long id;
    private String email;
    private String name;
    private String image;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtDto(String token, Long id, String email, Collection<? extends GrantedAuthority> authorities, String name, String image) {
        this.token = token;
        this.id = id;
        this.email = email;
        this.name = name;
        this.image = image;
        this.authorities = authorities;

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBearer() {
        return bearer;
    }

    public void setBearer(String bearer) {
        this.bearer = bearer;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
