package com.example.uptoyou.Entity;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.android.libraries.places.api.model.Place;

import java.util.List;

@Entity(tableName = "PlaceInfo")
public class PlaceInfo {
    @PrimaryKey (autoGenerate = false)
    @NonNull
    private String placeId;

    private String placeName;
    private String address;
    private double lat;
    private double lng;


    public PlaceInfo(String placeId, String placeName, String address, double lat, double lng){
        this.placeId = placeId;
        this.placeName = placeName;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
