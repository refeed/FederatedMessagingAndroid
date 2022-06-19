package com.refeed_ppb1.federatedmessaging;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class SetupIdentityActivity extends AppCompatActivity {

    public static final String PASSWORD_PREF_KEY = "password";

    SharedPreferences sharedPreferences;
    private TextInputEditText passwordET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_identity);
        getSupportActionBar().setTitle("Setup identity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        passwordET = findViewById(R.id.password);

        sharedPreferences = getSharedPreferences(
                BuildConfig.APPLICATION_ID + R.string.IDENTITY_PREF,
                MODE_PRIVATE);

        final String savedPassword = sharedPreferences.getString(PASSWORD_PREF_KEY, null);
        if (savedPassword == null)
            return;
        passwordET.setText(savedPassword);
    }

    public void setIdentity(View view) {
        final String password = passwordET.getText().toString();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PASSWORD_PREF_KEY, password);
        editor.commit();

        Toast
                .makeText(
                        SetupIdentityActivity.this,
                        "Successfully set the identity", Toast.LENGTH_SHORT)
                .show();
        super.onBackPressed();
    }
}