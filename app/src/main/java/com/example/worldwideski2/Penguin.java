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
//    final Bitmap penguinDied[] = new Bitmap[3];
    Bitmap penguinCollided;
    Bitmap penguinDied;
    int penguinWalkingCounter = -1;
    int penguinDeadCounter = -1;


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
//        penguinDied[0] = BitmapFactory.decodeResource(res, R.drawable.penguin_collision);
//        penguinDied[1] = BitmapFactory.decodeResource(res, R.drawable.penguin_died_1);
//        penguinDied[2] = BitmapFactory.decodeResource(res, R.drawable.penguin_died_2);
//
//        penguinDied[0] =Bitmap.createScaledBitmap(penguinDied[0], width, height, false);
//        penguinDied[1] =Bitmap.createScaledBitmap(penguinDied[1], width, height, false);
//        penguinDied[2] =Bitmap.createScaledBitmap(penguinDied[2], width, height, false);

        penguinCollided = BitmapFactory.decodeResource(res, R.drawable.penguin_collision);
        penguinCollided = Bitmap.createScaledBitmap(penguinCollided, width, height, false);


        penguinDied = BitmapFactory.decodeResource(res, R.drawable.penguin_died_2);
        penguinDied = Bitmap.createScaledBitmap(penguinDied, width, height, false);

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



    public int getWidth() {
        return width;
    }

    Rect getCollisionShape() {
        return new Rect(x, y, x + width, y + height);
    }

    Bitmap getWalkingPenguin() {
        penguinWalkingCounter++;

        if (penguinWalkingCounter == 4) {
            penguinWalkingCounter = 0;
        }

        return penguinWalking[penguinWalkingCounter];
    }

    Bitmap getPenguinCollidedBitmap() {
        return penguinCollided;
    }

    Bitmap getPenguinDiedBitMap() {
        return penguinDied;
    }

    public void resetCounter() {
        penguinWalkingCounter = -1;
        penguinDeadCounter = -1;
    }
}
