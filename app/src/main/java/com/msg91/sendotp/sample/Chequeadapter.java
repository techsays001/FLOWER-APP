package com.msg91.sendotp.sample;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class Chequeadapter extends RecyclerView.Adapter<Chequeadapter.ProductViewHolder> {
    Intent i;
    private Context mCtx;
    private List<Cheque> productList;

    public Chequeadapter(Context mCtx, List<Cheque> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.recycler_c, null);
        return new ProductViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
      final   Cheque cheque;   cheque = productList.get(position);

        //loading the image
holder.blog.setText(cheque.getImage());
        Picasso.get().load(cheque.getPrize()).into(holder.date);
holder.datem.setText(cheque.getUser());
holder.txtt.setText(cheque.getStatus());
       // holder.pid.setText(cheque.getPrize1());



        holder.viewreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i=new Intent(mCtx,Eventok1.class);
                // i.putExtra("BitmapImage", image);
                // your bitmap


              //  i.putExtra("name",cheque.getUser());
                i.putExtra("name1",cheque.getImage());
               // i.putExtra("name2",cheque.getStatus());
                //i.putExtra("name3",cheque.getPrize());
                mCtx.startActivity(i);
            }
        });

        holder.review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i=new Intent(mCtx,Review.class);
                // i.putExtra("BitmapImage", image);
                // your bitmap


                i.putExtra("name",cheque.getUser());
                i.putExtra("name1",cheque.getImage());
                i.putExtra("name2",cheque.getStatus());
                //i.putExtra("name3",cheque.getPrize());
                mCtx.startActivity(i);
            }
        });
holder.purchase.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {


        Intent i=new Intent(mCtx,Purchase.class);
       // i.putExtra("BitmapImage", image);
         // your bitmap

        i.putExtra("image", cheque.getPrize());
        i.putExtra("name",cheque.getUser());
        i.putExtra("name1",cheque.getImage());
        i.putExtra("name2",cheque.getStatus());
        //i.putExtra("name3",cheque.getPrize());
        mCtx.startActivity(i);
    }
});
      //  SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        //Creating editor to store values to shared preferences


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {



        TextView text,txtt,datem,blog;
        ImageView date;
        Button purchase,review,viewreview;
        public ProductViewHolder(View itemView) {
            super(itemView);




            txtt=itemView.findViewById(R.id.pph2);
            date=itemView.findViewById(R.id.t_name1);
            datem=itemView.findViewById(R.id.pph1);
            blog=itemView.findViewById(R.id.t_discription1);
            purchase=itemView.findViewById(R.id.purchase);
            review=itemView.findViewById(R.id.re);
            viewreview=itemView.findViewById(R.id.ve);
          //  pid=itemView.findViewById(R.id.productidd);

        }

    }



}