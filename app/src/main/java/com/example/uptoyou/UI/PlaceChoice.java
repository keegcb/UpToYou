package com.example.uptoyou.UI;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
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
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
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
    private boolean mLocationPermissionsGranted = false;
    private static final String TAG = "PlaceChoiceActivity";
    private static int choiceIndicatorId;
    private LatLng currentLatLng;
    private PlacesClient client;
    private List<AutocompletePrediction> autoPredictions;
    private List<String> placeIds;

    private Selector select = new Selector();

    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 2345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_choice);
        initPlacesClient();
        initAutoPredictionsList();
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
        String search = "";

        List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS,
                Place.Field.PHONE_NUMBER, Place.Field.WEBSITE_URI,Place.Field.LAT_LNG, Place.Field.TYPES);

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
                activityDesired = repo.getmActivityDesired(true);
                search = select.activitySelector(activityDesired);
                //Add Place Detail search logic for place selection
                break;
            case 3:
                choiceIndicatorId = 3;
                foodDesired = repo.getFoodDesired(true);
                activityDesired = repo.getmActivityDesired(true);
                search = select.randomSelector(foodDesired, activityDesired);
                //Add Place Detail search logic for place selection
                break;
            default: Log.e("identifySelector", "no case");
        }
        if(!placeIds.isEmpty()){
            for(int i=0; i<placeIds.size(); i++){
                convertPlace(placeIds.get(i));
            }
        }
    }

    //TODO: Is this the proper way to set up an autocomplete PlacesClient object before using it?
    private void initPlacesClient(){
        client = new PlacesClient() {
            @NonNull
            @Override
            public Task<FetchPhotoResponse> fetchPhoto(@NonNull FetchPhotoRequest fetchPhotoRequest) {
                return null;
            }

            @NonNull
            @Override
            public Task<FetchPlaceResponse> fetchPlace(@NonNull FetchPlaceRequest fetchPlaceRequest) {
                return null;
            }

            @NonNull
            @Override
            public Task<FindAutocompletePredictionsResponse> findAutocompletePredictions(@NonNull FindAutocompletePredictionsRequest findAutocompletePredictionsRequest) {
                return null;
            }

            @NonNull
            @Override
            public Task<FindCurrentPlaceResponse> findCurrentPlace(@NonNull FindCurrentPlaceRequest findCurrentPlaceRequest) {
                return null;
            }
        };
    }

    private void initAutoPredictionsList(){
        autoPredictions = new List<AutocompletePrediction>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(@Nullable Object o) {
                return false;
            }

            @NonNull
            @Override
            public Iterator<AutocompletePrediction> iterator() {
                return null;
            }

            @NonNull
            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @NonNull
            @Override
            public <T> T[] toArray(@NonNull T[] ts) {
                return null;
            }

            @Override
            public boolean add(AutocompletePrediction autocompletePrediction) {
                return false;
            }

            @Override
            public boolean remove(@Nullable Object o) {
                return false;
            }

            @Override
            public boolean containsAll(@NonNull Collection<?> collection) {
                return false;
            }

            @Override
            public boolean addAll(@NonNull Collection<? extends AutocompletePrediction> collection) {
                return false;
            }

            @Override
            public boolean addAll(int i, @NonNull Collection<? extends AutocompletePrediction> collection) {
                return false;
            }

            @Override
            public boolean removeAll(@NonNull Collection<?> collection) {
                return false;
            }

            @Override
            public boolean retainAll(@NonNull Collection<?> collection) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public AutocompletePrediction get(int i) {
                return null;
            }

            @Override
            public AutocompletePrediction set(int i, AutocompletePrediction autocompletePrediction) {
                return null;
            }

            @Override
            public void add(int i, AutocompletePrediction autocompletePrediction) {

            }

            @Override
            public AutocompletePrediction remove(int i) {
                return null;
            }

            @Override
            public int indexOf(@Nullable Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(@Nullable Object o) {
                return 0;
            }

            @NonNull
            @Override
            public ListIterator<AutocompletePrediction> listIterator() {
                return null;
            }

            @NonNull
            @Override
            public ListIterator<AutocompletePrediction> listIterator(int i) {
                return null;
            }

            @NonNull
            @Override
            public List<AutocompletePrediction> subList(int i, int i1) {
                return null;
            }
        };
    }

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
        getDeviceLocation();
        RectangularBounds bounds = RectangularBounds.newInstance(
                new LatLng((currentLatLng.latitude + distance), (currentLatLng.longitude + distance)),
                new LatLng((currentLatLng.latitude - distance), (currentLatLng.longitude - distance)));
        FindAutocompletePredictionsRequest request = FindAutocompletePredictionsRequest.builder()
                .setLocationRestriction(bounds)
                .setOrigin(currentLatLng)
                .setTypesFilter(Arrays.asList(type))
                .setSessionToken(token)
                .setQuery(query)
                .build();
        client.findAutocompletePredictions(request).addOnSuccessListener((response) -> {
            for (AutocompletePrediction prediction : response.getAutocompletePredictions()) {
                Log.i(TAG, prediction.getPlaceId());
                Log.i(TAG, prediction.getPrimaryText(null).toString());
                autoPredictions.add(prediction);
            }
            AutocompletePrediction selectedPrediction = select.selectPrediction(autoPredictions);
            placeIds.add(selectedPrediction.getPlaceId());
        }).addOnFailureListener((exception) -> {
            if (exception instanceof ApiException) {
                ApiException apiException = (ApiException) exception;
                Log.e(TAG, "Place not found: " + apiException.getStatusCode());
                redoPlaceSearch();
            }
        });
    }

    private void convertPlace(String placeId) {
        List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS,
                Place.Field.PHONE_NUMBER, Place.Field.WEBSITE_URI,Place.Field.LAT_LNG, Place.Field.TYPES);
        FetchPlaceRequest placeRequest = FetchPlaceRequest.newInstance(placeId, fields);
        client.fetchPlace(placeRequest).addOnSuccessListener((response) -> {
            Place place = response.getPlace();
            Log.i(TAG, "Place found: " + place.getName());
            //convert place details into place info object
            PlaceInfo placeInfo = new PlaceInfo(place.getName(), place.getAddress(), place.getPhoneNumber(),
                    place.getWebsiteUri().toString(), place.getLatLng().latitude, place.getLatLng().longitude);
            placeInfo.setPlaceId(Integer.parseInt(place.getId()));
            placeOptions.add(placeInfo);
        }).addOnFailureListener((exception) -> {
            if(exception instanceof ApiException){
                final ApiException apiException = (ApiException) exception;
                Log.e(TAG, "Place not found: " + exception.getMessage());
                final int statusCode = apiException.getStatusCode();
            }
        });
    }

    private void getDeviceLocation(){
        Log.d(TAG, "getDeviceLocation: Getting the devices current location.");
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        try {
            if (mLocationPermissionsGranted) {
                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "onComplete: Current location found.");
                            Location currentLocation = (Location) task.getResult();
                            currentLatLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
                            Log.d(TAG, "Lat: " + currentLocation.getLatitude() + ", Lng: " + currentLocation.getLongitude());
                        }
                        else{
                            Log.d(TAG, "onComplete: Current location is null");
                            Toast.makeText(PlaceChoice.this, "Unable to find current location.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        } catch (SecurityException e){
            Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage());
        }
    }

    private void getLocationPermission(){
        Log.d(TAG, "getLocationPermission: Getting location permissions");
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        if(ContextCompat.checkSelfPermission(this.getApplicationContext(), FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(), COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                mLocationPermissionsGranted = true;
            }
            else {
                ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
            }
        }
        else {
            ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
        }
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