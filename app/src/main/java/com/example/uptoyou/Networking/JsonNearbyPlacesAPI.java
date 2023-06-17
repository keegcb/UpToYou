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

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=8046&type=restaurant&keyword=food&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getFoodResults();

    @GET("https://maps.googleapis.com/maps/api/place/nearbysearch/json?pagetoken=AZose0lHwI0Nre6eaJuHpGXWTx1u3XuSXxkJjS_XbUhfKUSSRKnOj90G7y2RsV7NF2cNKkeiyIXVfzHVHraRx297P1xO4AYcXWkezK8wzQTDfFlbqRIrdfTGRH9urixVImwHwaPh_pUjrvckWL_5ORuC-2MYJ2jntZ7X2ldw-KG6rmcMQaU6wIJae5Hiz-PROtgeDgE8LJtjZXtWojUqBjD2kSNKXONkpiMxJ8j8z_vRfRdnqwiTkv8vwBtwDIo28hRZE-UFh_lYvumzHf6uphCdpRt5H0fmKcP2ZoYu5Yyf5y8mf20sPbycAz-mPZ-HlezHaDjr1qRXmOlHzwzQz6q2p6z1BT2BqE5D2i69SdSlxWK6pQxVXmHDSjRq7fHMwxFvKvJ1BWZq0tx7wFnVyF37S5ozwc_7t8FwWd4-y64i9e49fI09cDXOv8jxFatn&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getFoodResults2();

    @GET("json?location=-33.8670522%2C151.1957362&radius=1500&type={type}&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityResults(@Path("type") String type);

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=8000&type=amusement_park&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityAmusement();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=8000&type=aquarium&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityAquarium();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=8000&type=art_gallery&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityArt();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=8000&type=bowling_alley&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityBowling();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=8000&type=casino&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityCasino();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=8000&type=clothing_store&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityClothing();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=8000&type=movie_theater&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityMovie();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=8000&type=museum&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityMuseum();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=8000&type=night_club&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityClub();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=8000&type=park&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityPark();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=8000&type=shopping_mall&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityMall();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=8000&type=spa&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivitySpa();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=8000&type=tourist_attraction&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityTourist();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=8000&type=zoo&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityZoo();

}
