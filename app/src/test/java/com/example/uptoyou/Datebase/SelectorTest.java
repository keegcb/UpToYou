package com.example.uptoyou.Datebase;

import static org.junit.Assert.*;

import com.example.uptoyou.Entity.ActivityPreference;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SelectorTest {

    @Test
    public void activitySelection() {
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

        List<ActivityPreference> activityList = new ArrayList<>();
        activityList.add(amusement_park);
        activityList.add(aquarium);
        activityList.add(art_gallery);
        activityList.add(bowling);
        activityList.add(casino);
        activityList.add(clothing_store);
        activityList.add(movie_theater);
        activityList.add(museum);
        activityList.add(night_club);
        activityList.add(park);
        activityList.add(shopping_mall);
        activityList.add(spa);
        activityList.add(tourist_attraction);


        Selector selector = new Selector();
        assertNotEquals(0, selector.activitySelection(activityList));
    }
}