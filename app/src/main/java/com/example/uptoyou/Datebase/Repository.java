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
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
        Future<List<FoodPreference>> foodPreferenceFuture = databaseExecutor.submit(new Callable<List<FoodPreference>>() {
            @Override
            public List<FoodPreference> call() throws Exception {
                return foodPreferenceDAO.getFoodByPreference(id);
            }
        });
        List<FoodPreference> result = null;
        try{
            result = foodPreferenceFuture.get();
        } catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
        return result;
    }

    public List<ActivityPreference> getActivityByPreference(int id){
        Future<List<ActivityPreference>> activityPreferenceFuture = databaseExecutor.submit(new Callable<List<ActivityPreference>>() {
            @Override
            public List<ActivityPreference> call() throws Exception {
                return activityPreferenceDAO.getActivityByPreference(id);
            }
        });
        List<ActivityPreference> result = null;
        try{
            result = activityPreferenceFuture.get();
        } catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
        return result;
    }

    public Preference getPreferenceById(int id) {
        Future<Preference> preferenceFuture = databaseExecutor.submit(new Callable<Preference>() {
            @Override
            public Preference call() throws Exception {
                return preferenceDAO.getPreference(id);
            }
        });
        Preference result = null;
        try
        {
            result = preferenceFuture.get();
        } catch(InterruptedException | ExecutionException e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public  List<User> userExists(int id) {
        Future<List<User>> userListFuture = databaseExecutor.submit(new Callable<List<User>>() {
            @Override
            public List<User> call() throws Exception {
                return userDAO.getUsers(id);
            }
        });
        List<User> result = null;
        try{
            result = userListFuture.get();
        } catch(InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
        return result;
    }

    public List<FoodPreference> getFoodDesired(boolean desired){
        Future<List<FoodPreference>> foodListFuture = databaseExecutor.submit(new Callable<List<FoodPreference>>() {
            @Override
            public List<FoodPreference> call() throws Exception {
                return foodPreferenceDAO.getFoodDesired(desired);
            }
        });
        List<FoodPreference> result = null;
        try{
            result = foodListFuture.get();
        } catch(InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
        return result;
    }

    public List<ActivityPreference> getActivityDesired(boolean desired){
        Future<List<ActivityPreference>> activityListFuture = databaseExecutor.submit(new Callable<List<ActivityPreference>>() {
            @Override
            public List<ActivityPreference> call() throws Exception {
                return activityPreferenceDAO.getActivityDesired(desired);
            }
        });
        List<ActivityPreference> result = null;
        try{
            result = activityListFuture.get();
        } catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
        return result;
    }


    public List<PlaceInfo> getAllPlaceInfo() {
        Future<List<PlaceInfo>> placeInfoListFuture = databaseExecutor.submit(new Callable<List<PlaceInfo>>() {
            @Override
            public List<PlaceInfo> call() throws Exception {
                return placeInfoDAO.getAllPlaceInfo();
            }
        });
        List<PlaceInfo> result = null;
        try{
            result = placeInfoListFuture.get();
        } catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
        return result;
    }

    public PlaceInfo getPlaceById(String id){
        Future<PlaceInfo> placeInfoFuture = databaseExecutor.submit(new Callable<PlaceInfo>() {
            @Override
            public PlaceInfo call() throws Exception {
                return placeInfoDAO.getPlaceById(id);
            }
        });
        PlaceInfo result = null;
        try{
            result = placeInfoFuture.get();
        } catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
        return result;
    }

    public List<History> getHistory() {
        Future<List<History>> historyFuture = databaseExecutor.submit(new Callable<List<History>>() {
            @Override
            public List<History> call() throws Exception {
                return historyDAO.getHistory();
            }
        });
        List<History> result = null;
        try{
            result = historyFuture.get();
        } catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
        return result;
    }

    public List<History> getHistoryBySelected(){
        Future<List<History>> historyFuture = databaseExecutor.submit(new Callable<List<History>>() {
            @Override
            public List<History> call() throws Exception {
                return historyDAO.getHistoryBySelected(true);
            }
        });
        List<History> result = null;
        try{
            result = historyFuture.get();
        } catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
        return  result;
    }

    public History getHistoryByPlace(String placeId){
        Future<History> historyFuture = databaseExecutor.submit(new Callable<History>() {
            @Override
            public History call() throws Exception {
                return historyDAO.getHistoryByPlace(placeId);
            }
        });
        History result = null;
        try{
            result = historyFuture.get();
        } catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
        return result;
    }
}
