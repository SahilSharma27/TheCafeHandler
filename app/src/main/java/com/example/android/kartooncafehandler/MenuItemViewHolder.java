package com.example.android.kartooncafehandler;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MenuItemViewHolder extends RecyclerView.ViewHolder {
    View itemView;
    TextView textView;
    ImageView imageView;

    public MenuItemViewHolder(@NonNull View itemView) {

        super(itemView);
        this.itemView = itemView;
        textView = itemView.findViewById(R.id.menu_item);
        imageView = itemView.findViewById(R.id.menuBD);
    }
}
