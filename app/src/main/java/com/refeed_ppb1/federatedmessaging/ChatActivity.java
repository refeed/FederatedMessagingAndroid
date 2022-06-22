package com.refeed_ppb1.federatedmessaging;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketFactory;
import com.refeed_ppb1.federatedmessaging.models.MessageModel;
import com.refeed_ppb1.federatedmessaging.services.ApiGetResponse;
import com.refeed_ppb1.federatedmessaging.services.FederatedMessagingService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatActivity extends AppCompatActivity {

    RecyclerView messageRv;

    List<MessageModel> messageModels = new ArrayList<>();
    MessageRVAdapter adapter = new MessageRVAdapter(messageModels);
    FederatedMessagingService federatedMessagingService;
    WebSocket ws;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra("server_name"));
        String server_address = getIntent().getStringExtra("server_address");

        messageRv = (RecyclerView) findViewById(R.id.message_rv);
        messageRv.setAdapter(adapter);
        messageRv.setLayoutManager(new LinearLayoutManager(this));
        federatedMessagingService = getFederatedMessagingService(server_address);
        populateMessages();
        connectToWebsocket(server_address);
    }

    public static FederatedMessagingService getFederatedMessagingService(String serverAddress) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(serverAddress)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        FederatedMessagingService service = retrofit.create(FederatedMessagingService.class);
        return service;
    }

    private void populateMessages() {
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

    private void connectToWebsocket(String serverAddress) {
        try {
            ws = new WebSocketFactory().createSocket(
                    convertUrlToWebsocketURL(serverAddress));
        } catch (Exception e) {
            Log.e("ws.createSocket", e.toString());
        }
        ws.addListener(new WebSocketAdapter() {
            @Override
            public void onTextMessage(WebSocket websocket, String message) throws Exception {
                Gson gson = new Gson();
                MessageModel messageModel = gson.fromJson(message, MessageModel.class);
                messageModels.add(messageModel);
                final int newPosition = messageModels.size() - 1;
                adapter.notifyItemInserted(newPosition);
                messageRv.scrollToPosition(newPosition);
            }
        });
        try {
            ws.connectAsynchronously();
        }
        catch (Exception e) {
            Log.e("ws.connect", e.toString());
        }
    }

    public static String convertUrlToWebsocketURL(String url) {
        // FIXME: Still buggy consider using a library
        String newUrl = url.replaceFirst("^https?:\\/\\/", "ws://");
        newUrl += "/ws";
        return newUrl;
    }
}