package com.example.worldwideski2;

import static com.example.worldwideski2.GameView.screenRatioX;
import static com.example.worldwideski2.GameView.screenRatioY;
import static java.security.AccessController.getContext;


import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Display;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class Penguin {
      boolean isGoingUp = false;
      int x;
      int y;
      int width;
      int height;
      final Bitmap penguinWalking[] = new Bitmap[4];
      int penguinFrame = -1;
//    private Bitmap penguinTwo;
//    private Bitmap penguinThree;
//    private Bitmap penguinFour;

     Penguin(int screenY, Resources res) {
        penguinWalking[0] = BitmapFactory.decodeResource(res, R.drawable.walking_1);
        penguinWalking[1] = BitmapFactory.decodeResource(res, R.drawable.walking_2);
        penguinWalking[2] = BitmapFactory.decodeResource(res, R.drawable.walking_3);
        penguinWalking[3] = BitmapFactory.decodeResource(res, R.drawable.walking_4);

        width = penguinWalking[0].getWidth();
        height = penguinWalking[0].getHeight();

        width /= 2;
        height /= 2;

        width = (int) (screenRatioX * width);
        height = (int) (screenRatioY * height);

        penguinWalking[0] = Bitmap.createScaledBitmap(penguinWalking[0], width, height, false);
        penguinWalking[1] = Bitmap.createScaledBitmap(penguinWalking[1], width, height, false);
        penguinWalking[2] = Bitmap.createScaledBitmap(penguinWalking[2], width, height, false);
        penguinWalking[3] = Bitmap.createScaledBitmap(penguinWalking[3], width, height, false);

      //  setScaledBitmap();

        y = screenY / 2;
        x = (int) (64 * screenRatioX);
    }

    /**
     * here we scale the walkingPenguin bitmaps to the desired size
     */
    private void setScaledBitmap() {
        for(Bitmap bitmap : penguinWalking){
            bitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);
        }
    }

    public boolean isGoingUp() {
        return isGoingUp;
    }

    public void setGoingUp(boolean goingUp) {
        isGoingUp = goingUp;
    }

    public Bitmap getWalkingPenguin() {
        penguinFrame++;

        if (penguinFrame == 4) {
            penguinFrame = 0;
        }

        return penguinWalking[penguinFrame];
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
