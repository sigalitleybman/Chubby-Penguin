package com.example.worldwideski2;

import static com.example.worldwideski2.GameView.screenRatioX;
import static com.example.worldwideski2.GameView.screenRatioY;

import android.content.res.Resources;
import android.graphics.Bitmap;

public class Falafel {
    int width;
    int height;
    Bitmap falafelBitmap;

    Falafel(Resources res){
        width = falafelBitmap.getWidth();
        height = falafelBitmap.getHeight();

        width /= 2;
        height /= 2;

        width = (int) (screenRatioX * width);
        height = (int) (screenRatioY * height);

        falafelBitmap = Bitmap.createScaledBitmap(falafelBitmap, width, height, false);
    }


}
