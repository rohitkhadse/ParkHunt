package com.example.saurabh.parkhunt;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.*;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.saurabh.parkhunt.maps.MapsActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText TFemail=(EditText)findViewById(R.id.TFemail);
        final EditText TFpassword=(EditText)findViewById(R.id.TFpassword);


        Button NewReg = (Button) findViewById(R.id.NewReg);
        NewReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newreg= new Intent(Login.this,Register.class);
                startActivity(newreg);
            }
        });

        Button Butlogin = (Button) findViewById(R.id.Butlogin);
        Butlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = TFemail.getText().toString();
                final String password = TFpassword.getText().toString();


                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success)
                            {
                                Intent intent = new Intent(Login.this,MapsActivity.class);
                                Login.this.startActivity(intent);
                                //startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                                builder.setMessage("Login Failed").setNegativeButton("Retry", null).create().show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(email, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Login.this);
                queue.add(loginRequest);

            }
        });
    }
}
