package com.example.uptoyou.UI;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uptoyou.Datebase.Repository;
import com.example.uptoyou.Entity.ActivityPreference;
import com.example.uptoyou.Entity.FoodPreference;
import com.example.uptoyou.Entity.Preference;
import com.example.uptoyou.R;
import com.example.uptoyou.UI.Adapter.ActivityPreferenceAdapter;
import com.example.uptoyou.UI.Adapter.FoodPreferenceAdapter;

import java.util.ArrayList;
import java.util.List;

public class PreferencesActivity extends AppCompatActivity {
    Preference preference;
    Spinner distanceSpinner;
    Button btnSave;
    List<FoodPreference> foodList;
    List<ActivityPreference> activityList;

    @Override
    public void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        Repository repo = new Repository(getApplication());
        preference = repo.getPreferenceById(1);
        String pDis = Integer.toString(preference.getDistance());

        String[] distanceList = getResources().getStringArray(R.array.distance);
        ArrayAdapter<String> distanceAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, distanceList);
        distanceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        distanceSpinner = findViewById(R.id.spnDistance);
        distanceSpinner.setAdapter(distanceAdapter);
        String dSpin = "";
        int i = 0;
        do{
            dSpin = distanceSpinner.getItemAtPosition(i).toString();
            if(dSpin.equals(pDis)){
                distanceSpinner.setSelection(i);
            }
            i++;
        } while(!dSpin.equals(pDis));
        distanceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String nDis = adapterView.getItemAtPosition(i).toString();
                preference.setDistance(Integer.parseInt(nDis));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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

    private void initSave(){
        Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Repository repo = new Repository(getApplication());
                repo.updatePreference(preference);

    //TODO: Figure out how to loop through Recycler View
                for(int i=0; i<foodList.size(); i++){
                    FoodPreference foodUpdate = foodList.get(i);
                    repo.updateFoodPreference(foodUpdate);
                    ActivityPreference activityUpdate = activityList.get(i);
                    repo.updateActivityPreference(activityUpdate);
                }
            }
        });
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
