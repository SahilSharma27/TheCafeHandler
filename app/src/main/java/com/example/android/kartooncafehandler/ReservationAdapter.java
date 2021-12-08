package com.example.android.kartooncafehandler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationItemViewHolder> {
    ArrayList <TableReservation> reservationArrayList;
    ReservationClickListener listener;
    Context context;

    public ReservationAdapter(Context context, ArrayList<TableReservation> reservationArrayList ,ReservationClickListener listener){
        this.context=context;
        this.reservationArrayList=reservationArrayList;
        this.listener=listener;
    }


    @NonNull
    @Override
    public ReservationItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowlayout = inflater.inflate(R.layout.reservation_item_layout, parent, false);
        return new ReservationItemViewHolder(rowlayout);
    }

    @Override
    public void onBindViewHolder(@NonNull final ReservationItemViewHolder holder, int position) {

        TableReservation tableReservation =reservationArrayList.get(position);
            holder.textView.setText(tableReservation.getUserName());
        if(tableReservation.getReservationStatus().equals("Requested")){
            // holder.layout.setBackgroundColor(ContextCompat.getColor(context,R.color.red));
            holder.imageView.setImageResource(R.drawable.newww);
        }
        else if(tableReservation.getReservationStatus().equals("Confirmed")){
            //holder.layout.setBackgroundColor(ContextCompat.getColor(context,R.color.yellow));
            holder.imageView.setImageResource(R.drawable.delivered);
        }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.OnReservationItemClick(view,holder.getAdapterPosition());
                }
            });
    }

    @Override
    public int getItemCount() {
        return reservationArrayList.size();
    }
}
