package com.example.cafequbaa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import com.google.firebase.auth.FirebaseAuth;

public class signInpage extends AppCompatActivity {

    public void login(View view) {
        FirebaseAuth firebaseAuth;
         EditText usernameEditText = (EditText) findViewById(R.id.mail);
         EditText passwordEditText = (EditText) findViewById(R.id.password);
        firebaseAuth = FirebaseAuth.getInstance();

        String email = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter username", Toast.LENGTH_LONG).show();
            return;
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        } else {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                finish();
                                startActivity(new Intent(getApplicationContext(), homepage.class));
                            }
                            else {
                                Toast.makeText(signInpage.this, "Enter correct details", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
    public void  signup(View view){
        startActivity(new Intent(getApplicationContext(), signuppage.class));
    }

    public void  admin(View view){
        startActivity(new Intent(getApplicationContext(), adminsignin.class));
        finish();
    }
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_inpage);


        }
}
