package com.example.uptoyou.Datebase;

import com.example.uptoyou.Entity.ActivityPreference;
import com.example.uptoyou.Entity.FoodPreference;
import com.example.uptoyou.Entity.PlaceInfo;
import com.example.uptoyou.Networking.JsonNearbyPlacesAPI;
import com.example.uptoyou.Networking.Serialization.NearbyPlace;
import com.example.uptoyou.Networking.Serialization.Results;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.Place;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Selector {
    private List<FoodPreference> foodSelectList;
    private List<ActivityPreference> activitySelectList;
    private FoodPreference foodPreference;
    private ActivityPreference activityPreference;
    private JsonNearbyPlacesAPI jsonNearbyPlacesAPI;

    public String foodSelection(List<FoodPreference> foodPrefs){
        String search = "";
        List<FoodPreference> foodPref = foodPrefs;
        int listSize = foodPref.size();
        Random rand = new Random();

        int randNum = rand.nextInt(listSize);

        for(int i=0; i<listSize; i++){
            if(i == randNum){
                foodPreference = foodPref.get(i);
                search = foodPreference.getFoodName();
            }
        }
        return search;
    }

    public String activitySelection(List<ActivityPreference> activityPrefs){
        String search = "";
        List<ActivityPreference> activityPref = activityPrefs;
        int listSize = activityPref.size();
        Random rand = new Random();

        int randNum = rand.nextInt(listSize);

        for(int i=0; i<listSize; i++){
            if(i == randNum){
                activityPreference = activityPref.get(i);
                search = activityPreference.getActivityName();
            }
        }
        return search;
    }

    public String randomSelector(List<FoodPreference> food, List<ActivityPreference> activity){
        String search = "";


        return search;
    }

    public AutocompletePrediction selectPrediction(List<AutocompletePrediction> autoPredictions) {
        int listSize = autoPredictions.size();

        List<Integer> randList = new ArrayList<>();
        for(int i=0; i<listSize; i++){
            randList.add(i);
        }

        int choice = getRandomElement(randList);

        AutocompletePrediction predictionChoice = autoPredictions.get(choice);
        return predictionChoice;

    }

    public int getRandomElement(List<Integer> list){
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

    private NearbyPlace pickPlace(List<Results> results){
        NearbyPlace nearbyPlace = new NearbyPlace();
        int listSize = results.size();
        Random rand = new Random();
        for (int i=0; i<listSize; i++){

        }
        return nearbyPlace;
    }

    private String searchBuilder(String search){
        String url = "";

        return url;
    }

    public void connectFoodAPI(String search){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/maps/api/place/nearbysearch/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonNearbyPlacesAPI = retrofit.create(JsonNearbyPlacesAPI.class);
        Call<Results> call = jsonNearbyPlacesAPI.getFoodResults(search);

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
                        //TODO: Implement place converter to save place data
                    }
                }
            }
            @Override
            public void onFailure(Call<Results> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    public void connectActivityAPI(String search){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/maps/api/place/nearbysearch/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonNearbyPlacesAPI = retrofit.create(JsonNearbyPlacesAPI.class);
        Call<Results> call = jsonNearbyPlacesAPI.getActivityResults(search);

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
                        //TODO: Implement place converter to save place data
                    }
                }
            }
            @Override
            public void onFailure(Call<Results> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    public List<PlaceInfo> convertNearbyPlace(List<NearbyPlace> placeList){
        List<PlaceInfo> placeInfoList = null;
        String type;
        for(NearbyPlace nearbyPlace : placeList){
            PlaceInfo placeInfo = new PlaceInfo(nearbyPlace.getPlaceId(),
                    nearbyPlace.getName(),
                    nearbyPlace.getVicinity(),
                    nearbyPlace.getGeometry().getLocation().getLatitude(),
                    nearbyPlace.getGeometry().getLocation().getLongitude());
        }
        return placeInfoList;
    }


}