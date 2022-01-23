package com.example.worldwideski2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MusicalBase extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPause() {
        MusicManager.Instance().pause(false);
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
        MusicManager.Instance().play(false);
    }
}
