package com.example.uptoyou.UI.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uptoyou.Datebase.Repository;
import com.example.uptoyou.Entity.FoodPreference;
import com.example.uptoyou.R;
import com.example.uptoyou.UI.PreferencesActivity;

import java.util.ArrayList;
import java.util.List;

public class FoodPreferenceAdapter extends RecyclerView.Adapter<FoodPreferenceAdapter.MyViewHolder> {
    Context context;
    List<FoodPreference> foodList;
    List<FoodPreference> foods;

    public FoodPreferenceAdapter(Context context, List<FoodPreference> foodList){
        this.context = context;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public FoodPreferenceAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.preference_item, parent,false);

        return new FoodPreferenceAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodPreferenceAdapter.MyViewHolder holder, int position) {
        FoodPreference food = foodList.get(position);
        int tPosition = position;

        holder.checkBox.setOnCheckedChangeListener(null);

        holder.checkBox.setChecked(food.isFoodDesired());
        holder.foodName.setText(food.getFoodName());
        holder.foodRank.setText(Integer.toString(food.getFoodRank()));

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                food.setFoodDesired(isChecked);
                foodList.set(tPosition, food);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        CheckBox checkBox;
        TextView foodName;
        TextView foodRank;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            checkBox = itemView.findViewById(R.id.checkboxPreference);
            foodName = itemView.findViewById(R.id.txtPreferenceName);
            foodRank = itemView.findViewById(R.id.txtRank);
        }
    }

    public List<FoodPreference> getSelected(){
        List<FoodPreference> selected = new ArrayList<FoodPreference>();
        for(int i=0; i<foodList.size(); i++){
            if(foodList.get(i).isFoodDesired()){
                selected.add(foodList.get(i));
            }
        }
        return selected;
    }

    public List<FoodPreference> getUnselected(){
        List<FoodPreference> unselected = new ArrayList<FoodPreference>();
        for(int i=0; i<foodList.size(); i++){
            if(!foodList.get(i).isFoodDesired()){
                unselected.add(foodList.get(i));
            }
        }
        return unselected;
    }

}
