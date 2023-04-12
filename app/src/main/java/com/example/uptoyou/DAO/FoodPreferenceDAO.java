package com.example.uptoyou.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.uptoyou.Entity.FoodPreference;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface FoodPreferenceDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertFood(FoodPreference foodPreference);

    @Update
    void updateFood(FoodPreference foodPreference);

    @Delete
    void deleteFood(FoodPreference foodPreference);

    @Query("SELECT * FROM FoodPreference WHERE preferenceId= :id")
    List<FoodPreference> getFoodByPreference(int id);
}
