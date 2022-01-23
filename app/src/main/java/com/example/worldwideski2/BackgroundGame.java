package com.example.worldwideski2;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BackgroundGame {
    private int x;
    private int y;
    private Bitmap background;

    BackgroundGame (int screenX, int screenY , Resources res){
        //create the background bitmap
        background = BitmapFactory.decodeResource(res, R.drawable.background_map);
        background = Bitmap.createScaledBitmap(background, screenX, screenY, false);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Bitmap getBackground() {
        return background;
    }

    public void setBackground(Bitmap background) {
        this.background = background;
    }
}