package com.example.uptoyou.Entity;

import android.net.Uri;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "PlaceInfo")
public class PlaceInfo {
    @PrimaryKey private int placeId;

    private String placeName;
    private String address;
    private String phoneNumber;
    private Uri websiteUri;
    private double lat;
    private double lng;
    private String type;

    public PlaceInfo(int placeId, String name, String address, String phoneNumber, Uri websiteUri, double lat, double lng, String type) {
        this.placeId = placeId;
        this.placeName = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.websiteUri = websiteUri;
        this.lat = lat;
        this.lng = lng;
        this.type = type;
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

    public Uri getWebsiteUri() {
        return websiteUri;
    }

    public void setWebsiteUri(Uri websiteUri) {
        this.websiteUri = websiteUri;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
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
