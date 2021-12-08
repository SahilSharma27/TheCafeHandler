package com.example.android.kartooncafehandler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;

import java.util.ArrayList;

public class AddItemActivity extends AppCompatActivity {
    EditText ed1;
    EditText ed2;
    EditText ed3;
    EditText ed4;
    EditText cedt1;
    EditText cedt2;
    EditText cedt3;
    Button cbtn;
    RadioButton rb1;
    RadioButton rb2;
    RadioButton rb3;
    RadioButton rb4;
    CheckBox cb1;
    ListView listView;

    Button btn1,btn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);


        ed1 =  findViewById(R.id.item_code);
        ed2 =  findViewById(R.id.item_name);
        ed3 =  findViewById(R.id.item_desc);
        ed4 =  findViewById(R.id.item_price);
        rb1 =  findViewById(R.id.Vg);
        rb2 =  findViewById(R.id.nonVg);
        rb3 =  findViewById(R.id.egg);
        rb4 =  findViewById(R.id.all);
        cb1 =  findViewById(R.id.custom_check);

        btn1 =  findViewById(R.id.add_cust);
        btn2 = findViewById(R.id.save_item);



        ArrayList<Customizables> list =  new ArrayList<>();




        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Open Dialog



                String cname = cedt1.getText().toString();

                double cprice = Double.parseDouble(cedt2.getText().toString());

                int ctype = Integer.parseInt(cedt3.getText().toString());

                Customizables newCust =  new Customizables(cname,cprice,ctype);

                list.add(newCust);






            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {





                String code = ed1.getText().toString();
                String name = ed2.getText().toString();
                String desc = ed3.getText().toString();



                double price = Double.parseDouble(ed1.getText().toString());

                int cust;
                if(cb1.isChecked()){
                    cust = 1;
                }else{
                    cust = 0;
                }

                int catg = 0;
                if(rb1.isChecked()){
                    catg = 0;
                }
                else if(rb1.isChecked()){
                    catg = 1;
                }
                else if(rb1.isChecked()){
                    catg = 2;
                }else if(rb1.isChecked()){
                    catg = 3;
                }
                Item newItem = new Item(code,name,desc,catg,price,cust,list);

                AddMenuActivity.menuSubItems.add(newItem);
            }
        });




    }
}