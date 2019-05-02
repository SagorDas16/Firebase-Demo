package com.example.firebasepreoject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ComHolder extends RecyclerView.ViewHolder {
    View fView;
    public ComHolder(@NonNull View itemView) {
        super(itemView);
        fView = itemView;
    }
    public void setComments(Context ctx, String comment){

        TextView mreviewTv = fView.findViewById(R.id.rReviewTv);


        mreviewTv.setText(comment);




    }
}
