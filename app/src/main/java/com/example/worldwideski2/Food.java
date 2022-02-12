package com.example.worldwideski2;

import static com.example.worldwideski2.GameLogic.screenRatioX;
import static com.example.worldwideski2.GameLogic.screenRatioY;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

/**
 * Food class represents the goods that out character is going to collect
 */
public class Food {
    public int speed = 10;
    int x,y,height,width;
    Bitmap foodBitmap;

    Food(Resources res, int foodPicID, int widthDivider, int heightDivider) {

        foodBitmap = BitmapFactory.decodeResource(res,foodPicID);

        width = foodBitmap.getWidth();
        height = foodBitmap.getHeight();

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
