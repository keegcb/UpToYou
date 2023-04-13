package com.example.uptoyou.UI;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

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

public class PreferencesActivity extends AppCompatActivity {
    Preference preference;
    Spinner distanceSpinner;
    Button btnSave;
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

        initSave();
    }

    private void initSave() {
        Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Repository repo = new Repository(getApplication());
                repo.updatePreference(preference);

                for(int i=0; i<foodRecycler.getChildCount(); i++){
                    CheckBox check = Objects.requireNonNull(foodRecycler.findViewHolderForAdapterPosition(i)).itemView.findViewById(R.id.checkboxPreference);
                    TextView txtName = Objects.requireNonNull(foodRecycler.findViewHolderForAdapterPosition(i)).itemView.findViewById(R.id.txtPreferenceName);
                    TextView txtRank = Objects.requireNonNull(foodRecycler.findViewHolderForAdapterPosition(i)).itemView.findViewById(R.id.txtRank);

                    int pId = preference.getPreferenceId();
                    boolean desired = check.isChecked();
                    String name = (String) txtName.getText();
                    String rank = (String) txtRank.getText();

                    FoodPreference updateFood = new FoodPreference(pId, name, desired, Integer.parseInt(rank));
                    updateFood.setFoodId(i+1);
                    repo.updateFoodPreference(updateFood);
                }
                for(int i=0; i<activityRecycler.getChildCount(); i++){
                    CheckBox check = Objects.requireNonNull(activityRecycler.findViewHolderForAdapterPosition(i)).itemView.findViewById(R.id.checkboxPreference);
                    TextView txtName = Objects.requireNonNull(activityRecycler.findViewHolderForAdapterPosition(i)).itemView.findViewById(R.id.txtPreferenceName);
                    TextView txtRank = Objects.requireNonNull(activityRecycler.findViewHolderForAdapterPosition(i)).itemView.findViewById(R.id.txtRank);

                    int pId = preference.getPreferenceId();
                    boolean desired = check.isChecked();
                    String name = (String) txtName.getText();
                    String rank = (String) txtRank.getText();

                    ActivityPreference updateActivity = new ActivityPreference(pId, name, desired, Integer.parseInt(rank));
                    updateActivity.setActivityId(i+1);
                    repo.updateActivityPreference(updateActivity);
                }
                finish();
            }
        });
    }

}
