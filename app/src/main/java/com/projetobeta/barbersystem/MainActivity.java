package com.projetobeta.barbersystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.projetobeta.barbersystem.general.FullScrean;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new FullScrean(this);
        setContentView(R.layout.activity_main);
    }
}