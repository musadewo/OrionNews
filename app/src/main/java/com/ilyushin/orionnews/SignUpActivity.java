package com.ilyushin.orionnews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    EditText inpname, inpemail, inppass, inpconfpass;
    String name, email, password, confpass;
    Button btnsignup;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        inpname = findViewById(R.id.inp_name);
        inpemail = findViewById(R.id.inp_email_up);
        inppass = findViewById(R.id.inp_pass);
        inpconfpass = findViewById(R.id.inp_confpass);
        btnsignup = findViewById(R.id.btn_signup);
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = inpname.getText().toString();
                email = inpemail.getText().toString();
                password = inppass.getText().toString();
                confpass = inpconfpass.getText().toString();

                if (name.isEmpty()){
                    inpname.setError("Name is Required");
                }
                if (email.isEmpty()){
                    inpemail.setError("Email is Required");
                }
                if (password.isEmpty()){
                    inppass.setError("Password is Required");
                }
                if (confpass.isEmpty()){
                    inpconfpass.setError("Confirm Password is Required");
                }
                if (!password.equals(confpass)){
                    inpconfpass.setError("Password Do Not Match");
                }

                Toast.makeText(SignUpActivity.this, "Data Validated", Toast.LENGTH_SHORT).show();


                mAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        startActivity(new Intent(getApplicationContext(),VerifyActivity.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        
        TextView txtSignUp = findViewById(R.id.txtSignUp);
        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
            }
        });
    }
}