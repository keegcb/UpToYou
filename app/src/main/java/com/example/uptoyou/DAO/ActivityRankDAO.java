package com.example.uptoyou.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import com.example.uptoyou.Entity.ActivityRank;

@Dao
public interface ActivityRankDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertActivity(ActivityRank activityRank);

    @Update
    void updateActivity(ActivityRank activityRank);

    @Delete
    void deleteActivity(ActivityRank activityRank);
}
