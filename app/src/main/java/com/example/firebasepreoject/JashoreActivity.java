package com.example.firebasepreoject;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;

public class JashoreActivity extends AppCompatActivity {

    LinearLayoutManager mLayoutManager;
    SharedPreferences mSharedPref;
    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jashore);
        String loc = getIntent().getStringExtra("location");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(loc);

        mSharedPref = getSharedPreferences("SortSettings", MODE_PRIVATE);
        String mSorting = mSharedPref.getString("Sort", "High to Low");


        if(mSorting.equals("Low to High")){
            mLayoutManager = new LinearLayoutManager(this);
            mLayoutManager.setReverseLayout(true);
            mLayoutManager.setStackFromEnd(true);
        }
        else if(mSorting.equals("High to Low")){
            mLayoutManager = new LinearLayoutManager(this);
            mLayoutManager.setReverseLayout(false);
            mLayoutManager.setStackFromEnd(false);
        }

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(mLayoutManager);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference(loc);
        //mRef = mFirebaseDatabase.getReference("khulna/com");

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

                        viewHolder.setDetails(getApplicationContext(),model.getName(), model.getLocation(),model.getImage(),model.getPrice(),model.getRating());
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
                                String mid = getItem(position).getId();

                                Intent intent = new Intent(view.getContext(), DetailsActivity.class);

                                intent.putExtra("name", mname);
                                intent.putExtra("location", mlocation);
                                intent.putExtra("price", mprice);
                                intent.putExtra("image", mimage);
                                intent.putExtra("number", mnumber);
                                intent.putExtra("position", mposition);
                                intent.putExtra("id", mid);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu , menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_sort){
            showSortDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void showSortDialog(){
        String[] sortOptions = {"Low to High", "High to Low"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sort by")
                .setIcon(R.drawable.ic_action_sort)
                .setItems(sortOptions, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which == 0){
                            SharedPreferences.Editor editor = mSharedPref.edit();
                            editor.putString("Sort", "Low to High");
                            editor.apply();
                            recreate();
                        }
                        else if(which == 1){
                            SharedPreferences.Editor editor = mSharedPref.edit();
                            editor.putString("Sort", "High to Low");
                            editor.apply();
                            recreate();
                        }
                    }
                });
        builder.show();
    }
}
