package com.example.uptoyou.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Preference")
public class Preference {
    @PrimaryKey(autoGenerate = true)
    private int preferenceId;

    private int foodId;
    private int activityId;
    private float distance;


    public Preference(int preferenceId, int foodId, int activityId, float distance) {
        this.preferenceId = preferenceId;
        this.foodId = foodId;
        this.activityId = activityId;
        this.distance = distance;
    }

    public int getPreferenceId() {
        return preferenceId;
    }

    public void setPreferenceId(int preferenceId) {
        this.preferenceId = preferenceId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }
}
