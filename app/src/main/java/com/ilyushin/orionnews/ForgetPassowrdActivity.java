package com.ilyushin.orionnews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassowrdActivity extends AppCompatActivity {
    EditText email;
    Button confirm;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_passowrd);
        email = findViewById(R.id.inp_email_forget);
        confirm = findViewById(R.id.btn_forget);
        mAuth = FirebaseAuth.getInstance();

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String useremail = email.getText().toString().trim();
                if (TextUtils.isEmpty(useremail)){
                    Toast.makeText(ForgetPassowrdActivity.this, "Please Enter the Email", Toast.LENGTH_SHORT).show();
                }else {
                    mAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(ForgetPassowrdActivity.this, "Please Check Your Email", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),SignInActivity.class));
                            }else {
                                String message = task.getException().getMessage();
                                Toast.makeText(ForgetPassowrdActivity.this, "Error Occured : "+message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}