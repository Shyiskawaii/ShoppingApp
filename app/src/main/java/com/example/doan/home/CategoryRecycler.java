package com.example.doan.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.R;

public class CategoryRecycler extends RecyclerView.Adapter<CategoryRecycler.ViewHolder> {

    // Array to hold the hardcoded data
    private final String[] names = {"Iphone", "Samsung", "Xiomi"};
    private final String[] prices = {"", "", ""};
    private final int[] images = {R.drawable.iphone, R.drawable.samsung, R.drawable.xiaomi};

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phone, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Bind data to the views
        holder.nameTextView.setText(names[position]);
        holder.priceTextView.setText(prices[position]);
        holder.imageView.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        // Return the size of the data array
        return names.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nameTextView;
        TextView priceTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize views
            imageView = itemView.findViewById(R.id.img_list_phone);
            nameTextView = itemView.findViewById(R.id.namePhone);
            priceTextView = itemView.findViewById(R.id.pricePhone);
        }
    }
}
