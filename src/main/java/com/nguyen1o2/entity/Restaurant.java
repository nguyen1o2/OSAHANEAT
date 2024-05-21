package com.nguyen1o2.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "subtitle")
    private String subTitle;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "is_free_ship")
    private boolean isFreeShip;

    @Column(name = "address")
    private String address;

    @Column(name = "open_date")
    private Date openDate;

    @OneToMany(mappedBy = "restaurant")
    private Set<RatingRestaurant> listRatingRestaurant;

    @OneToMany(mappedBy = "restaurant")
    private Set<Orders> listOrders;

    @OneToMany(mappedBy = "restaurant")
    private Set<Promo> listPromo;

    @OneToMany(mappedBy = "restaurant")
    private Set<MenuRestaurant> listMenuRestaurant;

    public Set<MenuRestaurant> getListMenuRestaurant() {
        return listMenuRestaurant;
    }

    public void setListMenuRestaurant(Set<MenuRestaurant> listMenuRestaurant) {
        this.listMenuRestaurant = listMenuRestaurant;
    }

    public Set<Promo> getListPromo() {
        return listPromo;
    }

    public void setListPromo(Set<Promo> listPromo) {
        this.listPromo = listPromo;
    }

    public Set<Orders> getListOrders() {
        return listOrders;
    }

    public void setListOrders(Set<Orders> listOrders) {
        this.listOrders = listOrders;
    }

    public Set<RatingRestaurant> getListRaitingRestaurant() {
        return listRatingRestaurant;
    }

    public void setListRaitingRestaurant(Set<RatingRestaurant> listRatingRestaurant) {
        this.listRatingRestaurant = listRatingRestaurant;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isFreeShip() {
        return isFreeShip;
    }
    public void setFreeShip(boolean freeShip) {
        isFreeShip = freeShip;
    }
    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }
}
