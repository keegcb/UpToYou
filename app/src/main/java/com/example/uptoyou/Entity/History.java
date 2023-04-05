package com.example.uptoyou.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "History")
public class History {
    @PrimaryKey(autoGenerate = true)
    private int historyId;

    private int userId;
    private int placeId;
    private Date date;

    public History(int historyId, int userId, int placeId, Date date) {
        this.historyId = historyId;
        this.userId = userId;
        this.placeId = placeId;
        this.date = date;
    }

    public int getHistoryId() {
        return historyId;
    }

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
