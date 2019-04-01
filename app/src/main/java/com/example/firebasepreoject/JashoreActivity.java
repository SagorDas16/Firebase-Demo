package com.example.firebasepreoject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;

public class JashoreActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jashore);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Posts List");

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("Jashore");

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Model, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Model, ViewHolder>(
                        Model.class,
                        R.layout.row,
                        ViewHolder.class,
                        mRef
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Model model, int position) {

                        viewHolder.setDetails(getApplicationContext(),model.getName(), model.getLocation(),model.getImage(),model.getPrice());
                    }

                    //itemclick ->

                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                        ViewHolder viewHolder = super.onCreateViewHolder(parent,viewType);
                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClik(View view, int position) {


                                String mname = getItem(position).getName();
                                String mlocation = getItem(position).getLocation();
                                String mprice = getItem(position).getPrice();
                                String mimage = getItem(position).getImage();
                                String mnumber = getItem(position).getNumber();
                                String mposition = getItem(position).getPosition();

                                Intent intent = new Intent(view.getContext(), DetailsActivity.class);

                                intent.putExtra("name", mname);
                                intent.putExtra("location", mlocation);
                                intent.putExtra("price", mprice);
                                intent.putExtra("image", mimage);
                                intent.putExtra("number", mnumber);
                                intent.putExtra("position", mposition);

                                startActivity(intent);


                            }

                            @Override
                            public void onItemLongClick(View view, int position) {

                            }
                        });

                        return viewHolder;
                    }

                    //itemclick <-
                };

        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }
}
