package com.example.quizzie;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        if (isOnline()) {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();
        } else {
            new AlertDialog.Builder(SplashActivity.this)
                    .setTitle("No internet...")
                    .setMessage("Connect to network and try again!")
                    .setPositiveButton("Ok", (dialog, id) -> finish())
                    .setCancelable(false)
                    .show();
        }
    }

    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}