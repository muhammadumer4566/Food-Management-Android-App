package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity {

    Button btnorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        btnorder = findViewById(R.id.btnorder);
        btnorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(OrderActivity.this, "Your Order is Placed", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(OrderActivity.this,UserDasboardActivity.class);
                 startActivity(intent);


            }
        });


    }
}