package com.refeed_ppb1.federatedmessaging.services;

public class ApiPostMessageRequest {
    public String passphrase;
    public String body;

    public ApiPostMessageRequest(String passphrase, String body) {
        this.passphrase = passphrase;
        this.body = body;
    }
}
