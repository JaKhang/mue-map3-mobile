package com.mue.music.ui.adapter.search;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mue.music.R;
import com.mue.music.model.Genre;


import java.util.List;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.CategoryViewHolder> {
    private List<Genre> genres;

    public GenreAdapter(List<Genre> genres) {
        this.genres = genres;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Genre genre = genres.get(position);
        holder.bindData(genre);
    }

    @Override
    public int getItemCount() {
        return genres.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        CardView cardView;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.category_name);
            cardView = itemView.findViewById(R.id.card_view);
        }

        public void bindData(Genre genre) {
            categoryName.setText(genre.getName());
            cardView.setCardBackgroundColor(Color.parseColor(genre.getColor()));
        }
    }
}
