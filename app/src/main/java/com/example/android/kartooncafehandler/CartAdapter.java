package com.example.android.kartooncafehandler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartItemViewHolder> {

    Context context;
    ArrayList<Cart>cartArrayList;

    public CartAdapter(Context context, ArrayList<Cart>cartArrayList){
        this.context=context;
        this.cartArrayList=cartArrayList;
    }

    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowlayout = inflater.inflate(R.layout.order_detail_item_layout, parent, false);
        return new CartItemViewHolder(rowlayout);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolder holder, int position) {
        Cart cart =cartArrayList.get(position);
        holder.nameTextView.setText(cart.getCartItemName());
        holder.qtyTextView.setText("x "+cart.getQuantity());
        if(cart.getCustom()==null){
            holder.customTextView.setVisibility(View.GONE);
        }
        else {
            holder.customTextView.setText(cart.getCustom());
        }
        if(cart.getCartItemCategory()==0){
            holder.imageView.setImageResource(R.drawable.veg);
        }
        else if(cart.getCartItemCategory()==1){
            holder.imageView.setImageResource(R.drawable.nonveg);
        }
        else{
            holder.imageView.setImageResource(R.drawable.egg);
        }

    }

    @Override
    public int getItemCount() {
        return cartArrayList.size();
    }
}
