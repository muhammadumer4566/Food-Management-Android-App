package com.example.foodapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class userProductAdopter extends ArrayAdapter<ProductModel> {

    Context context;

    ArrayList<ProductModel> myproducts;


    public userProductAdopter(@NonNull Context context, ArrayList<ProductModel> myproducts) {
        super(context, R.layout.user_products,myproducts);
        this.context = context;
        this.myproducts= myproducts;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_products,null,true);


        TextView productName= view.findViewById(R.id.userproduct_name);
        TextView productdiscription= view.findViewById(R.id.userproduct_discription);
        TextView productPrice= view.findViewById(R.id.userproduct_price);
        ImageView productimage = view.findViewById(R.id.userproduct_imageid);

        productName.setText(myproducts.get(position).getProductname());
        productdiscription.setText(myproducts.get(position).getProductdiscription());
        productPrice.setText(myproducts.get(position).getProductprice());

        Glide.with(context).load(myproducts.get(position).getProductimage()).into(productimage);
        return view;
    }

}
