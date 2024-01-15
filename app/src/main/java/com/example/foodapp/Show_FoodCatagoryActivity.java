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

public class Show_FoodCatagoryActivity extends AppCompatActivity {

    ListView mylist;

    CatagoryAdopter catagoryAdopter;

    CatagoryModel catagoryModel;

    String url = "https://catalogapp2037.000webhostapp.com/foodApp/show_FoodCatagory.php";

   String delete_url = "https://catalogapp2037.000webhostapp.com/foodApp/delete_FoodCatagory.php";

    public static ArrayList<CatagoryModel> myarraylist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_food_catagory);

        mylist = findViewById(R.id.category_listview);
        displayData();
        catagoryAdopter = new CatagoryAdopter(this, myarraylist);
        mylist.setAdapter(catagoryAdopter);

        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle(myarraylist.get(position).getCatagoryName());
                CharSequence item[] = {"Edit", "Delete"};
                builder.setItems(item, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        switch (which) {

                           case 0:
                                Intent next = new Intent(Show_FoodCatagoryActivity.this, Edit_foodCatagoryActivity.class);
                               next.putExtra("cat_id",position);
                                startActivity(next);
                                break;

                            case 1:
                                deleteData(myarraylist.get(position).getCatagoryId());
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


                            String cat_id = object.getString("cat_id");
                            String cat_name = object.getString("cat_name");


                            catagoryModel = new CatagoryModel(cat_id, cat_name);
                            myarraylist.add(catagoryModel);
                            catagoryAdopter.notifyDataSetChanged();

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
        ProgressDialog myprogress = new ProgressDialog(Show_FoodCatagoryActivity.this);
        myprogress.setTitle("Please Wait");
        myprogress.setMessage("Please wait process start");
        myprogress.show();
        StringRequest request = new StringRequest(Request.Method.POST, delete_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                myprogress.dismiss();
                if (response.equalsIgnoreCase("sucess")){
                    displayData();
                    Toast.makeText(Show_FoodCatagoryActivity.this, "Category Delete Sucessfully", Toast.LENGTH_SHORT).show();

                }else{

                    Toast.makeText(Show_FoodCatagoryActivity.this, "Category Delete Un-Sucessfully", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Show_FoodCatagoryActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();

            }
        }){

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                Map<String,String> parms = new HashMap<>();
                parms.put("cat_id",id);

                return parms;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Show_FoodCatagoryActivity.this);
        requestQueue.add(request);



    }

}