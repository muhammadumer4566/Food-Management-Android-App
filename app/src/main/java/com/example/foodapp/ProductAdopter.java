package com.example.foodapp;

import android.annotation.SuppressLint;
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

public class ProductAdopter extends ArrayAdapter<ProductModel> {

    Context context;

    ArrayList<ProductModel> myproducts;


    public ProductAdopter(@NonNull Context context, ArrayList<ProductModel> myproducts) {
        super(context, R.layout.product_rows,myproducts);
        this.context = context;
        this.myproducts= myproducts;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_rows,null,true);


        TextView productName= view.findViewById(R.id.row_productname);
        TextView productDiscription= view.findViewById(R.id.row_productDiscription);
       TextView productPrice= view.findViewById(R.id.row_productprice);
        ImageView productimage = view.findViewById(R.id.img_product);

        productName.setText(myproducts.get(position).getProductname());
        productDiscription.setText(myproducts.get(position).getProductdiscription());
        productPrice.setText(myproducts.get(position).getProductprice());

        Glide.with(context).load(myproducts.get(position).getProductimage()).into(productimage);


        return view;
    }

}
