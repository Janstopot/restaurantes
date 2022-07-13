package com.jwttest.jwttest.model;


import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jwttest.jwttest.security.entity.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double lat;
    private Double lng;
    private String name;
    private String image;
    private String description;
    private Long price;
    private boolean open;
    private Long distance;
    @ElementCollection(targetClass=String.class)
    private List<String> imagesList = new ArrayList<>();
    @ManyToMany(mappedBy = "favoritesList", fetch = FetchType.EAGER)
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    private List<User> userList = new ArrayList<>();


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_restaurant")
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    private User owner;


    public Restaurant(Double lat, Double lng, String name, String image, String description, Long price, boolean open, Long distance) {
        this.lat = lat;
        this.lng = lng;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.open = open;
        this.distance = distance;
        this.userList = getUserList();
        this.owner = getOwner();
        this.imagesList = getImagesList();
    }

    public Restaurant(Double lat, Double lng, String name, String description, String image,  Long price) {
        this.lat = lat;
        this.lng = lng;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.owner = getOwner();
        this.userList = getUserList();
        this.imagesList = getImagesList();

    }

    public Restaurant() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) { this.lat = lat; }

    public Double getLng() { return lng; }

    public void setLng(Double lng) {
        this.lng = lng;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) { this.open = open; }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(User user) {
        this.userList.add(user);
    }

    public User getOwner(){
        return this.owner;
    }
    public void setOwner(User user){
        this.owner = user;
    }

    public List<String> getImagesList() {
        return imagesList;
    }

    public void setImagesList(String... images) {
        for(String img: images){
            this.imagesList.add(img);
        }
    }
}