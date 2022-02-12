package com.example.worldwideski2;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.WindowManager;

/**
 * The
 */
public class GameActivity extends MusicalBase {

    // game view represents the logic of the app
    private GameLogic gameLogic;
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
        gameLogic = new GameLogic(this,point.x, point.y, level);

        setContentView(gameLogic);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameLogic.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameLogic.resume();
    }



}