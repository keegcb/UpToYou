package com.example.uptoyou.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ActivityPreference")
public class ActivityPreference {
    @PrimaryKey(autoGenerate = true)
    private int activityId;
    private int preferenceId;

    private String activityName;
    private boolean activityDesired;
    private int activityRank;

    public ActivityPreference(int preferenceId, String activityName, boolean activityDesired, int activityRank){
        this.preferenceId = preferenceId;
        this.activityName = activityName;
        this.activityDesired = activityDesired;
        this.activityRank = activityRank;
    }


    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public boolean isActivityDesired() {
        return activityDesired;
    }

    public void setActivityDesired(boolean activityDesired) {
        this.activityDesired = activityDesired;
    }

    public int getActivityRank() {
        return activityRank;
    }

    public void setActivityRank(int activityRank) {
        this.activityRank = activityRank;
    }

    public int getPreferenceId() {
        return preferenceId;
    }

    public void setPreferenceId(int preferenceId) {
        this.preferenceId = preferenceId;
    }
}
