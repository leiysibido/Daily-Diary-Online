package com.example.dailydairyonline;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;

public class PrivateActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    FirebaseStorage mStorage;
    ImageButton imageButton;
    EditText edTitle;
    EditText edDesc;
    Button btnUpload;
    TextView txtViewDate;
    private static final int Gallery_Code=1;
    Uri imageUrl=null;
    ProgressDialog progressDialog;
    private Spinner spinner;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private);

        txtViewDate = findViewById(R.id.txtViewDate);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        txtViewDate.setText(currentDate);

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Privacy, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        imageButton = findViewById(R.id.imageButton);
        edTitle = findViewById(R.id.title);
        edDesc = findViewById(R.id.description);
        btnUpload = findViewById(R.id.upload);

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference().child("Private User");
        mStorage = FirebaseStorage.getInstance();
        progressDialog = new ProgressDialog(this);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/");
                startActivityForResult(intent, Gallery_Code);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==Gallery_Code && resultCode==RESULT_OK){
            imageUrl = data.getData();
            imageButton.setImageURI(imageUrl);
        }

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = txtViewDate.getText().toString().trim();
                String spin = spinner.getSelectedItem().toString().trim();
                String title = edTitle.getText().toString().trim();
                String description = edDesc.getText().toString().trim();

                if (!(title.isEmpty() && description.isEmpty() && imageUrl!=null)){

                    progressDialog.setTitle("Uploading...");
                    progressDialog.show();

                    StorageReference filepath = mStorage.getReference().child("imagePost").child(imageUrl.getLastPathSegment());
                    filepath.putFile(imageUrl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Task<Uri> downloadUrl = taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {

                                    String t = task.getResult().toString();

                                    DatabaseReference newPost = mRef.push();

                                    newPost.child("Date").setValue(date);
                                    newPost.child("Privacy").setValue(spin);
                                    newPost.child("Title").setValue(title);
                                    newPost.child("Description").setValue(description);
                                    newPost.child("image").setValue(task.getResult().toString());
                                    progressDialog.dismiss();

                                    Intent intent = new Intent(PrivateActivity.this, ProfileActivity.class);
                                    startActivity(intent);

                                }
                            });

                        }
                    });
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String choice = parent.getItemAtPosition(position).toString();
        Toast.makeText(getApplicationContext(), choice, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
