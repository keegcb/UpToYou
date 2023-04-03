package com.example.uptoyou.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Activity")
public class Activity {
    @PrimaryKey(autoGenerate = true)
    private int activityId;

    private boolean arcade;
    private boolean axe_throwing;
    private boolean beach;
    private boolean bowling;
    private boolean casino;
    private boolean disk_golf;
    private boolean escape_room;
    private boolean garden;
    private boolean golf;
    private boolean library;
    private boolean hiking;
    private boolean mini_golf;
    private boolean movie_rental;
    private boolean movie_theater;
    private boolean museum;
    private boolean park;
    private boolean rage_room;
    private boolean shopping_mall;
    private boolean spa;
    private boolean theme_park;
    private boolean tourist_attraction;
    private boolean zoo;


    public Activity(int activityId, boolean arcade, boolean axe_throwing, boolean beach, boolean bowling, boolean casino, boolean disk_golf, boolean escape_room, boolean garden, boolean golf, boolean library, boolean hiking, boolean mini_golf, boolean movie_rental, boolean movie_theater, boolean museum, boolean park, boolean rage_room, boolean shopping_mall, boolean spa, boolean theme_park, boolean tourist_attraction, boolean zoo) {
        this.activityId = activityId;
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

    public boolean isArcade() {
        return arcade;
    }

    public void setArcade(boolean arcade) {
        this.arcade = arcade;
    }

    public boolean isAxe_throwing() {
        return axe_throwing;
    }

    public void setAxe_throwing(boolean axe_throwing) {
        this.axe_throwing = axe_throwing;
    }

    public boolean isBeach() {
        return beach;
    }

    public void setBeach(boolean beach) {
        this.beach = beach;
    }

    public boolean isBowling() {
        return bowling;
    }

    public void setBowling(boolean bowling) {
        this.bowling = bowling;
    }

    public boolean isCasino() {
        return casino;
    }

    public void setCasino(boolean casino) {
        this.casino = casino;
    }

    public boolean isDisk_golf() {
        return disk_golf;
    }

    public void setDisk_golf(boolean disk_golf) {
        this.disk_golf = disk_golf;
    }

    public boolean isEscape_room() {
        return escape_room;
    }

    public void setEscape_room(boolean escape_room) {
        this.escape_room = escape_room;
    }

    public boolean isGarden() {
        return garden;
    }

    public void setGarden(boolean garden) {
        this.garden = garden;
    }

    public boolean isGolf() {
        return golf;
    }

    public void setGolf(boolean golf) {
        this.golf = golf;
    }

    public boolean isLibrary() {
        return library;
    }

    public void setLibrary(boolean library) {
        this.library = library;
    }

    public boolean isHiking() {
        return hiking;
    }

    public void setHiking(boolean hiking) {
        this.hiking = hiking;
    }

    public boolean isMini_golf() {
        return mini_golf;
    }

    public void setMini_golf(boolean mini_golf) {
        this.mini_golf = mini_golf;
    }

    public boolean isMovie_rental() {
        return movie_rental;
    }

    public void setMovie_rental(boolean movie_rental) {
        this.movie_rental = movie_rental;
    }

    public boolean isMovie_theater() {
        return movie_theater;
    }

    public void setMovie_theater(boolean movie_theater) {
        this.movie_theater = movie_theater;
    }

    public boolean isMuseum() {
        return museum;
    }

    public void setMuseum(boolean museum) {
        this.museum = museum;
    }

    public boolean isPark() {
        return park;
    }

    public void setPark(boolean park) {
        this.park = park;
    }

    public boolean isRage_room() {
        return rage_room;
    }

    public void setRage_room(boolean rage_room) {
        this.rage_room = rage_room;
    }

    public boolean isShopping_mall() {
        return shopping_mall;
    }

    public void setShopping_mall(boolean shopping_mall) {
        this.shopping_mall = shopping_mall;
    }

    public boolean isSpa() {
        return spa;
    }

    public void setSpa(boolean spa) {
        this.spa = spa;
    }

    public boolean isTheme_park() {
        return theme_park;
    }

    public void setTheme_park(boolean theme_park) {
        this.theme_park = theme_park;
    }

    public boolean isTourist_attraction() {
        return tourist_attraction;
    }

    public void setTourist_attraction(boolean tourist_attraction) {
        this.tourist_attraction = tourist_attraction;
    }

    public boolean isZoo() {
        return zoo;
    }

    public void setZoo(boolean zoo) {
        this.zoo = zoo;
    }
}
