package com.bignerdranch.android.tradeapp;

import android.content.Context;
import android.content.Intent;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AdapterPost extends RecyclerView.Adapter<AdapterPost.MyHolder> {

    Context context;
    List<ModelPost> postList;

    public AdapterPost(Context context, List<ModelPost> postList) {
        this.context = context;
        this.postList = postList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_posts, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        String uid = postList.get(i).getUid();
        String uEmail = postList.get(i).getuEmail();
        String uName = postList.get(i).getuName();
        String uDp = postList.get(i).getuDp();
        String pId = postList.get(i).getpId();
        String pTitle = postList.get(i).getpTitle();
        String pDescription = postList.get(i).getpDescr();
        String pImage = postList.get(i).getpImage();
        String pTimeStamp = postList.get(i).getpTime();

        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTimeInMillis(Long.parseLong(pTimeStamp));
        String pTime = DateFormat.format("dd/MM/yyyy hh:mm aa", calendar).toString();

        myHolder.uNameTv.setText(uName);
        myHolder.pTimeTv.setText(pTime);
        myHolder.pTitleTv.setText(pTitle);
        myHolder.pDescriptionTv.setText(pDescription);

        try {
            Picasso.get().load(uDp).placeholder(R.drawable.ic_users_black).into(myHolder.uPictureIv);
        }
        catch(Exception e) {

        }

        if(pImage.equals("noImage")) {
            myHolder.pImageIv.setVisibility(View.GONE);
        }
        else {
            try {
                Picasso.get().load(pImage).into(myHolder.pImageIv);
            }
            catch(Exception e) {

            }
        }

        myHolder.moreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "More", Toast.LENGTH_SHORT).show();
            }
        });
        myHolder.upvoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Upvote", Toast.LENGTH_SHORT).show();
            }
        });
        myHolder.commentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Comment", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        ImageView uPictureIv, pImageIv;
        TextView uNameTv, pTimeTv, pTitleTv, pDescriptionTv, pUpvotesTv;
        ImageButton moreBtn;
        Button upvoteBtn, commentBtn;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            uPictureIv = itemView.findViewById(R.id.uPicureIv);
            pImageIv=itemView.findViewById(R.id.pImageIv);
            uNameTv=itemView.findViewById(R.id.uNameTv);
            pTimeTv=itemView.findViewById(R.id.pTimeTv);
            pTitleTv=itemView.findViewById(R.id.pTitleTv);
            pDescriptionTv=itemView.findViewById(R.id.pDescriptionTv);
            pUpvotesTv=itemView.findViewById(R.id.pLikesTv);
            moreBtn=itemView.findViewById(R.id.moreBtn);
            upvoteBtn=itemView.findViewById(R.id.upvoteBtn);
            commentBtn=itemView.findViewById(R.id.commentBtn);

        }
    }
}
