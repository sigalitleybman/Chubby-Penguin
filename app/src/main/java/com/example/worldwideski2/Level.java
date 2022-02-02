package com.example.worldwideski2;

import android.os.Parcel;
import android.os.Parcelable;

public class Level implements Parcelable {
    private int obstacleAmount;
    private int foodAmount;
    private int obstaclePicID;
    private int foodPicID;
    private int scorePerFood;
    private int neededScore;
    private int widthImageDivider;
    private int heightImageDivider;


    public Level(int obstacleAmount, int foodAmount, int obstaclePicID,
                 int foodPicID, int scorePerFood, int neededScore,
                 int widthImageDivider, int heightImageDivider) {
        this.obstacleAmount = obstacleAmount;
        this.foodAmount = foodAmount;
        this.obstaclePicID = obstaclePicID;
        this.foodPicID = foodPicID;
        this.scorePerFood = scorePerFood;
        this.neededScore = neededScore;
        this.widthImageDivider = widthImageDivider;
        this.heightImageDivider = heightImageDivider;

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

    public void setObstacleAmount(int obstacleAmount) {
        this.obstacleAmount = obstacleAmount;
    }

    public int getFoodAmount() {
        return foodAmount;
    }

    public void setFoodAmount(int foodAmount) {
        this.foodAmount = foodAmount;
    }

    public int getWidthImageDivider() {
        return widthImageDivider;
    }

    public void setWidthImageDivider(int widthImageDivider) {
        this.widthImageDivider = widthImageDivider;
    }

    public int getHeightImageDivider() {
        return heightImageDivider;
    }

    public void setHeightImageDivider(int heightImageDivider) {
        this.heightImageDivider = heightImageDivider;
    }

    public int getFoodPicID() {
        return foodPicID;
    }

    public void setFoodPicID(int foodPicID) {
        this.foodPicID = foodPicID;
    }

    public int getScorePerFood() {
        return scorePerFood;
    }

    public void setScorePerFood(int scorePerFood) {
        this.scorePerFood = scorePerFood;
    }

    public int getNeededScore() {
        return neededScore;
    }

    public void setNeededScore(int neededScore) {
        this.neededScore = neededScore;
    }

    @Override
    public String toString() {
        return "Level{" + "obstacleAmount=" + obstacleAmount +
                ", foodAmount=" + foodAmount +
                ", obstaclePicID=" + obstaclePicID +
                ", foodPicID=" + foodPicID +
                ", scorePerFood=" + scorePerFood +
                ", neededScore=" + neededScore +
                ", widthImageDivider=" + widthImageDivider +
                ", heightImageDivider=" + heightImageDivider +
                '}';
    }

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
    }
}
