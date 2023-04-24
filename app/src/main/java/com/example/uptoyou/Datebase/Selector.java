package com.example.uptoyou.Datebase;

import com.example.uptoyou.Entity.ActivityPreference;
import com.example.uptoyou.Entity.FoodPreference;
import com.example.uptoyou.UI.Main;

import java.util.List;

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

}