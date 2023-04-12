package com.example.uptoyou.Datebase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.uptoyou.DAO.ActivityPreferenceDAO;
import com.example.uptoyou.DAO.FoodPreferenceDAO;
import com.example.uptoyou.DAO.HistoryDAO;
import com.example.uptoyou.DAO.PlaceInfoDAO;
import com.example.uptoyou.DAO.PreferenceDAO;
import com.example.uptoyou.DAO.UserDAO;
import com.example.uptoyou.Entity.ActivityPreference;
import com.example.uptoyou.Entity.FoodPreference;
import com.example.uptoyou.Entity.History;
import com.example.uptoyou.Entity.PlaceInfo;
import com.example.uptoyou.Entity.Preference;
import com.example.uptoyou.Entity.User;

@Database(entities = {User.class, Preference.class, FoodPreference.class, ActivityPreference.class, PlaceInfo.class, History.class}, version = 1, exportSchema = false)
@TypeConverters(DataConverter.class)
public abstract class DatabaseBuilder extends RoomDatabase {
    public abstract UserDAO userDAO();
    public abstract PreferenceDAO preferenceDAO();
    public abstract FoodPreferenceDAO foodPreferenceDAO();
    public abstract ActivityPreferenceDAO activityPreferenceDAO();
    public abstract PlaceInfoDAO placeInfoDAO();
    public abstract HistoryDAO historyDAO();

    private static volatile DatabaseBuilder INSTANCE;

    public static synchronized DatabaseBuilder getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DatabaseBuilder.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DatabaseBuilder.class, "upToYouDatabase.db")
                            .fallbackToDestructiveMigration().allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
