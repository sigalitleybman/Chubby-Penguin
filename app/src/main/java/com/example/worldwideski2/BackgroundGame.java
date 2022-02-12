package com.example.worldwideski2;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * This class is responsible for generating the background
 */
public class BackgroundGame {
    int x = 0;
    int y = 0;
    Bitmap background;

    BackgroundGame (int screenX, int screenY , Resources res) {
        background = BitmapFactory.decodeResource(res, R.drawable.snow_map);
        background = Bitmap.createScaledBitmap(background, screenX, screenY, false);
    }
}