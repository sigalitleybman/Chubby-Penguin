package com.example.worldwideski2;

import static com.example.worldwideski2.GameLogic.screenRatioX;
import static com.example.worldwideski2.GameLogic.screenRatioY;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Heart {
    int width;
    int height;
    final Bitmap[] heartImages = new Bitmap[2];


    Heart(Resources res){
        heartImages[0] = BitmapFactory.decodeResource(res, R.drawable.red_heart);
        heartImages[1] = BitmapFactory.decodeResource(res, R.drawable.white_heart);


        width = heartImages[0].getWidth();
        height = heartImages[0].getHeight();

        width /= 2;
        height /= 2;

        width = (int) (screenRatioX * width);
        height = (int) (screenRatioY * height);

        heartImages[0] = Bitmap.createScaledBitmap(heartImages[0], width, height, false);
        heartImages[1] = Bitmap.createScaledBitmap(heartImages[1], width, height, false);
    }

    public Bitmap[] getHeartImagesArray() {
        return heartImages;
    }
}
