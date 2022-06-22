package com.refeed_ppb1.federatedmessaging.services;

import com.refeed_ppb1.federatedmessaging.models.MessageModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FederatedMessagingService {
    @GET("api/msg")
    Call<ApiGetResponse> getMessages();
}
