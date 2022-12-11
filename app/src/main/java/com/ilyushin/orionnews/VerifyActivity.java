package com.ilyushin.orionnews;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class VerifyActivity extends AppCompatActivity {
    TextView txtverify;
    Button btnverify;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        txtverify = findViewById(R.id.txtverify);
        btnverify = findViewById(R.id.btnverify);
        mAuth = FirebaseAuth.getInstance();

        if (!mAuth.getCurrentUser().isEmailVerified()){
            btnverify.setVisibility(View.VISIBLE);
            txtverify.setVisibility(View.VISIBLE);
        }

        btnverify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.getCurrentUser().sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(VerifyActivity.this, "Verification Email Sent", Toast.LENGTH_SHORT).show();
                        btnverify.setVisibility(View.GONE);
                        txtverify.setVisibility(View.GONE);
                        startActivity(new Intent(getApplicationContext(),SignInActivity.class));
                    }
                });
            }
        });
    }
}