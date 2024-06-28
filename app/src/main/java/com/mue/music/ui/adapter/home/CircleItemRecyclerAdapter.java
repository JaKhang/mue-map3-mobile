package com.mue.music.ui.adapter.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mue.music.R;

import java.util.List;

public class CircleItemRecyclerAdapter extends RecyclerView.Adapter<CircleItemRecyclerAdapter.CircleItemViewHolder> {
    private final List<String> imageUrlList;
    private final int layoutId;

    public CircleItemRecyclerAdapter(List<String> imageUrlList, int layoutId) {
        this.imageUrlList = imageUrlList;
        this.layoutId = layoutId;
    }

    @NonNull
    @Override
    public CircleItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new CircleItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CircleItemViewHolder holder, int position) {
        String imageUrl = imageUrlList.get(position);

        // Sử dụng Glide để tải ảnh từ URL vào ImageView trong item layout, thêm circleCrop() để tạo hình tròn
        Glide.with(holder.imageView.getContext())
                .load(imageUrl)
                .circleCrop()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return imageUrlList.size();
    }

    public static class CircleItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public CircleItemViewHolder(View itemView) {
            super(itemView);
            // Các image thuộc 2 xml khác nhau =>
            // Có thể giống id là image do xml là độc lập với nhau => Tái sử dụng cùng id.
            imageView = itemView.findViewById(R.id.image);
        }
    }
}
