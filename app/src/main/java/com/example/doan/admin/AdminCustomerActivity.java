package com.example.doan.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.doan.R;
import com.example.doan.admin.model.CustomerModel;

import java.util.List;

public class AdminCustomerActivity extends AppCompatActivity {

    Button btn_add,btn_refresh;
    EditText et_name,et_password;
    RecyclerView rcv_ino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_customer);

        btn_add = findViewById(R.id.btn_add);
        btn_refresh = findViewById(R.id.btn_refresh);
        et_name = findViewById(R.id.et_Name);
        et_password = findViewById(R.id.et_Pass);

        RecyclerView recyclerView = findViewById(R.id.rcv_info);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<CustomerModel> customerList = databaseHelper.getEveryone();

                CustomerRecycler adapter = new CustomerRecycler(AdminCustomerActivity.this,customerList);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(AdminCustomerActivity.this));
            }
        });



        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                CustomerModel customerModel;

                try {
                    customerModel = new CustomerModel(et_name.getText().toString(), et_password.getText().toString());
                    Toast.makeText(AdminCustomerActivity.this, customerModel.toString(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(AdminCustomerActivity.this, "Error Creating Customer!", Toast.LENGTH_SHORT).show();
                    customerModel = new CustomerModel("error", "error");
                }


                DatabaseHelper databaseHelper = new DatabaseHelper(AdminCustomerActivity.this);
                boolean success = databaseHelper.addOne(customerModel);
                if (success) {
                    Toast.makeText(AdminCustomerActivity.this, "User added successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AdminCustomerActivity.this, "Failed to add user!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}