package com.example.foodapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Add_FoodCatagoryActivity extends AppCompatActivity {

    EditText categoryname;

    Button btnaddcategory;
    String url= "https://catalogapp2037.000webhostapp.com/foodApp/add_FoodCatagory.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_catagory);

        categoryname= findViewById(R.id.categoryname);
        btnaddcategory= findViewById(R.id.btnaddcategory);

        btnaddcategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProgressDialog myprogress= new ProgressDialog(Add_FoodCatagoryActivity.this);
                myprogress.setTitle("Please wait");
                myprogress.setMessage("please wait process start");

                myprogress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                myprogress.show();

                String mycatname = categoryname.getText().toString();
                categoryname.setText("");

                StringRequest request= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        myprogress.dismiss();

                        if(response.equalsIgnoreCase("success")){

                            Toast.makeText(Add_FoodCatagoryActivity.this, "Add Food Catagory Successfully",
                                    Toast.LENGTH_SHORT).show();

                        }
                        else {
                            Toast.makeText(Add_FoodCatagoryActivity.this, "Add Food Catagory UnSuccessfull",
                                    Toast.LENGTH_SHORT).show();

                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(Add_FoodCatagoryActivity.this,error.getMessage().toString(),
                                Toast.LENGTH_SHORT).show();

                    }
                }){

                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String,String> params = new HashMap<String,String>();
                        params.put("cat_name",mycatname);


                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(Add_FoodCatagoryActivity.this);
                requestQueue.add(request);

            }
        });

    }
}