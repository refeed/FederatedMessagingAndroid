package com.refeed_ppb1.federatedmessaging;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SetupIdentityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_identity);
    }

    public void setIdentity(View view) {
        Intent setIdentity = new Intent(SetupIdentityActivity.this,MainActivity.class);
        startActivity(setIdentity);
    }
}