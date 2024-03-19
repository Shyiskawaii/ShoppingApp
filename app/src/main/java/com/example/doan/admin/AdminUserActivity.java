package com.example.doan.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.doan.R;
import com.example.doan.admin.model.UserModel;
import com.example.doan.admin.recycler.UserRecycler;

import java.util.List;

public class AdminUserActivity extends AppCompatActivity {


    EditText etsearch;
    ImageView imgSearch;
    RecyclerView recyclerView;
    DatabaseHelper databaseHelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_customer);

        etsearch = findViewById(R.id.etSearch);
        imgSearch = findViewById(R.id.imgSearch);

        recyclerView = findViewById(R.id.rcv_info_list);

        show(null,0);

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String search = String.valueOf(etsearch.getText());
                show(search,0);
            }
        });
    }

    private void show(String search,int filter){
        List<UserModel> userList = databaseHelper.getUser(search,filter);

        UserRecycler adapter = new UserRecycler(AdminUserActivity.this,userList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(AdminUserActivity.this));
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