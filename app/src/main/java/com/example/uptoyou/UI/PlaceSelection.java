package com.example.uptoyou.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
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
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
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
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PlaceSelection extends AppCompatActivity implements OnMapReadyCallback{

    private List<PlaceInfo> placeOptions = new ArrayList<>();
    private static LatLng currentLatLng;
    private Location currentLocation;
    private PlacesClient placesClient;
    private static int choiceIndicatorId;
    private List<FoodPreference> foodDesired = new ArrayList<>();
    private List<ActivityPreference> activityDesired= new ArrayList<>();
    private List<AutocompletePrediction> autoPredictions = new ArrayList<>();
    private Selector select = new Selector();
    private int selectionType = 0;
    private static String apiKey = "AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw";

    private static final String TAG = "MapActivity";

    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final float DEFAULT_ZOOM = 15f;

    //variables
    private boolean mLocationPermissionsGranted = false;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_selection);


        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        Places.initialize(getApplicationContext(), apiKey);
        placesClient = Places.createClient(this);

        Bundle b = getIntent().getExtras();
        int value = 0; // or other values
        if(b != null){
            value = b.getInt("key");
            selectionType = value;
        }

        getLocationPermission();


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(this, "Map is Ready", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onMapReady: map is ready");
        mMap = googleMap;

        if(mLocationPermissionsGranted){
            getDeviceLocation();
        }
    }

    private void connectHTTP(String path){
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url(path)
                //"https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522%2C151.1957362&radius=1500&type=restaurant&keyword=cruise&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw"
                .method("GET", body)
                .build();
        try {
            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
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
        //currentLatLng = new LatLng(42.33461099979685, -83.0465496496764 );

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

    private void getDeviceLocation(){
        Log.d(TAG, "getDeviceLocation: Getting the devices current location.");
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        try {
            if (mLocationPermissionsGranted) {
                @SuppressLint("MissingPermission") final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "onComplete: Current location found.");
                            currentLocation = (Location) task.getResult();
                            LatLng tempLatLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
                            currentLatLng = tempLatLng;
                            Log.d(TAG, "Lat: " + currentLocation.getLatitude() + ", Lng: " + currentLocation.getLongitude());
                            moveCamera(tempLatLng, DEFAULT_ZOOM);
                            identifySelector(selectionType);
                        }
                        else{
                            Log.d(TAG, "onComplete: Current location is null");
                            Toast.makeText(PlaceSelection.this, "Unable to find current location.", Toast.LENGTH_LONG).show();
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

    private void moveCamera(LatLng latLng, float zoom){
        Log.d(TAG, "moveCamera: Moving camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }

    private void initMap(){
        Log.d(TAG, "initMap: Initializing map");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(PlaceSelection.this);
    }

    private void getLocationPermission(){
        Log.d(TAG, "getLocationPermission: Getting location permissions");
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        if(ContextCompat.checkSelfPermission(this.getApplicationContext(), FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(), COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                mLocationPermissionsGranted = true;
                initMap();
            }
            else {
                ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
            }
        }
        else {
            ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionResult: Verifying needed location permissions are in place");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mLocationPermissionsGranted = false;
        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionsGranted = false;
                            Log.d(TAG, "onRequestPermissionResult: Location permissions failed.");
                            return;
                        }
                    }
                    mLocationPermissionsGranted = true;
                    Log.d(TAG, "onRequestPermissionResult: Location permissions granted.");
                    //initialize map
                    initMap();
                }
            }
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
            PlaceInfo placeInfo = new PlaceInfo(place.getName(), place.getAddress(), place.getPhoneNumber(),
                    Objects.toString(place.getWebsiteUri(), ""), place.getLatLng().latitude, place.getLatLng().longitude);
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