package com.example.worldwideski2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

/**
 * LevelActivity is responsible for creating the levels and calling the GameActivity Intent.
 */
public class LevelActivity extends MusicalBase {
    private ImageButton imageButtonFranceLock;
    private ImageButton imageButtonFranceUnlock;
    private ImageButton imageButtonIsraelUnlock;
    private ImageButton imageButtonSwitzerlandLock;
    private ImageButton imageButtonSwitzerlandUnlock;
    private int foodPicID;
    private int obstaclePicID;
    private int neededScore;
    private int widthImageDivider;
    private int heightImageDivider;
    private int obstacleAmount;
    private int foodAmount;
    private final int scorePerFood = 50;
    private String countryName;
    private Animation rotateAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        imageButtonFranceLock = findViewById(R.id.france_with_lock);
        imageButtonFranceUnlock = findViewById(R.id.france_without_lock);

        imageButtonIsraelUnlock = findViewById(R.id.israel_map);
        imageButtonSwitzerlandLock = findViewById(R.id.switzerland_with_lock);
        imageButtonSwitzerlandUnlock = findViewById(R.id.switzerland_without_lock);
        rotateAnim = AnimationUtils.loadAnimation(this,R.anim.rotate);


        imageButtonFranceUnlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foodPicID = R.drawable.croissant;
                obstaclePicID = R.drawable.shark;
                neededScore = 2000;
                widthImageDivider = 7;
                heightImageDivider = 7;
                obstacleAmount = 2;
                foodAmount = 3;
                countryName ="FRANCE";

                Level frenchLevel = new Level(obstacleAmount, foodAmount,
                        obstaclePicID, foodPicID, scorePerFood, neededScore,
                        widthImageDivider, heightImageDivider,countryName);

                Intent intent = new Intent(LevelActivity.this, GameActivity.class);

                intent.putExtra("level", frenchLevel);

                startActivity(intent);
            }
        });

        //Israel
        imageButtonIsraelUnlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foodPicID = R.drawable.falafel;
                obstaclePicID = R.drawable.shark;
                neededScore = 500;
                widthImageDivider = 10;
                heightImageDivider = 10;
                obstacleAmount = 1;
                foodAmount = 3;
                countryName ="ISRAEL";

                Level israeliLevel = new Level(obstacleAmount, foodAmount,
                        obstaclePicID, foodPicID, scorePerFood, neededScore,
                        widthImageDivider, heightImageDivider,countryName);

                Intent intent = new Intent(LevelActivity.this, GameActivity.class);

                intent.putExtra("level", israeliLevel);

                startActivity(intent);
            }
        });


        imageButtonSwitzerlandUnlock.setOnClickListener(v -> {
            foodPicID = R.drawable.cheese;
            obstaclePicID = R.drawable.shark;
            neededScore = 1000;
            widthImageDivider = 5;
            heightImageDivider = 5;
            obstacleAmount = 2;
            foodAmount = 3;
            countryName ="SWITZERLAND";
            Level switzerlandLevel = new Level(obstacleAmount, foodAmount,
                    obstaclePicID, foodPicID, scorePerFood, neededScore,
                    widthImageDivider, heightImageDivider,countryName);
            Intent intent = new Intent(LevelActivity.this, GameActivity.class);
            intent.putExtra("level", switzerlandLevel);
            startActivity(intent);
        });

    }

    /**
     * Reading the data returned from the sharedPreferences from GameView and opening relevant levels
     */
    @Override
    protected void onPostResume() {
        super.onPostResume();
        SharedPreferences sharedPreferences=getSharedPreferences("countries",MODE_PRIVATE);

        //checking if the user has passed the SWITZERLAND level and opening the FRANCE level
        if(sharedPreferences.getBoolean("FRANCE",false)
                && (sharedPreferences.getBoolean("SWITZERLAND",false))){
            imageButtonFranceLock.setVisibility(View.INVISIBLE);
            imageButtonFranceUnlock.setVisibility(View.VISIBLE);
            imageButtonFranceUnlock.startAnimation(rotateAnim);
            imageButtonSwitzerlandLock.setVisibility(View.INVISIBLE);
            imageButtonSwitzerlandUnlock.setVisibility(View.VISIBLE);

        }
        //checking if the user has passed the ISRAEL level and opening the SWITZERLAND level
        else if(sharedPreferences.getBoolean("SWITZERLAND",false)){

            imageButtonSwitzerlandLock.setVisibility(View.INVISIBLE);
            imageButtonSwitzerlandUnlock.setVisibility(View.VISIBLE);
            imageButtonSwitzerlandUnlock.startAnimation(rotateAnim);
        }
    }
}
