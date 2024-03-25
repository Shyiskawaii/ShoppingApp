package com.example.doan.admin.Brand;

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
import com.example.doan.admin.model.BrandModel;

import java.util.List;

public class BrandRecycler extends RecyclerView.Adapter<BrandRecycler.MyViewHolder> {

    Context context;
    List<BrandModel> brandList;
    public BrandRecycler(Context context, List<BrandModel> brandList){
        this.context = context;
        this.brandList = brandList;
    }
    public BrandRecycler(){
    }

    @NonNull
    @Override
    public BrandRecycler.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.admin_recycler_view_row,parent,false);
        return new BrandRecycler.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BrandRecycler.MyViewHolder holder, int position) {
        holder.txtID.setText(String.valueOf(brandList.get(position).getBrandID()));
        holder.txtName.setText(brandList.get(position).getBrandName());
        holder.txtExtra.setText(brandList.get(position).getBrandDescription());
        holder.txtExtra2.setText(null);

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedPosition = holder.getAdapterPosition();
                if (clickedPosition != RecyclerView.NO_POSITION) {
                    Intent intent = new Intent(context, EditBrandActivity.class);
                    intent.putExtra("brandID", brandList.get(clickedPosition).getBrandID());
                    context.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return brandList.size();
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
