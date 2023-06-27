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

    public int activitySelection(List<ActivityPreference> activityPrefs){
        int activity = 0;
        List<ActivityPreference> activityPref = activityPrefs;
        int listSize = activityPref.size();
        Random rand = new Random();

        int randNum = rand.nextInt(listSize);

        for(int i=0; i<listSize; i++){
            if(i == randNum){
                activityPreference = activityPref.get(i);
                activity = activityPreference.getActivityId();
            }
        }
        return activity;
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
                    place.getGeometry().getLocation().getLatitude(),
                    place.getGeometry().getLocation().getLatitude());
            System.out.println(place.getName());
            System.out.println(place.getGeometry().getLocation().getLatitude());
            System.out.println(place.getGeometry().getLocation().getLongitude());
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

    public Call<Results> foodSelector(JsonNearbyPlacesAPI jsonNearbyPlacesAPI, int distance){
        Call<Results> call = jsonNearbyPlacesAPI.getFoodResults();
        switch (distance){
            case 5:
                call = jsonNearbyPlacesAPI.getFoodResults();
                break;
            case 10:
                call = jsonNearbyPlacesAPI.getFoodResults10();
                break;
            case 20:
                call = jsonNearbyPlacesAPI.getFoodResults20();
                break;
            case 30:
                call = jsonNearbyPlacesAPI.getFoodResults30();
                break;
            default:
                call = jsonNearbyPlacesAPI.getFoodResults();
        }
        return call;
    }

    public Call<Results> activitySelector(JsonNearbyPlacesAPI jsonNearbyPlacesAPI, int activity, int distance){
        Call <Results> call = jsonNearbyPlacesAPI.getActivityTourist();
        switch (activity){
            case 1:
                switch (distance){
                    case 5:
                        call = jsonNearbyPlacesAPI.getActivityAmusement();
                        break;
                    case 10:
                        call = jsonNearbyPlacesAPI.getActivityAmusement10();
                        break;
                    case 20:
                        call = jsonNearbyPlacesAPI.getActivityAmusement20();
                        break;
                    case 30:
                        call = jsonNearbyPlacesAPI.getActivityAmusement30();
                        break;
                    default:
                        call = jsonNearbyPlacesAPI.getActivityAmusement();
                }
                break;
            case 2:
                switch (distance){
                    case 5:
                        call = jsonNearbyPlacesAPI.getActivityAquarium();
                        break;
                    case 10:
                        call = jsonNearbyPlacesAPI.getActivityAquarium10();
                        break;
                    case 20:
                        call = jsonNearbyPlacesAPI.getActivityAquarium20();
                        break;
                    case 30:
                        call = jsonNearbyPlacesAPI.getActivityAquarium30();
                        break;
                    default:
                        call = jsonNearbyPlacesAPI.getActivityAquarium();
                }
                break;
            case 3:
                switch (distance){
                    case 5:
                        call = jsonNearbyPlacesAPI.getActivityArt();
                        break;
                    case 10:
                        call = jsonNearbyPlacesAPI.getActivityArt10();
                        break;
                    case 20:
                        call = jsonNearbyPlacesAPI.getActivityArt20();
                        break;
                    case 30:
                        call = jsonNearbyPlacesAPI.getActivityArt30();
                        break;
                    default:
                        call = jsonNearbyPlacesAPI.getActivityArt();
                }
                break;
            case 4:
                switch (distance){
                    case 5:
                        call = jsonNearbyPlacesAPI.getActivityBowling();
                        break;
                    case 10:
                        call = jsonNearbyPlacesAPI.getActivityBowling10();
                        break;
                    case 20:
                        call = jsonNearbyPlacesAPI.getActivityBowling20();
                        break;
                    case 30:
                        call = jsonNearbyPlacesAPI.getActivityBowling30();
                        break;
                    default:
                        call = jsonNearbyPlacesAPI.getActivityBowling();
                }
                break;
            case 5:
                switch (distance){
                    case 5:
                        call = jsonNearbyPlacesAPI.getActivityCasino();
                        break;
                    case 10:
                        call = jsonNearbyPlacesAPI.getActivityCasino10();
                        break;
                    case 20:
                        call = jsonNearbyPlacesAPI.getActivityCasino20();
                        break;
                    case 30:
                        call = jsonNearbyPlacesAPI.getActivityCasino30();
                        break;
                    default:
                        call = jsonNearbyPlacesAPI.getActivityCasino();
                }
                break;
            case 6:
                switch (distance){
                    case 5:
                        call = jsonNearbyPlacesAPI.getActivityClothing();
                        break;
                    case 10:
                        call = jsonNearbyPlacesAPI.getActivityClothing10();
                        break;
                    case 20:
                        call = jsonNearbyPlacesAPI.getActivityClothing20();
                        break;
                    case 30:
                        call = jsonNearbyPlacesAPI.getActivityClothing30();
                        break;
                    default:
                        call = jsonNearbyPlacesAPI.getActivityClothing();
                }
                break;
            case 7:
                switch (distance){
                    case 5:
                        call = jsonNearbyPlacesAPI.getActivityMovie();
                        break;
                    case 10:
                        call = jsonNearbyPlacesAPI.getActivityMovie10();
                        break;
                    case 20:
                        call = jsonNearbyPlacesAPI.getActivityMovie20();
                        break;
                    case 30:
                        call = jsonNearbyPlacesAPI.getActivityMovie30();
                        break;
                    default:
                        call = jsonNearbyPlacesAPI.getActivityMovie();
                }
                break;
            case 8:
                switch (distance){
                    case 5:
                        call = jsonNearbyPlacesAPI.getActivityMuseum();
                        break;
                    case 10:
                        call = jsonNearbyPlacesAPI.getActivityMuseum10();
                        break;
                    case 20:
                        call = jsonNearbyPlacesAPI.getActivityMuseum20();
                        break;
                    case 30:
                        call = jsonNearbyPlacesAPI.getActivityMuseum30();
                        break;
                    default:
                        call = jsonNearbyPlacesAPI.getActivityMuseum();
                }
                break;
            case 9:
                switch (distance){
                    case 5:
                        call = jsonNearbyPlacesAPI.getActivityClub();
                        break;
                    case 10:
                        call = jsonNearbyPlacesAPI.getActivityClub10();
                        break;
                    case 20:
                        call = jsonNearbyPlacesAPI.getActivityClub20();
                        break;
                    case 30:
                        call = jsonNearbyPlacesAPI.getActivityClub30();
                        break;
                    default:
                        call = jsonNearbyPlacesAPI.getActivityClub();
                }
                break;
            case 10:
                switch (distance){
                    case 5:
                        call = jsonNearbyPlacesAPI.getActivityPark();
                        break;
                    case 10:
                        call = jsonNearbyPlacesAPI.getActivityPark10();
                        break;
                    case 20:
                        call = jsonNearbyPlacesAPI.getActivityPark20();
                        break;
                    case 30:
                        call = jsonNearbyPlacesAPI.getActivityPark30();
                        break;
                    default:
                        call = jsonNearbyPlacesAPI.getActivityPark();
                }
                break;
            case 11:
                switch (distance){
                    case 5:
                        call = jsonNearbyPlacesAPI.getActivityMall();
                        break;
                    case 10:
                        call = jsonNearbyPlacesAPI.getActivityMall10();
                        break;
                    case 20:
                        call = jsonNearbyPlacesAPI.getActivityMall20();
                        break;
                    case 30:
                        call = jsonNearbyPlacesAPI.getActivityMall30();
                        break;
                    default:
                        call = jsonNearbyPlacesAPI.getActivityMall();
                }
                break;
            case 12:
                switch (distance){
                    case 5:
                        call = jsonNearbyPlacesAPI.getActivitySpa();
                        break;
                    case 10:
                        call = jsonNearbyPlacesAPI.getActivitySpa10();
                        break;
                    case 20:
                        call = jsonNearbyPlacesAPI.getActivitySpa20();
                        break;
                    case 30:
                        call = jsonNearbyPlacesAPI.getActivitySpa30();
                        break;
                    default:
                        call = jsonNearbyPlacesAPI.getActivitySpa();
                }
                break;
            case 13:
                switch (distance){
                    case 5:
                        call = jsonNearbyPlacesAPI.getActivityTourist();
                        break;
                    case 10:
                        call = jsonNearbyPlacesAPI.getActivityTourist10();
                        break;
                    case 20:
                        call = jsonNearbyPlacesAPI.getActivityTourist20();
                        break;
                    case 30:
                        call = jsonNearbyPlacesAPI.getActivityTourist30();
                        break;
                    default:
                        call = jsonNearbyPlacesAPI.getActivityTourist();
                }
                break;
                    /*
                case 14:
                    switch (distance){
                        case 5:
                            call = jsonNearbyPlacesAPI.getActivityZoo();
                            break;
                        case 10:
                            call = jsonNearbyPlacesAPI.getActivityZoo10();
                            break;
                        case 20:
                            call = jsonNearbyPlacesAPI.getActivityZoo20();
                            break;
                        case 30:
                            call = jsonNearbyPlacesAPI.getActivityZoo30();
                            break;
                        default:
                            call = jsonNearbyPlacesAPI.getActivityZoo();
                    }
                    break;
                     */
            default: call = jsonNearbyPlacesAPI.getActivityTourist();
        }
        return call;
    }


}