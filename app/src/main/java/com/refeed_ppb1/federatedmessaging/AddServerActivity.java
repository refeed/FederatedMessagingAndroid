package com.refeed_ppb1.federatedmessaging;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.refeed_ppb1.federatedmessaging.models.DBHelper;
import com.refeed_ppb1.federatedmessaging.models.ServerModel;
import com.refeed_ppb1.federatedmessaging.widget.ServerWidget;

public class AddServerActivity extends AppCompatActivity {

    private Button addServerBtn;
    private TextInputEditText serverNameET;
    private TextInputEditText serverAddressET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_server);
        getSupportActionBar().setTitle(R.string.addserver);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        serverNameET = findViewById(R.id.server_name);
        serverAddressET = findViewById(R.id.server_address);
        addServerBtn = findViewById(R.id.add_server_button);

        addServerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String serverName = serverNameET.getText().toString();
                String serverAddress = serverAddressET.getText().toString();

                ServerModel server = new ServerModel(serverName, serverAddress);

                DBHelper dbHelper = new DBHelper(AddServerActivity.this);
                boolean success = dbHelper.addServer(server);
                if (!success) {
                    Toast.makeText(AddServerActivity.this, "Failed to add server", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(AddServerActivity.this, "Successfully added server", Toast.LENGTH_SHORT).show();
                AddServerActivity.super.onBackPressed();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ServerWidget.sendRefreshBroadcast(AddServerActivity.this);
                    }
                });
            }
        });
    }
}