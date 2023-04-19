package com.example.uptoyou.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.uptoyou.Entity.ActivityPreference;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ActivityPreferenceDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertActivity(ActivityPreference activityPreference);

    @Update
    void updateActivity(ActivityPreference activityPreference);

    @Delete
    void deleteActivity(ActivityPreference activityPreference);

    @Query("SELECT * FROM ActivityPreference WHERE preferenceId= :id")
    List<ActivityPreference> getActivityByPreference(int id);

    @Query("SELECT * FROM ActivityPreference WHERE activityDesired= :desired")
    List<ActivityPreference> getActivityDesired(boolean desired);
}
