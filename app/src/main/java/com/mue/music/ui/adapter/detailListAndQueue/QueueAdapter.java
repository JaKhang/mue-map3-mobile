package com.mue.music.ui.adapter.detailListAndQueue;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mue.music.R;
import com.mue.music.ui.adapter.home.CardItem;

import java.util.List;

public class QueueAdapter<T extends CardItem> extends RecyclerView.Adapter<QueueAdapter.QueueViewHolder<T>> {
    private List<T> items;
    private int indexActive;

    public QueueAdapter(List<T> items, int indexActive) {
        this.items = items;
        this.indexActive = indexActive;
    }

    @NonNull
    @Override
    public QueueViewHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_result_item, parent, false);
        return new QueueViewHolder<T>(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QueueViewHolder<T> holder, int position) {
        T item = items.get(position);
        holder.bindData(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class QueueViewHolder<T extends CardItem> extends RecyclerView.ViewHolder {
        private TextView txtTitle;
        private TextView txtArtist;

        public QueueViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.title);
            txtArtist = itemView.findViewById(R.id.artist);
        }

        public void bindData(T item) {

        }
    }
}
