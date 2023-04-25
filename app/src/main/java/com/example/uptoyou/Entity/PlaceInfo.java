package com.example.uptoyou.Entity;

import android.net.Uri;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.android.libraries.places.api.model.Place;

import java.util.List;

@Entity(tableName = "PlaceInfo")
public class PlaceInfo {
    @PrimaryKey (autoGenerate = false)
    private int placeId;

    private String placeName;
    private String address;
    private String phoneNumber;
    private String websiteUri;
    private double lat;
    private double lng;
    private List<Place.Type> type;


    public PlaceInfo(String placeName, String address, String phoneNumber, String websiteUri, double lat, double lng{
        this.placeName = placeName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.websiteUri = websiteUri;
        this.lat = lat;
        this.lng = lng;
    }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWebsiteUri() {
        return websiteUri;
    }

    public void setWebsiteUri(String websiteUri) {
        this.websiteUri = websiteUri;
    }

    public List<Place.Type> getType() {
        return type;
    }

    public void setType(List<Place.Type> type) {
        this.type = type;
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
