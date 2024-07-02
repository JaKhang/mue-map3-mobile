package com.mue.music.ui.adapter.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mue.music.R;

import java.util.List;

public class SquareItemRecyclerAdapter extends RecyclerView.Adapter<SquareItemRecyclerAdapter.SquareItemViewHolder> {
    private final List<CardItem> items;
    private final int layoutId;
    private final RecyclerHandler handler;

    public SquareItemRecyclerAdapter(List<CardItem> items, int layoutId, RecyclerHandler handler) {
        this.items = items;
        this.layoutId = layoutId;
        this.handler = handler;
    }

    @NonNull
    @Override
    public SquareItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new SquareItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SquareItemViewHolder holder, int position) {
        CardItem item = items.get(position);
        holder.bindData(item, position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class SquareItemViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout itemLayout;
        private ImageView imageView;
        private TextView txtTitle;
        private TextView txtArtist;
        private RecyclerHandler handler;

        public SquareItemViewHolder(View itemView) {
            super(itemView);
            // Các image thuộc 2 xml khác nhau =>
            // Có thể giống id là image do xml là độc lập với nhau => Tái sử dụng cùng id.
            itemLayout = itemView.findViewById(R.id.item);
            imageView = itemView.findViewById(R.id.image);
            txtTitle = itemView.findViewById(R.id.title);
            txtArtist = itemView.findViewById(R.id.subtitle);
        }

        public SquareItemViewHolder(@NonNull View itemView, RecyclerHandler handler) {
            this(itemView);
            this.handler = handler;
        }

        public void bindData(CardItem item, int position) {
            // Sử dụng Glide để tải ảnh từ URL vào ImageView trong item layout
            Glide.with(imageView.getContext())
                    .load(item.getThumbnail())
                    .placeholder(R.drawable.square_loader)
                    .into(imageView);
            txtTitle.setText(item.getTitle());
            txtArtist.setText(item.getSubtitle());
            itemLayout.setOnClickListener(v -> {
                if (handler != null)
                    handler.onClick(item);
            });
        }

    }
}
