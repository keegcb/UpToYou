package com.example.uptoyou.UI.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uptoyou.Entity.PlaceInfo;
import com.example.uptoyou.R;

import java.util.ArrayList;
import java.util.List;

public class PlaceChoiceAdapter extends RecyclerView.Adapter<PlaceChoiceAdapter.MyViewHolder> {
    Context context;
    List<PlaceInfo> placeList = new ArrayList<>();

    public PlaceChoiceAdapter(Context context, List<PlaceInfo> places){
        this.context = context;
        this.placeList = places;
    }

    @NonNull
    @Override
    public PlaceChoiceAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.choice_item, parent, false);
        return new PlaceChoiceAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceChoiceAdapter.MyViewHolder holder, int position) {
        PlaceInfo place = placeList.get(position);

        holder.name.setText(place.getPlaceName());
        holder.address.setText(place.getAddress());
    }

    @Override
    public int getItemCount() {
        return placeList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView type;
        TextView address;
        TextView website;
        ImageView photo;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            name = itemView.findViewById(R.id.txtPlaceName);
            type = itemView.findViewById(R.id.txtPlaceType);
            address = itemView.findViewById(R.id.txtPlaceAddress);
            website = itemView.findViewById(R.id.txtPlaceWebsite);
            photo = itemView.findViewById(R.id.imgPlacePhoto);
        }
    }

}
