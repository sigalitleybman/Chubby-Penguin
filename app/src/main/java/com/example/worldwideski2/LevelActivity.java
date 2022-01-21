package com.example.worldwideski2;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class LevelActivity extends AppCompatActivity {
    private ImageButton imageButtonFranceLock;
    private ImageButton imageButtonFranceUnlock;
    private ImageButton imageButtonIsraelLock;
    private ImageButton imageButtonIsraelUnlock;
    private ImageButton imageButtonSwitzerlandlLock;
    private ImageButton imageButtonSwitzerlandUnlock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        imageButtonFranceLock = findViewById(R.id.france_with_lock);
        imageButtonFranceUnlock = findViewById(R.id.france_without_lock);
        imageButtonIsraelLock = findViewById(R.id.israel_with_lock);
        imageButtonIsraelUnlock = findViewById(R.id.israel_without_lock);
        imageButtonSwitzerlandlLock = findViewById(R.id.switzerland_with_lock);
        imageButtonSwitzerlandUnlock = findViewById(R.id.switzerland_without_lock);

        //France
        imageButtonFranceLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageButtonFranceLock.setVisibility(View.INVISIBLE);
                imageButtonFranceUnlock.setVisibility(View.VISIBLE);
            }
        });

        imageButtonFranceUnlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageButtonFranceUnlock.setVisibility(View.INVISIBLE);
                imageButtonFranceLock.setVisibility(View.VISIBLE);
            }
        });

        //Israel
        imageButtonIsraelLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageButtonIsraelLock.setVisibility(View.INVISIBLE);
                imageButtonIsraelUnlock.setVisibility(View.VISIBLE);
            }
        });

        imageButtonIsraelUnlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageButtonIsraelUnlock.setVisibility(View.INVISIBLE);
                imageButtonIsraelLock.setVisibility(View.VISIBLE);
            }
        });

        //switzerland
        imageButtonSwitzerlandlLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageButtonSwitzerlandlLock.setVisibility(View.INVISIBLE);
                imageButtonSwitzerlandUnlock.setVisibility(View.VISIBLE);
            }
        });

        imageButtonSwitzerlandUnlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageButtonSwitzerlandUnlock.setVisibility(View.INVISIBLE);
                imageButtonSwitzerlandlLock.setVisibility(View.VISIBLE);
            }
        });

    }



}
