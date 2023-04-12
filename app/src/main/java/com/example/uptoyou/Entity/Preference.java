package com.example.uptoyou.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Preference")
public class Preference {
    @PrimaryKey(autoGenerate = false)
    private int preferenceId;
    private float distance;
    private int foodId;
    private int activityId;

    public Preference(float distance, int foodId, int activityId) {
        this.distance = distance;
        this.foodId = foodId;
        this.activityId = activityId;
    }

    public int getPreferenceId() {
        return preferenceId;
    }

    public void setPreferenceId(int preferenceId) {
        this.preferenceId = preferenceId;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
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
}
