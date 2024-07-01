package com.mue.music.ui.adapter.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mue.music.R;

import java.util.List;

public class SquareItemRecyclerAdapter extends RecyclerView.Adapter<SquareItemRecyclerAdapter.SquareItemViewHolder> {
    private final List<String> imageUrlList;
    private final int layoutId;

    public SquareItemRecyclerAdapter(List<String> imageUrlList, int layoutId) {
        this.imageUrlList = imageUrlList;
        this.layoutId = layoutId;
    }

    @NonNull
    @Override
    public SquareItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new SquareItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SquareItemViewHolder holder, int position) {
        String imageUrl = imageUrlList.get(position);
        holder.bindData(imageUrl, position);
    }

    @Override
    public int getItemCount() {
        return imageUrlList.size();
    }

    public static class SquareItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public SquareItemViewHolder(View itemView) {
            super(itemView);
            // Các image thuộc 2 xml khác nhau =>
            // Có thể giống id là image do xml là độc lập với nhau => Tái sử dụng cùng id.
            imageView = itemView.findViewById(R.id.image);
        }

        public void bindData(String imageUrl, int position) {
            // Sử dụng Glide để tải ảnh từ URL vào ImageView trong item layout
            Glide.with(imageView.getContext())
                    .load(imageUrl)
                    .into(imageView);

            setAllEvent(position);
        }

        private void setAllEvent(int position) {
            imageView.setOnClickListener(v -> {
                Toast.makeText(imageView.getContext(), "Image square clicked position: " + position, Toast.LENGTH_SHORT).show();
            });
        }
    }
}
