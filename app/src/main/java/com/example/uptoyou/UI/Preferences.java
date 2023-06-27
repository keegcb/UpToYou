package com.example.uptoyou.UI;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.Objects;

public class Preferences extends AppCompatActivity {
    Preference preference;
    Spinner distanceSpinner;
    List<FoodPreference> foodList;
    List<ActivityPreference> activityList;
    RecyclerView foodRecycler;
    RecyclerView activityRecycler;

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
        do {
            dSpin = distanceSpinner.getItemAtPosition(i).toString();
            if (dSpin.equals(pDis)) {
                distanceSpinner.setSelection(i);
            }
            i++;
        } while (!dSpin.equals(pDis));
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

        foodRecycler = findViewById(R.id.recycleFoodPref);
        activityRecycler = findViewById(R.id.recycleActivityPref);
        foodList = repo.getFoodByPreference(1);
        FoodPreferenceAdapter foodAdapter = new FoodPreferenceAdapter(this, foodList);
        foodRecycler.setAdapter(foodAdapter);
        foodRecycler.setLayoutManager(new LinearLayoutManager(this));
        activityList = repo.getActivityByPreference(1);
        ActivityPreferenceAdapter activityAdapter = new ActivityPreferenceAdapter(this, activityList);
        activityRecycler.setAdapter(activityAdapter);
        activityRecycler.setLayoutManager(new LinearLayoutManager(this));

        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<FoodPreference> foodSelected = foodAdapter.getSelected();
                List<FoodPreference> foodUnselected = foodAdapter.getUnselected();
                List<ActivityPreference> activitySelected = activityAdapter.getSelected();
                List<ActivityPreference> activityUnselected = activityAdapter.getUnselected();
                int pId = preference.getPreferenceId();

                if (activitySelected.isEmpty()){
                    createAlert();
                } else {
                    String distance = distanceSpinner.getSelectedItem().toString();
                    preference.setDistance(Integer.parseInt(distance));
                    repo.updatePreference(preference);
                    //Update selected and unselected Food Preferences from list in DB
                    for(int i=0; i<foodSelected.size(); i++){
                        FoodPreference food = new FoodPreference(pId, foodSelected.get(i).getFoodId(), foodSelected.get(i).getFoodName(), foodSelected.get(i).isFoodDesired(), foodSelected.get(i).getFoodRank());
                        repo.updateFoodPreference(food);
                    }
                    for(int i=0; i<foodUnselected.size(); i++){
                        FoodPreference food = new FoodPreference(pId, foodUnselected.get(i).getFoodId(), foodUnselected.get(i).getFoodName(), foodUnselected.get(i).isFoodDesired(), foodUnselected.get(i).getFoodRank());
                        repo.updateFoodPreference(food);
                    }
                    //Update selected and unselected Activity Preferences from list in DB
                    for(int i=0; i<activitySelected.size(); i++){
                        ActivityPreference actvitiy = new ActivityPreference(pId, activitySelected.get(i).getActivityId(), activitySelected.get(i).getActivityName(), activitySelected.get(i).isActivityDesired(),activitySelected.get(i).getActivityRank());
                        repo.updateActivityPreference(actvitiy);
                    }
                    for(int i=0; i<activityUnselected.size(); i++){
                        ActivityPreference actvitiy = new ActivityPreference(pId, activityUnselected.get(i).getActivityId(), activityUnselected.get(i).getActivityName(), activityUnselected.get(i).isActivityDesired(), activityUnselected.get(i).getActivityRank());
                        repo.updateActivityPreference(actvitiy);
                    }
                    finish();
                }
            }
        });
    }

    private void createAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Preferences.this);
        builder.setTitle("No Preferences Selected");
        builder.setMessage("You must have at least one preference selected to save.");
        builder.setCancelable(true);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(Preferences.this, "Save Unsuccessful", Toast.LENGTH_LONG).show();
                dialogInterface.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

}
