package com.example.dailydairyonline;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;


public class MyFeedFragment extends Fragment {

    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    FirebaseStorage mStorage;
    RecyclerView recyclerView;
    PrivateAdapter privateAdapter;
    List<UserModel> userModelList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_feed, container, false);

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference().child("Private User");
        mStorage = FirebaseStorage.getInstance();
        recyclerView = view.findViewById(R.id.recyclerview_id);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        userModelList = new ArrayList<UserModel>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);


        privateAdapter = new PrivateAdapter(MyFeedFragment.this, userModelList);
        recyclerView.setAdapter(privateAdapter);

        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                UserModel userModel = dataSnapshot.getValue(UserModel.class);
                userModelList.add(userModel);
                privateAdapter.notifyDataSetChanged();
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
        return view;
    }
}
