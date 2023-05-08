package com.example.uptoyou.UI;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
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
import com.example.uptoyou.Entity.PlaceInfo;
import com.example.uptoyou.Entity.Preference;
import com.example.uptoyou.R;
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
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.Priority;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.OnTokenCanceledListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.PhotoMetadata;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.api.net.FetchPhotoRequest;
import com.google.android.libraries.places.api.net.FetchPhotoResponse;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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

        getLocationPermission();
        Bundle b = getIntent().getExtras();
        int value; // or other values
        if(b != null){
            value = b.getInt("key");
            identifySelector(value);
            if(!placeOptions.isEmpty()){
                //Populate recycler view with place options
            }
        } else {
            finish();
        }
    }

    private void identifySelector(int id){
        Repository repo = new Repository(getApplication());
        String search = "";

        switch (id){
            case 1:
                choiceIndicatorId = 1;
                foodDesired = repo.getFoodDesired(true);
                for(int i=0; i<2; i++){
                    search = select.foodSelection(foodDesired);
                    placeSearchResult(search, "restaurant", 1);
                }
                //Add Place Detail search logic for place selection
                break;
            case 2:
                choiceIndicatorId = 2;
                activityDesired = repo.getActivityDesired(true);
                search = select.activitySelector(activityDesired);
                //Add Place Detail search logic for place selection
                break;
            case 3:
                choiceIndicatorId = 3;
                foodDesired = repo.getFoodDesired(true);
                activityDesired = repo.getActivityDesired(true);
                search = select.randomSelector(foodDesired, activityDesired);
                //Add Place Detail search logic for place selection
                break;
            default: Log.e("identifySelector", "no case");
        }
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
        getLocationPermission();

        //getDeviceLocation();

        currentLatLng = new LatLng(42.33461099979685, -83.0465496496764 );

        RectangularBounds bounds = RectangularBounds.newInstance(
                new LatLng((currentLatLng.latitude - distance), (currentLatLng.longitude - distance)),
                new LatLng((currentLatLng.latitude + distance), (currentLatLng.longitude + distance)));

        FindAutocompletePredictionsRequest request = FindAutocompletePredictionsRequest.builder()
                .setLocationRestriction(bounds)
                .setOrigin(currentLatLng)
                .setTypesFilter(Arrays.asList(type))
                .setSessionToken(token)
                .setQuery(query)
                .build();
        Log.i(TAG, request.toString());
        placesClient.findAutocompletePredictions(request).addOnSuccessListener((response) -> {
            Log.i(TAG, "AutocompletePredictionRequest: Successful");
            for (AutocompletePrediction prediction : response.getAutocompletePredictions()) {
                Log.i(TAG, prediction.getPlaceId());
                Log.i(TAG, prediction.getPrimaryText(null).toString());
                autoPredictions.add(prediction);
            }
            AutocompletePrediction selectedPrediction = select.selectPrediction(autoPredictions);
            convertPlace(selectedPrediction.getPlaceId(),type);
        }).addOnFailureListener((exception) -> {
            if (exception instanceof ApiException) {
                ApiException apiException = (ApiException) exception;
                Log.e(TAG, "Place not found: " + apiException.getStatusCode());
                redoPlaceSearch();
            }
        });
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
            PlaceInfo placeInfo = new PlaceInfo(place.getName(), place.getAddress(), place.getPhoneNumber(),
                    place.getWebsiteUri().toString(), place.getLatLng().latitude, place.getLatLng().longitude);
            placeInfo.setPlaceId(Integer.parseInt(place.getId()));
            placeInfo.setType(type);
            placeOptions.add(placeInfo);
        }).addOnFailureListener((exception) -> {
            if(exception instanceof ApiException){
                final ApiException apiException = (ApiException) exception;
                Log.e(TAG, "Place not found: " + exception.getMessage());
                final int statusCode = apiException.getStatusCode();
            }
        });
    }
/*
    private void getDeviceLocation(){
        Log.d(TAG, "getDeviceLocation: Getting the devices current location.");
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        try {
            if (mLocationPermissionsGranted) {
                final Task location1 = mFusedLocationProviderClient.getLastLocation();
                if(location1 == null)
                    Log.d(TAG, "Null");
                location1.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "onComplete: Current location found.");
                            Location currentLocation = (Location) task.getResult();
                            LatLng currentLatLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
                            Log.d(TAG, "Lat: " + currentLocation.getLatitude() + ", Lng: " + currentLocation.getLongitude());
                        }
                        else{
                            Log.d(TAG, "onComplete: Current location is null");
                            Toast.makeText(PlaceChoice.this, "Unable to find current location.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        } catch (SecurityException se){
            Log.e(TAG, "getDeviceLocation: SecurityException: " + se.getMessage());
        }
        catch (Exception e){
            Log.e(TAG, "getDeviceLocation: Exception: " + e.getMessage());
        }
    }

    private void getDeviceLocation_bad(){
        Log.d(TAG, "getDeviceLocation: Getting the devices current location.");
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        try{
            if(mLocationPermissionsGranted){

                Task<Location> locationTask = mFusedLocationProviderClient.getLastLocation();
//TODO: Figure out why onSuccess AND onFailure are never called and fix so that app can get devices current location
                locationTask.addOnSuccessListener(location -> {
                    if(location != null){
                        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                        currentLatLng = latLng;
                        Log.d(TAG, "Lat: " + location.getLatitude() + ", Lng: " + location.getLongitude());
                        Log.d(TAG, "onSuccess: Current location found.");
                    } else {
                        Log.d(TAG, "onSuccess: Location is null");
                    }
                });
                locationTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onFailure: " + e.getMessage());
                        Toast.makeText(PlaceChoice.this, "Unable to find current location.", Toast.LENGTH_LONG).show();
                    }
                });

            }
        }
        catch (SecurityException se){
            Log.e(TAG, "getDeviceLocation: Security Exception " + se.getMessage());
        }
        catch (Exception e){
            Log.e(TAG, "getDeviceLocation: Security Exception " + e.getMessage());
        }

    }

 */
    private void getLocationPermission(){
        Log.d(TAG, "getLocationPermission: Getting location permissions");
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        if(ContextCompat.checkSelfPermission(this.getApplicationContext(), FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(), COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED){

                mLocationPermissionsGranted = true;

                //setupLocationUpdates();
                //getCurrentLocation();
            }
            else {
                ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
            }
        }
        else {
            ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {
        CurrentLocationRequest currentLocationRequest = new CurrentLocationRequest.Builder()
                .setGranularity(Granularity.GRANULARITY_FINE)
                .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
                .setDurationMillis(10000)
                .setMaxUpdateAgeMillis(0)
                .build();

        CancellationTokenSource cancellationTokenSource = new CancellationTokenSource();

        mFusedLocationProviderClient.getCurrentLocation(currentLocationRequest, cancellationTokenSource.getToken()).addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                if(task.isSuccessful()){
                    Location location = task.getResult();
                    Log.d(TAG, "onComplete: " + location);
                    currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                } else {
                    task.getException().printStackTrace();
                }
            }
        });

        /*
        mFusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                if(task.isSuccessful()){
                    Location location = task.getResult();
                    Log.d(TAG, "onComplete: " + location);
                } else {
                    task.getException().printStackTrace();
                }
            }
        });

         */
    }

    @SuppressLint("MissingPermission")
    private void setupLocationUpdates() {
        LocationRequest locationRequest = new LocationRequest.Builder(10000)
                .setGranularity(Granularity.GRANULARITY_FINE)
                .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
                .setDurationMillis(10000)
                .setMaxUpdateAgeMillis(100)
                .build();

        LocationSettingsRequest locationSettingsRequest = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest)
                .build();

        SettingsClient settingsClient = LocationServices.getSettingsClient(this);
        settingsClient.checkLocationSettings(locationSettingsRequest).addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {
                if(task.isSuccessful()){
                    mFusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
                }else if(task.getException() instanceof ResolvableApiException){
                    try{
                        ResolvableApiException resolvableApiException = (ResolvableApiException) task.getException();
                        resolvableApiException.startResolutionForResult(PlaceChoice.this, REQUEST_CHECK_SETTINGS);
                    }
                    catch (IntentSender.SendIntentException e){
                        e.printStackTrace();
                    }
                }else{
                    //Do somethings else
                }
            }
        });
    }

    LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(@NonNull LocationResult locationResult) {
            super.onLocationResult(locationResult);
            Log.d(TAG, "onLocationResult: " + locationResult);
        }
    };
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