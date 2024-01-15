package com.example.foodapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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
import java.util.HashMap;
import java.util.Map;

public class Show_MenuActivity extends AppCompatActivity {

    ListView mylist;

    ProductAdopter productAdopter;

    ProductModel productModel;

    String url = "https://catalogapp2037.000webhostapp.com/foodApp/show_FoodMenu.php";

    String delete_url = "https://catalogapp2037.000webhostapp.com/foodApp/delete_menu.php";


    public static ArrayList<ProductModel> myarraylist = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_menu);

        mylist = findViewById(R.id.menu_listview);
        displayData();
        productAdopter = new ProductAdopter(this, myarraylist);
        mylist.setAdapter(productAdopter);

        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle(myarraylist.get(position).getProductname());
                CharSequence item[] = {"Delete"};
                builder.setItems(item, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        switch (which) {

                            case 0:
                                deleteData(myarraylist.get(position).getProductid());
                                break;

                        }


                    }
                });


                AlertDialog alertDialog = builder.create();
                alertDialog.show();


            }
        });


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

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);


    }

    void deleteData(String id){


        int cat_id = Integer.parseInt(id);
        ProgressDialog myprogress = new ProgressDialog(Show_MenuActivity.this);
        myprogress.setTitle("Please Wait");
        myprogress.setMessage("Please wait process start");
        myprogress.show();
        StringRequest request = new StringRequest(Request.Method.POST, delete_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                myprogress.dismiss();
                if (response.equalsIgnoreCase("sucess")){
                    displayData();
                    Toast.makeText(Show_MenuActivity.this, "Menu Delete Sucessfully", Toast.LENGTH_SHORT).show();

                }else{

                    Toast.makeText(Show_MenuActivity.this, "Menu Delete Un-Sucessfully", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Show_MenuActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();

            }
        }){

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                Map<String,String> parms = new HashMap<>();
                parms.put("product_id",id);

                return parms;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Show_MenuActivity.this);
        requestQueue.add(request);



    }

}