package com.example.uptoyou.UI.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uptoyou.Entity.ActivityPreference;
import com.example.uptoyou.R;

import java.util.ArrayList;
import java.util.List;

public class ActivityPreferenceAdapter extends RecyclerView.Adapter<ActivityPreferenceAdapter.MyViewHolder> {
    Context context;
    List<ActivityPreference> activityList;

    public ActivityPreferenceAdapter(Context context, List<ActivityPreference> activityList){
        this.context = context;
        this.activityList = activityList;
    }

    @NonNull
    @Override
    public ActivityPreferenceAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.preference_item, parent,false);

        return new ActivityPreferenceAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityPreferenceAdapter.MyViewHolder holder, int position) {
        ActivityPreference activity = activityList.get(position);

        holder.checkBox.setOnCheckedChangeListener(null);

        holder.checkBox.setChecked(activity.isActivityDesired());
        holder.activityName.setText(activity.getActivityName());
        holder.activityRank.setText(Integer.toString(activity.getActivityRank()));

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked){
                activity.setActivityDesired(isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return activityList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        CheckBox checkBox;
        TextView activityName;
        TextView activityRank;

        public  MyViewHolder(@NonNull View itemView){
            super(itemView);

            checkBox = itemView.findViewById(R.id.checkboxPreference);
            activityName = itemView.findViewById(R.id.txtPreferenceName);
            activityRank = itemView.findViewById(R.id.txtRank);
        }
    }
}
