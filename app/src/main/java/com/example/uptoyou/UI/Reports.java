package com.example.uptoyou.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.uptoyou.Datebase.Repository;
import com.example.uptoyou.Datebase.Selector;
import com.example.uptoyou.Entity.ActivityPreference;
import com.example.uptoyou.Entity.FoodPreference;
import com.example.uptoyou.Entity.History;
import com.example.uptoyou.Entity.PlaceInfo;
import com.example.uptoyou.R;

import java.util.ArrayList;
import java.util.List;

public class Reports extends AppCompatActivity {

    List<PlaceInfo> placeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
    }

    private void initSelected(){
        Button btnSelected = (Button) findViewById(R.id.btnSelectedPlaces);
        btnSelected.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Repository repo = new Repository(getApplication());
                List<History> historyList = repo.getHistory();
                for(History history : historyList){
                    PlaceInfo placeInfo = repo.getPlaceById(history.getPlaceId());
                    placeList.add(placeInfo);
                }
            }
        });
    }
}