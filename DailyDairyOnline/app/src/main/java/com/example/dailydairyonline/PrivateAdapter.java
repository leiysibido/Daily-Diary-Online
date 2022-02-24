package com.example.dailydairyonline;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.List;

public class PrivateAdapter extends RecyclerView.Adapter<PrivateAdapter.ViewHolder>{

    MyFeedFragment myFeedFragment;
    List<UserModel> userModelList;


    public PrivateAdapter(MyFeedFragment context, List<UserModel> userModelList) {
        this.myFeedFragment = context;
        this.userModelList = userModelList;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_row_for_recyclerview,parent, false);

        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        UserModel userModel = userModelList.get(position);
        holder.tvTitle.setText(" "+userModel.getTitle());
        holder.tvDate.setText(" "+userModel.getDate());
        holder.tvPrivacy.setText(" "+userModel.getPrivacy());

        String imageUri = null;
        imageUri = userModel.getImage();
        Picasso.get().load(imageUri).into(holder.imageView);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), InfoPrivate.class);
                String dateUser = userModel.getDate();
                String privacyUser = userModel.getPrivacy();
                String imgUser = userModel.getImage();
                String titleUser = userModel.getTitle();
                String desUser = userModel.getDescription();

                intent.putExtra("Date", dateUser);
                intent.putExtra("Privacy", privacyUser);
                intent.putExtra("Image", imgUser);
                intent.putExtra("Title", titleUser);
                intent.putExtra("Description", desUser);
                myFeedFragment.startActivity(intent);
            }
        });

    }



    @Override
    public int getItemCount() {
        return userModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        CardView btnView;
        TextView tvTitle;
        TextView tvDate;
        TextView tvPrivacy;

        @RequiresApi(api = Build.VERSION_CODES.N)
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.txtDate);
            tvPrivacy = itemView.findViewById(R.id.txtPrivacy);
            btnView= itemView.findViewById(R.id.btnView);
            imageView= itemView.findViewById(R.id.imageView3);
            tvTitle = itemView.findViewById(R.id.textView2);





        }
    }
}
