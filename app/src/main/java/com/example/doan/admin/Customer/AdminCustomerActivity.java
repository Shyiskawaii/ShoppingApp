package com.example.doan.admin.Customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.doan.R;
import com.example.doan.admin.DatabaseHelper;
import com.example.doan.admin.model.CustomerModel;

import java.util.List;

public class AdminCustomerActivity extends AppCompatActivity {


    EditText etsearch;
    ImageView imgSearch,imgAdd;
    CardView imgNewCard;
    RecyclerView recyclerView;
    DatabaseHelper databaseHelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user);

        etsearch = findViewById(R.id.etSearch);
        imgSearch = findViewById(R.id.imgSearch);

        imgAdd = findViewById(R.id.imgNew);
        imgNewCard = findViewById(R.id.imgNewCard);

        recyclerView = findViewById(R.id.rcv_info_list);

        show(null,0);
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String search = String.valueOf(etsearch.getText());
                show(search,0);
            }
        });

        imgAdd.setVisibility(View.GONE);
        imgNewCard.setVisibility(View.GONE);
    }

    private void show(String search,int filter){
        List<CustomerModel> customerList = databaseHelper.getCustomer(search,filter,-1);

        if(customerList != null){
            CustomerRecycler adapter = new CustomerRecycler(AdminCustomerActivity.this,customerList);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(AdminCustomerActivity.this));
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