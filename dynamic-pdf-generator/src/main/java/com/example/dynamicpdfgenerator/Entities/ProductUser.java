package com.example.dynamicpdfgenerator.Entities;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@ToString
@Component
public class ProductUser {
    String user ;
    String userGSTIN;
    String address;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserGSTIN() {
        return userGSTIN;
    }

    public void setUserGSTIN(String userGSTIN) {
        this.userGSTIN = userGSTIN;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
