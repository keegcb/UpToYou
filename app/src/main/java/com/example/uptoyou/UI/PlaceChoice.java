package com.example.uptoyou.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.example.uptoyou.Datebase.Repository;
import com.example.uptoyou.Datebase.Selector;
import com.example.uptoyou.Entity.ActivityPreference;
import com.example.uptoyou.Entity.FoodPreference;
import com.example.uptoyou.Entity.History;
import com.example.uptoyou.Entity.PlaceInfo;
import com.example.uptoyou.Entity.Preference;
import com.example.uptoyou.R;
import com.example.uptoyou.UI.Adapter.PlaceChoiceAdapter;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.CurrentLocationRequest;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.Granularity;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.Priority;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.PhotoMetadata;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.api.net.FetchPhotoRequest;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.util.Arrays;
import java.util.List;

public class PlaceChoice extends AppCompatActivity {
    private List<FoodPreference> foodDesired;
    private List<ActivityPreference> activityDesired;
    private List<PlaceInfo> placeOptions;
    private static int AUTOCOMPLETE_REQUEST_CODE= 1;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private LocationManager locationManager;
    private boolean mLocationPermissionsGranted = false;
    private static final String TAG = "PlaceChoiceActivity";
    private static int choiceIndicatorId;
    private LatLng currentLatLng;
    private PlacesClient placesClient;
    private List<AutocompletePrediction> autoPredictions;
    private static final int REQUEST_CHECK_SETTINGS = 10001;

    //TODO: REMOVE HARD CODED API KEY VALUE
    private static String apiKey = "AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw";
    private GoogleMap mMap;

    private Selector select = new Selector();

    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 2345;
    private LocationRequest locationRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_choice);
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        Places.initialize(getApplicationContext(), apiKey);
        placesClient = Places.createClient(this);

        List<PlaceInfo> placeList = Main.placeList;
        Repository repo = new Repository(getApplication());
        for(PlaceInfo place : placeList){
            repo.insertPlaceInfo(place);
        }
        RecyclerView mRecyclerView = findViewById(R.id.recyclerView);
        final PlaceChoiceAdapter adapter = new PlaceChoiceAdapter(this, placeList);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    //TODO: Is this the proper way to set up an autocomplete PlacesClient object before using it?

    //LatLng Distances in Double format
    /*
    Double 0.07237 = 5 miles
        7.071 miles to corner of Square Bounds
    Double 0.14474 = 10 miles
        14.143 miles to corner of Square Bounds
    Double 0.28949 = 20 miles
        28.287 miles to corner of Square Bounds
    Double 0.3618 = 25 miles
        35.35 miles to corner of Square Bounds
    Double 0.7237 = 50 miles
        70.71 miles to corner of Square Bounds
     */
    private void placeSearchResult(String query, String type, int id){
        AutocompleteSessionToken token = AutocompleteSessionToken.newInstance();
        PlaceInfo placeResult;
        Double distance;
        Repository repo = new Repository(getApplication());
        Preference mPref = repo.getPreferenceById(1);
        int prefDistance = mPref.getDistance();
        switch (prefDistance){
            case 5: distance = 0.036185;
                break;
            case 10: distance = 0.07237;
                break;
            case 20: distance = 0.14474;
                break;
            case 50: distance = 0.3618;
                break;
            default: distance = 0.1809;
        }

    }


    private void convertPlace(String placeId, String type) {
        List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS,
                Place.Field.PHONE_NUMBER, Place.Field.WEBSITE_URI,Place.Field.LAT_LNG, Place.Field.TYPES, Place.Field.PHOTO_METADATAS);
        PlacesClient placesClient = Places.createClient(this);
        FetchPlaceRequest placeRequest = FetchPlaceRequest.newInstance(placeId, fields);
        placesClient.fetchPlace(placeRequest).addOnSuccessListener((response) -> {
            Place place = response.getPlace();
            Log.i(TAG, "Place found: " + place.getName());

            List<PhotoMetadata> metadata = place.getPhotoMetadatas();
            if(metadata == null || metadata.isEmpty()){
                Log.w(TAG, "No photo metadata.");
                return;
            }
            PhotoMetadata photoMetadata = metadata.get(0);
            String attributions = photoMetadata.getAttributions();
            FetchPhotoRequest photoRequest = FetchPhotoRequest.builder(photoMetadata).setMaxHeight(200).setMaxWidth(350).build();
            placesClient.fetchPhoto(photoRequest).addOnSuccessListener(fetchPhotoResponse -> {
                Bitmap bitmap = fetchPhotoResponse.getBitmap();
                //imageView.setImageBitmap(bitmap);

                //TODO: Save image in PlaceInfo Object using... BLOB?

            }).addOnFailureListener((exception) -> {
                if(exception instanceof ApiException) {
                    final ApiException apiException = (ApiException) exception;
                    Log.e(TAG, "Place not found: " + exception.getMessage());
                    final int statusCode = apiException.getStatusCode();
                }
            });

            //convert place details into place info object
            PlaceInfo placeInfo = new PlaceInfo(place.getId(), place.getName(), place.getAddress(), place.getLatLng().latitude, place.getLatLng().longitude);
            placeOptions.add(placeInfo);
        }).addOnFailureListener((exception) -> {
            if(exception instanceof ApiException){
                final ApiException apiException = (ApiException) exception;
                Log.e(TAG, "Place not found: " + exception.getMessage());
                final int statusCode = apiException.getStatusCode();
            }
        });
    }

    private void redoPlaceSearch(){
        Repository repo = new Repository(getApplication());
        String search = "";
        if (choiceIndicatorId == 1){
            foodDesired = repo.getFoodDesired(true);
            search = select.foodSelection(foodDesired);
            placeSearchResult(search, "restaurant", 1);
        }
        else if (choiceIndicatorId == 2){

        }
        else if (choiceIndicatorId == 3){

        }
        else {
            Log.d(TAG, "redoPlaceSearch: Unknown search type on reroll");
        }
    }


}