package com.example.android.kartooncafehandler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuItemViewHolder>{
    Context context;
    ArrayList<MenuItem> list;
    OnMenuItemClickListener listener;


    public MenuAdapter(Context context,ArrayList<MenuItem> list , OnMenuItemClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener  = listener;

    }

    @NonNull
    @Override
    public MenuItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowlayout = inflater.inflate(R.layout.menu_row_layout, parent, false);
        return new MenuItemViewHolder(rowlayout);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuItemViewHolder holder, int position) {
        MenuItem item = list.get(position);
        holder.textView.setText(item.getTitle());
        Glide.with(context).load(item.getBackdrop()).into(holder.imageView);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onMenuItemClick(view, holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        if(list != null){
            return list.size();
        }
        else return 0;

    }
}
