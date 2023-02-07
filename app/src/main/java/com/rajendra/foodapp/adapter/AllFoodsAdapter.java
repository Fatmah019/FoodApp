package com.rajendra.foodapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rajendra.foodapp.FoodDetails;
import com.rajendra.foodapp.R;
import com.rajendra.foodapp.model.AllMenu;
import com.rajendra.foodapp.model.Foods;

import java.util.List;

public class AllFoodsAdapter extends RecyclerView.Adapter<AllFoodsAdapter.AllMenuViewHolder> {

    Context context;
    List<Foods> allmenuList;

    public AllFoodsAdapter(Context context, List<Foods> allmenuList) {
        this.context = context;
        this.allmenuList = allmenuList;
    }

    @NonNull
    @Override
    public AllMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.allmenu_recycler_items, parent, false);

        return new AllMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllMenuViewHolder holder, final int position) {

        holder.allMenuName.setText(allmenuList.get(position).getFavoriteDish());
        holder.allMenuPrice.setText("₹ "+allmenuList.get(position).getPrice());
//        holder.allMenuTime.setText(allmenuList.get(position).getDeliveryTime());
//        holder.allMenuRating.setText(allmenuList.get(position).getRating());
//        holder.allMenuCharges.setText(allmenuList.get(position).getDeliveryCharges());
        holder.allMenuNote.setText("Available: "+String.valueOf(allmenuList.get(position).getPlatesAvailable()));

        Glide.with(context).load(allmenuList.get(position).getImageUrl()).into(holder.allMenuImage);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, FoodDetails.class);
                i.putExtra("data",allmenuList.get(position));
                i.putExtra("id", allmenuList.get(position).get_id());
                i.putExtra("name", allmenuList.get(position).getFavoriteDish());
                i.putExtra("price", allmenuList.get(position).getPrice());
                i.putExtra("ava", allmenuList.get(position).getPlatesAvailable());
                i.putExtra("image", allmenuList.get(position).getImageUrl());

                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return allmenuList.size();
    }

    public static class AllMenuViewHolder extends RecyclerView.ViewHolder{

        TextView allMenuName, allMenuNote, allMenuRating, allMenuTime, allMenuCharges, allMenuPrice;
        ImageView allMenuImage;

        public AllMenuViewHolder(@NonNull View itemView) {
            super(itemView);

            allMenuName = itemView.findViewById(R.id.all_menu_name);
            allMenuNote = itemView.findViewById(R.id.all_menu_note);
//            allMenuCharges = itemView.findViewById(R.id.all_menu_delivery_charge);
//            allMenuRating = itemView.findViewById(R.id.all_menu_rating);
//            allMenuTime = itemView.findViewById(R.id.all_menu_deliverytime);
            allMenuPrice = itemView.findViewById(R.id.all_menu_price);
            allMenuImage = itemView.findViewById(R.id.all_menu_image);
        }
    }

}
