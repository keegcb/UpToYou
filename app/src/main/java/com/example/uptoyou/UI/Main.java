package com.example.uptoyou.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.example.uptoyou.Datebase.Repository;
import com.example.uptoyou.Datebase.Selector;
import com.example.uptoyou.Entity.ActivityPreference;
import com.example.uptoyou.Entity.FoodPreference;
import com.example.uptoyou.Entity.History;
import com.example.uptoyou.Entity.PlaceInfo;
import com.example.uptoyou.Entity.Preference;
import com.example.uptoyou.Entity.User;
import com.example.uptoyou.Networking.JsonNearbyPlacesAPI;
import com.example.uptoyou.Networking.Serialization.NearbyPlace;
import com.example.uptoyou.Networking.Serialization.Results;
import com.example.uptoyou.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.sql.Date;
import java.time.Instant;
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
            initActivity();
            initData();
            initReport();
        }
        configureDatabase();
    }

    private void initPlace() {
        Button btnPlace = findViewById(R.id.btnPlace);
        btnPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main.this, PlaceSelection.class);
                startActivity(intent);
            }
        });
    }

    private void initMap(){
        Button btnMap = findViewById(R.id.btnMap);
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
        Button btnSetting = findViewById(R.id.btnSetting);
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main.this, Preferences.class);
                startActivity(intent);
            }
        });
    }

    private void initFood(){
        Button btnFood = findViewById(R.id.btnFood);
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
        Call<Results> call = jsonNearbyPlacesAPI.getFoodResults2();
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
                            System.out.println(place);
                            Selector selector = new Selector();
                            PlaceInfo placeInfo = selector.convertNearbyPlace(place);
                            placeInfo.setIcon(place.getIcon());
                            placeList.add(placeInfo);
                        }
                    }
                }while(placeList.size() < 2);
                Intent intent = new Intent(Main.this, PlaceChoice.class);
                intent.putExtra("food", 1);
                startActivity(intent);
            }
            @Override
            public void onFailure(Call<Results> call, Throwable t) {
                t.printStackTrace();
                AlertDialog.Builder builder = new AlertDialog.Builder(Main.this);
                builder.setMessage("Results failed to get a response. Please check your network connectivity and try again.");
                builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //OK button confirms message but does not perform any action
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    private void initActivity(){
        Button btnFood = findViewById(R.id.btnActivity);
        btnFood.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                /*
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
                 */
                connectActivityAPI();
            }
        });
    }

    //TODO: Fix call so application waits for results before moving to launch activity
    public void connectActivityAPI(){
        Repository repo = new Repository(getApplication());
        List<ActivityPreference> activityDesired = repo.getActivityDesired(true);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/maps/api/place/nearbysearch/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonNearbyPlacesAPI = retrofit.create(JsonNearbyPlacesAPI.class);
        Selector selector = new Selector();
            int activity = selector.activitySelection(activityDesired);
            Call <Results> call = jsonNearbyPlacesAPI.getActivityTourist();
            switch (activity){
                case 1: call = jsonNearbyPlacesAPI.getActivityAmusement();
                    break;
                case 2: call = jsonNearbyPlacesAPI.getActivityAquarium();
                    break;
                case 3: call = jsonNearbyPlacesAPI.getActivityArt();
                    break;
                case 4: call = jsonNearbyPlacesAPI.getActivityBowling();
                    break;
                case 5: call = jsonNearbyPlacesAPI.getActivityCasino();
                    break;
                case 6: call = jsonNearbyPlacesAPI.getActivityClothing();
                    break;
                case 7: call = jsonNearbyPlacesAPI.getActivityMovie();
                    break;
                case 8: call = jsonNearbyPlacesAPI.getActivityMuseum();
                    break;
                case 9: call = jsonNearbyPlacesAPI.getActivityClub();
                    break;
                case 10: call = jsonNearbyPlacesAPI.getActivityPark();
                    break;
                case 11: call = jsonNearbyPlacesAPI.getActivityMall();
                    break;
                case 12: call = jsonNearbyPlacesAPI.getActivitySpa();
                    break;
                case 13: call = jsonNearbyPlacesAPI.getActivityTourist();
                    break;
                    /*
                case 14: call = jsonNearbyPlacesAPI.getActivityZoo();
                    break;

                     */
                default: call = jsonNearbyPlacesAPI.getActivityTourist();
            }
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
                    int randNum = rand.nextInt(listSize);
                    for(int i=0; i<listSize; i++){
                         if(i == randNum){
                              NearbyPlace place = nearbyPlaces.get(i);
                              Selector selector = new Selector();
                              PlaceInfo placeInfo = selector.convertNearbyPlace(place);
                              placeList.add(placeInfo);
                             Intent intent = new Intent(Main.this, PlaceChoice.class);
                             startActivity(intent);
                         }
                    }
                }
                @Override
                public void onFailure(Call<Results> call, Throwable t) {
                    t.printStackTrace();
                    AlertDialog.Builder builder = new AlertDialog.Builder(Main.this);
                    builder.setMessage("Results failed to get a response. Please check your network connectivity and try again.");
                    builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //OK button confirms message but does not perform any action
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });
    }

    private void initReport(){
        Button btnReport = findViewById(R.id.btnReport);
        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main.this, Reports.class);
                startActivity(intent);
            }
        });
    }

    private void configureDatabase(){
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
        ActivityPreference amusement_park = new ActivityPreference(1, 1,"Amusement Park", true, 0);
        ActivityPreference aquarium = new ActivityPreference(1, 2,"Aquarium", true, 0);
        ActivityPreference art_gallery = new ActivityPreference(1, 3,"Art Gallery", true, 0);
        ActivityPreference bowling = new ActivityPreference(1, 4,"Bowling", true, 0);
        ActivityPreference casino = new ActivityPreference(1, 5,"Casino", true, 0);
        ActivityPreference clothing_store = new ActivityPreference(1, 6,"Clothing Store", true, 0);
        ActivityPreference movie_theater = new ActivityPreference(1, 7, "Movie Theater", true, 0);
        ActivityPreference museum = new ActivityPreference(1, 8,"Museum", true, 0);
        ActivityPreference night_club = new ActivityPreference(1, 9,"Night Club", true, 0);
        ActivityPreference park = new ActivityPreference(1, 10,"Park", true, 0);
        ActivityPreference shopping_mall = new ActivityPreference(1, 11,"Shopping Mall", true, 0);
        ActivityPreference spa = new ActivityPreference(1, 12,"Spa", true, 0);
        ActivityPreference tourist_attraction = new ActivityPreference(1, 13,"Tourist Attraction", true, 0);
        //ActivityPreference zoo = new ActivityPreference(1, 14,"Zoo", true, 0);

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

        repo.insertActivityPreference(amusement_park);
        repo.insertActivityPreference(aquarium);
        repo.insertActivityPreference(art_gallery);
        repo.insertActivityPreference(bowling);
        repo.insertActivityPreference(casino);
        repo.insertActivityPreference(clothing_store);
        repo.insertActivityPreference(movie_theater);
        repo.insertActivityPreference(museum);
        repo.insertActivityPreference(night_club);
        repo.insertActivityPreference(park);
        repo.insertActivityPreference(shopping_mall);
        repo.insertActivityPreference(spa);
        repo.insertActivityPreference(tourist_attraction);
        //repo.insertActivityPreference(zoo);
    }

    private void initData(){
        Button btnData = findViewById(R.id.btnData);
        btnData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Repository repo = new Repository(getApplication());
                //Create place info data
                PlaceInfo place1 = new PlaceInfo("ChIJK0BlbjAtO4gR-6KVDtss8Aw", "American Coney Island",
                        "114 West Lafayette Boulevard, Detroit", 42.3315160999999, -83.0486713);
                PlaceInfo place2 = new PlaceInfo("ChIJgVEtgSgtO4gR26c1oo9NbtY", "Andiamo Detroit Riverfront",
                        "400 Renaissance Center A-03, Detroit", 42.3282595, -83.0394295999999);
                PlaceInfo place3 = new PlaceInfo("ChIJJau6CLnSJIgRCEVudU_RXbE", "Museum of Contemporary Art Detroit",
                        "4454 Woodward Avenue, Detroit", 42.3535866999999, -83.0618497999999);
                PlaceInfo place4 = new PlaceInfo("ChIJ5zhKUb7SJIgR8KEEmAw0q3k", "Charles H. Wright Museum of African American History",
                        "315 East Warren Avenue, Detroit", 42.358777, -83.0609516);
                PlaceInfo place5 = new PlaceInfo("ChIJVd4y1bnSJIgRZzTakau0PS4", "Garden Bowl",
                        "4140 Woodward Avenue, Detroit", 42.351157, -83.060051);
                PlaceInfo place6 = new PlaceInfo("ChIJs4nAZVjNJIgRMESotwiZwec", "Woodbridge Pub",
                        "5169 Trumbull, Detroit", 42.3540171, -83.0801359);
                repo.insertPlaceInfo(place1);
                repo.insertPlaceInfo(place2);
                repo.insertPlaceInfo(place3);
                repo.insertPlaceInfo(place4);
                repo.insertPlaceInfo(place5);
                repo.insertPlaceInfo(place6);
                //Create history data
                History history1 = new History(1, "ChIJK0BlbjAtO4gR-6KVDtss8Aw", Date.from(Instant.now()));
                history1.setFood(true);
                History history2 = new History(1, "ChIJgVEtgSgtO4gR26c1oo9NbtY", Date.from(Instant.now()));
                history2.setFood(true);
                History history3 = new History(1, "ChIJJau6CLnSJIgRCEVudU_RXbE", Date.from(Instant.now()));
                history3.setFood(false);
                History history4 = new History(1, "ChIJK0BlbjAtO4gR-6KVDtss8Aw", Date.from(Instant.now()));
                history4.setFood(true);
                History history5 = new History(1, "ChIJ5zhKUb7SJIgR8KEEmAw0q3k", Date.from(Instant.now()));
                history5.setFood(false);
                History history6 = new History(1, "ChIJVd4y1bnSJIgRZzTakau0PS4", Date.from(Instant.now()));
                history6.setFood(false);
                History history7 = new History(1, "ChIJJau6CLnSJIgRCEVudU_RXbE", Date.from(Instant.now()));
                history7.setFood(false);
                History history8 = new History(1, "ChIJs4nAZVjNJIgRMESotwiZwec", Date.from(Instant.now()));
                history8.setFood(true);
                History history9 = new History(1, "ChIJK0BlbjAtO4gR-6KVDtss8Aw", Date.from(Instant.now()));
                history9.setFood(true);
                repo.insertHistory(history1);
                repo.insertHistory(history2);
                repo.insertHistory(history3);
                repo.insertHistory(history4);
                repo.insertHistory(history5);
                repo.insertHistory(history6);
                repo.insertHistory(history7);
                repo.insertHistory(history8);
                repo.insertHistory(history9);

            }
        });
    }

}