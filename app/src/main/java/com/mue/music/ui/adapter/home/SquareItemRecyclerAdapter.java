package com.mue.music.ui.adapter.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mue.music.R;
import com.mue.music.model.Album;
import com.mue.music.model.SimpleObject;
import com.mue.music.model.Track;
import com.mue.music.model.enums.AlbumType;

import java.util.ArrayList;
import java.util.List;

public class SquareItemRecyclerAdapter extends RecyclerView.Adapter<SquareItemRecyclerAdapter.SquareItemViewHolder> {
    private final List<CardItem> items;
    private final int layoutId;

    public SquareItemRecyclerAdapter(List<CardItem> items, int layoutId) {
        this.items = items;
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

        public SquareItemViewHolder(View itemView) {
            super(itemView);
            // Các image thuộc 2 xml khác nhau =>
            // Có thể giống id là image do xml là độc lập với nhau => Tái sử dụng cùng id.
            itemLayout = itemView.findViewById(R.id.item);
            imageView = itemView.findViewById(R.id.image);
            txtTitle = itemView.findViewById(R.id.title);
            txtArtist = itemView.findViewById(R.id.artist);
        }

        public void bindData(CardItem item, int position) {
            // Sử dụng Glide để tải ảnh từ URL vào ImageView trong item layout
            Glide.with(imageView.getContext())
                    .load(item.getThumbnail())
                    .into(imageView);
            txtTitle.setText(item.getTitle());
            txtArtist.setText(item.getSubtitle());

            setAllEvent(position);
        }

        private void setAllEvent(int position) {
            itemLayout.setOnClickListener(v -> {
                Toast.makeText(itemLayout.getContext(), "Item square clicked position: " + position, Toast.LENGTH_SHORT).show();
            });
        }
    }
}
