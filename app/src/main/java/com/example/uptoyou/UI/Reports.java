package com.example.uptoyou.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.uptoyou.Datebase.Repository;
import com.example.uptoyou.Datebase.Selector;
import com.example.uptoyou.Entity.ActivityPreference;
import com.example.uptoyou.Entity.FoodPreference;
import com.example.uptoyou.Entity.History;
import com.example.uptoyou.Entity.PlaceInfo;
import com.example.uptoyou.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Reports extends AppCompatActivity {
    TableLayout tableLayout = (TableLayout) findViewById(R.id.tableReports);
    List<PlaceInfo> placeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        addTableHistory();
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

    private void addTableHistory(){
        TableRow tbRow0 = new TableRow(this);
        //Table Headers
        TextView col0 = new TextView(this);
        col0.setText("Date");
        col0.setTextColor(Color.BLACK);
        tbRow0.addView(col0);
        TextView col1 = new TextView(this);
        col1.setText("Name");
        col1.setTextColor(Color.BLACK);
        tbRow0.addView(col1);
        TextView col2 = new TextView(this);
        col2.setText("Address");
        col2.setTextColor(Color.BLACK);
        tbRow0.addView(col2);

        tableLayout.addView(tbRow0);
    }

    private void placesHistoryData(){
        Repository repo = new Repository(getApplication());
        List<History> history = repo.getHistory();
        List<PlaceInfo> placeInfoList = new ArrayList<>();
        for(int i=0; i<history.size(); i++){
            PlaceInfo place = repo.getPlaceById(history.get(i).getPlaceId());
            TableRow tbRow = new TableRow(this);
            TextView date = new TextView(this);
            date.setText(history.get(i).getDate().toString());
            date.setGravity(Gravity.LEFT);
            tbRow.addView(date);
            TextView name = new TextView(this);
            name.setText(place.getPlaceName());
            name.setGravity(Gravity.LEFT);
            tbRow.addView(name);
            TextView address = new TextView(this);
            address.setText(place.getAddress());
            address.setGravity(Gravity.LEFT);
            tbRow.addView(address);
            tableLayout.addView(tbRow);
        }
    }

    private void addTableAll(){
        TableLayout tableLayout = (TableLayout) findViewById(R.id.tableReports);
        TableRow tbRow0 = new TableRow(this);
        //Table Headers
        TextView col0 = new TextView(this);
        col0.setText("Date");
        col0.setTextColor(Color.BLACK);
        tbRow0.addView(col0);
        TextView col1 = new TextView(this);
        col1.setText("Name");
        col1.setTextColor(Color.BLACK);
        tbRow0.addView(col1);
        TextView col2 = new TextView(this);
        col2.setText("Address");
        col2.setTextColor(Color.BLACK);
        tbRow0.addView(col2);
        TextView col3 = new TextView(this);
        col3.setText("Type");
        col3.setTextColor(Color.BLACK);
        tbRow0.addView(col3);

        tableLayout.addView(tbRow0);
    }

    private void placesData(){

    }
}