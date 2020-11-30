package com.josephyubileo.pbp_uas_kel_a;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    FirebaseAuth auth;
    EditText etEmail, etPassword;
    Button login;
    TextView gotoRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.inputEmail);
        etPassword = findViewById(R.id.inputPassword);
        login = findViewById(R.id.btnLogin);
        gotoRegister = findViewById(R.id.btnGotoRegister);
        auth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if(etEmail.getText().toString().isEmpty()){
                    Toast.makeText(com.josephyubileo.pbp_uas_kel_a.LoginActivity.this, "Email is Required!", Toast.LENGTH_SHORT).show();
                }
                if(etPassword.getText().toString().isEmpty()){
                    Toast.makeText(com.josephyubileo.pbp_uas_kel_a.LoginActivity.this, "Password is Required!", Toast.LENGTH_SHORT).show();
                }

                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(com.josephyubileo.pbp_uas_kel_a.LoginActivity.this, "Logged in Successfully!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                        }else{
                            Toast.makeText(com.josephyubileo.pbp_uas_kel_a.LoginActivity.this, "Login Error!" +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        gotoRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            }
        });


    }
}