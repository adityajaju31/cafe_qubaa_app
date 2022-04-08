package com.example.cafequbaa;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cafequbaa.Database.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signuppage extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    static int counter = 0;
    Button btn;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signuppage);
        final EditText nameEditText     = findViewById(R.id.name);
        final EditText usernameEditText = findViewById(R.id.email);
        final EditText passwordEditText = findViewById(R.id.pass);
        final EditText phoneEditText    = findViewById(R.id.phone);
        btn = findViewById(R.id.Signup);
        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("users");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name     = nameEditText.getText().toString().trim();
                String email    = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String phone    = phoneEditText.getText().toString().trim();
                Profile profile=new Profile();
                profile.setUsername(name);
                profile.setEmail(email);
                profile.setPhone(phone);
                mDatabase.push().setValue(profile);
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(signuppage.this, "Please enter name", Toast.LENGTH_LONG).show();
                    return;
                } else if (TextUtils.isEmpty(email)) {
                    Toast.makeText(signuppage.this, "Please enter username", Toast.LENGTH_LONG).show();
                    return;
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(signuppage.this, "Please enter password", Toast.LENGTH_LONG).show();
                    return;
                } else if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(signuppage.this, "Please enter phone", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    SharedPreferences pref=getSharedPreferences("profile", Context.MODE_PRIVATE);
                    SharedPreferences.Editor ed=pref.edit();
                    ed.putString("name",name);
                    ed.putString("email",email);
                    ed.putString("password",password);
                    ed.putString("phone",phone);
                    ed.apply();
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(signuppage.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        finish();
                                        startActivity(new Intent(getApplicationContext(), homepage.class));
                                    }
                                }
                            });
                }
            }
        });

    }
}
