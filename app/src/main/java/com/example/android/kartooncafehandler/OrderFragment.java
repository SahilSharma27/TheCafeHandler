package com.example.android.kartooncafehandler;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;


public class OrderFragment extends Fragment {
    RecyclerView OrderRCView;
    OrdersAdapter adapter;
    ArrayList<Orders> OrdersList;
    private ChildEventListener mChildEventListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mOrdersDatabaseReference;

    public static String key;

    public OrderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_order, container, false);
        OrdersList= new ArrayList<>();
        mFirebaseDatabase=FirebaseDatabase.getInstance();

        mOrdersDatabaseReference=mFirebaseDatabase.getReference().child("Orders");
        attachDatabaseReadListener();

        OrderRCView = root.findViewById(R.id.order_rcview);
        adapter = new OrdersAdapter(getContext(),OrdersList, new OnOrderClickListener() {
            @Override
            public void OnOrderItemClick(View view, int position) {
                Orders order=OrdersList.get(position);
                Intent intent=new Intent(getContext(),OrderDetailsActivity.class);
                intent.putExtra("selectedOrder",order);
                startActivity(intent);

            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        OrderRCView.setLayoutManager(linearLayoutManager);
        OrderRCView.setAdapter(adapter);

        return root;
    }
    private void attachDatabaseReadListener(){
        if(mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                   Orders orders = dataSnapshot.getValue(Orders.class);
                   orders.setOrderKey(dataSnapshot.getKey());
                   OrdersList.add(orders);
                    adapter.notifyDataSetChanged();
//                    orderNames.add(orders.getName());

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) { }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) { }
            };
            mOrdersDatabaseReference.addChildEventListener(mChildEventListener);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}


