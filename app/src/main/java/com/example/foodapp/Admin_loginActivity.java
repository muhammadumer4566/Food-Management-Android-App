package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Admin_loginActivity extends AppCompatActivity {

    EditText login_Username1,Login_Password1;

    Button btnlogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        login_Username1 = findViewById(R.id.login_Username1);
        Login_Password1  = findViewById(R.id.Login_Password1);

        btnlogin = findViewById(R.id.btnlogin);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String myusername = login_Username1.getText().toString();
                String mypassword = Login_Password1.getText().toString();

                if(myusername.isEmpty()){

                    login_Username1.requestFocus();
                    login_Username1.setError("Please enter username");

                } else if (mypassword.isEmpty()) {

                Login_Password1.requestFocus();
                Login_Password1.setError("Please enter password");

                }
                else {

                    ProgressDialog myprogress= new ProgressDialog(Admin_loginActivity.this);
                    myprogress.setTitle("Please wait");
                    myprogress.setMessage("please wait process start");

                    myprogress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    myprogress.show();

                    Thread thread= new Thread(){

                        @Override
                        public void run() {

                            try {

                                sleep(3000);
                                myprogress.dismiss();


                                String Username = "Food123@gmail.com";
                                String Password = "foodapp";

                                if (myusername.equalsIgnoreCase(Username) && mypassword.equalsIgnoreCase(Password)) {


                                    Intent next = new Intent(Admin_loginActivity.this, DashbaordActivity.class);
                                    startActivity(next);

                                }
                                else {

     //                               Toast.makeText(Admin_loginActivity.this, "Please enter valid username and password", Toast.LENGTH_SHORT).show();

                                }

                            }
                            catch (InterruptedException e){


                            }

                        }
                    };
                    thread.start();


                }


            }
        });

    }
}