package com.mue.music.ui.adapter.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mue.music.R;
import com.mue.music.model_test_ui.Album;

import java.util.List;

// TODO: Album này là album test UI
public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {
    private List<Album> albums;

    public AlbumAdapter(List<Album> albums) {
        this.albums = albums;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_result_item, parent, false);
        return new AlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        Album album = albums.get(position);
        holder.bindData(album);
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public void updateAlbums(List<Album> albums) {
        this.albums = albums;
        notifyDataSetChanged();
    }


    public static class AlbumViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView artistTextView;

        public AlbumViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name);
            artistTextView = itemView.findViewById(R.id.artist);
        }

        public void bindData(Album album) {
            nameTextView.setText(album.getName());
            artistTextView.setText(album.getArtist());
        }
    }
}
