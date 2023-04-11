package com.example.uptoyou.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "User")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int userId;

    private String userName;
    private int preferenceId;
    private double homeLat;
    private double homeLng;


    public User(String userName, int preferenceId, double homeLat, double homeLng) {
        this.userName = userName;
        this.preferenceId = preferenceId;
        this.homeLat = homeLat;
        this.homeLng = homeLng;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getPreferenceId() {
        return preferenceId;
    }

    public void setPreferenceId(int preferenceId) {
        this.preferenceId = preferenceId;
    }

    public double getHomeLat() {
        return homeLat;
    }

    public void setHomeLat(double homeLat) {
        this.homeLat = homeLat;
    }

    public double getHomeLng() {
        return homeLng;
    }

    public void setHomeLng(double homeLng) {
        this.homeLng = homeLng;
    }
}
