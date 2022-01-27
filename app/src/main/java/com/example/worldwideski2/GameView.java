package com.example.worldwideski2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceView;

import java.util.Random;

public class GameView extends SurfaceView implements Runnable {
    public static final int BACKGROUND_MOVEMENT = 10;
    private Context context;
    private Thread thread;
    private boolean isPlaying = false;
    private boolean isGameOver = false;
    private int screenX = 0;
    private int  screenY = 0;
    public static float screenRatioX;
    public static float screenRatioY;
    private Penguin penguin;
    private Paint paint;
    private BackgroundGame backgroundGame1;
    private BackgroundGame backgroundGame2;
    private Shark[] sharks;
    private Random random;

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

        paint = new Paint();
//        paint.setTextSize(100);
//        paint.setColor(Color.WHITE);

        sharks=new Shark[2];
        for(int i=0;i<2;i++){
            Shark shark=new Shark(getResources());
            sharks[i]=shark;
        }

        random=new Random();


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



//        backgroundGame1.setX((int)(backgroundGame1.getX() - BACKGROUND_MOVEMENT * screenRatioX));
//        backgroundGame2.setX((int)(backgroundGame2.getX() - BACKGROUND_MOVEMENT * screenRatioX));

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

        if (penguin.y < 0) {
            penguin.y = 0;
        }

        if (penguin.y >= screenY - penguin.height) {
            penguin.y = screenY - penguin.height;
        }

        for (Shark shark:sharks){
            shark.x-=shark.speed;
            if(shark.x+shark.width<0){
                int bound = (int) (30*screenRatioX);
                shark.speed=random.nextInt(bound);
                if(shark.speed<10*screenRatioX){
                    shark.speed= (int) (10*screenRatioX);
                }
                shark.x=screenX;
                shark.y=random.nextInt(screenY-shark.height);
            }
            if(Rect.intersects(shark.getCollisionShape(),penguin.getCollisionShape())){
                shark.x=-800;
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
                isPlaying=false;
                canvas.drawBitmap(penguin.getDeadPenguin(),penguin.x,penguin.y,paint);
                getHolder().unlockCanvasAndPost(canvas);
                return;
            }
            for(Shark shark :sharks){
                canvas.drawBitmap(shark.getSharkBitmap(),shark.x,shark.y,paint);
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
