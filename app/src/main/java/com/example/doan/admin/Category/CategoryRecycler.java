package com.example.doan.admin.Category;

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
import com.example.doan.admin.model.CategoryModel;
import com.example.doan.admin.model.UserModel;

import java.util.List;

public class CategoryRecycler extends RecyclerView.Adapter<CategoryRecycler.MyViewHolder> {

    Context context;
    List<CategoryModel> categoryList;
    public CategoryRecycler(Context context, List<CategoryModel> categoryList){
        this.context = context;
        this.categoryList = categoryList;
    }
    public CategoryRecycler(){
    }

    @NonNull
    @Override
    public CategoryRecycler.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.admin_recycler_view_row,parent,false);
        return new CategoryRecycler.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRecycler.MyViewHolder holder, int position) {

        holder.txtID.setText(String.valueOf(categoryList.get(position).getCategoryID()));
        holder.txtName.setText(categoryList.get(position).getCategoryName());
        holder.txtExtra.setText(categoryList.get(position).getCategoryDescription());
        holder.txtExtra2.setText(null);

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedPosition = holder.getAdapterPosition();
                if (clickedPosition != RecyclerView.NO_POSITION) {
                    Intent intent = new Intent(context, EditCategoryActivity.class);
                    intent.putExtra("categoryID", categoryList.get(clickedPosition).getCategoryID());
                    context.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
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
