package com.example.uptoyou.Model;

public class FoodPreferenceModel {
    String foodPreferenceName;
    boolean foodDesired;
    int foodPreferenceRank;

    public FoodPreferenceModel(String foodPreferenceName, boolean foodDesired, int foodPreferenceRank) {
        this.foodPreferenceName = foodPreferenceName;
        this.foodDesired = foodDesired;
        this.foodPreferenceRank = foodPreferenceRank;
    }

    public String getFoodPreferenceName() {
        return foodPreferenceName;
    }

    public boolean getFoodDesired(){
        return foodDesired;
    }

    public int getFoodPreferenceRank() {
        return foodPreferenceRank;
    }
}
