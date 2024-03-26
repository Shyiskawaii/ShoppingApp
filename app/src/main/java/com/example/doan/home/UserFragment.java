package com.example.doan.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.doan.LoginActivity;
import com.example.doan.MainActivity;
import com.example.doan.R;
import com.example.doan.admin.AdminActivity;
import com.example.doan.admin.DatabaseHelper;

public class UserFragment extends Fragment {

    Button btn_logout,btn_admin;
    DatabaseHelper databaseHelper;
    SharedPreferences SECTION;

    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_user, container, false);
        databaseHelper = new DatabaseHelper(rootView.getContext());

        btn_logout = rootView.findViewById(R.id.button_logout);
        btn_admin = rootView.findViewById(R.id.button_admin);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSection();
            }
        });

        btn_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(rootView.getContext(), AdminActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

    private void ClearSection() {
        SharedPreferences.Editor editor = SECTION.edit();
        editor.clear();
        editor.apply();
    }
}