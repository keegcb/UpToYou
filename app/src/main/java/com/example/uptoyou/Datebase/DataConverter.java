package com.example.uptoyou.Datebase;

import androidx.room.TypeConverter;

import com.google.android.gms.maps.model.LatLng;

import java.util.Date;

public class DataConverter {
    @TypeConverter
    public static Date toDate(Long timestamp){
        return timestamp == null?null: new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date){
        return date == null?null:date.getTime();
    }


    public static LatLng toLatLng(double lat, double lng){
        LatLng latLng = new LatLng(lat, lng);
        return latLng;
    }

    public static double toDoubleLat(LatLng latLng){
        double lat = latLng.latitude;
        return lat;
    }

    public static double toDoubleLng(LatLng latLng){
        double lng = latLng.longitude;
        return lng;
    }
}
