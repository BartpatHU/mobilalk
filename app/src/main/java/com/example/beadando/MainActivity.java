package com.example.beadando;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private EditText mName, mHref, mStatus, mStatusReason;
    private Button mSaveBtn, mShowBtn, mSideBtn;
    private FirebaseFirestore db;
    private String uId, uName, uHref, uStatus, uStatusReason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mName = findViewById(R.id.edit_name);
        mHref = findViewById(R.id.edit_href);
        mStatus = findViewById(R.id.edit_status);
        mStatusReason = findViewById(R.id.edit_statusReason);

        mSaveBtn = findViewById(R.id.save_btn);
        mShowBtn = findViewById(R.id.showall_btn);
        mSideBtn = findViewById(R.id.side_btn);

        db = FirebaseFirestore.getInstance();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            mSaveBtn.setText("Update");

            uId = bundle.getString("uId");
            uName = bundle.getString("uName");
            uHref = bundle.getString("uHref");
            uStatus = bundle.getString("uStatus");
            uStatusReason = bundle.getString("uStatusReason");

            mName.setText(uName);
            mHref.setText(uHref);
            mStatus.setText(uStatus);
            mStatusReason.setText(uStatusReason);

        }else{
            mSaveBtn.setText("Save");
        }

        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String name = mName.getText().toString();
                String href = mHref.getText().toString();
                String status = mStatus.getText().toString();
                String statusreason = mStatusReason.getText().toString();

                Bundle bundle1 = getIntent().getExtras();
                if (bundle1 != null){
                    String id = uId;
                    updateToFireStore(id, name, href, status, statusreason);
                }else{
                    String id = UUID.randomUUID().toString();
                    saveToFireStore(id, name, href, status, statusreason);
                }
            }
        });

        mShowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ShowActivity.class));
            }
        });


        mSideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SideActivity.class));
            }
        });
    }

    private void updateToFireStore(String id, String name, String href, String status, String statusreason){
        db.collection("Document").document(id).update("name", name, "href", href, "status", status, "statusreason", statusreason)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Data updated!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this, "Error : " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void saveToFireStore(String id, String name, String href, String status, String statusreason){
        if(!name.isEmpty() && !href.isEmpty() && !status.isEmpty() && !statusreason.isEmpty()){
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", id);
            map.put("name", name);
            map.put("href", href);
            map.put("status", status);
            map.put("statusreason", statusreason);

            db.collection("Document").document(id).set(map)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "Data Saved!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this, "Failed!", Toast.LENGTH_SHORT).show();

                }
            });

        }else{
            Toast.makeText(this,"Empty Fields not Allowed", Toast.LENGTH_SHORT).show();
        }
    }
}