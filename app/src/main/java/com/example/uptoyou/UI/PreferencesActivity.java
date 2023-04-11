package com.example.uptoyou.UI;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.uptoyou.Model.ActivityPreferenceModel;
import com.example.uptoyou.Model.FoodPreferenceModel;
import com.example.uptoyou.R;

import java.util.ArrayList;

public class PreferencesActivity extends AppCompatActivity {

    ArrayList<FoodPreferenceModel> foodList = new ArrayList<>();
    ArrayList<ActivityPreferenceModel> activityList = new ArrayList<>();

    @Override
    public void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
    }

    private void setFoodList(){
        String[] foodPreferences = getResources().getStringArray(R.array.food);

        for (int i=0; i<foodPreferences.length; i++){
            foodList.add(new FoodPreferenceModel(foodPreferences[i], false, 0));
        }
    }

    private void setActivityList(){
        String[] activityPreferences = getResources().getStringArray(R.array.activity);

        for(int i=0; i<activityPreferences.length; i++){
            activityList.add(new ActivityPreferenceModel(activityPreferences[i], false, 0));
        }
    }
}
