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
    private Paint paint;
    private BackgroundGame backgroundGame1;
    private BackgroundGame backgroundGame2;

    public GameView(Context context, int screenX, int screenY) {
        super(context);

        this.context = context;
        this.screenX = screenX;
        this.screenY = screenY;
        backgroundGame1 = new BackgroundGame(screenX, screenY, getResources());
        backgroundGame2 = new BackgroundGame(screenX, screenY, getResources());


        backgroundGame2.setX(screenX);

        paint = new Paint();
        paint.setTextSize(100);
        paint.setColor(Color.WHITE);

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

        backgroundGame1.setX(backgroundGame1.getX() - BACKGROUND_MOVEMENT);
        backgroundGame2.setX(backgroundGame2.getX() - -BACKGROUND_MOVEMENT);

        if ((backgroundGame1.getX() + backgroundGame1.getBackground().getWidth()) < 0) {
            backgroundGame1.setX(screenX);
        }
        if ((backgroundGame2.getX() + backgroundGame2.getBackground().getWidth()) < 0) {
            backgroundGame2.setX(screenX);
        }

    }

    private void draw() {

        if (getHolder().getSurface().isValid()) {

            Canvas canvas = getHolder().lockCanvas();

            //background
            canvas.drawBitmap(backgroundGame1.getBackground(), backgroundGame1.getX(), backgroundGame1.getY(), paint);
            canvas.drawBitmap(backgroundGame2.getBackground(), backgroundGame2.getX(), backgroundGame2.getY(), paint);

            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    private void sleep() {
        try {
            Thread.sleep(17);
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