package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserDasboardActivity extends AppCompatActivity {

    BottomNavigationView bottomnav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dasboard);

        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new HomeFragment()).commit();

        bottomnav = findViewById(R.id.bottomnav);

        bottomnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();

                if (itemId == R.id.home) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new HomeFragment()).commit();
                    return true;
                }
                else if (itemId == R.id.shop) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new MenuFragment()).commit();
                    return true;
                }
                else if (itemId == R.id.categories) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new CatagoryFragment()).commit();
                    return true;
                }
                return false;

            }

        });


    }
}