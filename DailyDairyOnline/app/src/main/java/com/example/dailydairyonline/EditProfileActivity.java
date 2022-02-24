package com.example.dailydairyonline;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfileActivity extends AppCompatActivity {

    EditText etName;
    EditText etNickname;
    EditText etLives;
    EditText etFrom;
    EditText etWork;
    EditText etEduc;
    EditText etStatus;
    EditText etBday;
    EditText etEmail;

    Button btnSave;
    ImageView btnBack;

    FirebaseDatabase mData;
    DatabaseReference dRef;
    String userFname, userNickname, userLives, userFrom, userWork, userEduc, userStatus, userBday, userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        etName = findViewById(R.id.txtName);
        etNickname = findViewById(R.id.txtNickname);
        etLives = findViewById(R.id.txtLives);
        etFrom = findViewById(R.id.txtFrom);
        etWork = findViewById(R.id.txtWork);
        etEduc = findViewById(R.id.txtEduc);
        etStatus = findViewById(R.id.txtStatus);
        etBday = findViewById(R.id.txtBday);
        etEmail = findViewById(R.id.txtEmail);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            userFname = bundle.getString("Name");
            userNickname = bundle.getString("Nickname");
            userLives = bundle.getString("Lives");
            userFrom = bundle.getString("From");
            userWork = bundle.getString("Work");
            userEduc = bundle.getString("Education");
            userStatus = bundle.getString("Status");
            userBday = bundle.getString("Bday");
            userEmail = bundle.getString("Email");
        }
        etName.setText(userFname);
        etNickname.setText(userNickname);
        etLives.setText(userLives);
        etFrom.setText(userFrom);
        etWork.setText(userWork);
        etEduc.setText(userEduc);
        etStatus.setText(userStatus);
        etBday.setText(userBday);
        etEmail.setText(userEmail);


        btnSave = findViewById(R.id.save);

        mData = FirebaseDatabase.getInstance();
        dRef = mData.getReference().child("Profile");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etName.getText().toString();
                String nickname = etNickname.getText().toString();
                String lives = etLives.getText().toString();
                String from = etFrom.getText().toString();
                String work = etWork.getText().toString();
                String educ = etEduc.getText().toString();
                String status = etStatus.getText().toString();
                String bday = etBday.getText().toString();
                String email = etEmail.getText().toString();

                if (name.isEmpty() || nickname.isEmpty() || lives.isEmpty() || from.isEmpty() || work.isEmpty() || educ.isEmpty() || status.isEmpty() || bday.isEmpty() || email.isEmpty()){
                    Toast.makeText(EditProfileActivity.this, "Please Fill the Required Fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    DatabaseReference mRef = dRef;
                    mRef.child("Name").setValue(name);
                    mRef.child("Nickname").setValue(nickname);
                    mRef.child("Lives").setValue(lives);
                    mRef.child("From").setValue(from);
                    mRef.child("Work").setValue(work);
                    mRef.child("Education").setValue(educ);
                    mRef.child("Status").setValue(status);
                    mRef.child("Bday").setValue(bday);
                    mRef.child("Email").setValue(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(EditProfileActivity.this, "Profile Data Saved Successfully", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(EditProfileActivity.this, "Profile Data Unsuccessful Insert", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }
            }
        });


        btnBack = findViewById(R.id.back);
        btnBack.setOnClickListener(view ->{
            Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class);
            startActivity(intent);});


    }
}