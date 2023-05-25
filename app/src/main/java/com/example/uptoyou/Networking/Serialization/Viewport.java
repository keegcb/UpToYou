package com.example.uptoyou.Networking.Serialization;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Viewport implements Serializable
{

    @SerializedName("northeast")
    @Expose
    private Northeast northeast;
    @SerializedName("southwest")
    @Expose
    private Southwest southwest;
    private final static long serialVersionUID = 1878555520235769665L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Viewport() {
    }

    /**
     *
     * @param southwest
     * @param northeast
     */
    public Viewport(Northeast northeast, Southwest southwest) {
        super();
        this.northeast = northeast;
        this.southwest = southwest;
    }

    public Northeast getNortheast() {
        return northeast;
    }

    public void setNortheast(Northeast northeast) {
        this.northeast = northeast;
    }

    public Viewport withNortheast(Northeast northeast) {
        this.northeast = northeast;
        return this;
    }

    public Southwest getSouthwest() {
        return southwest;
    }

    public void setSouthwest(Southwest southwest) {
        this.southwest = southwest;
    }

    public Viewport withSouthwest(Southwest southwest) {
        this.southwest = southwest;
        return this;
    }

}
