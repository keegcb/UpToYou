package com.example.uptoyou.UI;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.uptoyou.Entity.ActivityPreference;
import com.example.uptoyou.Entity.FoodPreference;
import com.example.uptoyou.R;

import java.util.ArrayList;

public class PreferencesActivity extends AppCompatActivity {

    ArrayList<FoodPreference> foodList = new ArrayList<>();
    ArrayList<ActivityPreference> activityList = new ArrayList<>();

    @Override
    public void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
    }

    private void setFoodList(){
        String[] foodPreferences = getResources().getStringArray(R.array.food);

        for (int i=0; i<foodPreferences.length; i++){
            foodList.add(new FoodPreference(1, foodPreferences[i], false, 0));
        }
    }

    private void setActivityList(){
        String[] activityPreferences = getResources().getStringArray(R.array.activity);

        for(int i=0; i<activityPreferences.length; i++){
            activityList.add(new ActivityPreference(1, activityPreferences[i], false, 0));
        }
    }
}
