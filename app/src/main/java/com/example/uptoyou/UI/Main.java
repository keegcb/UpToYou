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
import com.example.uptoyou.Entity.ActivityPreference;
import com.example.uptoyou.Entity.FoodPreference;
import com.example.uptoyou.Entity.Preference;
import com.example.uptoyou.Entity.User;
import com.example.uptoyou.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.util.List;

public class Main extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;

    Button btnAddData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        if(correctServices()){
            initMap();
            initPlace();
            initSettings();
            initFood();
            initData();
        }
    }

    private void initPlace() {
        Button btnPlace = (Button) findViewById(R.id.btnPlace);
        btnPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main.this, PlaceSelection.class);
                startActivity(intent);
            }
        });
    }

    private void initMap(){
        Button btnMap = (Button) findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main.this, Map.class);
                startActivity(intent);
            }
        });
    }

    public boolean correctServices(){
        Log.d(TAG, "correctServices: checking google services version");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(Main.this);

        if(available == ConnectionResult.SUCCESS){
            //correct services are installed and users can make map requests
            Log.d(TAG, "correctServices: Google Play Services are working");
            return true;
        }
        else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //error occurred but is fixable
            Log.d(TAG, "correctServices: an error occurred but it can be fixed");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(Main.this, available, ERROR_DIALOG_REQUEST);
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
                Intent intent = new Intent(Main.this, Preferences.class);
                startActivity(intent);
            }
        });
    }

    private void initFood(){
        Button btnFood = (Button) findViewById(R.id.btnFood);
        btnFood.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main.this, PlaceChoice.class);
                Bundle b = new Bundle();
                b.putInt("key", 1);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }

    private void initActivity(){
        Button btnFood = (Button) findViewById(R.id.btnActivity);
        btnFood.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main.this, PlaceChoice.class);
                Bundle b = new Bundle();
                b.putInt("key", 2);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }

    private void initRandom(){
        Button btnFood = (Button) findViewById(R.id.btnRandom);
        btnFood.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main.this, PlaceChoice.class);
                Bundle b = new Bundle();
                b.putInt("key", 3);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }

    private void initData(){
        Button btnData = (Button) findViewById(R.id.btnData);
        btnData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Repository repo = new Repository(getApplication());
                User user = new User("user", 1, 42.33461099979685, -83.0465496496764);
                Preference preference = new Preference(20);
                preference.setPreferenceId(1);

                //Food Preference Settings
                FoodPreference american = new FoodPreference(1, 1,"American", false, 0);
                FoodPreference bbq = new FoodPreference(1, 2,"BBQ", false, 0);
                FoodPreference chinese = new FoodPreference(1, 3,"Chinese", false, 0);
                FoodPreference french = new FoodPreference(1, 4,"French", false, 0);
                FoodPreference hamburger = new FoodPreference(1, 5,"Hamburger", false, 0);
                FoodPreference indian = new FoodPreference(1, 6,"Indian", false, 0);
                FoodPreference italian = new FoodPreference(1, 7,"Italian", false, 0);
                FoodPreference japanese = new FoodPreference(1, 8,"Japanese", false, 0);
                FoodPreference mexican = new FoodPreference(1, 9,"Mexican", false, 0);
                FoodPreference pizza = new FoodPreference(1, 10,"Pizza", false, 0);
                FoodPreference seafood = new FoodPreference(1, 11,"Seafood", false, 0);
                FoodPreference steak = new FoodPreference(1, 12,"Steak", false, 0);
                FoodPreference sushi = new FoodPreference(1, 13,"Sushi", false, 0);
                FoodPreference thai = new FoodPreference(1, 14,"Thai", false, 0);
                //Activity Preference Settings
                ActivityPreference arcade = new ActivityPreference(1, 1,"Arcade", false, 0);
                ActivityPreference axe_throwing = new ActivityPreference(1, 2,"Axe Throwing", false, 0);
                ActivityPreference beach = new ActivityPreference(1, 3,"Beach", false, 0);
                ActivityPreference bowling = new ActivityPreference(1, 4,"Bowling", false, 0);
                ActivityPreference casino = new ActivityPreference(1, 5,"Casino", false, 0);
                ActivityPreference disk_golf = new ActivityPreference(1, 6,"Disk Golf", false, 0);
                ActivityPreference escape_room = new ActivityPreference(1, 7, "Escape Room", false, 0);
                ActivityPreference garden = new ActivityPreference(1, 8,"Garden", false, 0);
                ActivityPreference golf = new ActivityPreference(1, 9,"Golf", false, 0);
                ActivityPreference library = new ActivityPreference(1, 10,"Library", false, 0);
                ActivityPreference hiking = new ActivityPreference(1, 11,"Hiking", false, 0);
                ActivityPreference mini_golf = new ActivityPreference(1, 12,"Mini Golf", false, 0);
                ActivityPreference movie_rental = new ActivityPreference(1, 13,"Movie Rental", false, 0);
                ActivityPreference movie_theater = new ActivityPreference(1, 14,"Movie Theater", false, 0);
                ActivityPreference museum = new ActivityPreference(1, 15,"Museum", false, 0);
                ActivityPreference park = new ActivityPreference(1, 16,"Park", false, 0);
                ActivityPreference rage_room = new ActivityPreference(1, 17,"Rage Room", false, 0);
                ActivityPreference shopping_mall = new ActivityPreference(1, 18,"Shopping Mall", false, 0);
                ActivityPreference spa = new ActivityPreference(1, 19,"Spa", false, 0);
                ActivityPreference theme_park = new ActivityPreference(1, 20,"Theme Park", false, 0);
                ActivityPreference tourist_attraction = new ActivityPreference(1, 21,"Tourist Attraction", false, 0);
                ActivityPreference zoo = new ActivityPreference(1, 22,"Zoo", false, 0);

                repo.insertUser(user);
                repo.insertPreference(preference);
                repo.insertFoodPreference(american);
                repo.insertFoodPreference(bbq);
                repo.insertFoodPreference(chinese);
                repo.insertFoodPreference(french);
                repo.insertFoodPreference(hamburger);
                repo.insertFoodPreference(indian);
                repo.insertFoodPreference(italian);
                repo.insertFoodPreference(japanese);
                repo.insertFoodPreference(mexican);
                repo.insertFoodPreference(pizza);
                repo.insertFoodPreference(seafood);
                repo.insertFoodPreference(steak);
                repo.insertFoodPreference(sushi);
                repo.insertFoodPreference(thai);

                repo.insertActivityPreference(arcade);
                repo.insertActivityPreference(axe_throwing);
                repo.insertActivityPreference(beach);
                repo.insertActivityPreference(bowling);
                repo.insertActivityPreference(casino);
                repo.insertActivityPreference(disk_golf);
                repo.insertActivityPreference(escape_room);
                repo.insertActivityPreference(garden);
                repo.insertActivityPreference(golf);
                repo.insertActivityPreference(library);
                repo.insertActivityPreference(hiking);
                repo.insertActivityPreference(mini_golf);
                repo.insertActivityPreference(movie_rental);
                repo.insertActivityPreference(movie_theater);
                repo.insertActivityPreference(museum);
                repo.insertActivityPreference(park);
                repo.insertActivityPreference(rage_room);
                repo.insertActivityPreference(shopping_mall);
                repo.insertActivityPreference(spa);
                repo.insertActivityPreference(theme_park);
                repo.insertActivityPreference(tourist_attraction);
                repo.insertActivityPreference(zoo);
            }
        });
    }

}