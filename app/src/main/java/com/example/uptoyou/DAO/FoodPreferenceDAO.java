package com.example.uptoyou.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import com.example.uptoyou.Entity.FoodPreference;

@Dao
public interface FoodPreferenceDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertFood(FoodPreference foodPreference);

    @Update
    void updateFood(FoodPreference foodPreference);

    @Delete
    void deleteFood(FoodPreference foodPreference);
}
