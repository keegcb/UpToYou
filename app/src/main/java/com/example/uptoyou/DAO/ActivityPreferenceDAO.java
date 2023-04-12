package com.example.uptoyou.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import com.example.uptoyou.Entity.ActivityPreference;

@Dao
public interface ActivityPreferenceDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertActivity(ActivityPreference activityPreference);

    @Update
    void updateActivity(ActivityPreference activityPreference);

    @Delete
    void deleteActivity(ActivityPreference activityPreference);
}
