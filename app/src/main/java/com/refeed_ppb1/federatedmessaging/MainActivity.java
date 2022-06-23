package com.refeed_ppb1.federatedmessaging;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.refeed_ppb1.federatedmessaging.models.DBHelper;
import com.refeed_ppb1.federatedmessaging.models.ServerModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<ServerModel> serverModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(R.string.chatserver);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        RecyclerView serverRv = (RecyclerView) findViewById(R.id.server_rv);

        DBHelper dbHelper = new DBHelper(MainActivity.this);
        serverModels = dbHelper.getAllServers();
        ServerRVAdapter adapter = new ServerRVAdapter(serverModels);
        serverRv.setAdapter(adapter);
        serverRv.setLayoutManager(new LinearLayoutManager(this));
    }


    public void setupIdentity(View view) {
        Intent setupIdentityIntent = new Intent(MainActivity.this,SetupIdentityActivity.class);
        startActivity(setupIdentityIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_server_item:
                Intent addServerIntent = new Intent(MainActivity.this, AddServerActivity.class);
                startActivity(addServerIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
