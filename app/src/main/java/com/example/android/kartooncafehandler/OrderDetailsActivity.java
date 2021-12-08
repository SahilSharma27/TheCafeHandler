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
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class OrderDetailsActivity extends AppCompatActivity {
    TextView idTextView;
    TextView nameTextView;
    TextView mailTextView;
    TextView addressTextView;
    TextView dateTextView;
    TextView contactTextView;
    TextView paymentMethodTextView;
    TextView orderStatusTextView;
    TextView paymentStatusView;
    TextView orderTotalTextView;
    TextView commentTextView;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mOrdersDatabaseReference;

    Button confirmButton,deliveredButton;
 //   public Orders ordersDEMO;
    String Key;



    RecyclerView cartListRCView;
    CartAdapter adapter;
    ArrayList<Cart> cartArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        findViews();
        Intent intent=getIntent();
        final Orders order=intent.getParcelableExtra("selectedOrder");
        idTextView.setText("#"+order.getOrderId());
        nameTextView.setText(order.getUserName());
        mailTextView.setText(order.getUserEmail());
        addressTextView.setText(order.getUserAddress());
        dateTextView.setText(order.getOrderTime());
        contactTextView.setText(order.getUserContact());
        paymentMethodTextView.setText("Payment Method: "+order.getPaymentMethod());
        commentTextView.setText(order.getSpecialInstruction());
        paymentStatusView.setText("Payment Status: "+order.getPaymentStatus());
        orderTotalTextView.setText(order.getOrderTotal()+"");
        orderStatusTextView.setText("Order Status: "+order.getOrderStatus());
        cartArrayList=new ArrayList<>();
        cartArrayList=order.getOrderList();
        mFirebaseDatabase=FirebaseDatabase.getInstance();

        switch (order.getOrderStatus()) {
            case "Placed":
                confirmButton.setEnabled(true);

                break;
            case "Confirmed":
                confirmButton.setEnabled(false);
                deliveredButton.setEnabled(true);
                break;
            case "Delivered":
                confirmButton.setEnabled(false);
                deliveredButton.setEnabled(false);
                break;
        }

       // ordersDEMO=order;


        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(OrderDetailsActivity.this,"CLick",Toast.LENGTH_SHORT).show();
                Key=order.getOrderKey();
                updateOrderStatus1();
                confirmButton.setEnabled(false);
//                Intent intent =new Intent(Intent.ACTION_SENDTO);
//                intent.setData(Uri.parse("mailto:" + order.getUserEmail() ));
//                intent.putExtra(Intent.EXTRA_SUBJECT,"Order Confirmation");
//                intent.putExtra(Intent.EXTRA_TEXT,"Your order has been confirmed");
//             //   intent.putExtra(Intent.EXTRA_EMAIL,order.getUserEmail());
//                if(intent.resolveActivity(getPackageManager())!=null){
//                    startActivity(intent);
//                }

            }
        });

        deliveredButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Key=order.getOrderKey();
                updateOrderStatus2();
                deliveredButton.setEnabled(false);
            }
        });

        adapter=new CartAdapter(this,cartArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        cartListRCView.setLayoutManager(linearLayoutManager);
        cartListRCView.setAdapter(adapter);




    }

    private void findViews() {

        cartListRCView=findViewById(R.id.order_cart_list);
        idTextView=findViewById(R.id.order_by_id);
        nameTextView=findViewById(R.id.order_by_name);
        mailTextView=findViewById(R.id.order_by_email);
        contactTextView=findViewById(R.id.order_by_contact);
        addressTextView=findViewById(R.id.order_by_address);
        paymentMethodTextView=findViewById(R.id.order_payment_method);
        orderTotalTextView=findViewById(R.id.order_total);
        paymentStatusView=findViewById(R.id.order_payment_status);
        dateTextView=findViewById(R.id.order_date_time);
        confirmButton=findViewById(R.id.confirm_order);
        commentTextView=findViewById(R.id.order_comment);
        orderStatusTextView=findViewById(R.id.order_status);
        deliveredButton=findViewById(R.id.order_delivered);
    }
    private void updateOrderStatus1(){
        //ref.child("myDb").child("awais@gmailcom").child("leftSpace").setValue("YourDateHere");

        mOrdersDatabaseReference=mFirebaseDatabase.getReference().child("Orders").child(Key);
        mOrdersDatabaseReference.child("orderStatus").setValue("Confirmed");
    }

    private void updateOrderStatus2(){
        //ref.child("myDb").child("awais@gmailcom").child("leftSpace").setValue("YourDateHere");

        mOrdersDatabaseReference=mFirebaseDatabase.getReference().child("Orders").child(Key);
        mOrdersDatabaseReference.child("orderStatus").setValue("Delivered");
    }

}
