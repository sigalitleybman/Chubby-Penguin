package com.example.worldwideski2;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Level class implements Parcelable inorder to send a compound object from LevelActivity.
 * It has all the attributes that suppose to be in Level object.
 */
public class Level implements Parcelable {
    private int obstacleAmount;
    private int foodAmount;
    private int obstaclePicID;
    private int foodPicID;
    private int scorePerFood;
    private int neededScore;
    private int widthImageDivider;
    private int heightImageDivider;
    private String countryName;


    public Level(int obstacleAmount, int foodAmount, int obstaclePicID,
                 int foodPicID, int scorePerFood, int neededScore,
                 int widthImageDivider, int heightImageDivider,String countryName) {
        this.obstacleAmount = obstacleAmount;
        this.foodAmount = foodAmount;
        this.obstaclePicID = obstaclePicID;
        this.foodPicID = foodPicID;
        this.scorePerFood = scorePerFood;
        this.neededScore = neededScore;
        this.widthImageDivider = widthImageDivider;
        this.heightImageDivider = heightImageDivider;
        this.countryName = countryName;


    }

    //this constructor was generated due to the CREATOR
    protected Level(Parcel in) {
        obstacleAmount = in.readInt();
        foodAmount = in.readInt();
        obstaclePicID = in.readInt();
        foodPicID = in.readInt();
        scorePerFood = in.readInt();
        neededScore = in.readInt();
        widthImageDivider = in.readInt();
        heightImageDivider = in.readInt();
        countryName =in.readString();
    }

    public static final Creator<Level> CREATOR = new Creator<Level>() {
        @Override
        public Level createFromParcel(Parcel in) {
            return new Level(in);
        }

        @Override
        public Level[] newArray(int size) {
            return new Level[size];
        }
    };

    public int getObstacleAmount() {
        return obstacleAmount;
    }


    public int getFoodAmount() {
        return foodAmount;
    }


    public int getWidthImageDivider() {
        return widthImageDivider;
    }

    public int getHeightImageDivider() {
        return heightImageDivider;
    }

    public int getFoodPicID() {
        return foodPicID;
    }

    public int getScorePerFood() {
        return scorePerFood;
    }

    public int getNeededScore() {
        return neededScore;
    }

    public String getCountryName() {
        return countryName;
    }


    @Override
    public String toString() {
        return "Level{" +
                "obstacleAmount=" + obstacleAmount +
                ", foodAmount=" + foodAmount +
                ", obstaclePicID=" + obstaclePicID +
                ", foodPicID=" + foodPicID +
                ", scorePerFood=" + scorePerFood +
                ", neededScore=" + neededScore +
                ", widthImageDivider=" + widthImageDivider +
                ", heightImageDivider=" + heightImageDivider +
                ", countryName='" + countryName + '\'' +
                '}';
    }


    /**
     * Overridable method from Parcelable.
     * @return 0.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(obstacleAmount);
        dest.writeInt(foodAmount);
        dest.writeInt(obstaclePicID);
        dest.writeInt(foodPicID);
        dest.writeInt(scorePerFood);
        dest.writeInt(neededScore);
        dest.writeInt(widthImageDivider);
        dest.writeInt(heightImageDivider);
        dest.writeString(countryName);
    }
}
