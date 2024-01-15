package com.example.foodapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class MainActivity extends AppCompatActivity {

    TextView nextbtn;

    EditText login_Username1, Login_Password1;

    Button btnlogin;

    String url = "https://catalogapp2037.000webhostapp.com/foodApp/user_Login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_Username1 = findViewById(R.id.login_Username1);
        Login_Password1 = findViewById(R.id.Login_Password1);
        nextbtn = findViewById(R.id.nextbtn);
        btnlogin = findViewById(R.id.btnlogin);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String myusername = login_Username1.getText().toString();
                String mypassword = Login_Password1.getText().toString();

                if (myusername.isEmpty()) {

                    login_Username1.requestFocus();
                    login_Username1.setError("Please enter username");

                } else if (mypassword.isEmpty()) {

                    Login_Password1.requestFocus();
                    Login_Password1.setError("Please enter password");

                } else {

                    login_Username1.setText("");
                    Login_Password1.setText("");

                    ProgressDialog myprogress = new ProgressDialog(MainActivity.this);
                    myprogress.setTitle("Please wait");
                    myprogress.setMessage("please wait process start");
                    myprogress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    myprogress.show();

                    StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            myprogress.dismiss();

                            if (response.equals("ok")){

                                Intent next = new Intent(MainActivity.this,UserDasboardActivity.class);
                                startActivity(next);
                            
                            }else {

                                Toast.makeText(MainActivity.this, "Please enter valid username and password",
                                        Toast.LENGTH_SHORT).show();
                                
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                                myprogress.dismiss();

                            Toast.makeText(MainActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();

                        }
                    }) {

                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            Map<String,String> params = new HashMap<String,String>();
                            params.put("user_name",myusername);
                            params.put("user_password",mypassword);



                            return params;
                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                    requestQueue.add(request);


                }


            }
        });


        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent next = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(next);

            }
        });
    }
}