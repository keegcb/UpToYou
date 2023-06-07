package com.example.uptoyou.Networking.Serialization;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location implements Serializable
{

    @SerializedName("lat")
    @Expose
    private double lat;
    @SerializedName("lng")
    @Expose
    private double lng;
    private final static long serialVersionUID = 3099163972723140355L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Location() {
    }

    /**
     *
     * @param lng
     * @param lat
     */
    public Location(double lat, double lng) {
        super();
        this.lat = lat;
        this.lng = lng;
    }

    public double getLatitude() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public Location withLat(Double lat) {
        this.lat = lat;
        return this;
    }

    public double getLongitude() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public Location withLng(Double lng) {
        this.lng = lng;
        return this;
    }

}
