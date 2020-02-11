package com.msg91.sendotp.sample;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Chequeadapter3 extends RecyclerView.Adapter<Chequeadapter3.ProductViewHolder> {
    Intent i;
    private Context mCtx;
    private List<Cheque3> productList;

    public Chequeadapter3(Context mCtx, List<Cheque3> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.recycler_c2, null);
        return new ProductViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
      final   Cheque3 cheque;   cheque = productList.get(position);

        //loading the image
holder.blog.setText(cheque.getImage());
        Picasso.get().load(cheque.getPrize()).into(holder.date);
holder.datem.setText(cheque.getUser());
holder.txtt.setText(cheque.getStatus());
        holder.pid.setText(cheque.getPrize1());




        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i=new Intent(mCtx,Updatedetails.class);
                i.putExtra("id",cheque.getPrize1());
                mCtx.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {



        TextView text,txtt,datem,blog,pid;
        ImageView date;
        Button update;
        public ProductViewHolder(View itemView) {
            super(itemView);




            txtt=itemView.findViewById(R.id.pph22);
            date=itemView.findViewById(R.id.t_name12);
            datem=itemView.findViewById(R.id.pph12);
            blog=itemView.findViewById(R.id.t_discription12);

         update=itemView.findViewById(R.id.update12);
            pid=itemView.findViewById(R.id.pph14);

        }

    }



}