package com.example.uptoyou.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.uptoyou.Datebase.Repository;
import com.example.uptoyou.Datebase.Selector;
import com.example.uptoyou.Entity.ActivityPreference;
import com.example.uptoyou.Entity.FoodPreference;
import com.example.uptoyou.R;

import java.util.List;

public class PlaceChoice extends AppCompatActivity {
    private List<FoodPreference> foodDesired;
    private List<ActivityPreference> activityDesired;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_choice);

        Bundle b = getIntent().getExtras();
        int value; // or other values
        if(b != null){
            value = b.getInt("key");
            identifySelector(value);
        } else {
            finish();
        }
    }

    private void identifySelector(int id){
        Repository repo = new Repository(getApplication());
        Selector select = new Selector();
        String search = "";
        switch (id){
            case 1: foodDesired = repo.getFoodDesired(true);
                search = select.foodSelection(foodDesired);
                //Add Place Detail search logic for place selection
                break;
            case 2: activityDesired = repo.getmActivityDesired(true);
                search = select.activitySelector(activityDesired);
                //Add Place Detail search logic for place selection
                break;
            case 3:
                foodDesired = repo.getFoodDesired(true);
                activityDesired = repo.getmActivityDesired(true);
                search = select.randomSelector(foodDesired, activityDesired);
                //Add Place Detail search logic for place selection
            default: Log.e("identifySelector", "no case");
        }
    }
}