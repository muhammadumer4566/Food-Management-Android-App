package com.example.foodapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
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

public class Edit_foodCatagoryActivity extends AppCompatActivity {

    EditText catagoryName;

    Button btnUpdate;

    String url = "https://catalogapp2037.000webhostapp.com/foodApp/update_FoodCatagory.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_food_catagory);

        catagoryName = findViewById(R.id.edit_categoryname);

        Intent next = getIntent();
        int cat_id= next.getExtras().getInt("cat_id");
        String catid =  Show_FoodCatagoryActivity.myarraylist.get(cat_id).getCatagoryId();

        catagoryName.setText(Show_FoodCatagoryActivity.myarraylist.get(cat_id).getCatagoryName());

        btnUpdate = findViewById(R.id.edit_btnaddcategory);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String catname= catagoryName.getText().toString();

                ProgressDialog myprogress= new ProgressDialog(Edit_foodCatagoryActivity.this);
                myprogress.setTitle("Please wait");
                myprogress.setMessage("please wait process start");

                myprogress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                myprogress.show();

                StringRequest request= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        myprogress.dismiss();

                        if(response.equalsIgnoreCase("sucess")){

                            Toast.makeText(Edit_foodCatagoryActivity.this, "Food Update Successfully", Toast.LENGTH_SHORT).show();

                            Intent next = new Intent(Edit_foodCatagoryActivity.this, Show_FoodCatagoryActivity.class);
                            startActivity(next);

                        }
                        else {
                            Toast.makeText(Edit_foodCatagoryActivity.this, "Food Update UnSuccessfull", Toast.LENGTH_SHORT).show();

                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(Edit_foodCatagoryActivity.this,error.getMessage().toString(), Toast.LENGTH_SHORT).show();

                    }
                }){

                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String,String> params = new HashMap<String,String>();
                        params.put("cat_id",catid);
                        params.put("cat_name",catname);


                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(Edit_foodCatagoryActivity.this);
                requestQueue.add(request);



            }
        });

    }
}