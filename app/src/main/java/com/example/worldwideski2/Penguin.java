package com.example.worldwideski2;

import static com.example.worldwideski2.GameView.screenRatioX;
import static com.example.worldwideski2.GameView.screenRatioY;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;


public class Penguin {
    boolean isGoingUp = false;
    int x;
    int y;
    int width;
    int height;
    final Bitmap penguinWalking[] = new Bitmap[4];
    int penguinFrame = -1;
    Bitmap deadPenguin[] = new Bitmap[3];
    int penguinDeadCounter = -1;
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

        width /= 1.7;
        height /= 1.7;

        width = (int) (screenRatioX * width);
        height = (int) (screenRatioY * height);

        penguinWalking[0] = Bitmap.createScaledBitmap(penguinWalking[0], width, height, false);
        penguinWalking[1] = Bitmap.createScaledBitmap(penguinWalking[1], width, height, false);
        penguinWalking[2] = Bitmap.createScaledBitmap(penguinWalking[2], width, height, false);
        penguinWalking[3] = Bitmap.createScaledBitmap(penguinWalking[3], width, height, false);

        //  setScaledBitmap();
        deadPenguin[0] = BitmapFactory.decodeResource(res, R.drawable.penguin_collision);
        deadPenguin[1] = BitmapFactory.decodeResource(res, R.drawable.penguin_died_1);
        deadPenguin[2] = BitmapFactory.decodeResource(res, R.drawable.penguin_died_2);

        deadPenguin[0] =Bitmap.createScaledBitmap(deadPenguin[0], width, height, false);
        deadPenguin[1] =Bitmap.createScaledBitmap(deadPenguin[1], width, height, false);
        deadPenguin[2] =Bitmap.createScaledBitmap(deadPenguin[2], width, height, false);


        y = screenY / 2;
        x = (int) (64 * screenRatioX);
    }

    /**
     * here we scale the walkingPenguin bitmaps to the desired size
     */
    private void setScaledBitmap() {
        for (Bitmap bitmap : penguinWalking) {
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

    public int getWidth() {
        return width;
    }

    Rect getCollisionShape() {
        return new Rect(x, y, x + width, y + height);
    }

    Bitmap getDeadPenguin() {
        penguinDeadCounter++;

        if (penguinDeadCounter == 3) {
            penguinDeadCounter = 0;
        }

        return deadPenguin[penguinDeadCounter];
    }


}
