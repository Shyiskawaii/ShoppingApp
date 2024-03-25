package com.example.doan.admin.Phone;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.R;
import com.example.doan.admin.Brand.EditBrandActivity;
import com.example.doan.admin.model.BrandModel;
import com.example.doan.admin.model.PhoneModel;

import java.util.List;

public class PhoneRecycler extends RecyclerView.Adapter<PhoneRecycler.MyViewHolder> {

    Context context;
    List<PhoneModel> phoneList;
    public PhoneRecycler(Context context, List<PhoneModel> phoneList){
        this.context = context;
        this.phoneList = phoneList;
    }
    public PhoneRecycler(){
    }

    @NonNull
    @Override
    public PhoneRecycler.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.admin_recycler_view_row,parent,false);
        return new PhoneRecycler.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneRecycler.MyViewHolder holder, int position) {
        holder.txtID.setText(String.valueOf(phoneList.get(position).getPhoneID()));
        holder.txtName.setText(phoneList.get(position).getPhoneName());
        holder.txtExtra.setText(String.valueOf(phoneList.get(position).getPrice()));
        holder.txtExtra2.setText(String.valueOf(phoneList.get(position).getDiscount()));
        Bitmap image = byteArrayToBitmap(phoneList.get(position).getPhoneImage());
        holder.imgCard.setImageBitmap(image);


        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedPosition = holder.getAdapterPosition();
                if (clickedPosition != RecyclerView.NO_POSITION) {
                    Intent intent = new Intent(context, EditPhoneActivity   .class);
                    intent.putExtra("phoneID", phoneList.get(clickedPosition).getPhoneID());
                    context.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return phoneList.size();
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
            imgCard = itemView.findViewById(R.id.imgCard);
            imgCard.setImageTintMode(null);
        }
    }

    public Bitmap byteArrayToBitmap(byte[] byteArray) {
        if (byteArray != null && byteArray.length > 0) {
            return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        } else {
            return null;
        }
    }
}
