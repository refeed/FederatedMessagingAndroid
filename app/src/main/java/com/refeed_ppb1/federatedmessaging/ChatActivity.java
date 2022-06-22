package com.refeed_ppb1.federatedmessaging;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.refeed_ppb1.federatedmessaging.models.MessageModel;
import com.refeed_ppb1.federatedmessaging.models.ServerModel;
import com.refeed_ppb1.federatedmessaging.services.ApiGetResponse;
import com.refeed_ppb1.federatedmessaging.services.FederatedMessagingService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatActivity extends AppCompatActivity {

    List<MessageModel> messageModels = new ArrayList<>();
    MessageRVAdapter adapter = new MessageRVAdapter(messageModels);
    FederatedMessagingService federatedMessagingService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra("server_name"));

        RecyclerView messageRv = (RecyclerView) findViewById(R.id.message_rv);
        messageRv.setAdapter(adapter);
        messageRv.setLayoutManager(new LinearLayoutManager(this));
        federatedMessagingService = getFederatedMessagingService(
                getIntent().getStringExtra("server_address"));
        getMessages();
    }

    public static FederatedMessagingService getFederatedMessagingService(String serverAddress) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(serverAddress)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        FederatedMessagingService service = retrofit.create(FederatedMessagingService.class);
        return service;
    }

    private void getMessages() {
        federatedMessagingService.getMessages().enqueue(new retrofit2.Callback<ApiGetResponse>() {
            @Override
            public void onResponse(retrofit2.Call<ApiGetResponse> call, retrofit2.Response<ApiGetResponse> response) {
                messageModels.addAll(response.body().messages);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ApiGetResponse> call, Throwable t) {
                Toast.makeText(ChatActivity.this, "Error when fetching messages", Toast.LENGTH_SHORT).show();
            }
        });
    }
}