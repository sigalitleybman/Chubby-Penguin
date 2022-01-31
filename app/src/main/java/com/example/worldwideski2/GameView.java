package com.example.worldwideski2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class GameView extends SurfaceView implements Runnable {
    public static final int BACKGROUND_MOVEMENT = 10;
    private Context context;
    private Thread thread;
    private boolean isPlaying = false;
    private boolean isGameOver = false;
    private boolean hasCollided = false;
    private int screenX = 0;
    private int  screenY = 0;
    public static float screenRatioX;
    public static float screenRatioY;
    private Penguin penguin;
    private Paint paint;
    private BackgroundGame backgroundGame1;
    private BackgroundGame backgroundGame2;
   // private Shark[] sharks;
    private Shark shark;
    private Random random;
    private int lifeCounter = 3;
    private Heart hearts;
    private int score = 0;
    private Falafel falafel;

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

        penguin = new Penguin(screenY, getResources());
        backgroundGame2.x = screenX;

        hearts = new Heart(getResources());
        falafel = new Falafel(getResources());

        paint = new Paint();
        paint.setTextSize(100);
        paint.setColor(Color.WHITE);

//        sharks = new Shark[2];
//        for(int i=0 ; i<2 ; i++){
//            Shark shark=new Shark(getResources());
//            sharks[i]=shark;
//        }

        shark = new Shark(getResources());

        random=new Random();
//        points = new TextView();
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
        backgroundGame1.x -= 15 * screenRatioX;
        backgroundGame2.x -= 15 * screenRatioX;

        if (backgroundGame1.x + backgroundGame1.background.getWidth() < 0) {
            backgroundGame1.x = screenX;
        }

        if (backgroundGame2.x + backgroundGame2.background.getWidth() < 0) {
            backgroundGame2.x = screenX;
        }

        if (penguin.isGoingUp) {
            penguin.y -= 30 * screenRatioY;
        }
        else {
            penguin.y += 30 * screenRatioY;
        }

        if (penguin.y < penguin.height / 2) {
            penguin.y = penguin.height;
        }

        if (penguin.y >= screenY - penguin.height) {
            penguin.y = screenY - penguin.height;
        }

//        for (Shark shark:sharks){
            shark.x -= shark.speed;
            if(shark.x + shark.width < 0){
                int bound = (int) (30*screenRatioX);
//                shark.speed=random.nextInt(bound);
//                if(shark.speed<10*screenRatioX){
//                    shark.speed= (int) (20*screenRatioX);
//                }
                shark.speed=(int)(15*screenRatioX);
                shark.x=screenX;
                try {
                    shark.y=random.nextInt(screenY-shark.height);
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//            }

            if(Rect.intersects(shark.getCollisionShape(),penguin.getCollisionShape())){
                shark.x=-800;
                hasCollided = true;
                lifeCounter--;

                if (lifeCounter == 0) {
                    //Toast.makeText(getContext(), "GameOver", Toast.LENGTH_SHORT).show();
                    isGameOver=true;
                }
            }


        }


        //TODO: check how to make the penguin seen
//        penguin.setY((int) (backgroundGame1.getY() + 80));
//        penguin.setY((int)(screenY - penguin.getHeight()/1.5));
    }

    private void draw() {
        if (getHolder().getSurface().isValid()) {

            //lock the canvas to draw the backgrounds again
            Canvas canvas = getHolder().lockCanvas();

            //background drawing
            canvas.drawBitmap(backgroundGame1.background, backgroundGame1.x,
                    backgroundGame1.y, paint);

            canvas.drawBitmap(backgroundGame2.background, backgroundGame2.x,
                    backgroundGame2.y, paint);


            if(isGameOver){
                isPlaying = false;
                canvas.drawBitmap(penguin.getPenguinDiedBitMap(),penguin.x,penguin.y,paint);
                getHolder().unlockCanvasAndPost(canvas);
                penguin.resetCounter();
                return;
            }

            if (hasCollided) {
//                penguin.getWalkingPenguin().eraseColor(android.graphics.Color.TRANSPARENT);
//                canvas.drawBitmap(penguin.getPenguinCollidedBitmap(),penguin.x,penguin.y,paint);
                hasCollided = false;
            }


            for(Shark shark : sharks){
                canvas.drawBitmap(shark.getSharkBitmap(),shark.x,shark.y,paint);
            }

            for (int i = 0; i < 3; i++) {
                int x = (int) (1800 + hearts.heartImages[0].getWidth() * 1.1 * i);
                int y = 80;

                if (i < lifeCounter) {
                    canvas.drawBitmap(hearts.heartImages[0], x, y, null);
                }
                else {
                    canvas.drawBitmap(hearts.heartImages[1], x, y, null);
                }
            }



            canvas.drawBitmap(penguin.getWalkingPenguin(),
                    penguin.x, penguin.y, paint);

            //after drawing unlock the canvas
            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    private void sleep() {
        try {
            Thread.sleep(25);
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            // When the user puts his finger down the screen
            //the penguin x stay the same
            //the y change according to the finger movement
            case MotionEvent.ACTION_DOWN:
                if (event.getX() < screenX / 2) {
                    penguin.isGoingUp = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                penguin.setGoingUp(false);
                break;
        }

        return true;
    }
}