package com.example.akshatapp.activities;

import java.util.ArrayList;

public class User {

    private String name;
    private String email;
    private String phone;
    private String address;
    private String website;
    private ArrayList<String> skills;
    private String gender;

    public User()
    {

    }
    public String getName()
    {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress()
    {
        return address;
    }

    public ArrayList<String> getSkills() {
        return skills;
    }

    public String getWebsite() {
        return website;
    }

    public String getGender() {
        return gender;
    }

    public void setSkills(ArrayList<String> skills) {
        this.skills = skills;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
