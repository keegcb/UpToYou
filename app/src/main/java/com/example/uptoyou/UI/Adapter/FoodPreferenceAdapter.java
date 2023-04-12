package com.example.uptoyou.UI.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uptoyou.Entity.FoodPreference;
import com.example.uptoyou.R;

import java.util.ArrayList;

public class FoodPreferenceAdapter extends RecyclerView.Adapter<FoodPreferenceAdapter.MyViewHolder> {
    Context context;
    ArrayList<FoodPreference> foodList;

    public FoodPreferenceAdapter(Context context, ArrayList<FoodPreference> foodList){
        this.context = context;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public FoodPreferenceAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull FoodPreferenceAdapter.MyViewHolder holder, int position) {
        holder.checkBox.setActivated(foodList.get(position).isFoodDesired());
        holder.foodName.setText(foodList.get(position).getFoodName());
        holder.foodRank.setText(foodList.get(position).getFoodRank());
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
}
