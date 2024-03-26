package com.example.doan.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.R;
import com.example.doan.admin.DatabaseHelper;
import com.example.doan.admin.Phone.AdminPhoneActivity;
import com.example.doan.admin.Phone.PhoneRecycler;
import com.example.doan.admin.model.PhoneModel;

import java.util.List;

public class SearchRecycler extends RecyclerView.Adapter<SearchRecycler.MyViewHolder> {

    Context  context;
    List<PhoneModel> phoneList;

    DatabaseHelper databaseHelper;
    public SearchRecycler(Context context, List<PhoneModel> phoneList){
        this.context = context;
        this.phoneList = phoneList;
    }
    public SearchRecycler(){
    }

    @NonNull
    @Override
    public SearchRecycler.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_phone,parent,false);

        return new SearchRecycler.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchRecycler.MyViewHolder holder, int position) {
        holder.txtName.setText(phoneList.get(position).getPhoneName());
        holder.txtExtra.setText(String.valueOf(phoneList.get(position).getPrice()));
        Bitmap image = byteArrayToBitmap(phoneList.get(position).getPhoneImage());
        holder.imgCard.setImageBitmap(image);


        holder.imgCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedPosition = holder.getAdapterPosition();
                if (clickedPosition != RecyclerView.NO_POSITION) {
                    Intent intent = new Intent(context, DetailsActivity.class);
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
        TextView txtName,txtExtra;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            imgCard = itemView.findViewById(R.id.img_list_phone);
            txtName = itemView.findViewById(R.id.namePhone);
            txtExtra = itemView.findViewById(R.id.pricePhone);


        }
    }
    public Bitmap byteArrayToBitmap ( byte[] byteArray){
        if (byteArray != null && byteArray.length > 0) {
            return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        } else {
            return null;
        }
    }



}
