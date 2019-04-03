package com.example.firebasepreoject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    //itemclick ->
    TextView mpriceTV, mlocationTv, mnameTv, mnumberTv;
    ImageView mimageIv;
    Button call,map;
    String position,number;

    //itemclik <-

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //itemclick->

        ActionBar actionBar = getSupportActionBar();
        //actionBar.setTitle("Details");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        mnameTv = findViewById(R.id.nameTv);
        mlocationTv = findViewById(R.id.locationTv);
        mpriceTV = findViewById(R.id.priceTv);
        mimageIv = findViewById(R.id.imageView);
        mnumberTv = findViewById(R.id.numberTv);
        call = findViewById(R.id.callbutton);
        map = findViewById(R.id.mapbutton);

        String image = getIntent().getStringExtra("image");
        String name = getIntent().getStringExtra("name");
        String location = getIntent().getStringExtra("location");
        String price = getIntent().getStringExtra("price");
        number = getIntent().getStringExtra("number");
        position = getIntent().getStringExtra("position");

        actionBar.setTitle(name);

        mnameTv.setText(name);
        mlocationTv.setText(location);
        mpriceTV.setText(price);
        mnumberTv.setText(number);
        Picasso.get().load(image).into(mimageIv);

        call.setOnClickListener(this);
        map.setOnClickListener(this);

        //itemclick <-


    }
    //itemclick ->

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.callbutton){
            String str = "tel:"+ number;
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse(str));
            startActivity(intent);
        }
        if(v.getId() == R.id.mapbutton){
            Uri ref = Uri.parse(position);
            Intent intent = new Intent(Intent.ACTION_VIEW,ref);
            intent.setPackage("com.google.android.apps.maps");
            startActivity(intent);
        }
    }
    //itemclick <-
}
