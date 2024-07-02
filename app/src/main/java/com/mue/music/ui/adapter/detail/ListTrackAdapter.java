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
import com.mue.music.ui.adapter.event.OnItemClickListener;
import com.mue.music.ui.adapter.home.CardItem;

import java.util.List;

public class ListTrackAdapter<T extends CardItem> extends RecyclerView.Adapter<ListTrackAdapter.ListTrackViewHolder<T>> {
    private List<T> items;
    private int indexActive;

    public ListTrackAdapter(List<T> items, int indexActive) {
        this.items = items;
        this.indexActive = indexActive;
    }

    @NonNull
    @Override
    public ListTrackViewHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_result_item, parent, false);
        return new ListTrackViewHolder<T>(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListTrackViewHolder<T> holder, int position) {
        T item = items.get(position);
        holder.bindData(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ListTrackViewHolder<T extends CardItem> extends RecyclerView.ViewHolder {
        private TextView txtTitle;
        private TextView txtArtist;

        public ListTrackViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.title);
            txtArtist = itemView.findViewById(R.id.artist);
        }

        public void bindData(T item) {

        }
    }
}
