package com.refeed_ppb1.federatedmessaging.services;

import com.google.gson.annotations.SerializedName;
import com.refeed_ppb1.federatedmessaging.models.MessageModel;

import java.util.List;

public class ApiGetResponse {
    public String status;
    public List<MessageModel> messages;
}
