package com.mue.music.ui.adapter.detail;

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
import com.mue.music.model.TrackDetails;
import com.mue.music.ui.adapter.event.OnItemClickListener;
import com.mue.music.ui.adapter.home.CardItem;

import java.util.List;

public class ListTrackRecyclerAdapter extends RecyclerView.Adapter<ListTrackRecyclerAdapter.ListTrackViewHolder> {
    private List<CardItem> items;
    private final OnItemClickListener listener;

    public ListTrackRecyclerAdapter(List<CardItem> items, OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ListTrackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_detail, parent, false);
        return new ListTrackViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ListTrackViewHolder holder, int position) {
        CardItem item = items.get(position);
        holder.bindData(item);
    }

    public void updateItems(List<CardItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ListTrackViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layoutItem;
        private ImageView backgroundImg;
        private TextView txtName;
        private TextView txtCounter;

        public ListTrackViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            this.layoutItem = itemView.findViewById(R.id.track_item);
            this.backgroundImg = itemView.findViewById(R.id.track_image);
            this.txtName = itemView.findViewById(R.id.track_name);
            this.txtCounter = itemView.findViewById(R.id.track_counter);

            layoutItem.setOnClickListener(v -> {

            });
        }

        public void bindData(CardItem item) {
            Glide.with(backgroundImg.getContext())
                    .load(item.getImageUrl())
                    .into(backgroundImg);
            txtName.setText(item.getTitle());
            txtCounter.setText("");
        }
    }
}
