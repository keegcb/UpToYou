package com.example.uptoyou.UI.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uptoyou.Datebase.Repository;
import com.example.uptoyou.Entity.PlaceInfo;
import com.example.uptoyou.Networking.DownloadImageTask;
import com.example.uptoyou.R;
import com.example.uptoyou.UI.Main;
import com.example.uptoyou.UI.Map;
import com.example.uptoyou.UI.PlaceChoice;
import com.example.uptoyou.UI.PlaceSelection;

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

        new DownloadImageTask(holder.photo).execute(place.getIcon());
    }

    @Override
    public int getItemCount() {
        return placeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView address;
        ImageView photo;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            name = itemView.findViewById(R.id.txtPlaceName);
            address = itemView.findViewById(R.id.txtPlaceAddress);
            photo = itemView.findViewById(R.id.imgPlacePhoto);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    PlaceInfo place = placeList.get(position);

                    Main.placeList.clear();
                    Intent intent = new Intent(context, Map.class);

                    intent.putExtra("address", place.getAddress());
                    intent.putExtra("lat", place.getLat());
                    intent.putExtra("lng", place.getLng());

                    intent.putExtra("placeId", place.getPlaceId());

                    context.startActivity(intent);
                }
            });
        }
    }


}
