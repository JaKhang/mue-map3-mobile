package com.mue.music.ui.adapter.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mue.music.R;
import com.mue.music.model_test_ui.Track;

import java.util.List;

// TODO: Track này là track test UI
public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.TrackViewHolder> {
    private List<Track> tracks;

    public TrackAdapter(List<Track> tracks) {
        this.tracks = tracks;
    }

    @NonNull
    @Override
    public TrackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_result_item, parent, false);
        return new TrackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrackViewHolder holder, int position) {
        Track track = tracks.get(position);
        holder.bindData(track);
    }

    @Override
    public int getItemCount() {
        return tracks.size();
    }

    public void updateTracks(List<Track> tracks) {
        this.tracks = tracks;
        notifyDataSetChanged();
    }

    public static class TrackViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView artistTextView;
        private ImageView imageView;

        public TrackViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name);
            artistTextView = itemView.findViewById(R.id.artist);
            imageView = itemView.findViewById(R.id.image);
        }

        public void bindData(Track track) {
            nameTextView.setText(track.getName());
            artistTextView.setText(track.getArtist());
            Glide.with(imageView.getContext())
                    .load(track.getImageUrl())
                    .into(imageView);

            setAllEvent();
        }

        private void setAllEvent() {

        }
    }
}
