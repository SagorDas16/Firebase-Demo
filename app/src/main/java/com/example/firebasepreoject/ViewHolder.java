package com.example.firebasepreoject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder {

    View mView;


    public ViewHolder(@NonNull final View itemView) {
        super(itemView);
        mView = itemView;

        /*
        //item click
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClik(view, getAdapterPosition());
            }
        });
        //item Long Click
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClickListener.onItemLongClick(view, getAdapterPosition());
                return true;
            }
        });*/

    }
    public void setDetails(Context ctx,String name, String location, String image, String price){

        TextView mnameTv = mView.findViewById(R.id.rTitleTv);
        TextView mlocationTv = mView.findViewById(R.id.rDescriptionTv);
        TextView mPriceTv = mView.findViewById(R.id.rPriceTv);
        ImageView mImageTv = mView.findViewById(R.id.rImageView);

        mnameTv.setText(name);
        mlocationTv.setText(location);
        mPriceTv.setText(price);
        Picasso.get().load(image).into(mImageTv);


    }
    /*private ViewHolder.ClickListener mClickListener;

    public interface ClickListener{
        void onItemClik(View view,int position);
        void onItemLongClick(View view, int position);
    }
    public void setOnClickListener(ViewHolder.ClickListener clickListener){
        mClickListener = clickListener;
    }*/

}
