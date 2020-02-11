package com.msg91.sendotp.sample;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Class_time_Admin extends AppCompatActivity {
    EditText productname, productdetails, productprice;
    Button imgbtn, f_btn;
    ImageView imgview,imageView;
    private static int RESULT_LOAD_IMAGE = 1;
    Button submit;
    private Bitmap bitmap;
    private Uri filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_time__admin);
        productname = findViewById(R.id.productname);
        productdetails = findViewById(R.id.productdetailes);
        productprice = findViewById(R.id.productammount);
        imgbtn = findViewById(R.id.imgupload);
       imgview = findViewById(R.id.imgview);
       // productid= findViewById(R.id.productname2);
        f_btn = findViewById(R.id.f_btn);

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
                if (productdetails.getText().toString().isEmpty()) {


                    productdetails.setError("Enter a Value");
                } else if (productprice.getText().toString().isEmpty()) {

                    productprice.setError("enter a value");
                } else {
                    class UploadImage extends AsyncTask<Bitmap, Void, String> {

                        ProgressDialog loading;
                        RequestHandler rh = new RequestHandler();

                        @Override
                        protected void onPreExecute() {
                            super.onPreExecute();
                            loading = ProgressDialog.show(Class_time_Admin.this, "Uploading...", null, true, false);
                        }

                        @Override
                        protected void onPostExecute(String s) {
                            super.onPostExecute(s);
                            loading.dismiss();
                            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
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
                            String result = rh.sendPostRequest("https://androidprojectstechsays.000webhostapp.com/Flower_maegument_system/upload_image.php", data);

                            return result;
                        }
                    }
                    UploadImage ui = new UploadImage();
                    ui.execute(bitmap);
                }
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
