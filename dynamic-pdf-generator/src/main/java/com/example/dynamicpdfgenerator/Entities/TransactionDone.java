package com.example.dynamicpdfgenerator.Entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionDone {

    String seller;
    String sellerGSTIN;
    String sellerAddress;
    String buyer;
    String buyerGSTIN;
    String buyerAddress;
    List items = new ArrayList<>();


//    public TransactionDone(ProductUser sellerDetails, ProductUser buyerDetails, List itemsPurchased){
//        this.seller = sellerDetails.user;
//        this.sellerGSTIN = sellerDetails.userGSTIN;
//        this.sellerAddress = sellerDetails.address;
//        this.buyer = buyerDetails.user;
//        this.buyerGSTIN = buyerDetails.userGSTIN;
//        this.buyerAddress = buyerDetails.address;
//        this.items = itemsPurchased;
//    }

    public String getSeller() {
        return seller;
    }

    public String getSellerGSTIN() {
        return sellerGSTIN;
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public String getBuyer() {
        return buyer;
    }

    public String getBuyerGSTIN() {
        return buyerGSTIN;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public void setSellerGSTIN(String sellerGSTIN) {
        this.sellerGSTIN = sellerGSTIN;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public void setBuyerGSTIN(String buyerGSTIN) {
        this.buyerGSTIN = buyerGSTIN;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
