package com.msg91.sendotp.sample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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

public class Admin_login extends AppCompatActivity {
Button admin_login;
    CheckBox pchekk;
    private boolean loggedIn = false;
    SharedPreferences sharedPreferences,shh;
EditText admin_uname,admin_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        admin_login=findViewById(R.id.admin_login);
        admin_uname=findViewById(R.id.admin_uname);
        admin_pass=findViewById(R.id.admin_pass);
        pchekk=findViewById(R.id.checkBox4);
        shh=getSharedPreferences("Official12",MODE_PRIVATE);
        loggedIn=shh.getBoolean("ph12",false);
        sharedPreferences=getSharedPreferences("phone",MODE_PRIVATE);
        if (loggedIn) {
            startActivity(new Intent(Admin_login.this,MainActivityhome2.class));
            // Snackbar.make(v,"Enter emergency number",Snackbar.LENGTH_SHORT).show();

        }

        pchekk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {

                    admin_pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    pchekk.setText("Hide");
                }
                else
                {

                    admin_pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    pchekk.setText("Show");
                }
            }
        });

        admin_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (admin_uname.getText().toString().isEmpty()) {

                    admin_uname.setError("enter valid  username");
                } else if (admin_pass.getText().toString().isEmpty()) {

                    admin_pass.setError("enter a valid password");
                } else {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://androidprojectstechsays.000webhostapp.com/Flower_maegument_system/adminlogin.php",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
//If we are getting success from server
//                                    Toast.makeText(Admin_login.this,response,Toast.LENGTH_LONG).show();

                                    admin_pass.getText().clear();
                                    admin_uname.getText().clear();
                                    if(response.contains("success"))
                                    {

                                        Intent in=new Intent(Admin_login.this, MainActivityhome2.class);
                                        startActivity(in);
                                    }


                                }
                            },

                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
//You can handle error here if you want
                                }

                            }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
//Adding parameters to request
                            params.put("aphone",admin_uname.getText().toString());
                            params.put("apass",admin_pass.getText().toString());

// Toast.makeText(MainActivity.this,"submitted",Toast.LENGTH_LONG).show();

//returning parameter
                            return params;
                        }

                    };

// m = Integer.parseInt(ba) - Integer.parseInt(result.getContents());
// balance.setText(m+"");


//Adding the string request to the queue
                    RequestQueue requestQueue = Volley.newRequestQueue(Admin_login.this);
                    requestQueue.add(stringRequest);
                }


            }
        });

    }
}
