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
import com.example.uptoyou.Datebase.Selector;
import com.example.uptoyou.Entity.ActivityPreference;
import com.example.uptoyou.Entity.FoodPreference;
import com.example.uptoyou.Entity.PlaceInfo;
import com.example.uptoyou.Entity.Preference;
import com.example.uptoyou.Entity.User;
import com.example.uptoyou.Networking.JsonNearbyPlacesAPI;
import com.example.uptoyou.Networking.Serialization.NearbyPlace;
import com.example.uptoyou.Networking.Serialization.Results;
import com.example.uptoyou.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;
    private JsonNearbyPlacesAPI jsonNearbyPlacesAPI;
    private List<NearbyPlace> nearbyPlaceList;
    public static List<PlaceInfo> placeList = new ArrayList<>();

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
/*
                Repository repo = new Repository(getApplication());
                List<FoodPreference> food = repo.getFoodDesired(true);
                Selector selector = new Selector();
                String food1 = selector.foodSelection(food);
                String food2 = selector.foodSelection(food);
                
 */
                connectFoodAPI();
            }
        });
    }

    public void connectFoodAPI(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/maps/api/place/nearbysearch/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonNearbyPlacesAPI = retrofit.create(JsonNearbyPlacesAPI.class);
        Call<Results> call = jsonNearbyPlacesAPI.getFoodResults();
        //TODO: Toast message attempt to connect to AIP
        call.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Call<Results> call, Response<Results> response) {
                if(!response.isSuccessful()){
                    String code = "Code: " + response.code();
                    System.out.println(code);
                    return;
                }
                Results results = response.body();
                List<NearbyPlace> nearbyPlaces = results.getResults();
                int listSize = nearbyPlaces.size();
                Random rand = new Random();
                do{
                    int randNum = rand.nextInt(listSize);
                    for(int i=0; i<listSize; i++){
                        if(i == randNum){
                            NearbyPlace place = nearbyPlaces.get(i);
                            Selector selector = new Selector();
                            PlaceInfo placeInfo = selector.convertNearbyPlace(place);
                            placeList.add(placeInfo);
                        }
                    }
                }while(placeList.size() < 2);
                Intent intent = new Intent(Main.this, PlaceChoice.class);
                startActivity(intent);
            }
            @Override
            public void onFailure(Call<Results> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void initActivity(){
        Button btnFood = (Button) findViewById(R.id.btnActivity);
        btnFood.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Repository repo = new Repository(getApplication());
                List<ActivityPreference> activity = repo.getActivityDesired(true);
                Selector select = new Selector();
                String act1 = select.activitySelection(activity);
                String act2 = select.activitySelection(activity);
                Intent intent = new Intent(Main.this, PlaceChoice.class);
                Bundle b = new Bundle();
                b.putString("key1", act1);
                b.putString("key2", act2);
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
                Repository repo = new Repository(getApplication());
                List<FoodPreference> foodPref = repo.getFoodDesired(true);
                List<ActivityPreference> activityPref = repo.getActivityDesired(true);
                Selector select = new Selector();
                String food = select.foodSelection(foodPref);
                String activity = select.activitySelection(activityPref);
                Intent intent = new Intent(Main.this, PlaceChoice.class);
                Bundle b = new Bundle();
                b.putString("key1", food);
                b.putString("key2", activity);
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
                FoodPreference american = new FoodPreference(1, 1,"American", true, 0);
                FoodPreference bbq = new FoodPreference(1, 2,"BBQ", true, 0);
                FoodPreference chinese = new FoodPreference(1, 3,"Chinese", true, 0);
                FoodPreference french = new FoodPreference(1, 4,"French", true, 0);
                FoodPreference hamburger = new FoodPreference(1, 5,"Hamburger", true, 0);
                FoodPreference indian = new FoodPreference(1, 6,"Indian", true, 0);
                FoodPreference italian = new FoodPreference(1, 7,"Italian", true, 0);
                FoodPreference japanese = new FoodPreference(1, 8,"Japanese", true, 0);
                FoodPreference mexican = new FoodPreference(1, 9,"Mexican", true, 0);
                FoodPreference pizza = new FoodPreference(1, 10,"Pizza", true, 0);
                FoodPreference seafood = new FoodPreference(1, 11,"Seafood", true, 0);
                FoodPreference steak = new FoodPreference(1, 12,"Steak", true, 0);
                FoodPreference sushi = new FoodPreference(1, 13,"Sushi", true, 0);
                FoodPreference thai = new FoodPreference(1, 14,"Thai", true, 0);
                //Activity Preference Settings
                ActivityPreference arcade = new ActivityPreference(1, 1,"Arcade", true, 0);
                ActivityPreference axe_throwing = new ActivityPreference(1, 2,"Axe Throwing", true, 0);
                ActivityPreference beach = new ActivityPreference(1, 3,"Beach", true, 0);
                ActivityPreference bowling = new ActivityPreference(1, 4,"Bowling", true, 0);
                ActivityPreference casino = new ActivityPreference(1, 5,"Casino", true, 0);
                ActivityPreference disk_golf = new ActivityPreference(1, 6,"Disk Golf", true, 0);
                ActivityPreference escape_room = new ActivityPreference(1, 7, "Escape Room", true, 0);
                ActivityPreference garden = new ActivityPreference(1, 8,"Garden", true, 0);
                ActivityPreference golf = new ActivityPreference(1, 9,"Golf", true, 0);
                ActivityPreference library = new ActivityPreference(1, 10,"Library", true, 0);
                ActivityPreference hiking = new ActivityPreference(1, 11,"Hiking", true, 0);
                ActivityPreference mini_golf = new ActivityPreference(1, 12,"Mini Golf", true, 0);
                ActivityPreference movie_rental = new ActivityPreference(1, 13,"Movie Rental", true, 0);
                ActivityPreference movie_theater = new ActivityPreference(1, 14,"Movie Theater", true, 0);
                ActivityPreference museum = new ActivityPreference(1, 15,"Museum", true, 0);
                ActivityPreference park = new ActivityPreference(1, 16,"Park", true, 0);
                ActivityPreference rage_room = new ActivityPreference(1, 17,"Rage Room", true, 0);
                ActivityPreference shopping_mall = new ActivityPreference(1, 18,"Shopping Mall", true, 0);
                ActivityPreference spa = new ActivityPreference(1, 19,"Spa", true, 0);
                ActivityPreference theme_park = new ActivityPreference(1, 20,"Theme Park", true, 0);
                ActivityPreference tourist_attraction = new ActivityPreference(1, 21,"Tourist Attraction", true, 0);
                ActivityPreference zoo = new ActivityPreference(1, 22,"Zoo", true, 0);

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