package com.example.uptoyou.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "FoodPreference")
public class FoodPreference {
    @PrimaryKey(autoGenerate = false)
    private int foodId;
    private int preferenceId;

    private String foodName;
    private boolean foodDesired;
    private int foodRank;

    public FoodPreference(int preferenceId, int foodId, String foodName, boolean foodDesired, int foodRank) {
        this.preferenceId = preferenceId;
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodDesired = foodDesired;
        this.foodRank = foodRank;
    }

    public int getFoodId() { return foodId; }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getPreferenceId() {
        return preferenceId;
    }

    public void setPreferenceId(int preferenceId) {
        this.preferenceId = preferenceId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public boolean isFoodDesired() {
        return foodDesired;
    }

    public void setFoodDesired(boolean foodDesired) {
        this.foodDesired = foodDesired;
    }

    public int getFoodRank() {
        return foodRank;
    }

    public void setFoodRank(int foodRank) {
        this.foodRank = foodRank;
    }
}
