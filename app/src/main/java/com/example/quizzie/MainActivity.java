package com.example.quizzie;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Window;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(R.style.Theme_Quizzie);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_main);

    }
}