package com.msg91.sendotp.sample;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;


public class StatusFragment2 extends Fragment {
View root;
    EditText productname, productdetails, productprice,productid;
    Button imgbtn, f_btn;
    ImageView imgview,imageView;
    private static int RESULT_LOAD_IMAGE = 1;
    Button submit;
    private Bitmap bitmap;
    private Uri filePath;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
         root= inflater.inflate(R.layout.fragment_status2, container, false);
        productname = root.findViewById(R.id.productname);
        productdetails = root.findViewById(R.id.productdetailes);
        productprice =root. findViewById(R.id.productammount);
        imgbtn = root.findViewById(R.id.imgupload);
        imgview = root.findViewById(R.id.imgview);
        productid=root.findViewById(R.id.productname2);
        f_btn = root.findViewById(R.id.f_btn);

        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);

            }


//                Intent intent = new Intent(Intent.ACTION_PICK);
//                intent.setType("image/*");
//                startActivityForResult(intent,IMAGE_PICK_CODE);

        });


        f_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (productname.getText().toString().isEmpty()) {

                    productname.setError("enter a valuel");

                }
              else  if (productdetails.getText().toString().isEmpty()) {


                    productdetails.setError("Enter a Value");
                } else if (productprice.getText().toString().isEmpty()) {

                    productprice.setError("enter a value");
                }


                if (productid.getText().toString().isEmpty()) {

                    productid.setError("enter a valuel");

                }

                else {
                    class UploadImage extends AsyncTask<Bitmap, Void, String> {

                        ProgressDialog loading;
                        RequestHandler rh = new RequestHandler();

                        @Override
                        protected void onPreExecute() {
                            super.onPreExecute();
                            loading = ProgressDialog.show(getActivity(), "Uploading...", null, true, false);
                        }

                        @Override
                        protected void onPostExecute(String s) {
                            super.onPostExecute(s);
                            loading.dismiss();
                           // Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();



                            if(s.equals("success"))
                            {

                                new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText("success")
                                        .setContentText("Back To Login!")
                                        .setConfirmText("Yes,Login")
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {
                                                sDialog
                                                        .setTitleText("Logining...!")

                                                        .setConfirmText("OK")

                                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                            @Override
                                                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                               productdetails.getText().clear();
                                                                 productname.getText().clear();
                                                                 productprice.getText().clear();
                                                                productid.getText().clear();
                                                            }
                                                        })
                                                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                            }
                                        })
                                        .show();




//
                            }
                            else{
                                new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText("Product Id already Exit")
                                        .setContentText("Back To Home")
                                        .setConfirmText("Yes")
                                        .show();
                                productdetails.getText().clear();
                                productname.getText().clear();
                                productprice.getText().clear();
                                productid.getText().clear();


                            }

                        }

                        @SuppressLint("WrongThread")
                        @Override
                        protected String doInBackground(Bitmap... params) {
                            bitmap = params[0];
                            String uploadImage = getStringImage(bitmap);

                            HashMap<String, String> data = new HashMap<>();


                            data.put("name", productname.getText().toString());
                            data.put("det",productdetails.getText().toString());
                            data.put("price",productprice.getText().toString());
                            //  data.put("id",productid.getText().toString());
                            data.put("img",uploadImage);
                            data.put("pd",productid.getText().toString());
                            String result = rh.sendPostRequest("https://androidprojectstechsays.000webhostapp.com/Flower_maegument_system/upload_image.php", data);

                            return result;
                        }
                    }
                    UploadImage ui = new UploadImage();
                    ui.execute(bitmap);
                }
            }

        });

return root;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                imgview.setImageBitmap(bitmap);
                getStringImage(bitmap);
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }
    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

}
