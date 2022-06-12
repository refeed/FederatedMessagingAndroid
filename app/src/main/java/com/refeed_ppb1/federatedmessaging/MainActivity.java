package com.refeed_ppb1.federatedmessaging;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Server> servers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView serverRv = (RecyclerView) findViewById(R.id.server_rv);

        servers = Server.getServerList();
        ServerRVAdapter adapter = new ServerRVAdapter(servers);
        serverRv.setAdapter(adapter);
        serverRv.setLayoutManager(new LinearLayoutManager(this));
    }

    public void setupIdentity(View view) {
        Intent setupIdentityIntent = new Intent(MainActivity.this,SetupIdentityActivity.class);
        startActivity(setupIdentityIntent);
    }
}
