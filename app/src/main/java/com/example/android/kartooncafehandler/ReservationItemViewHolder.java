package com.example.android.kartooncafehandler;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReservationItemViewHolder extends RecyclerView.ViewHolder {
    TextView textView;
    ImageView imageView;
    View itemView;
    public ReservationItemViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView=itemView;
        textView = itemView.findViewById(R.id.reservation_by_name);
        imageView=itemView.findViewById(R.id.reservation_status_view);
    }
}
