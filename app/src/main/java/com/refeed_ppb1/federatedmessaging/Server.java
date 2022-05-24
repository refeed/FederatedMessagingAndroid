package com.refeed_ppb1.federatedmessaging;

import java.util.ArrayList;

public class Server {
    private String name;
    private String address;

    public Server(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public static ArrayList<Server> getServerList() {
        ArrayList<Server> servers = new ArrayList<>();
        // FIXME: Still dummy
        servers.add(new Server("Indonesian server", "https://localhost"));
        return servers;
    }

}
