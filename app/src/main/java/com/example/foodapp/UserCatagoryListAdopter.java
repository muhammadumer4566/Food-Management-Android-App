package com.example.foodapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class UserCatagoryListAdopter extends ArrayAdapter<CatagoryModel> {

    Context context;

    ArrayList<CatagoryModel> mycatagories;


    public UserCatagoryListAdopter(@NonNull Context context, ArrayList<CatagoryModel> mycatagories ) {
        super(context, R.layout.user_catagory,mycatagories);
        this.context = context;
        this.mycatagories= mycatagories;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_catagory,null,true);


        // TextView catagoryid= view.findViewById(R.id.row_catagory_id);
        TextView catagoryname= view.findViewById(R.id.txt_user_cata);

        //   catagoryid.setText(mycatagories.get(position).getCatagoryId());
        catagoryname.setText(mycatagories.get(position).getCatagoryName());


        return view;
    }

}
