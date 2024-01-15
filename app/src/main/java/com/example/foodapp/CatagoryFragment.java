package com.example.foodapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

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

public class CatagoryFragment extends Fragment {

    CatagoryModel catagoryModel;

    UserCatagoryListAdopter userCatagoryListAdopter;

    String url = "https://catalogapp2037.000webhostapp.com/foodApp/show_FoodCatagory.php";

    public static ArrayList<CatagoryModel> myarraylist = new ArrayList<>();

    ListView catagory_fragmentlistview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v =inflater.inflate(R.layout.fragment_catagory, container, false);
        catagory_fragmentlistview = v.findViewById(R.id.catagory_fragmentlistview);

        displayData();
        userCatagoryListAdopter = new UserCatagoryListAdopter(getContext(),myarraylist);
        catagory_fragmentlistview.setAdapter(userCatagoryListAdopter);

        catagory_fragmentlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getContext(), CatMenuActivity.class);
                intent.putExtra("cat_name",position);
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


                            String myid = object.getString("cat_id");
                            String myname = object.getString("cat_name");


                            catagoryModel = new CatagoryModel(myid, myname);
                            myarraylist.add(catagoryModel);
                            userCatagoryListAdopter.notifyDataSetChanged();

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