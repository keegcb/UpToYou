package com.example.uptoyou.Networking;

import com.example.uptoyou.Networking.Serialization.Results;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonNearbyPlacesAPI {

    /* Radius Conversions
        5 miles away = 8046 meters
        10 miles away = 16093 meters
        20 miles away = 32186 meters
        25 miles away = 40233 meters
        30 miles away = 48280 meters
     */

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=8000&type=restaurant&keyword=food&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getFoodResults();

    @GET("json?location=-33.8670522%2C151.1957362&radius=1500&type={type}&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityResults(@Path("type") String type);


}
