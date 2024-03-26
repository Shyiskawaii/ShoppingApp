package com.example.doan.home;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.doan.R;
import com.example.doan.admin.DatabaseHelper;
import com.example.doan.admin.Phone.AdminPhoneActivity;
import com.example.doan.admin.Phone.PhoneRecycler;
import com.example.doan.admin.model.PhoneModel;

import java.util.List;

public class SearchFragment extends Fragment {

    Context context;
    DatabaseHelper databaseHelper;
    RecyclerView rcv_search_phone;
    private static final String ARG_PARAM1 = null;
    private static final String ARG_PARAM2 = null;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);

        databaseHelper= new DatabaseHelper(context);
        rcv_search_phone = rootView.findViewById(R.id.rcv_search_phone);
        show(null,-1);
        rcv_search_phone.setLayoutManager(new GridLayoutManager(getContext(), 2));

        return rootView;
    }
    private void show(String search,int filter) {

        List<PhoneModel> phoneList = databaseHelper.getPhones(search, filter, -1);

        if (phoneList != null) {
            SearchRecycler adapter = new SearchRecycler(context, phoneList);
            rcv_search_phone.setAdapter(adapter);
            rcv_search_phone.setLayoutManager(new LinearLayoutManager(context));
        } else {
            rcv_search_phone.setAdapter(null);
            //Toast.makeText(AdminUserActivity.this, "No users found", Toast.LENGTH_SHORT).show();
        }

    }

}