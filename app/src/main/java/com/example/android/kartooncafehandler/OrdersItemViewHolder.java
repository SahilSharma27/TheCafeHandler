package com.example.android.kartooncafehandler;

import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OrdersItemViewHolder extends RecyclerView.ViewHolder {
    TextView AddressTextview;
    View itemview;
    ImageView imageView;
    LinearLayout layout;

    public OrdersItemViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemview=itemView;
        layout=itemView.findViewById(R.id.order_layout);
        AddressTextview=itemView.findViewById(R.id.order_address);
        imageView=itemView.findViewById(R.id.order_status_view);

    }
}
