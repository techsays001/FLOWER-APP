package com.msg91.sendotp.sample;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {
    EditText trackid;
    Button serch;
    TextView tproductname,tproductdetails,tracknos,trackprice;
    String a,b,c,d;
    SharedPreferences sh;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        View root= inflater.inflate(R.layout.fragment_chat, container, false);
         trackid=root.findViewById(R.id.trtid);
        serch=root.findViewById(R.id.tr_btn);
        tproductname=root.findViewById(R.id.tpn);
        tproductdetails=root.findViewById(R.id.tpd);
        tracknos=root.findViewById(R.id.tnos);
        trackprice=root.findViewById(R.id.tpr);
   sh=getActivity().getSharedPreferences("Official",MODE_PRIVATE);


        serch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (trackid.getText().toString().isEmpty()){

                    trackid.setError("field is empty");
                }


                else {




                    StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://androidprojectstechsays.000webhostapp.com/Flower_maegument_system/track.php",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
//If we are getting success from server
//                    Toast.makeText(Phycology2.this,response,Toast.LENGTH_LONG).show();
                                    try {
                                        JSONArray jsonArray = new JSONArray(response);
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            JSONObject json_obj = jsonArray.getJSONObject(i);
//ba = json_obj.getString("balance");
                                            a=json_obj.getString("product_name");
                                            b=json_obj.getString("product_details");
                                            c=json_obj.getString("nos");
                                            d=json_obj.getString("price");
//                                e=json_obj.getString("address");
//                                f=json_obj.getString("gender");
//                                g=json_obj.getString("dob");


                                            tproductname.setText(a);
                                            tproductdetails.setText(b);
                                            tracknos.setText(c);
                                            trackprice.setText(d);
                                        }
//Toast.makeText(Recharge.this, "your new balnce is "+ba, Toast.LENGTH_LONG).show();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }



                                    if (response.contains("enter valid data")) {
                                        trackid.setError("Enter a valid Track ID");
                                    }


                                    //   Toast.makeText(Signin.this, "success", Toast.LENGTH_LONG).show();

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
                            params.put("tid",trackid.getText().toString());

// Toast.makeText(MainActivity.this,"submitted",Toast.LENGTH_LONG).show();

//returning parameter
                            return params;
                        }

                    };

// m = Integer.parseInt(ba) - Integer.parseInt(result.getContents());
// balance.setText(m+"");


//Adding the string request to the queue
                    RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                    requestQueue.add(stringRequest);
                }



                }

       });






        return root;






    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {


        inflater.inflate(R.menu.menu_main1,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();





//
//        if (id==R.id.logg){
//
//            SharedPreferences.Editor e=sh.edit();
//            e.clear();
//            e.apply();
//            Intent iiiij=new Intent(getActivity(), Signin.class);
//            startActivity(iiiij);
//
//
//
//
//        }



        return super.onOptionsItemSelected(item);
    }



}

