package com.example.worldwideski2;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameView extends SurfaceView implements Runnable {
    public static final int BACKGROUND_MOVEMENT = 10;
    private Context context;
    private Thread thread;
    private boolean isPlaying = false;
    private boolean isGameOver = false;
    private boolean hasFinishedLevel = false;
    private int screenX = 0;
    private int screenY = 0;
    public static float screenRatioX;
    public static float screenRatioY;
    private Penguin penguin;
    private Paint paint;

    private BackgroundGame backgroundGame1;
    private BackgroundGame backgroundGame2;

    private final List<Shark> sharks = new ArrayList<>();
    private final List<Food> listOfFoods = new ArrayList<>();

    private Random random;

    //hearts
    private int lifeCounter = 3;
    private Heart hearts;

    //level
    private Level level;
    private int foodScore;
    private int neededScore;
    private int currentScore;

    //SharedPreferences
    private SharedPreferences sharedPreferences;



    public GameView(Context context, int screenX, int screenY, Level level) {
        super(context);

        //the context is the gameActivity
        this.context = context;

        // screen's size
        this.screenX = screenX;
        this.screenY = screenY;

        screenRatioX = 1920f / screenX;
        screenRatioY = 1080f / screenY;


        //        currentScore + "/" + needScore
        //        100 /1000
        backgroundGame1 = new BackgroundGame(screenX, screenY, getResources());
        backgroundGame2 = new BackgroundGame(screenX, screenY, getResources());

        penguin = new Penguin(screenY, getResources());
        this.level = level;

        backgroundGame2.x = screenX;

        hearts = new Heart(getResources());
        neededScore = level.getNeededScore();

        paint = new Paint();
        paint.setTextSize(100);
        paint.setColor(Color.WHITE);


        initLevel();

        initListOfFoods();

        initListOfSharks();

        random = new Random();
        //points = new TextView();

        //creating sharedPreferences table for saving passed levels
        sharedPreferences= context.getSharedPreferences("countries",Context.MODE_PRIVATE);
    }

    private void initListOfSharks() {
        for (int i = 0; i < level.getObstacleAmount(); i++) {
            sharks.add(new Shark(getResources()));
        }
    }

    private void initListOfFoods() {
        for (int i = 0; i < level.getFoodAmount(); i++) {
            listOfFoods.add(new Food(getResources(), level.getFoodPicID(), level.getWidthImageDivider(), level.getHeightImageDivider()));
        }
    }

    private void initLevel() {
        foodScore = level.getScorePerFood();
        neededScore = level.getNeededScore();
        currentScore = 0;
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

        for (Shark shark : sharks) {
            shark.x -= shark.speed;
            if (shark.x + shark.width < 0) {
                // int bound = (int) (30 * screenRatioX);
                //                shark.speed=random.nextInt(bound);
                //                if(shark.speed<10*screenRatioX){
                //                    shark.speed= (int) (20*screenRatioX);
                //                }
                shark.speed = (int) (15 * screenRatioX);

                //locating the shark on the x axis.
                shark.x = screenX;

                // locating the shark on the y Axis randomly.
                try {
                    // Made upper and lower bounds for making a range to random, in order to fit
                    //the shark picture in the screen.
                    int upperBound = screenY - shark.height;
                    int lowerBound = shark.height / 2;

                    shark.y = random.nextInt(upperBound - lowerBound) + lowerBound;
                    Thread.sleep(10);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (Rect.intersects(shark.getCollisionShape(), penguin.getCollisionShape())) {
                shark.x = -800;
                // hasCollided = true;
                lifeCounter--;

                if (lifeCounter == 0) {
                    //Toast.makeText(getContext(), "GameOver", Toast.LENGTH_SHORT).show();
                    isGameOver = true;
                }
            }
        }

        random = new Random();
        //traversing through food pictures
        for (Food food : listOfFoods) {
            food.x -= food.speed;
            if (food.x + food.width < 0) {
                // int bound = (int) (30 * screenRatioX);
                //                shark.speed=random.nextInt(bound);
                //                if(shark.speed<10*screenRatioX){
                //                    shark.speed= (int) (20*screenRatioX);
                //                }
                food.speed = (int) (15 * screenRatioX);

                //locating the shark on the x axis.
                food.x = screenX;

                // locating the shark on the y Axis randomly.
                try {
                    // Made upper and lower bounds for making a range to random, in order to fit
                    //the food picture in the screen.
                    int upperBound = screenY - food.height;
                    int lowerBound = food.height / 2;

                    food.y = random.nextInt(upperBound - lowerBound) + lowerBound;
                    Thread.sleep(10);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (Rect.intersects(food.getCollisionShape(), penguin.getCollisionShape())) {
                food.x = -800;
                currentScore += foodScore;
                // hasCollided = true;

                if (currentScore == 50) {
                    //adding data to the SharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    //user passed the israel level
                    if(level.getCountryName().equals("ISRAEL")){
                        editor.putBoolean("SWITZERLAND",true);
                        editor.commit();
                    }
                    //user passed the SWITZERLAND level
                    else if(level.getCountryName().equals("SWITZERLAND")){
                        editor.putBoolean("FRANCE",true);
                        editor.commit();
                    }

                    hasFinishedLevel = true;
                    //isGameOver = true;
                }

            }
        }
    }

    private void draw() {
        if (getHolder().getSurface().isValid()) {

            //lock the canvas to draw the backgrounds again
            Canvas canvas = getHolder().lockCanvas();

            //background drawing
            canvas.drawBitmap(backgroundGame1.background, backgroundGame1.x, backgroundGame1.y, paint);
            canvas.drawBitmap(backgroundGame2.background, backgroundGame2.x, backgroundGame2.y, paint);


            // drawing the foodies.
            for (Food food : listOfFoods) {
                canvas.drawBitmap(food.getFoodBitmap(), food.x, food.y, paint);
            }

            canvas.drawText(currentScore + "/" + neededScore, screenX / 2.5f, 164, paint);


            if (hasFinishedLevel) {

                isPlaying = false;
                canvas.drawBitmap(penguin.getFinishedLevelHappyPenguin(), penguin.x, penguin.y, paint);
                getHolder().unlockCanvasAndPost(canvas);

                ((Activity) getContext()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //display the finished level pop-up activity.
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
                        View viewInflater = inflater.inflate(R.layout.pop_up_finished_level, null);
                        builder.setView(viewInflater);
                        AlertDialog finishDialog = builder.create();
                        finishDialog.setCancelable(false);
                        finishDialog.show();

                        // creating the arrow animation.
                        ImageButton arrow = viewInflater.findViewById(R.id.image_button_arrow);

                        ObjectAnimator animator = ObjectAnimator.
                                ofFloat(arrow, "scaleX", ((float) (1.15))).setDuration(250);
                        ObjectAnimator animator2 = ObjectAnimator.
                                ofFloat(arrow, "scaleY", ((float) (1.15))).setDuration(250);

                        animator.setRepeatMode(ValueAnimator.REVERSE);
                        animator.setRepeatCount(ValueAnimator.INFINITE);
                        animator2.setRepeatMode(ValueAnimator.REVERSE);
                        animator2.setRepeatCount(ValueAnimator.INFINITE);

                        AnimatorSet set1 = new AnimatorSet();

                        set1.playTogether(animator, animator2);
                        set1.start();
                        //creating the penguin animation
                        ImageView winningPenguin = viewInflater.findViewById(R.id.winning_penguin);
                        Animation swipe_up = AnimationUtils.loadAnimation(getContext(), R.anim.swipe_up);
                        swipe_up.setRepeatMode(Animation.INFINITE);
                        winningPenguin.startAnimation(swipe_up);



                        // Adding onClickLisener to arrow, takes us back to the level activity
                        arrow.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent((getContext()), LevelActivity.class);
                                (getContext()).startActivity(intent);
                                ((Activity) getContext()).finish();
                            }
                        });

                    }
                });

                //TODO: Solve this problem.
                //TODO: move all that code to draw and add hasFinishedTheLevel boolean.
                return;
            }

         //    checking if the game was over.
            if (isGameOver) {
                isPlaying = false;
                canvas.drawBitmap(penguin.getPenguinDiedBitMap(), penguin.x, penguin.y, paint);
                getHolder().unlockCanvasAndPost(canvas);
                penguin.resetCounter();

                //Takes us back to the level activity
                Intent intent = new Intent((getContext()), LevelActivity.class);
                (getContext()).startActivity(intent);
                ((Activity) getContext()).finish();
                return;
            }


            //drawing the current amount of red and white lives.
            for (int i = 0; i < 3; i++) {
                int x = (int) (1700 + hearts.heartImages[0].getWidth() * 1.1 * i);
                int y = 80;

                if (i < lifeCounter) {
                    canvas.drawBitmap(hearts.heartImages[0], x, y, null);
                }
                else {
                    canvas.drawBitmap(hearts.heartImages[1], x, y, null);
                }
            }

            canvas.drawBitmap(penguin.getWalkingPenguin(), penguin.x, penguin.y, paint);


            // drawing the obstacles.
            for (Shark shark : sharks) {
                canvas.drawBitmap(shark.getSharkBitmap(), shark.x, shark.y, paint);
            }


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
