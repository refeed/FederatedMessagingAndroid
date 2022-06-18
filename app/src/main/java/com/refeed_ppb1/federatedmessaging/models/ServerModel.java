package com.refeed_ppb1.federatedmessaging.models;

import java.util.ArrayList;

public class ServerModel {
    private String name;
    private String address;

    public ServerModel(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Server{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
