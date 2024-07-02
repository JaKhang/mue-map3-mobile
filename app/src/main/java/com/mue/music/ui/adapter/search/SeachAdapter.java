package com.mue.music.ui.adapter.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.mue.music.R;
import com.mue.music.ui.adapter.home.CardItem;
import com.mue.music.ui.adapter.home.CardType;

import java.util.List;

// TODO: Track này là item test UI
public class SeachAdapter<T extends CardItem> extends RecyclerView.Adapter<SeachAdapter.ItemViewHolder<T>> {
    private List<T> items;

    public SeachAdapter(List<T> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ItemViewHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_result_item, parent, false);
        return new ItemViewHolder<T>(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder<T> holder, int position) {
        T item = items.get(position);
        holder.bindData(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void update(List<T> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public static class ItemViewHolder<T extends CardItem> extends RecyclerView.ViewHolder {
        private final TextView nameView;
        private final TextView subtitle;
        private final ImageView thumbnailView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.title);
            subtitle = itemView.findViewById(R.id.subtitle);
            thumbnailView = itemView.findViewById(R.id.thumbnail);
        }

        public void bindData(T item) {
            nameView.setText(item.getTitle());
            subtitle.setText(item.getSubtitle());
            if (item.getCardType() == CardType.ARTIST) {
                Glide.with(thumbnailView.getContext())
                        .load(item.getThumbnail())
                        .placeholder(R.drawable.cycle_loader)
                        .circleCrop()
                        .into(thumbnailView);
            } else {
                Glide.with(thumbnailView.getContext())
                        .load(item.getThumbnail())
                        .placeholder(R.drawable.square_loader)
                        .apply(new RequestOptions().transform(new RoundedCorners(12)).error(R.drawable.square_loader).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE))
                        .into(thumbnailView);
            }


            setAllEvent();
        }

        private void setAllEvent() {

        }
    }
}
