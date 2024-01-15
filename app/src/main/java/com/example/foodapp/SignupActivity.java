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

public class SignupActivity extends AppCompatActivity {

    TextView backbtn;

    EditText signUp_Username1,signup_password1,signup_confrim2;

    Button btnsignUp;

    String url = "https://catalogapp2037.000webhostapp.com/foodApp/addUser.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        backbtn = findViewById(R.id.backbtn);
        signUp_Username1 =findViewById(R.id.signup_Username1);
        signup_password1 = findViewById(R.id.signup_Password1);
        signup_confrim2 = findViewById(R.id.signup_cnfrimPassword1);
        btnsignUp = findViewById(R.id.btnsignup);

        btnsignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String myusername= signUp_Username1.getText().toString();
                String mypassword= signup_password1.getText().toString();
                String cnfrimpassword= signup_confrim2.getText().toString();


                if(myusername.isEmpty()){

                    signUp_Username1.requestFocus();
                    signUp_Username1.setError("Please enter username");

                }
                else if (mypassword.isEmpty()) {

                    signup_password1.requestFocus();
                    signup_password1.setError("Please enter password");

                }
                else if (!mypassword.equals(cnfrimpassword)) {

                    signup_confrim2.requestFocus();
                    signup_confrim2.setError("Password and confrim password are not matched");

                }
                else {
                    signUp_Username1.setText("");
                    signup_password1.setText("");
                    signup_confrim2.setText("");

                    ProgressDialog myprogress= new ProgressDialog(SignupActivity.this);
                    myprogress.setTitle("Please wait");
                    myprogress.setMessage("please wait process start");

                    myprogress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    myprogress.show();

                    StringRequest request= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            myprogress.dismiss();

                            if(response.equals("success")){

                                Intent back= new Intent(SignupActivity.this,MainActivity.class);
                                startActivity(back);

                            }
                            else {
                                Toast.makeText(SignupActivity.this, "Data add UnSuccessfull", Toast.LENGTH_SHORT).show();

                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            myprogress.dismiss();

                            Toast.makeText(SignupActivity.this,error.getMessage().toString(), Toast.LENGTH_SHORT).show();

                        }
                    }){

                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            Map<String,String> params = new HashMap<String,String>();
                            params.put("user_name",myusername);
                            params.put("user_password",mypassword);



                            return params;
                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(SignupActivity.this);
                    requestQueue.add(request);

                }


            }
        });


        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent back = new Intent(SignupActivity.this,MainActivity.class);
                startActivity(back);

            }
        });
    }
}