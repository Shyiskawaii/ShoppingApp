package com.example.doan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.doan.admin.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {
    Context context;
    DatabaseHelper databaseHelper;
    Button btnLogin;
    EditText EdtUserName,EdtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseHelper = new DatabaseHelper(this);

        EdtUserName = findViewById(R.id.edtUserName);
        EdtPassword = findViewById(R.id.edtPassword);

        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean success = databaseHelper.Login(EdtUserName.getText().toString(), EdtPassword.getText().toString());

                if (success) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "something bug", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}



