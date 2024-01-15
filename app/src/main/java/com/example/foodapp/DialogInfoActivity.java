package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DialogInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_info);
    }

    public void admin_ic(View view) {

        Intent next = new Intent(DialogInfoActivity.this, Admin_loginActivity.class);
        startActivity(next);

    }

    public void txt_admin(View view) {

        Intent next = new Intent(DialogInfoActivity.this, Admin_loginActivity.class);
        startActivity(next);

    }

    public void ic_user(View view) {

        Intent next = new Intent(DialogInfoActivity.this, MainActivity.class);
        startActivity(next);

    }

    public void txt_user(View view) {

        Intent next = new Intent(DialogInfoActivity.this, MainActivity.class);
        startActivity(next);

    }
}