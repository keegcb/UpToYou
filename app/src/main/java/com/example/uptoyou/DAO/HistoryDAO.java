package com.example.uptoyou.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.uptoyou.Entity.History;

import java.util.List;

@Dao
public interface HistoryDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertHistory(History history);

    @Update
    void updateHistory(History history);

    @Delete
    void deleteHistory(History history);

    @Query("SELECT * FROM History")
    List<History> getHistory();
}
