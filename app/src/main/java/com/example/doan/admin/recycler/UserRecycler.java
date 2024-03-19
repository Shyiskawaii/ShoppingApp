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
import com.example.doan.admin.model.CustomerModel;
import com.example.doan.admin.model.UserModel;

import java.util.List;

public class UserRecycler extends RecyclerView.Adapter<UserRecycler.MyViewHolder> {

    Context context;
    List<UserModel> userList;
    public UserRecycler(Context context, List<UserModel> userList){
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserRecycler.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.admin_recycler_view_row,parent,false);
        return new UserRecycler.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserRecycler.MyViewHolder holder, int position) {

        holder.txtID.setText(String.valueOf(userList.get(position).getUserID()));
        holder.txtName.setText(userList.get(position).getUserName());
        holder.txtExtra.setText(userList.get(position).getRole());
        holder.txtExtra2.setText(userList.get(position).getStatus());

//        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, EditCustomerActivity.class);
//                intent.putExtra("customerId", userList.get(position).getUserID());
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
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
