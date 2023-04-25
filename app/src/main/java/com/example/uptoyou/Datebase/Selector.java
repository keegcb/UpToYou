package com.example.uptoyou.Datebase;

import com.example.uptoyou.Entity.ActivityPreference;
import com.example.uptoyou.Entity.FoodPreference;
import com.example.uptoyou.UI.Main;
import com.google.android.libraries.places.api.model.AutocompletePrediction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Selector {
    private List<FoodPreference> foodSelectList;
    private List<ActivityPreference> activitySelectList;

    public String foodSelection(List<FoodPreference> foodPrefs){
        String search = "";

        /*
        selection should be prioritized

        selection should be weighted

        selection should be semi-random proceeding the first two requirements

        selection should weight items based on previous history
	        history needs to be queried for each selection?
         */

        return search;
    }

    public String activitySelector(List<ActivityPreference> activityPrefs){
        String search = "";


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
}