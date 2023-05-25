package com.example.uptoyou.Networking.Serialization;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Northeast implements Serializable
{

    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lng")
    @Expose
    private Double lng;
    private final static long serialVersionUID = 3598217991433542467L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Northeast() {
    }

    /**
     *
     * @param lng
     * @param lat
     */
    public Northeast(Double lat, Double lng) {
        super();
        this.lat = lat;
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Northeast withLat(Double lat) {
        this.lat = lat;
        return this;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Northeast withLng(Double lng) {
        this.lng = lng;
        return this;
    }

}