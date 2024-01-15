package com.example.foodapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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

public class CatMenuActivity extends AppCompatActivity {


    ListView getproductid;

    userProductAdopter productAdopter;

    ProductModel productModel;

    String url = "https://catalogapp2037.000webhostapp.com/foodApp/get_cat_menu.php";


    public static ArrayList<ProductModel> myarraylist = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_menu);

        getproductid = findViewById(R.id.getproductid);

        Intent next = getIntent();
        int getpos= next.getExtras().getInt("cat_name");
        String getCatagoryName =  CatagoryFragment.myarraylist.get(getpos).getCatagoryName();
        sendaddCatagoryName(getCatagoryName);

        productAdopter = new userProductAdopter(getApplicationContext(),myarraylist);
        getproductid.setAdapter(productAdopter);

        getproductid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(CatMenuActivity.this,OrderActivity.class);
                startActivity(intent);

            }
        });

    }

    void sendaddCatagoryName(String getCatagoryName){

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
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

                            String img_url= "https://catalogapp2037.000webhostapp.com/foodApp/Images/"+product_image;

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
        }){

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<String,String>();
                params.put("cat_name",getCatagoryName);


                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(CatMenuActivity.this);
        requestQueue.add(request);

    }

}