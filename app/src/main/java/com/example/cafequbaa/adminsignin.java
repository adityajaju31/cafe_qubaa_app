package com.example.cafequbaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class adminsignin extends AppCompatActivity {
    public void adminlogin(View view) {

        EditText usernameEditText = (EditText) findViewById(R.id.adminmail);
        EditText passwordEditText = (EditText) findViewById(R.id.adminpassword);

        String email = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter username", Toast.LENGTH_LONG).show();
            return;
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        } else if(email.equals("admin") && password.equals("admin")){
            startActivity(new Intent(getApplicationContext(), adminhomepage.class));
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminsignin);
    }
}
