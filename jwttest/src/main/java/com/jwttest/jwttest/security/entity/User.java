package com.jwttest.jwttest.security.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.jwttest.jwttest.model.Restaurant;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(unique = true)
    private String email;
    @NotNull
    private String name;
    @NotNull
    private String password;
    @NotNull
    private String image;
    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name= "role_id"))
    private Set<Role> roles = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "restaurant_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "restaurant_id")
    )
    private List<Restaurant> favoritesList = new ArrayList<>();


    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Restaurant> createdRestaurantsList = new ArrayList<>();


    /////////////////////////////////////////////////////////////

    public User() {
    }

    public User(String email, String name, String password, String image) {
        this.roles = roles;
        this.email = email;
        this.name = name;
        this.password = password;
        this.image = image;
        this.favoritesList = getFavoritesList();
        this.createdRestaurantsList = getCreatedRestaurantsList();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Restaurant> getFavoritesList() {
        return favoritesList;
    }

    public void setFavoritesList(Restaurant restaurant) {
        this.favoritesList.add(restaurant);
    }


    public List<Restaurant> getCreatedRestaurantsList() {
        return createdRestaurantsList;
    }

    public void setCreatedRestaurantsList(Restaurant restaurant) {
        this.createdRestaurantsList.add(restaurant);
    }



    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
