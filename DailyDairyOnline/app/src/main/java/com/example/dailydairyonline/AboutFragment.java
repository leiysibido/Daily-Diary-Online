package com.example.dailydairyonline;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class AboutFragment extends Fragment {

    TextView tvLives;
    TextView tvFrom;
    TextView tvWork;
    TextView tvEduc;
    TextView tvStatus;
    TextView tvBday;
    TextView tvEmail;

    FirebaseDatabase mData;
    DatabaseReference dRef;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        tvLives = view.findViewById(R.id.viewLives);
        tvFrom = view.findViewById(R.id.viewFrom);
        tvWork = view.findViewById(R.id.viewWork);
        tvEduc = view.findViewById(R.id.viewEduc);
        tvStatus = view.findViewById(R.id.viewStatus);
        tvBday = view.findViewById(R.id.viewBday);
        tvEmail = view.findViewById(R.id.viewEmail);

        mData = FirebaseDatabase.getInstance();
        dRef = mData.getReference().child("Profile");
        dRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                String Lives = dataSnapshot.child("Lives").getValue(String.class);
                String From = dataSnapshot.child("From").getValue(String.class);
                String Work = dataSnapshot.child("Work").getValue(String.class);
                String Educ = dataSnapshot.child("Education").getValue(String.class);
                String Status = dataSnapshot.child("Status").getValue(String.class);
                String Bday = dataSnapshot.child("Bday").getValue(String.class);
                String Email = dataSnapshot.child("Email").getValue(String.class);


                tvLives.setText(Lives);
                tvFrom.setText(From);
                tvWork.setText(Work);
                tvEduc.setText(Educ);
                tvStatus.setText(Status);
                tvBday.setText(Bday);
                tvEmail.setText(Email);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError dataError) {

            }
        });

        return view;
    }
}