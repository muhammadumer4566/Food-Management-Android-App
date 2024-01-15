package com.example.foodapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MenuFragment extends Fragment {

    ListView shop_fragmentlistview;

    userProductAdopter productAdopter;

    ProductModel productModel;

    String url = "https://catalogapp2037.000webhostapp.com/foodApp/show_FoodMenu.php";


    public static ArrayList<ProductModel> myarraylist = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=   inflater.inflate(R.layout.fragment_menu, container, false);

        shop_fragmentlistview = v.findViewById(R.id.shop_fragmentlistview);
        displayData();
        productAdopter = new userProductAdopter(getContext(),myarraylist);
        shop_fragmentlistview.setAdapter(productAdopter);


        shop_fragmentlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getContext(), OrderActivity.class);
                startActivity(intent);

            }
        });


        return v;
    }

    void displayData() {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                myarraylist.clear();

                try {


                    JSONObject jsonObject = new JSONObject(response);
                    String sucess = jsonObject.getString("sucess");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");


                    if (sucess.equals("1")) {

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject object = jsonArray.getJSONObject(i);


                            String product_id = object.getString("product_id ");
                            String product_name = object.getString("product_name");
                            String product_discription = object.getString("product_discription");
                            String product_price = object.getString("product_price");
                            String product_quantity = object.getString("product_quantity");
                            String catagory_id = object.getString("catagory_id");
                            String product_image = object.getString("product_image");

                            String img_url= "https://catalogapp2037.000webhostapp.com/foodApp/Images/"+ product_image;


                            productModel = new ProductModel(product_id,product_name,product_discription, product_price,
                                    product_quantity,img_url,catagory_id);
                            myarraylist.add(productModel);
                            productAdopter.notifyDataSetChanged();

                        }

                    }


                } catch (JSONException e) {

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);


    }

}