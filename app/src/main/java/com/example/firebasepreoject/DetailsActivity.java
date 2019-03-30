package com.example.firebasepreoject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    /*TextView mpriceTV, mloctionTv, mnameTv;
    ImageView mimageIv;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        /*ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Details");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        mnameTv = findViewById(R.id.titleTv);
        mloctionTv = findViewById(R.id.descriptionTv);
        mpriceTV = findViewById(R.id.priceTv);
        mimageIv = findViewById(R.id.imageView);

        byte[] bytes = getIntent().getByteArrayExtra("image");
        String name = getIntent().getStringExtra("name");
        String location = getIntent().getStringExtra("location");
        String price = getIntent().getStringExtra("price");
        Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0 , bytes.length);
        mnameTv.setText(name);
        mloctionTv.setText(location);
        mpriceTV.setText(price);
        mimageIv.setImageBitmap(bmp);*/


    }
}
