package com.example.uptoyou.Datebase;

import android.app.Application;

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
import com.example.uptoyou.Entity.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private final UserDAO userDAO;
    private final PreferenceDAO preferenceDAO;
    private final FoodRankDAO foodRankDAO;
    private final ActivityRankDAO activityRankDAO;
    private final PlaceInfoDAO placeInfoDAO;
    private final HistoryDAO historyDAO;

    private static final int NUMBER_OF_THREADS = 8;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application){
        DatabaseBuilder db = DatabaseBuilder.getDatabase(application);
        userDAO = db.userDAO();
        preferenceDAO = db.preferenceDAO();
        foodRankDAO = db.foodRankDAO();
        activityRankDAO = db.activityRankDAO();
        placeInfoDAO = db.placeInfoDAO();
        historyDAO = db.hisotryDAO();
    }

    public void insertUser(User user){
        databaseExecutor.execute(()-> userDAO.insertUser(user));
        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void updateUser(User user){
        databaseExecutor.execute(()-> userDAO.updateUser(user));
        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void deleteUser(User user){
        databaseExecutor.execute(()-> userDAO.deleteUser(user));
        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void insertPreference(Preference preference){
        databaseExecutor.execute(()-> preferenceDAO.insertPreference(preference));
        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void updatePreference(Preference preference){
        databaseExecutor.execute(()-> preferenceDAO.updatePreference(preference));
        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void deletePreference(Preference preference){
        databaseExecutor.execute(()-> preferenceDAO.deletePreference(preference));
        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void insertFoodRank(FoodRank foodRank){
        databaseExecutor.execute(()-> foodRankDAO.insertFood(foodRank));
        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void updateFoodRank(FoodRank foodRank){
        databaseExecutor.execute(()-> foodRankDAO.updateFood(foodRank));
        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void deleteFoodRank(FoodRank foodRank){
        databaseExecutor.execute(()-> foodRankDAO.deleteFood(foodRank));
        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void insertActivityRank(ActivityRank activityRank){
        databaseExecutor.execute(()-> activityRankDAO.insertActivity(activityRank));
        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void updateActivityRank(ActivityRank activityRank){
        databaseExecutor.execute(()-> activityRankDAO.updateActivity(activityRank));
        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void deleteActivityRank(ActivityRank activityRank){
        databaseExecutor.execute(()-> activityRankDAO.deleteActivity(activityRank));
        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void insertPlaceInfo(PlaceInfo placeInfo){
        databaseExecutor.execute(()-> placeInfoDAO.insertPlace(placeInfo));
        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void updatePlaceInfo(PlaceInfo placeInfo){
        databaseExecutor.execute(()-> placeInfoDAO.updatePlace(placeInfo));
        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void deletePlaceInfo(PlaceInfo placeInfo){
        databaseExecutor.execute(()-> placeInfoDAO.deletePlace(placeInfo));
        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void insertHistory(History history){
        databaseExecutor.execute(()-> historyDAO.insertHistory(history));
        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void updateHistory(History history){
        databaseExecutor.execute(()-> historyDAO.updateHistory(history));
        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void deleteHistory(History history){
        databaseExecutor.execute(()-> historyDAO.deleteHistory(history));
        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
