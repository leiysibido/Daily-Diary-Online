package com.example.dailydairyonline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class InfoActivity extends AppCompatActivity {

    Button btnBack;
    ImageView imgView;
    TextView titleView, descriptionView, privacyView, dateView;
    String userImage, userTitle, userDescription, userDate, userPrivacy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        privacyView = findViewById(R.id.privacyView);
        dateView = findViewById(R.id.timeView);
        imgView = findViewById(R.id.imgView);
        titleView = findViewById(R.id.titleView);
        descriptionView = findViewById(R.id.descriptionView);

        descriptionView.setMovementMethod(new ScrollingMovementMethod());

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            userDate = bundle.getString("Date");
            userPrivacy = bundle.getString("Privacy");
            userImage = bundle.getString("Image");
            userTitle = bundle.getString("Title");
            userDescription = bundle.getString("Description");
        }

        Picasso.get().load(userImage).into(imgView);
        privacyView.setText(userPrivacy);
        dateView.setText(userDate);
        titleView.setText(userTitle);
        descriptionView.setText(userDescription);



        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(view ->{
            Intent intent = new Intent(InfoActivity.this, FeedActivity.class);
            startActivity(intent);});


    }
}