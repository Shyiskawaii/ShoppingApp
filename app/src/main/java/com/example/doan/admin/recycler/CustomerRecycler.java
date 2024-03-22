package com.example.doan.admin.recycler;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.R;
import com.example.doan.admin.EditUserActivity;
import com.example.doan.admin.model.CustomerModel;

import java.util.List;

public class CustomerRecycler extends RecyclerView.Adapter<CustomerRecycler.MyViewHolder> {

    Context context;
    List<CustomerModel> customerList;
    public CustomerRecycler(Context context, List<CustomerModel> customerList){
        this.context = context;
        this.customerList = customerList;
    }
    public CustomerRecycler(){
    }

    @NonNull
    @Override
    public CustomerRecycler.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.admin_recycler_view_row,parent,false);
        return new CustomerRecycler.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerRecycler.MyViewHolder holder, int position) {

        holder.txtID.setText(String.valueOf(customerList.get(position).getCustomerID()));
        holder.txtName.setText(customerList.get(position).getCustomerName());
        holder.txtExtra.setText(customerList.get(position).getAddress());
        holder.txtExtra2.setText(customerList.get(position).getNumber());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedPosition = holder.getAdapterPosition();
                if (clickedPosition != RecyclerView.NO_POSITION) {
                    Intent intent = new Intent(context, EditUserActivity.class);
                    intent.putExtra("customerID", customerList.get(clickedPosition).getCustomerID());
                    context.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCard;
        TextView txtID,txtName,txtExtra,txtExtra2;
        ImageButton btnEdit;
        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            txtID = itemView.findViewById(R.id.txtID);
            txtName = itemView.findViewById(R.id.txtName);
            txtExtra = itemView.findViewById(R.id.txtExtra);
            txtExtra2 = itemView.findViewById(R.id.txtExtra2);
            btnEdit = itemView.findViewById(R.id.btnEdit);
        }
    }
}
