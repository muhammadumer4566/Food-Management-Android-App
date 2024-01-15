package com.example.foodapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Add_MenuActivity extends AppCompatActivity {

    EditText productname,productprice,productdiscription,Productquantity;

    Button btnaddproduct,btnproductimage;
    public ArrayList<String> getcatagories = new ArrayList<>();
    public  ArrayList<String> getcatagoriesId = new ArrayList<>();

    Bitmap bitmap;

    String encodedImage;

    ImageView uploading;

    ArrayAdapter<String> adapter;

    AutoCompleteTextView getcatagoryid;

    String url= "https://catalogapp2037.000webhostapp.com/foodApp/get_FoodCatagory.php";
    String url_addproduct= "https://catalogapp2037.000webhostapp.com/foodApp/add_FoodProduct.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);

        btnproductimage = findViewById(R.id.btnproductimage);
        uploading = findViewById( R.id.uploading);

        getcatagoryid = findViewById(R.id.getcatagoryid);
        displayData();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,getcatagories);
        getcatagoryid.setAdapter(adapter);


        productname = findViewById(R.id.productname);
        productprice = findViewById(R.id.productprice);
        productdiscription = findViewById(R.id.productdiscription);
        Productquantity = findViewById(R.id.productquantity);
        btnaddproduct = findViewById(R.id.btnaddproduct);

        btnproductimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent  = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent,"Select Image"),1);


            }
        });


        btnaddproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mycatid = getcatagoryid.getText().toString();
                String myproductname = productname.getText().toString();
                String myproductprice = productprice.getText().toString();
                String myproductdiscription = productdiscription.getText().toString();
                String myProductquantity = Productquantity.getText().toString();

                ProgressDialog myprogress= new ProgressDialog(Add_MenuActivity.this);
                myprogress.setTitle("Please wait");
                myprogress.setMessage("please wait process start");

                myprogress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                myprogress.show();

                getcatagoryid.setText("");
                productname.setText("");
                productprice.setText("");
                productdiscription.setText("");
                Productquantity.setText("");

                StringRequest request= new StringRequest(Request.Method.POST, url_addproduct, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        myprogress.dismiss();

                        if(response.equalsIgnoreCase("success")){

                            Toast.makeText(Add_MenuActivity.this, "Add Menu Successfully", Toast.LENGTH_SHORT).show();

                        }
                        else {
                            Toast.makeText(Add_MenuActivity.this, "Add Menu UnSuccessfull", Toast.LENGTH_SHORT).show();

                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(Add_MenuActivity.this,error.getMessage().toString(), Toast.LENGTH_SHORT).show();

                    }
                }){

                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String,String> params = new HashMap<String,String>();
                        params.put("catagory_id",mycatid);
                        params.put("product_name",myproductname);
                        params.put("product_price",myproductprice);
                        params.put("product_quantity",myProductquantity);
                        params.put("product_discription",myproductdiscription);
                        params.put("image",encodedImage);



                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(Add_MenuActivity.this);
                requestQueue.add(request);



            }
        });

    }

    void displayData() {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                getcatagories.clear();

                try {


                    JSONObject jsonObject = new JSONObject(response);
                    String sucess = jsonObject.getString("sucess");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");


                    if (sucess.equals("1")) {

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject object = jsonArray.getJSONObject(i);


                            String myid = object.getString("cat_id");
                            String myname = object.getString("cat_name");



                            getcatagories.add(myname);
                            getcatagoriesId.add(myid);

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

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == 1 && resultCode == RESULT_OK && data!=null){

            Uri filePath = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(filePath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                uploading.setImageBitmap(bitmap);

                imageStore(bitmap);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }

        super.onActivityResult(requestCode, resultCode, data);

    }

    private void imageStore(Bitmap bitmap) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);

        byte[] imageBytes = stream.toByteArray();

        encodedImage = android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT);


    }

}