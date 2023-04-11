package com.example.uptoyou.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.uptoyou.Datebase.Repository;
import com.example.uptoyou.Entity.ActivityRank;
import com.example.uptoyou.Entity.FoodRank;
import com.example.uptoyou.Entity.Preference;
import com.example.uptoyou.Entity.User;
import com.example.uptoyou.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;

    Repository repo = new Repository(getApplication());

    Button btnAddData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddData = findViewById(R.id.btnAddData);
        if(correctServices()){
            initMap();
            initSettings();
        }
    }

    private void initMap(){
        Button btnMap = (Button) findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean correctServices(){
        Log.d(TAG, "correctServices: checking google services version");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

        if(available == ConnectionResult.SUCCESS){
            //correct services are installed and users can make map requests
            Log.d(TAG, "correctServices: Google Play Services are working");
            return true;
        }
        else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //error occurred but is fixable
            Log.d(TAG, "correctServices: an error occurred but it can be fixed");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }
        else{
            Toast.makeText(this, "Map requests cannot be made", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    private void initSettings(){
        Button btnSetting = (Button) findViewById(R.id.btnSetting);
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PreferencesActivity.class);
                startActivity(intent);
            }
        });
    }

    public void addData(View view) {
        User user = new User("user", 1, 42.33461099979685, -83.0465496496764);
        Preference preference = new Preference(20, false, false, false, false, false, false, false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false);
        preference.setPreferenceId(1);
        FoodRank fRank = new FoodRank(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        fRank.setFoodId(1);
        ActivityRank aRank = new ActivityRank(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        aRank.setActivityId(1);
        preference.setFoodId(1);
        preference.setActivityId(1);

        repo.insertUser(user);
        repo.insertPreference(preference);
        repo.insertFoodRank(fRank);
        repo.insertActivityRank(aRank);
    }
}