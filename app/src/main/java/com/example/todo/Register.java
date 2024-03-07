package com.example.todo;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {
    TextInputEditText em,pass;
    Button regbtn;
    FirebaseAuth mAuth;
    ProgressBar prog;
    TextView lgn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        em=findViewById(R.id.regemail);
        pass=findViewById(R.id.regpassword);
        regbtn=findViewById(R.id.regbtn);
        mAuth=FirebaseAuth.getInstance();
        prog=findViewById(R.id.rprog);
        lgn=findViewById(R.id.clkreg);

        lgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);
                finish();
            }
        });

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prog.setVisibility(View.VISIBLE);
                String email,pas;
                email=String.valueOf(em.getText());
                pas=String.valueOf(pass.getText());

                if(TextUtils.isEmpty(email) && TextUtils.isEmpty(pas)){
                    Toast.makeText(Register.this, "Enter Credentials", Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(email)){
                    Toast.makeText(Register.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(pas)){
                    Toast.makeText(Register.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email,pas).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        prog.setVisibility(View.GONE);
                        if(task.isSuccessful()){
                            Log.d(TAG,"createUserWithEmail:success");
                            FirebaseUser user=mAuth.getCurrentUser();
                            Toast.makeText(Register.this, "Account Created", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), Login.class);
                            startActivity(i);
                            finish();
                        }else {
                            Log.w(TAG, "createUserWithEmail:failure",task.getException());
                            Toast.makeText(Register.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
