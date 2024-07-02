package com.mue.music.ui.adapter.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mue.music.R;

import java.util.List;

public class CircleItemRecyclerAdapter extends RecyclerView.Adapter<CircleItemRecyclerAdapter.CircleItemViewHolder> {
    private final List<CardItem> items;
    private final int layoutId;
    private final RecyclerHandler handler;

    public CircleItemRecyclerAdapter(List<CardItem> items, int layoutId, RecyclerHandler recyclerHandler) {
        this.items = items;
        this.layoutId = layoutId;
        this.handler = recyclerHandler;
    }

    @NonNull
    @Override
    public CircleItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new CircleItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CircleItemViewHolder holder, int position) {
        CardItem item = items.get(position);
        holder.bindData(item, position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class CircleItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        private RecyclerHandler handler;

        public CircleItemViewHolder(View itemView) {
            super(itemView);
            // Các image thuộc 2 xml khác nhau =>
            // Có thể giống id là image do xml là độc lập với nhau => Tái sử dụng cùng id.
            imageView = itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.title);
        }

        public CircleItemViewHolder(@NonNull View itemView, RecyclerHandler handler) {
            this(itemView);
            this.handler = handler;
        }

        public void bindData(CardItem item, int position) {
            // Sử dụng Glide để tải ảnh từ URL vào ImageView trong item layout, thêm circleCrop() để tạo hình tròn
            Glide.with(imageView.getContext())
                    .load(item.getThumbnail())
                    .placeholder(R.drawable.cycle_loader)
                    .circleCrop()
                    .into(imageView);
            textView.setText(item.getTitle());
            imageView.setOnClickListener(v -> {
                if (handler != null){
                    handler.onClick(item);
                }
            });
        }
    }

    public static class CircleLoaderViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;


        public CircleLoaderViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bindData(CardItem item, int position) {
            imageView.setImageResource(R.drawable.cycle_loader);
        }
    }
}
