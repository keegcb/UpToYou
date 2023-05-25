package com.example.uptoyou.Networking.Serialization;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result implements Serializable
{

    @SerializedName("business_status")
    @Expose
    private String businessStatus;
    @SerializedName("geometry")
    @Expose
    private Geometry geometry;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("icon_background_color")
    @Expose
    private String iconBackgroundColor;
    @SerializedName("icon_mask_base_uri")
    @Expose
    private String iconMaskBaseUri;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("opening_hours")
    @Expose
    private OpeningHours openingHours;
    @SerializedName("photos")
    @Expose
    private List<Photo> photos;
    @SerializedName("place_id")
    @Expose
    private String placeId;
    @SerializedName("plus_code")
    @Expose
    private PlusCode plusCode;
    @SerializedName("price_level")
    @Expose
    private Integer priceLevel;
    @SerializedName("rating")
    @Expose
    private Integer rating;
    @SerializedName("reference")
    @Expose
    private String reference;
    @SerializedName("scope")
    @Expose
    private String scope;
    @SerializedName("types")
    @Expose
    private List<String> types;
    @SerializedName("user_ratings_total")
    @Expose
    private Integer userRatingsTotal;
    @SerializedName("vicinity")
    @Expose
    private String vicinity;
    private final static long serialVersionUID = -961944356582767108L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Result() {
    }

    /**
     *
     * @param types
     * @param plusCode
     * @param icon
     * @param placeId
     * @param rating
     * @param userRatingsTotal
     * @param businessStatus
     * @param priceLevel
     * @param photos
     * @param iconMaskBaseUri
     * @param reference
     * @param iconBackgroundColor
     * @param scope
     * @param name
     * @param geometry
     * @param openingHours
     * @param vicinity
     */
    public Result(String businessStatus, Geometry geometry, String icon, String iconBackgroundColor, String iconMaskBaseUri, String name, OpeningHours openingHours, List<Photo> photos, String placeId, PlusCode plusCode, Integer priceLevel, Integer rating, String reference, String scope, List<String> types, Integer userRatingsTotal, String vicinity) {
        super();
        this.businessStatus = businessStatus;
        this.geometry = geometry;
        this.icon = icon;
        this.iconBackgroundColor = iconBackgroundColor;
        this.iconMaskBaseUri = iconMaskBaseUri;
        this.name = name;
        this.openingHours = openingHours;
        this.photos = photos;
        this.placeId = placeId;
        this.plusCode = plusCode;
        this.priceLevel = priceLevel;
        this.rating = rating;
        this.reference = reference;
        this.scope = scope;
        this.types = types;
        this.userRatingsTotal = userRatingsTotal;
        this.vicinity = vicinity;
    }

    public String getBusinessStatus() {
        return businessStatus;
    }

    public void setBusinessStatus(String businessStatus) {
        this.businessStatus = businessStatus;
    }

    public Result withBusinessStatus(String businessStatus) {
        this.businessStatus = businessStatus;
        return this;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public Result withGeometry(Geometry geometry) {
        this.geometry = geometry;
        return this;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Result withIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public String getIconBackgroundColor() {
        return iconBackgroundColor;
    }

    public void setIconBackgroundColor(String iconBackgroundColor) {
        this.iconBackgroundColor = iconBackgroundColor;
    }

    public Result withIconBackgroundColor(String iconBackgroundColor) {
        this.iconBackgroundColor = iconBackgroundColor;
        return this;
    }

    public String getIconMaskBaseUri() {
        return iconMaskBaseUri;
    }

    public void setIconMaskBaseUri(String iconMaskBaseUri) {
        this.iconMaskBaseUri = iconMaskBaseUri;
    }

    public Result withIconMaskBaseUri(String iconMaskBaseUri) {
        this.iconMaskBaseUri = iconMaskBaseUri;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Result withName(String name) {
        this.name = name;
        return this;
    }

    public OpeningHours getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(OpeningHours openingHours) {
        this.openingHours = openingHours;
    }

    public Result withOpeningHours(OpeningHours openingHours) {
        this.openingHours = openingHours;
        return this;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public Result withPhotos(List<Photo> photos) {
        this.photos = photos;
        return this;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public Result withPlaceId(String placeId) {
        this.placeId = placeId;
        return this;
    }

    public PlusCode getPlusCode() {
        return plusCode;
    }

    public void setPlusCode(PlusCode plusCode) {
        this.plusCode = plusCode;
    }

    public Result withPlusCode(PlusCode plusCode) {
        this.plusCode = plusCode;
        return this;
    }

    public Integer getPriceLevel() {
        return priceLevel;
    }

    public void setPriceLevel(Integer priceLevel) {
        this.priceLevel = priceLevel;
    }

    public Result withPriceLevel(Integer priceLevel) {
        this.priceLevel = priceLevel;
        return this;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Result withRating(Integer rating) {
        this.rating = rating;
        return this;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Result withReference(String reference) {
        this.reference = reference;
        return this;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Result withScope(String scope) {
        this.scope = scope;
        return this;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public Result withTypes(List<String> types) {
        this.types = types;
        return this;
    }

    public Integer getUserRatingsTotal() {
        return userRatingsTotal;
    }

    public void setUserRatingsTotal(Integer userRatingsTotal) {
        this.userRatingsTotal = userRatingsTotal;
    }

    public Result withUserRatingsTotal(Integer userRatingsTotal) {
        this.userRatingsTotal = userRatingsTotal;
        return this;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public Result withVicinity(String vicinity) {
        this.vicinity = vicinity;
        return this;
    }

}