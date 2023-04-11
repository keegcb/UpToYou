package com.example.uptoyou.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ActivityRank")
public class ActivityRank {
    @PrimaryKey(autoGenerate = true)
    private int activityId;

    private int arcade;
    private int axe_throwing;
    private int beach;
    private int bowling;
    private int casino;
    private int disk_golf;
    private int escape_room;
    private int garden;
    private int golf;
    private int library;
    private int hiking;
    private int mini_golf;
    private int movie_rental;
    private int movie_theater;
    private int museum;
    private int park;
    private int rage_room;
    private int shopping_mall;
    private int spa;
    private int theme_park;
    private int tourist_attraction;
    private int zoo;

    public ActivityRank(int arcade, int axe_throwing, int beach, int bowling, int casino, int disk_golf, int escape_room, int garden, int golf, int library, int hiking, int mini_golf, int movie_rental, int movie_theater, int museum, int park, int rage_room, int shopping_mall, int spa, int theme_park, int tourist_attraction, int zoo) {
        this.arcade = arcade;
        this.axe_throwing = axe_throwing;
        this.beach = beach;
        this.bowling = bowling;
        this.casino = casino;
        this.disk_golf = disk_golf;
        this.escape_room = escape_room;
        this.garden = garden;
        this.golf = golf;
        this.library = library;
        this.hiking = hiking;
        this.mini_golf = mini_golf;
        this.movie_rental = movie_rental;
        this.movie_theater = movie_theater;
        this.museum = museum;
        this.park = park;
        this.rage_room = rage_room;
        this.shopping_mall = shopping_mall;
        this.spa = spa;
        this.theme_park = theme_park;
        this.tourist_attraction = tourist_attraction;
        this.zoo = zoo;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public int getArcade() {
        return arcade;
    }

    public void setArcade(int arcade) {
        this.arcade = arcade;
    }

    public int getAxe_throwing() {
        return axe_throwing;
    }

    public void setAxe_throwing(int axe_throwing) {
        this.axe_throwing = axe_throwing;
    }

    public int getBeach() {
        return beach;
    }

    public void setBeach(int beach) {
        this.beach = beach;
    }

    public int getBowling() {
        return bowling;
    }

    public void setBowling(int bowling) {
        this.bowling = bowling;
    }

    public int getCasino() {
        return casino;
    }

    public void setCasino(int casino) {
        this.casino = casino;
    }

    public int getDisk_golf() {
        return disk_golf;
    }

    public void setDisk_golf(int disk_golf) {
        this.disk_golf = disk_golf;
    }

    public int getEscape_room() {
        return escape_room;
    }

    public void setEscape_room(int escape_room) {
        this.escape_room = escape_room;
    }

    public int getGarden() {
        return garden;
    }

    public void setGarden(int garden) {
        this.garden = garden;
    }

    public int getGolf() {
        return golf;
    }

    public void setGolf(int golf) {
        this.golf = golf;
    }

    public int getLibrary() {
        return library;
    }

    public void setLibrary(int library) {
        this.library = library;
    }

    public int getHiking() {
        return hiking;
    }

    public void setHiking(int hiking) {
        this.hiking = hiking;
    }

    public int getMini_golf() {
        return mini_golf;
    }

    public void setMini_golf(int mini_golf) {
        this.mini_golf = mini_golf;
    }

    public int getMovie_rental() {
        return movie_rental;
    }

    public void setMovie_rental(int movie_rental) {
        this.movie_rental = movie_rental;
    }

    public int getMovie_theater() {
        return movie_theater;
    }

    public void setMovie_theater(int movie_theater) {
        this.movie_theater = movie_theater;
    }

    public int getMuseum() {
        return museum;
    }

    public void setMuseum(int museum) {
        this.museum = museum;
    }

    public int getPark() {
        return park;
    }

    public void setPark(int park) {
        this.park = park;
    }

    public int getRage_room() {
        return rage_room;
    }

    public void setRage_room(int rage_room) {
        this.rage_room = rage_room;
    }

    public int getShopping_mall() {
        return shopping_mall;
    }

    public void setShopping_mall(int shopping_mall) {
        this.shopping_mall = shopping_mall;
    }

    public int getSpa() {
        return spa;
    }

    public void setSpa(int spa) {
        this.spa = spa;
    }

    public int getTheme_park() {
        return theme_park;
    }

    public void setTheme_park(int theme_park) {
        this.theme_park = theme_park;
    }

    public int getTourist_attraction() {
        return tourist_attraction;
    }

    public void setTourist_attraction(int tourist_attraction) {
        this.tourist_attraction = tourist_attraction;
    }

    public int getZoo() {
        return zoo;
    }

    public void setZoo(int zoo) {
        this.zoo = zoo;
    }
}
