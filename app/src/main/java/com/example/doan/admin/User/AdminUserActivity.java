package com.example.doan.admin.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.doan.R;
import com.example.doan.admin.DatabaseHelper;
import com.example.doan.admin.model.UserModel;

import java.util.List;

public class AdminUserActivity extends AppCompatActivity {


    EditText etsearch;
    ImageView imgSearch,imgAdd;
    RecyclerView recyclerView;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user);

        databaseHelper = new DatabaseHelper(this);

        etsearch = findViewById(R.id.etSearch);
        imgSearch = findViewById(R.id.imgSearch);

        imgAdd = findViewById(R.id.imgNew);

        recyclerView = findViewById(R.id.rcv_info_list);

        show(null,0);
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String search = String.valueOf(etsearch.getText());
                show(search,0);
            }
        });

        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminUserActivity.this, AddUserActivity.class);
                AdminUserActivity.this.startActivity(intent);
            }
        });
    }

    private void show(String search,int filter){
        List<UserModel> userList = databaseHelper.getUser(search,filter,-1);

        if(userList != null){
            UserRecycler adapter = new UserRecycler(AdminUserActivity.this,userList);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(AdminUserActivity.this));
        }
        else {
            recyclerView.setAdapter(null);
            //Toast.makeText(AdminUserActivity.this, "No users found", Toast.LENGTH_SHORT).show();
        }
    }






//        btn_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                CustomerModel customerModel;
//
//                try {
//                    customerModel = new CustomerModel(et_name.getText().toString(), et_password.getText().toString());
//                    Toast.makeText(AdminCustomerActivity.this, customerModel.toString(), Toast.LENGTH_SHORT).show();
//                } catch (Exception e) {
//                    Toast.makeText(AdminCustomerActivity.this, "Error Creating Customer!", Toast.LENGTH_SHORT).show();
//                    customerModel = new CustomerModel("error", "error");
//                }
//
//
//                DatabaseHelper databaseHelper = new DatabaseHelper(AdminCustomerActivity.this);
//                boolean success = databaseHelper.userRegister(customerModel);
//                if (success) {
//                    Toast.makeText(AdminCustomerActivity.this, "User added successfully!", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(AdminCustomerActivity.this, "Failed to add user!", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });


}