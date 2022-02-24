package com.example.dailydairyonline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    TextView tvName;
    TextView tvNickname;

     private TabLayout tabLayout;
     private ViewPager viewPager;

    FirebaseDatabase mData;
    DatabaseReference dRef;
    FirebaseAuth mAuth;

    List<ProfileModel> profileModelList;

    Button btnLogout;
    AlertDialog.Builder builder;

    com.getbase.floatingactionbutton.FloatingActionButton btnEdit, btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        tabLayout.setupWithViewPager(viewPager);
        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new MyFeedFragment(),"MY FEED");
        vpAdapter.addFragment(new AboutFragment(),"ABOUT");

        viewPager.setAdapter(vpAdapter);

        tvName = findViewById(R.id.viewName);
        tvNickname = findViewById(R.id.viewNickname);

        mData = FirebaseDatabase.getInstance();
        dRef = mData.getReference().child("Profile");
        dRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                String Name = dataSnapshot.child("Name").getValue(String.class);
                String Nickname = dataSnapshot.child("Nickname").getValue(String.class);

                tvName.setText(Name);
                tvNickname.setText(Nickname);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError dataError) {

            }
        });

        btnLogout = findViewById(R.id.btnLogout);
        mAuth = FirebaseAuth.getInstance();

        builder = new AlertDialog.Builder(this);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builder.setMessage("Are you sure would you want to logout?")
                        .setCancelable(false)
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                                finish();
                                Toast.makeText(getApplicationContext(),"Logged Out Successfully",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                Toast.makeText(getApplicationContext(),"You Cancel the Exit Action!",Toast.LENGTH_SHORT).show();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.setTitle("EXIT APP");
                alertDialog.show();
            }
        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.profile);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.feed:
                        startActivity(new Intent(getApplicationContext(),FeedActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.profile:
                        return true;

                }
                return false;
            }
        });


        btnEdit = findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(view ->{
            Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);

            startActivity(intent);});

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(view ->{
            Intent intent = new Intent(ProfileActivity.this, PrivateActivity.class);
            startActivity(intent);});

    }

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null){
            startActivity(new Intent(ProfileActivity.this, MainActivity.class));
        }
    }
}