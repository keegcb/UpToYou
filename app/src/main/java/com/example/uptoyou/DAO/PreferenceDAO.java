package com.example.uptoyou.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.uptoyou.Entity.Preference;

@Dao
public interface PreferenceDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertPreference(Preference preference);

    @Update
    void updatePreference(Preference preference);

    @Delete
    void deletePreference(Preference preference);

    @Query("SELECT * FROM Preference WHERE preferenceId= :id")
    Preference getPreference(int id);
}
