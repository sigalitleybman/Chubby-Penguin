package com.example.worldwideski2;

import static com.example.worldwideski2.GameLogic.screenRatioX;
import static com.example.worldwideski2.GameLogic.screenRatioY;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

/**
 * The enemy of out character.
 */
public class Shark {
    public int speed = 20;
    int x,y,width,height;
    Bitmap sharkBitmap;

    Shark(Resources res) {
        sharkBitmap = BitmapFactory.decodeResource(res,R.drawable.shark);

        width = sharkBitmap.getWidth();
        height = sharkBitmap.getHeight();

        width/=5;
        height/=5;

        width = (int) (screenRatioX * width);
        height = (int) (screenRatioY * height);

        sharkBitmap = Bitmap.createScaledBitmap(sharkBitmap,width,height,false);

        y = -height;

    }

    Bitmap getSharkBitmap(){
        return sharkBitmap;
    }

    Rect getCollisionShape(){
        return new Rect(x, y, x + width,y + height);
    }
}