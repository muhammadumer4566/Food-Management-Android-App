package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DashbaordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbaord);
    }

    public void Addmenu(View view) {

        Intent next = new Intent(DashbaordActivity.this,Add_MenuActivity.class);
        startActivity(next);
    }

    public void AddmenuTitle(View view) {

        Intent next = new Intent(DashbaordActivity.this,Add_MenuActivity.class);
        startActivity(next);

    }

    public void Showmenu(View view) {

        Intent next = new Intent(DashbaordActivity.this,Show_MenuActivity.class);
        startActivity(next);

    }

    public void ShowmenuTitle(View view) {

        Intent next = new Intent(DashbaordActivity.this,Show_MenuActivity.class);
        startActivity(next);
    }

    public void Addcategory(View view) {

        Intent next = new Intent(DashbaordActivity.this,Add_FoodCatagoryActivity.class);
        startActivity(next);

    }

    public void AddcategoryTitle(View view) {

        Intent next = new Intent(DashbaordActivity.this,Add_FoodCatagoryActivity.class);
        startActivity(next);

    }

    public void ShowCategorytitle(View view) {

        Intent next = new Intent(DashbaordActivity.this,Show_FoodCatagoryActivity.class);
        startActivity(next);

    }

    public void ShowCategory(View view) {

        Intent next = new Intent(DashbaordActivity.this,Show_FoodCatagoryActivity.class);
        startActivity(next);

    }
}