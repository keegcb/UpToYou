package com.example.uptoyou.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

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
    List<PlaceInfo> placeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        initSelected();
        initHistory();
        initSearch();
        initDelete();

        addTableHistory();
        placesHistoryData();
    }

    private void initSelected(){
        Button btnSelected = findViewById(R.id.btnSelectedPlaces);
        btnSelected.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                placeList.clear();
                Repository repo = new Repository(getApplication());
                List<History> historyList = repo.getHistoryBySelected();
                for(History history : historyList){
                    PlaceInfo placeInfo = repo.getPlaceById(history.getPlaceId());
                    placeList.add(placeInfo);
                }
                fillData(placeList, historyList);
            }
        });
    }

    private void initHistory(){
        Button btnHistory = findViewById(R.id.btnHistory);
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                placeList.clear();
                Repository repo = new Repository(getApplication());
                List<History> historyList = repo.getHistory();
                for(History history : historyList){
                    PlaceInfo placeInfo = repo.getPlaceById(history.getPlaceId());
                    placeList.add(placeInfo);
                }
                fillData(placeList, historyList);
            }
        });
    }

    private void initSearch(){
        TextView txtSearch = findViewById(R.id.txtSearch);
        Button btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchString = txtSearch.getText().toString();
                Repository repo = new Repository(getApplication());
                List<History> historyList = repo.getHistory();
                for(History history : historyList){
                    PlaceInfo placeInfo = repo.getPlaceById(history.getPlaceId());
                    placeList.add(placeInfo);
                }
                searchString = searchString.toLowerCase();
                if(searchString.equals("food")){
                    List<PlaceInfo> foodPlaces = new ArrayList<>();
                    List<History> foodHistory = new ArrayList<>();
                    for(History history : historyList){
                        if (history.isFood()){
                            PlaceInfo placeInfo = repo.getPlaceById(history.getPlaceId());
                            foodPlaces.add(placeInfo);
                            foodHistory.add(history);
                        }
                    }
                    fillData(foodPlaces, foodHistory);
                }
                else if(searchString.equals("activity")){
                    List<PlaceInfo> activityPlaces = new ArrayList<>();
                    List<History> activityHistory = new ArrayList<>();
                    for(History history : historyList){
                        if (!history.isFood()){
                            PlaceInfo placeInfo = repo.getPlaceById(history.getPlaceId());
                            activityPlaces.add(placeInfo);
                            activityHistory.add(history);
                        }
                    }
                    fillData(activityPlaces, activityHistory);
                }
                else {
                    boolean equal = false;
                    for (int i=0; i<placeList.size(); i++){
                        if(searchString.equals(placeList.get(i).getPlaceName().toLowerCase())){
                            equal = true;
                            PlaceInfo placeInfo = placeList.get(i);
                            List<PlaceInfo> foundPlace = new ArrayList<>();
                            foundPlace.add(placeInfo);
                            History history = repo.getHistoryByPlace(placeInfo.getPlaceId());
                            List<History> foundHistory = new ArrayList<>();
                            foundHistory.add(history);
                            fillData(foundPlace, foundHistory);
                        }
                        else {
                            equal = false;
                        }
                    }
                    if (!equal){
                        AlertDialog.Builder builder = new AlertDialog.Builder(Reports.this);
                        builder.setTitle("No Matching Result");
                        builder.setMessage("There is no place in history that could be found matching your search.");
                        builder.setCancelable(true);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(Reports.this, "Search Canceled", Toast.LENGTH_LONG).show();
                                dialogInterface.cancel();
                            }
                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                }
            }
        });
    }

    private void initDelete(){
        Repository repo = new Repository(getApplication());
        List<History> history = repo.getHistory();
        Button btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Reports.this);
                builder.setTitle("Delete Place History");
                builder.setMessage("Are you sure you would like to delete the place history?");
                builder.setCancelable(true);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        for(int j=0; j<history.size(); j++){
                            repo.deleteHistory(history.get(j));
                        }
                        Toast.makeText(Reports.this, "History Deleted", Toast.LENGTH_LONG).show();
                        dialogInterface.cancel();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(Reports.this, "Delete Action Canceled", Toast.LENGTH_LONG).show();
                        dialogInterface.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

    }

    private void createAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Reports.this);
        builder.setTitle("No Matching Result");
        builder.setMessage("There is no place in history that could be found matching your search.");
        builder.setCancelable(true);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(Reports.this, "Search Canceled", Toast.LENGTH_LONG).show();
                dialogInterface.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void addTableHistory(){
        TableLayout tableLayout = findViewById(R.id.tableReports);
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
        TableLayout tableLayout = findViewById(R.id.tableReports);
        Repository repo = new Repository(getApplication());
        List<History> history = repo.getHistory();
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

    private void fillData(List<PlaceInfo> placeList, List<History> historyList){
        TableLayout tableLayout = findViewById(R.id.tableReports);
        int count = tableLayout.getChildCount();
        for(int i=0; i<count; i++){
            View child = tableLayout.getChildAt(i);
            if(child instanceof TableRow)((ViewGroup) child).removeAllViews();
        }
        addTableHistory();
        for(int i=0; i<placeList.size(); i++){
            PlaceInfo place = placeList.get(i);
            History history = historyList.get(i);
            TableRow tbRow = new TableRow(this);
            TextView date = new TextView(this);
            date.setText(history.getDate().toString());
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
        TableLayout tableLayout = findViewById(R.id.tableReports);
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

        tableLayout.addView(tbRow0);
    }

    private void placesData(){

    }
}