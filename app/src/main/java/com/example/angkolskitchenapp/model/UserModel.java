package com.example.angkolskitchenapp.model;

public class UserModel {

    String name;
    String email;
    String password;
    String profileImg;

    int calories;
    int carbs;
    int fat;
    int protein;

    public UserModel() {
    }

    public UserModel(String name, String email, String password, String profileImg, int calories, int carbs, int fat, int protein) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.profileImg = profileImg;

        this.calories = calories;
        this.carbs = carbs;
        this.fat = fat;
        this.protein = protein;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }
}
