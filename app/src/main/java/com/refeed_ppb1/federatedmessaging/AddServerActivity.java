package com.refeed_ppb1.federatedmessaging;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AddServerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_server);
        getSupportActionBar().setTitle("Add server");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}