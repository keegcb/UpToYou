package com.example.uptoyou.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import com.example.uptoyou.Entity.PlaceInfo;

@Dao
public interface PlaceInfoDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertPlace(PlaceInfo placeInfo);

    @Update
    void updatePlace(PlaceInfo placeInfo);

    @Delete
    void deletePlace(PlaceInfo placeInfo);
}
