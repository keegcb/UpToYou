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

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=16093&type=restaurant&keyword=food&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getFoodResults10();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=32186&type=restaurant&keyword=food&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getFoodResults20();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=48280&type=restaurant&keyword=food&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getFoodResults30();

    @GET("https://maps.googleapis.com/maps/api/place/nearbysearch/json?pagetoken=AZose0lHwI0Nre6eaJuHpGXWTx1u3XuSXxkJjS_XbUhfKUSSRKnOj90G7y2RsV7NF2cNKkeiyIXVfzHVHraRx297P1xO4AYcXWkezK8wzQTDfFlbqRIrdfTGRH9urixVImwHwaPh_pUjrvckWL_5ORuC-2MYJ2jntZ7X2ldw-KG6rmcMQaU6wIJae5Hiz-PROtgeDgE8LJtjZXtWojUqBjD2kSNKXONkpiMxJ8j8z_vRfRdnqwiTkv8vwBtwDIo28hRZE-UFh_lYvumzHf6uphCdpRt5H0fmKcP2ZoYu5Yyf5y8mf20sPbycAz-mPZ-HlezHaDjr1qRXmOlHzwzQz6q2p6z1BT2BqE5D2i69SdSlxWK6pQxVXmHDSjRq7fHMwxFvKvJ1BWZq0tx7wFnVyF37S5ozwc_7t8FwWd4-y64i9e49fI09cDXOv8jxFatn&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getFoodResults2();

    @GET("json?location=-33.8670522%2C151.1957362&radius=1500&type={type}&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityResults(@Path("type") String type);


    //Amusement Park
    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=8046&type=amusement_park&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityAmusement();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=16093&type=amusement_park&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityAmusement10();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=32186&type=amusement_park&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityAmusement20();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=40233&type=amusement_park&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityAmusement25();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=48280&type=amusement_park&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityAmusement30();


    //Aquarium
    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=8000&type=aquarium&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityAquarium();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=16093&type=aquarium&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityAquarium10();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=32186&type=aquarium&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityAquarium20();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=40233&type=aquarium&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityAquarium25();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=48280&type=aquarium&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityAquarium30();


    //Art
    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=8000&type=art_gallery&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityArt();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=16093&type=art_gallery&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityArt10();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=32186&type=art_gallery&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityArt20();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=40233&type=art_gallery&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityArt25();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=48280&type=art_gallery&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityArt30();


    //Bowling
    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=8000&type=bowling_alley&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityBowling();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=16093&type=bowling_alley&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityBowling10();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=32186&type=bowling_alley&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityBowling20();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=40233&type=bowling_alley&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityBowling25();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=48280&type=bowling_alley&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityBowling30();


    //Casino
    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=8000&type=casino&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityCasino();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=16093&type=casino&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityCasino10();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=32186&type=casino&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityCasino20();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=40233&type=casino&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityCasino25();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=48280&type=casino&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityCasino30();


    //Clothing
    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=8000&type=clothing_store&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityClothing();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=16093&type=clothing_store&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityClothing10();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=32186&type=clothing_store&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityClothing20();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=40233&type=clothing_store&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityClothing25();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=48280&type=clothing_store&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityClothing30();


    //Movie
    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=8000&type=movie_theater&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityMovie();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=16093&type=movie_theater&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityMovie10();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=32186&type=movie_theater&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityMovie20();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=40233&type=movie_theater&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityMovie25();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=48280&type=movie_theater&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityMovie30();


    //Museum
    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=8000&type=museum&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityMuseum();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=16093&type=museum&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityMuseum10();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=32186&type=museum&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityMuseum20();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=40233&type=museum&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityMuseum25();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=48280&type=museum&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityMuseum30();


    //Club
    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=8000&type=night_club&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityClub();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=16093&type=night_club&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityClub10();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=32186&type=night_club&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityClub20();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=40233&type=night_club&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityClub25();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=48280&type=night_club&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityClub30();


    //Park
    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=8000&type=park&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityPark();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=16093&type=park&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityPark10();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=32186&type=park&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityPark20();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=40233&type=park&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityPark25();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=48280&type=park&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityPark30();


    //Mall
    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=8000&type=shopping_mall&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityMall();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=16093&type=shopping_mall&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityMall10();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=32186&type=shopping_mall&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityMall20();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=40233&type=shopping_mall&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityMall25();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=48280&type=shopping_mall&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityMall30();


    //Spa
    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=8000&type=spa&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivitySpa();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=16093&type=spa&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivitySpa10();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=32186&type=spa&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivitySpa20();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=40233&type=spa&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivitySpa25();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=48280&type=spa&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivitySpa30();


    //Tourist
    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=8000&type=tourist_attraction&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityTourist();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=16093&type=tourist_attraction&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityTourist10();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=32186&type=tourist_attraction&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityTourist20();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=40233&type=tourist_attraction&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityTourist25();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=48280&type=tourist_attraction&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityTourist30();


    //Zoo
    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=8000&type=zoo&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityZoo();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=8000&type=zoo&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityZoo10();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=8000&type=zoo&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityZoo20();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=8000&type=zoo&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityZoo25();

    @GET("json?location=42.33461099979685%2C-83.0465496496764&radius=8000&type=zoo&key=AIzaSyAdTVZTSt6VA_jLNtMpDy3Ky9xqzdaCrIw")
    Call <Results> getActivityZoo30();

}
