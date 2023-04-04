package com.example.uptoyou.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import com.example.uptoyou.Entity.FoodRank;

@Dao
public interface FoodRankDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertFood(FoodRank foodRank);

    @Update
    void updateFood(FoodRank foodRank);

    @Delete
    void deleteFood(FoodRank foodRank);
}
