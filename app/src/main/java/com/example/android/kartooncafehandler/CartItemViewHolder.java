package com.example.android.kartooncafehandler;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CartItemViewHolder extends RecyclerView.ViewHolder {
    View itemView;
    ImageView imageView;
    TextView nameTextView, qtyTextView, customTextView;
    public CartItemViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView=itemView;
        nameTextView=itemView.findViewById(R.id.item_name);
        qtyTextView=itemView.findViewById(R.id.item_qty);
        customTextView=itemView.findViewById(R.id.item_custom);
        imageView=itemView.findViewById(R.id.food_type);

    }
}
