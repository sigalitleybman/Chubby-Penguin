package com.example.worldwideski2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {
    public static final int BACKGROUND_MOVEMENT = 10;
    private Context context;
    private Thread thread;
    private boolean isPlaying;
    private boolean isGameOver;
    private int screenX;
    private int  screenY;
    public static float screenRatioX;
    public static float screenRatioY;
    private WalkingPenguin walkingPenguin;
    private Paint paint;
    private BackgroundGame backgroundGame1;
    private BackgroundGame backgroundGame2;

    public GameView(Context context, int screenX, int screenY) {
        super(context);

        //the context is the gameActivity
        this.context = context;

        // screen's size
        this.screenX = screenX;
        this.screenY = screenY;

        screenRatioX = 1920f / screenX;
        screenRatioY = 1080f / screenY;

        backgroundGame1 = new BackgroundGame(screenX, screenY, getResources());
        backgroundGame2 = new BackgroundGame(screenX, screenY, getResources());

        walkingPenguin = new WalkingPenguin(screenY, getResources());
        backgroundGame2.setX(screenX);

        paint = new Paint();
//        paint.setTextSize(100);
//        paint.setColor(Color.WHITE);

    }

    @Override
    public void run() {
        while (isPlaying) {
            update();
            draw();
            sleep();
        }
    }

    private void update() {
        //backgroundGame1.x -= 10 * screenRatioX;

        backgroundGame1.setX((int) (backgroundGame1.getX() - BACKGROUND_MOVEMENT * screenRatioX));
        backgroundGame2.setX((int) (backgroundGame2.getX() - BACKGROUND_MOVEMENT * screenRatioX));

        if ((backgroundGame1.getX() + backgroundGame1.getBackground().getWidth()) < 0) {
            backgroundGame1.setX(screenX);
        }
        if ((backgroundGame2.getX() + backgroundGame2.getBackground().getWidth()) < 0) {
            backgroundGame2.setX(screenX);
        }

        //TODO: check how to make the penguin seen
        walkingPenguin.setY((int) (screenRatioY));
    }

    private void draw() {
        if (getHolder().getSurface().isValid()) {

            //lock the canvas to draw the backgrounds again
            Canvas canvas = getHolder().lockCanvas();

            //background drawing
            canvas.drawBitmap(backgroundGame1.getBackground(), backgroundGame1.getX(),
                    backgroundGame1.getY(), paint);

            canvas.drawBitmap(backgroundGame2.getBackground(), backgroundGame2.getX(),
                    backgroundGame2.getY(), paint);

            canvas.drawBitmap(walkingPenguin.getWalkingPenguin(),
                    walkingPenguin.getX(), walkingPenguin.getY(), paint);


            //after drawing unlock the canvas
            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    private void sleep() {
        try {
            Thread.sleep(80);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume() {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    public void pause() {
        try {
            isPlaying = false;
            thread.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        float yDown = 0, yMove;
//        float distanceY;
//        switch (event.getAction()) {
//            // When the user puts his finger down the screen
//            //the fish x stay the same
//            //the y change according to the finger movement
//            case MotionEvent.ACTION_DOWN:
//                yDown = event.getY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                yMove = event.getY();
//                distanceY = yMove - yDown;
//
//                fish.y = (int) distanceY;
//
//                if (fish.y >= screenY - fish.height)
//                    fish.y = screenY - fish.height;
//                break;
//        }
//        return true;
//    }
}
