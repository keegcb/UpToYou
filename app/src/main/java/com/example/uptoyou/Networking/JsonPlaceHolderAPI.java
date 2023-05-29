package com.example.uptoyou.Networking;

import com.example.uptoyou.Networking.Serialization.Results;
import com.google.android.libraries.places.api.model.Place;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderAPI {

    @GET("json?location=-33.8670522%2C151.1957362&radius=1500&type=restaurant&keyword=cruise&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call<List<Results>> getPlaces();

}
