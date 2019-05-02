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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    //itemclick ->
    TextView mpriceTV, mlocationTv, mnameTv, mnumberTv;
    ImageView mimageIv;
    Button call,map,setbutton,review;
    String position,number;
    Comment comment;
    DatabaseReference reff;
    EditText editText;
    String id;


    //itemclik <-

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        id = getIntent().getStringExtra("id");
        editText = findViewById(R.id.comtext);


        comment = new Comment();
        reff = FirebaseDatabase.getInstance().getReference().child(id);
        setbutton = findViewById(R.id.setbutton);
        setbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = editText.getText().toString().trim();
                if(str.length()==0){
                    Toast.makeText(DetailsActivity.this,"Please enter a Review", Toast.LENGTH_SHORT).show();
                }
                else {
                    comment.setCom(str);
                    reff.push().setValue(comment);
                    Toast.makeText(DetailsActivity.this,"Review set successful", Toast.LENGTH_SHORT).show();
                    TextView textView = findViewById(R.id.comtext);
                    textView.setText("");
                }

            }
        });
        review = findViewById(R.id.reviewid);
        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this,ReviewActivity.class);
                intent.putExtra("pathname", id);
                startActivity(intent);
            }
        });




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
