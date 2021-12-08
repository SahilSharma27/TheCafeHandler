package com.example.android.kartooncafehandler;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersItemViewHolder> {
    Context context;
    OnOrderClickListener listener;
    ArrayList<Orders> OrderList;

    public OrdersAdapter(Context context,ArrayList<Orders> OrderList,OnOrderClickListener listener){
        this.context=context;
        this.listener=listener;
        this.OrderList=OrderList;
    }

    @NonNull
    @Override
    public OrdersItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowlayout = inflater.inflate(R.layout.order_item_layout, parent, false);
        return new OrdersItemViewHolder(rowlayout);

    }

    @Override
    public void onBindViewHolder(@NonNull final OrdersItemViewHolder holder, int position) {
        Orders order =OrderList.get(position);

        holder.AddressTextview.setText(order.getUserAddress());
        if(order.getOrderStatus().equals("Placed")){
           // holder.layout.setBackgroundColor(ContextCompat.getColor(context,R.color.red));
            holder.imageView.setImageResource(R.drawable.newww);
        }
        else if(order.getOrderStatus().equals("Confirmed")){
            //holder.layout.setBackgroundColor(ContextCompat.getColor(context,R.color.yellow));
            holder.imageView.setImageResource(R.drawable.pending);
        }
        else{
           // holder.layout.setBackgroundColor(ContextCompat.getColor(context,R.color.green));
            holder.imageView.setImageResource(R.drawable.delivered);
        }




        holder.itemview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnOrderItemClick(view,holder.getAdapterPosition());
            }
        });
      //  holder.NameTextview.setText(order.getUserName());

    }

    @Override
    public int getItemCount() {
        return OrderList.size();
    }
}
