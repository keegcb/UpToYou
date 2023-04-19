package com.example.uptoyou.Datebase;

import android.app.Application;

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

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private final UserDAO userDAO;
    private final PreferenceDAO preferenceDAO;
    private final FoodPreferenceDAO foodPreferenceDAO;
    private final ActivityPreferenceDAO activityPreferenceDAO;
    private final PlaceInfoDAO placeInfoDAO;
    private final HistoryDAO historyDAO;

    private List<FoodPreference> mFoodPref;
    private List<ActivityPreference> mActivityPref;
    private Preference mPreference;
    private List<User> mUser;

    private static final int NUMBER_OF_THREADS = 8;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application){
        DatabaseBuilder db = DatabaseBuilder.getDatabase(application);
        userDAO = db.userDAO();
        preferenceDAO = db.preferenceDAO();
        foodPreferenceDAO = db.foodPreferenceDAO();
        activityPreferenceDAO = db.activityPreferenceDAO();
        placeInfoDAO = db.placeInfoDAO();
        historyDAO = db.historyDAO();
    }

    public void insertUser(User user){
        databaseExecutor.execute(()-> userDAO.insertUser(user));
        try{
            Thread.sleep(10);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void updateUser(User user){
        databaseExecutor.execute(()-> userDAO.updateUser(user));
        try{
            Thread.sleep(10);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void deleteUser(User user){
        databaseExecutor.execute(()-> userDAO.deleteUser(user));
        try{
            Thread.sleep(10);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void insertPreference(Preference preference){
        databaseExecutor.execute(()-> preferenceDAO.insertPreference(preference));
        try{
            Thread.sleep(10);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void updatePreference(Preference preference){
        databaseExecutor.execute(()-> preferenceDAO.updatePreference(preference));
        try{
            Thread.sleep(10);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void deletePreference(Preference preference){
        databaseExecutor.execute(()-> preferenceDAO.deletePreference(preference));
        try{
            Thread.sleep(10);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void insertFoodPreference(FoodPreference foodPreference){
        databaseExecutor.execute(()-> foodPreferenceDAO.insertFood(foodPreference));
        try{
            Thread.sleep(10);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void updateFoodPreference(FoodPreference foodPreference){
        databaseExecutor.execute(()-> foodPreferenceDAO.updateFood(foodPreference));
        try{
            Thread.sleep(10);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void deleteFoodPreference(FoodPreference foodPreference){
        databaseExecutor.execute(()-> foodPreferenceDAO.deleteFood(foodPreference));
        try{
            Thread.sleep(10);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void insertActivityPreference(ActivityPreference activityPreference){
        databaseExecutor.execute(()-> activityPreferenceDAO.insertActivity(activityPreference));
        try{
            Thread.sleep(10);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void updateActivityPreference(ActivityPreference activityPreference){
        databaseExecutor.execute(()-> activityPreferenceDAO.updateActivity(activityPreference));
        try{
            Thread.sleep(10);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void deleteActivityPreference(ActivityPreference activityPreference){
        databaseExecutor.execute(()-> activityPreferenceDAO.deleteActivity(activityPreference));
        try{
            Thread.sleep(10);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void insertPlaceInfo(PlaceInfo placeInfo){
        databaseExecutor.execute(()-> placeInfoDAO.insertPlace(placeInfo));
        try{
            Thread.sleep(10);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void updatePlaceInfo(PlaceInfo placeInfo){
        databaseExecutor.execute(()-> placeInfoDAO.updatePlace(placeInfo));
        try{
            Thread.sleep(10);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void deletePlaceInfo(PlaceInfo placeInfo){
        databaseExecutor.execute(()-> placeInfoDAO.deletePlace(placeInfo));
        try{
            Thread.sleep(10);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void insertHistory(History history){
        databaseExecutor.execute(()-> historyDAO.insertHistory(history));
        try{
            Thread.sleep(10);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void updateHistory(History history){
        databaseExecutor.execute(()-> historyDAO.updateHistory(history));
        try{
            Thread.sleep(10);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void deleteHistory(History history){
        databaseExecutor.execute(()-> historyDAO.deleteHistory(history));
        try{
            Thread.sleep(10);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public List<FoodPreference> getFoodByPreference(int id){
        databaseExecutor.execute(()->{
            mFoodPref = foodPreferenceDAO.getFoodByPreference(id);
        });
        try{
            Thread.sleep(10);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        return mFoodPref;
    }

    public List<ActivityPreference> getActivityByPreference(int id){
        databaseExecutor.execute(()->{
            mActivityPref = activityPreferenceDAO.getActivityByPreference(id);
        });
        try{
            Thread.sleep(10);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        return mActivityPref;
    }

    public Preference getPreferenceById(int id) {
        databaseExecutor.execute(()->{
            mPreference = preferenceDAO.getPreference(id);
        });
        try{
            Thread.sleep(10);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        return mPreference;
    }

    public  List<User> userExists(int id) {
        databaseExecutor.execute(()->{
            mUser = userDAO.getUsers(id);
        });
        try{
            Thread.sleep(10);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        return mUser;
    }

    public List<FoodPreference> getFoodDesired(boolean desired){
        databaseExecutor.execute(()->{
            mFoodPref = foodPreferenceDAO.getFoodDesired(desired);
        });
        try{
            Thread.sleep(10);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        return mFoodPref;
    }

    public List<ActivityPreference> getmActivityDesired(boolean desired){
        databaseExecutor.execute(()->{
            mActivityPref = activityPreferenceDAO.getActivityDesired(desired);
        });
        try{
            Thread.sleep(10);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        return mActivityPref;
    }
}
