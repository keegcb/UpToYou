package com.example.uptoyou.Datebase;

import com.example.uptoyou.Entity.ActivityPreference;
import com.example.uptoyou.Entity.FoodPreference;
import com.example.uptoyou.Entity.PlaceInfo;
import com.example.uptoyou.Entity.Type;
import com.example.uptoyou.Networking.JsonNearbyPlacesAPI;
import com.example.uptoyou.Networking.Serialization.NearbyPlace;
import com.example.uptoyou.Networking.Serialization.Results;
import com.example.uptoyou.R;
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

    //TODO: Fix Lat & Lng values being 0 in DB
    public PlaceInfo convertNearbyPlace(NearbyPlace place){
            PlaceInfo placeInfo = new PlaceInfo(place.getPlaceId(),
                    place.getName(),
                    place.getVicinity(),
                    place.getGeometry().getLocation().getLat(),
                    place.getGeometry().getLocation().getLng);
        return placeInfo;
    }

    public List<Type> convertPlaceType(NearbyPlace nearbyPlace){
        List<Type> typeList = null;
        for(int i=0; i<nearbyPlace.getTypes().size(); i++){
                Type placeType = new Type(nearbyPlace.getTypes().get(i), nearbyPlace.getPlaceId());
                typeList.add(placeType);
        }
        return typeList;
    }


}