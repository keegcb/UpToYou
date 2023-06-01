package com.example.uptoyou.Networking;

import com.example.uptoyou.Networking.Serialization.Results;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonNearbyPlacesAPI {

    @GET("json?location=-33.8670522%2C151.1957362&radius=1500&type=restaurant&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getFoodResults();

    @GET("json?location=-33.8670522%2C151.1957362&radius=1500&type={type}&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityResults(@Path("type") String type);


}
