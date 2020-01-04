package com.dxyy.myapplication;

import java.util.List;

public class User {

    public String userName;
    public List<Address> addresses;

    public User(String userName, List<Address> addresses) {
        this.userName = userName;
        this.addresses = addresses;
    }

    public static class Address{
        public Address(String street, String city) {
            this.street = street;
            this.city = city;
        }

        public String street;
        public String city;
    }
}
