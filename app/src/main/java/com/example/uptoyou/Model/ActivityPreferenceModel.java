package com.example.uptoyou.Model;

public class ActivityPreferenceModel {
    String activityPreferenceName;
    boolean activityDesired;
    int activityPreferenceRank;

    public ActivityPreferenceModel(String activityPreferenceName, boolean activityDesired, int activityPreferenceRank){
        this.activityPreferenceName = activityPreferenceName;
        this.activityDesired = activityDesired;
        this.activityPreferenceRank = activityPreferenceRank;
    }

    public String getActivityPreferenceName() {
        return activityPreferenceName;
    }

    public boolean getActivityDesired(){
        return activityDesired;
    }

    public int getActivityPreferenceRank() {
        return activityPreferenceRank;
    }
}
