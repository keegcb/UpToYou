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

}
