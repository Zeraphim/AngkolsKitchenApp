package com.example.angkolskitchenapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GalleryMenuAdapter extends RecyclerView.Adapter<GalleryMenuAdapter.GalleryMenuViewHolder> {

    Context context;
    ArrayList<GalleryMenuItem> galleryArrayList;

    public GalleryMenuAdapter(Context context, ArrayList<GalleryMenuItem> galleryArrayList) {
        this.context = context;
        this.galleryArrayList = galleryArrayList;
    }

    @NonNull
    @Override
    public GalleryMenuAdapter.GalleryMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.layout_gallerymenu, parent, false);

        return new GalleryMenuViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryMenuAdapter.GalleryMenuViewHolder holder, int position) {

        GalleryMenuItem galleryItems = galleryArrayList.get(position);
        holder.prod_name.setText(galleryItems.prod_name);
        holder.prod_img.setImageResource(galleryItems.prod_img);

    }

    @Override
    public int getItemCount() {
        return galleryArrayList.size();
    }

    public static class GalleryMenuViewHolder extends RecyclerView.ViewHolder{

        TextView prod_name;
        ImageView prod_img;

        public GalleryMenuViewHolder(@NonNull View itemView) {
            super(itemView);
            prod_name = itemView.findViewById(R.id.prod_name);
            prod_img = itemView.findViewById(R.id.prod_img);
        }
    }
}
