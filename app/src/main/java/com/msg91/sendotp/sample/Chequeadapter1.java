package com.msg91.sendotp.sample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import java.util.List;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Chequeadapter1 extends RecyclerView.Adapter<Chequeadapter1.ProductViewHolder> {

    private Context mCtx;
    private List<Cheque1> productList;

    public Chequeadapter1(Context mCtx, List<Cheque1> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.activity_event, null);
        return new ProductViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
      final   Cheque1 cheque;   cheque = productList.get(position);


                 holder.tid.setText(cheque.getImage());
                 holder.tname.setText(cheque.getUser());
                 holder.tph.setText(cheque.getStatus());
                 holder.temail.setText(cheque.getDes());
                 holder.taddress.setText(cheque.getPrize());
                 holder.tnos.setText(cheque.getPrize1());
                 holder.tprice.setText(cheque.getPrize2());
                 holder.tproductname.setText(cheque.getPrize3());
                 holder.tpproductdetailst.setText(cheque.getPrize4());

//
        //Toast.makeText(mCtx,cheque.getUser(),Toast.LENGTH_LONG).show();


        holder.msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://androidprojectstechsays.000webhostapp.com/Flower_maegument_system/sent_details.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
//If we are getting success from server
                              //  Toast.makeText(mCtx,response,Toast.LENGTH_LONG).show();


                                if(response.equals("Password sent to your registerd phonenumber"))
                                {
//
                                    new SweetAlertDialog(mCtx, SweetAlertDialog.WARNING_TYPE)
                                            .setTitleText("Track ID sent to  registerd phonenumber")
                                            .setContentText("Back To Home!")
                                            .show();

                                }
                                else{


                                    new SweetAlertDialog(mCtx, SweetAlertDialog.WARNING_TYPE)
                                            .setTitleText("Track ID  not sent to  registerd phonenumber")
                                            .setContentText("Back To Home!")
                                            .show();






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
                        params.put("phone",cheque.getUser());

//returning parameter
                        return params;
                    }

                };

// m = Integer.parseInt(ba) - Integer.parseInt(result.getContents());
// balance.setText(m+"");


//Adding the string request to the queue
                RequestQueue requestQueue = Volley.newRequestQueue(mCtx);
                requestQueue.add(stringRequest);





            }
        });


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {



        TextView tid,tname,tph,temail,taddress,tnos,tprice,tproductname,tpproductdetailst;
Button msg;
        public ProductViewHolder(View itemView) {
            super(itemView);




            tid=itemView.findViewById(R.id.tid);
            tname=itemView.findViewById(R.id.pnn);
            tph=itemView.findViewById(R.id.pph3);
            temail=itemView.findViewById(R.id.pemail);
            taddress=itemView.findViewById(R.id.padd);
            tnos=itemView.findViewById(R.id.noss);
            tprice=itemView.findViewById(R.id.pri);
            tproductname=itemView.findViewById(R.id.prnm);
            tpproductdetailst=itemView.findViewById(R.id.prdt);

            msg=itemView.findViewById(R.id.msg1);



        }

    }



}