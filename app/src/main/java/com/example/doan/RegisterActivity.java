package com.example.doan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan.admin.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    EditText edtCustomerName,edtCustomerPassword,edtCustomerConfirmPassword,edtNumber,edtAddress;
    TextView txtLogin;
    Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        databaseHelper = new DatabaseHelper(this);

        edtCustomerName = findViewById(R.id.edtCustomerName);
        edtCustomerPassword = findViewById(R.id.edtCustomerPassword);
        edtCustomerConfirmPassword = findViewById(R.id.edtCustomerConfirmPassword);
        edtNumber = findViewById(R.id.edtNumber);
        edtAddress = findViewById(R.id.edtAddress);

        btnRegister = findViewById(R.id.btnRegister);
        txtLogin = findViewById(R.id.txtLogin);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean success = databaseHelper.Register(edtCustomerName.getText().toString(),
                                                        edtCustomerPassword.getText().toString(),
                                                        edtCustomerConfirmPassword.getText().toString(),
                                                        edtNumber.getText().toString(),
                                                        edtAddress.getText().toString());
                if (success){
                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(RegisterActivity.this, "bug", Toast.LENGTH_SHORT).show();
            }
        });
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }


}