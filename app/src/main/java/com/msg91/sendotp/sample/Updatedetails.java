package com.msg91.sendotp.sample;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Updatedetails extends AppCompatActivity {

    Button imgbtn, f_btn;
    ImageView imgview,imageView;
    private static int RESULT_LOAD_IMAGE = 1;
    Button submit;
    Intent i;
    private Bitmap bitmap;
    private Uri filePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatedetails);
      i=getIntent();
        imgbtn = findViewById(R.id.imgupload11);
        imgview = findViewById(R.id.imgview11);
        f_btn = findViewById(R.id.f_btn11);
       // Toast.makeText(getApplicationContext(), i.getStringExtra("id"), Toast.LENGTH_LONG).show();
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

                    class UploadImage extends AsyncTask<Bitmap, Void, String> {

                        ProgressDialog loading;
                        RequestHandler rh = new RequestHandler();

                        @Override
                        protected void onPreExecute() {
                            super.onPreExecute();
                            loading = ProgressDialog.show(Updatedetails.this, "Uploading...", null, true, false);
                        }

                        @Override
                        protected void onPostExecute(String s) {
                            super.onPostExecute(s);
                            loading.dismiss();

                            if(s.equals("success"))
                            {

                                new SweetAlertDialog(Updatedetails.this, SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText("Update Success")
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
                                                                Intent in=new Intent(Updatedetails.this,MainActivityhome2.class);
                                                                startActivity(in);
                                                            }
                                                        })
                                                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                            }
                                        })
                                        .show();




//
                            }

                                //  Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                        }

                        @SuppressLint("WrongThread")
                        @Override
                        protected String doInBackground(Bitmap... params) {
                            bitmap = params[0];
                            String uploadImage = getStringImage(bitmap);

                            HashMap<String, String> data = new HashMap<>();



                            data.put("idd",i.getStringExtra("id"));
                            data.put("img",uploadImage);
                            String result = rh.sendPostRequest("https://androidprojectstechsays.000webhostapp.com/Flower_maegument_system/change_image.php", data);

                            return result;
                        }
                    }
                    UploadImage ui = new UploadImage();
                    ui.execute(bitmap);
                }


        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
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
