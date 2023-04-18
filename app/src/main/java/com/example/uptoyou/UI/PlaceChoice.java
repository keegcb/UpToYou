package com.example.uptoyou.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.uptoyou.R;

public class PlaceChoice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_choice);

        Bundle b = getIntent().getExtras();
        int value; // or other values
        if(b != null){
            value = b.getInt("key");
            identifySelector(value);
        } else {
            finish();
        }
    }

    private void identifySelector(int id){
        switch (id){
            case 1: //Do stuff for Food Selector
            case 2: //Do stuff for Activity Selector
            case 3: //Do stuff for Random Selector
            default: Log.e("identifySelector", "no case");
        }
    }
}