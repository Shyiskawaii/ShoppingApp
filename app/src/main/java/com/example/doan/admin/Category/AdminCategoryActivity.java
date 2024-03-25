package com.example.doan.admin.Category;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.doan.R;
import com.example.doan.admin.DatabaseHelper;
import com.example.doan.admin.model.CategoryModel;
import com.example.doan.admin.Customer.CustomerRecycler;

import java.util.List;

public class AdminCategoryActivity extends AppCompatActivity {


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
                if (search.equals(""))
                    search = null;
                show(search,0);
            }
        });
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AddCategoryActivity.class);
                AdminCategoryActivity.this.startActivity(intent);
            }
        });

    }

    private void show(String search,int filter){
        List<CategoryModel> categoryList = databaseHelper.getCategory(search,filter,-1);

        if(categoryList != null){
            CategoryRecycler adapter = new CategoryRecycler(AdminCategoryActivity.this,categoryList);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(AdminCategoryActivity.this));
        }
        else {
            recyclerView.setAdapter(null);
            //Toast.makeText(AdminUserActivity.this, "No users found", Toast.LENGTH_SHORT).show();
        }
    }









}