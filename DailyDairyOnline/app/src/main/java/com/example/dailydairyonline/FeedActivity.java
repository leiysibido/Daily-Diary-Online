package com.example.dailydairyonline;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class FeedActivity extends AppCompatActivity {

    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    FirebaseStorage mStorage;
    RecyclerView recyclerView;
    UserAdapter userAdapter;
    List<UserModel> userModelList;

    FloatingActionButton btnUpload;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);


        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference().child("User");
        mStorage = FirebaseStorage.getInstance();
        recyclerView = findViewById(R.id.recyclerview_id);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userModelList =  new ArrayList<UserModel>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FeedActivity.this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(linearLayoutManager);
        userAdapter = new UserAdapter(FeedActivity.this, userModelList);
        recyclerView.setAdapter(userAdapter);

        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                UserModel userModel = dataSnapshot.getValue(UserModel.class);
                userModelList.add(userModel);
                userAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot datasnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.feed);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.feed:
                        return true;

                }
                return false;
            }
        });



        btnUpload = findViewById(R.id.btnUpload);
        btnUpload.setOnClickListener(view ->{
            Intent intent = new Intent(FeedActivity.this, UploadActivity.class);
            startActivity(intent);});
    }
}