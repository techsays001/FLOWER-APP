package com.msg91.sendotp.sample;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

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
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class StatusFragment222 extends Fragment {
        View root;
        List<Cheque3> productList;
        SwipeRefreshLayout s;
        //the recyclerview
        RecyclerView recyclerView;
        SwipeRefreshLayout swipe;
        SharedPreferences sh;
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            setHasOptionsMenu(true);
            root= inflater.inflate(R.layout.fragment_status222, container, false);
            loadProducts();


       /* mySwipeRefreshLayout=findViewById(R.id.swipe);
        mySwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadProducts();
            }
        });*/
            // SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
            //  ok = sharedPreferences.getString("referral", null);
            //getting the recyclerview from xml


            recyclerView = root.findViewById(R.id.recylcerViewc12);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            swipe=root.findViewById(R.id.swiperefresh12);
            sh=getActivity().getSharedPreferences("Official",MODE_PRIVATE);
            productList = new ArrayList<>();
            swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    productList.clear();
                    loadProducts();







                }
            });
            return root;
        }

        private void loadProducts() {

            /*
             * Creating a String Request
             * The request type is GET defined by first parameter
             * The URL is defined in the second parameter
             * Then we have a Response Listener and a Error Listener
             * In response listener we will get the JSON response as a String
             * */
            StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://androidprojectstechsays.000webhostapp.com/Flower_maegument_system/upload_flower.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            swipe.setRefreshing(false);
                            try {
                                //converting the string to json array object
                                JSONArray array = new JSONArray(response);

                                //traversing through all the object
                                for (int i = array.length()-1; i >=0; i--) {


                                    //getting product object from json array
                                    JSONObject product = array.getJSONObject(i);
                                    //adding the product to product list
                                    productList.add(new Cheque3(
                                            product.getString("product_name"),
                                            product.getString("product_details"),
                                            product.getString("product_price"),
                                            product.getString("image"),
                                            product.getString("pdid")


                                    ));
                                }


                                Chequeadapter3 adapter = new Chequeadapter3(getActivity(), productList);
                                adapter.notifyDataSetChanged();
                                recyclerView.setAdapter(adapter);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }


                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    //Adding parameters to request


                    //returning parameter
                    return params;
                }

            };

            //Adding the string request to the queue
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            requestQueue.add(stringRequest);
        }

    }

