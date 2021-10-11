package com.example.angkolskitchenapp.model;

public class PopularModel {

    String prod_name;
    String description;
    String prod_rating  ;
    String discount;
    String prod_type;
    String img_url;

    public PopularModel() {

    }

    public PopularModel(String prod_name, String description, String prod_rating, String discount, String type, String img_url) {
        this.prod_name = prod_name;
        this.description = description;
        this.prod_rating = prod_rating;
        this.discount = discount;
        this.prod_type = prod_type;
        this.img_url = img_url;
    }

    public String getName() {
        return prod_name;
    }

    public void setName(String name) {
        this.prod_name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return prod_rating;
    }

    public void setRating(String rating) {
        this.prod_rating = rating;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getType() {
        return prod_type;
    }

    public void setType(String type) {
        this.prod_type = type;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
