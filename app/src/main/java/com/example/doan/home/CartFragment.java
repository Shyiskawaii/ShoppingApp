package com.example.doan.home;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.doan.R;
import com.example.doan.admin.DatabaseHelper;


public class CartFragment extends Fragment {
    Context context;
    TextView txtEmpty, txtTotal;
    Button btnBuy;
    RecyclerView recCart;
    DatabaseHelper databaseHelper;


    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);

        databaseHelper= new DatabaseHelper(context);
        txtEmpty = rootView.findViewById(R.id.txtEmpty);
        txtTotal = rootView.findViewById(R.id.txtTotal);
        btnBuy = rootView.findViewById(R.id.btnBuy);
        recCart = rootView.findViewById(R.id.recCart);

        return inflater.inflate(R.layout.fragment_cart, container, false);
    }




}
