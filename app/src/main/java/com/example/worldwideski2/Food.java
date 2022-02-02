package com.example.worldwideski2;

import static com.example.worldwideski2.GameView.screenRatioX;
import static com.example.worldwideski2.GameView.screenRatioY;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Food {
    public int speed = 10;
    int x,y,height,width;
    Bitmap foodBitmap;

    Food(Resources res, int foodPicID, int widthDivider, int heightDivider) {

        foodBitmap = BitmapFactory.decodeResource(res,foodPicID);

        width = foodBitmap.getWidth();
        height = foodBitmap.getHeight();

        //TODO: send here the dividing param's

        width /= widthDivider;
        height /= heightDivider;

        width = (int) (screenRatioX * width);
        height = (int) (screenRatioY * height);

        foodBitmap = Bitmap.createScaledBitmap(foodBitmap, width, height, false);

        y = -height;
    }


    Bitmap getFoodBitmap() {
        return foodBitmap;
    }

    Rect getCollisionShape(){
        return new Rect(x,y,x+width,y+height);
    }
}
