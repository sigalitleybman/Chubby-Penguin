package com.example.worldwideski2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.WindowManager;


public class GameActivity extends AppCompatActivity {

    // game view represents the logic of the app
    private GameView gameView;
    private Level level;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //For full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        Intent intent = getIntent();

        level = intent.getParcelableExtra("level");


        Point point = new Point();

        getWindowManager().getDefaultDisplay().getSize(point);

        /**
         * point.x and point.y represents the screen size
         */
        gameView = new GameView(this,point.x, point.y, level);

        setContentView(gameView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }
}