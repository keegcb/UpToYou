package com.example.uptoyou.Networking.Serialization;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NearbyPlaces implements Serializable
{

    @SerializedName("html_attributions")
    @Expose
    private List<Object> htmlAttributions;
    @SerializedName("next_page_token")
    @Expose
    private String nextPageToken;
    @SerializedName("results")
    @Expose
    private List<Result> results;
    @SerializedName("status")
    @Expose
    private String status;
    private final static long serialVersionUID = -6608887741669755989L;

    /**
     * No args constructor for use in serialization
     *
     */
    public NearbyPlaces() {
    }

    /**
     *
     * @param htmlAttributions
     * @param nextPageToken
     * @param results
     * @param status
     */
    public NearbyPlaces(List<Object> htmlAttributions, String nextPageToken, List<Result> results, String status) {
        super();
        this.htmlAttributions = htmlAttributions;
        this.nextPageToken = nextPageToken;
        this.results = results;
        this.status = status;
    }

    public List<Object> getHtmlAttributions() {
        return htmlAttributions;
    }

    public void setHtmlAttributions(List<Object> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
    }

    public NearbyPlaces withHtmlAttributions(List<Object> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
        return this;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public NearbyPlaces withNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
        return this;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public NearbyPlaces withResults(List<Result> results) {
        this.results = results;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public NearbyPlaces withStatus(String status) {
        this.status = status;
        return this;
    }

}
