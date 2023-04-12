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

    //food preferences
    private boolean american;
    private boolean bbq;
    private boolean chinese;
    private boolean french;
    private boolean hamburger;
    private boolean indian;
    private boolean italian;
    private boolean japanese;
    private boolean mexican;
    private boolean pizza;
    private boolean seafood;
    private boolean steak;
    private boolean sushi;
    private boolean thai;
    //activity preferences
    private boolean arcade;
    private boolean axe_throwing;
    private boolean beach;
    private boolean bowling;
    private boolean casino;
    private boolean disk_golf;
    private boolean escape_room;
    private boolean garden;
    private boolean golf;
    private boolean library;
    private boolean hiking;
    private boolean mini_golf;
    private boolean movie_rental;
    private boolean movie_theater;
    private boolean museum;
    private boolean park;
    private boolean rage_room;
    private boolean shopping_mall;
    private boolean spa;
    private boolean theme_park;
    private boolean tourist_attraction;
    private boolean zoo;

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
