package com.example.firebasepreoject;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReviewActivity extends AppCompatActivity {

    LinearLayoutManager fLayoutManager;
    RecyclerView fRecyclerView;
    FirebaseDatabase fFirebaseDatabase;
    DatabaseReference fRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Reviews");

        String str = getIntent().getStringExtra("pathname");


        fRecyclerView = findViewById(R.id.reviewrecyclerView);
        fRecyclerView.setHasFixedSize(true);

        fRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        fFirebaseDatabase = FirebaseDatabase.getInstance();
        fRef = fFirebaseDatabase.getReference(str);
        //Toast.makeText(ReviewActivity.this,str,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Comment, ComHolder> ffirebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Comment, ComHolder>(
                        Comment.class,
                        R.layout.review_row,
                        ComHolder.class,
                        fRef
                ) {
                    @Override
                    protected void populateViewHolder(ComHolder comHolder, Comment comment, int position) {

                        comHolder.setComments(getApplicationContext(),comment.getCom());
                    }

                };
        fRecyclerView.setAdapter(ffirebaseRecyclerAdapter);
    }

}
