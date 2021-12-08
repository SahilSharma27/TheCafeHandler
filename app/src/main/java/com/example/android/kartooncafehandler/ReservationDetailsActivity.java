package com.example.android.kartooncafehandler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ReservationDetailsActivity extends AppCompatActivity {
    TextView resIdTextView;
    TextView nameTextView;
    TextView mailTextView;
    TextView timeTextView;
    TextView dateTextView;
    TextView numPplTextView;
    TextView contactTextView;
    TextView resStatusView;
    TextView orderTotalTextView;
    TextView commentTextView;
    TextView specialTextView;
    Button button;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mOrdersDatabaseReference;
    String Key;

    RecyclerView cartListRCView;
    CartAdapter adapter;
    ArrayList<Cart> cartArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_details);
        findViews();
        Intent intent=getIntent();
        final TableReservation reservation=intent.getParcelableExtra("selectedReservation");
        resIdTextView.setText("#"+reservation.getReservationId());
        nameTextView.setText(reservation.getUserName());
        mailTextView.setText(reservation.getUserEmail());
        timeTextView.setText(reservation.getReservationForDate());
        dateTextView.setText(reservation.getReservationForTime());
        contactTextView.setText(reservation.getUserContact());
        commentTextView.setText(reservation.getSpecialInstruction());
        numPplTextView.setText(reservation.getNumberOfPpl());
        resStatusView.setText(reservation.getReservationStatus());
        specialTextView.setText(reservation.getSpecialOccassion());
        orderTotalTextView.setText(reservation.getOrderTotal()+"");
        cartArrayList=new ArrayList<>();
        cartArrayList=reservation.getReservationOrderAheadList();
        mFirebaseDatabase=FirebaseDatabase.getInstance();

        switch (reservation.getReservationStatus()) {
            case "Requested":
                button.setEnabled(true);

                break;
            case "Confirmed":
                button.setEnabled(false);
                break;
        }

    if(cartArrayList!=null) {
        adapter = new CartAdapter(this, cartArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        cartListRCView.setLayoutManager(linearLayoutManager);
        cartListRCView.setAdapter(adapter);
    }

    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
                Key=reservation.getReservationKey();
                updateReservationStatus();
                button.setEnabled(false);

//            Intent intent =new Intent(Intent.ACTION_SENDTO);
//            intent.setData(Uri.parse("mailto:" + reservation.getUserEmail() ));
//            intent.putExtra(Intent.EXTRA_SUBJECT,"Order Confirmation");
//            intent.putExtra(Intent.EXTRA_TEXT,"Your order has been confirmed");
//            if(intent.resolveActivity(getPackageManager())!=null){
//                startActivity(intent);
//            }
        }
    });

    }

    private void findViews(){
        resIdTextView=findViewById(R.id.reservation_by_id);
        cartListRCView=findViewById(R.id.reservation_cart_list);
        nameTextView=findViewById(R.id.reservation_by_name);
        mailTextView=findViewById(R.id.reservation_by_email);
        contactTextView=findViewById(R.id.reservation_by_contact);
        timeTextView=findViewById(R.id.reservation_date);
        orderTotalTextView=findViewById(R.id.reservation_total);
        resStatusView=findViewById(R.id.reservation_status);
        dateTextView=findViewById(R.id.reservation_time);
        button=findViewById(R.id.confirm_reservation);
        commentTextView=findViewById(R.id.reservation_comment);
        numPplTextView=findViewById(R.id.reservation_ppl);
        specialTextView=findViewById(R.id.reservation_special);
    }

    private void updateReservationStatus(){
        //ref.child("myDb").child("awais@gmailcom").child("leftSpace").setValue("YourDateHere");

        mOrdersDatabaseReference=mFirebaseDatabase.getReference().child("TableReservation").child(Key);
        mOrdersDatabaseReference.child("reservationStatus").setValue("Confirmed");
    }
}


