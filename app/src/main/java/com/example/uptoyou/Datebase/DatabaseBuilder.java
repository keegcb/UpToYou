package com.example.uptoyou.Datebase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.uptoyou.DAO.ActivityRankDAO;
import com.example.uptoyou.DAO.FoodRankDAO;
import com.example.uptoyou.DAO.HistoryDAO;
import com.example.uptoyou.DAO.PlaceInfoDAO;
import com.example.uptoyou.DAO.PreferenceDAO;
import com.example.uptoyou.DAO.UserDAO;
import com.example.uptoyou.Entity.ActivityRank;
import com.example.uptoyou.Entity.FoodRank;
import com.example.uptoyou.Entity.History;
import com.example.uptoyou.Entity.PlaceInfo;
import com.example.uptoyou.Entity.Preference;

@Database(entities = {Preference.class, FoodRank.class, ActivityRank.class, PlaceInfo.class, History.class}, version = 1, exportSchema = false)
@TypeConverters(DataConverter.class)
public abstract class DatabaseBuilder extends RoomDatabase {
    public abstract UserDAO userDAO();
    public abstract PreferenceDAO preferenceDAO();
    public abstract FoodRankDAO foodRankDAO();
    public abstract ActivityRankDAO activityRankDAO();
    public abstract PlaceInfoDAO placeInfoDAO();
    public abstract HistoryDAO hisotryDAO();

    private static volatile DatabaseBuilder INSTANCE;

    public static synchronized DatabaseBuilder getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DatabaseBuilder.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DatabaseBuilder.class, "studentSchedulerDatabase.db")
                            .fallbackToDestructiveMigration().allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
