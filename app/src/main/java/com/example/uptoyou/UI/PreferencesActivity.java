package com.example.uptoyou.UI;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uptoyou.Datebase.Repository;
import com.example.uptoyou.Entity.ActivityPreference;
import com.example.uptoyou.Entity.FoodPreference;
import com.example.uptoyou.R;
import com.example.uptoyou.UI.Adapter.ActivityPreferenceAdapter;
import com.example.uptoyou.UI.Adapter.FoodPreferenceAdapter;

import java.util.ArrayList;
import java.util.List;

public class PreferencesActivity extends AppCompatActivity {


    List<FoodPreference> foodList;
    List<ActivityPreference> activityList;

    @Override
    public void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        Repository repo = new Repository(getApplication());

        RecyclerView foodRecycler = findViewById(R.id.recycleFoodPref);
        RecyclerView activityRecycler = findViewById(R.id.recycleActivityPref);

        foodList = repo.getFoodByPreference(1);
        FoodPreferenceAdapter foodAdapter = new FoodPreferenceAdapter(this, foodList);
        foodRecycler.setAdapter(foodAdapter);
        foodRecycler.setLayoutManager(new LinearLayoutManager(this));

        activityList = repo.getActivityByPreference(1);
        ActivityPreferenceAdapter activityAdapter = new ActivityPreferenceAdapter(this, activityList);
        activityRecycler.setAdapter(activityAdapter);
        activityRecycler.setLayoutManager(new LinearLayoutManager(this));
    }
/*
    private void setFoodList(){
        foodList = repo.getFoodByPreference(1);
    }

    private void setActivityList(){
        activityList = repo.getActivityByPreference(1);
    }
 */
}
