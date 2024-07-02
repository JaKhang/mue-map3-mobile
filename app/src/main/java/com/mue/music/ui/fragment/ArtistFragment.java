package com.mue.music.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mue.music.R;
import com.mue.music.ui.adapter.home.CardItem;

import java.util.UUID;

public class ArtistFragment extends Fragment {
    private static final String PASSING_ITEM = "passing_item";
    public static ArtistFragment newInstance(CardItem item) {
        ArtistFragment fragment = new ArtistFragment();
        Bundle args = new Bundle();
        args.putSerializable(PASSING_ITEM, item);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("TEST", "ArtistFragment onCreateView success!");
        return inflater.inflate(R.layout.item_list_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CardItem item;
        if (getArguments() != null) {
            item = (CardItem) getArguments().getSerializable(PASSING_ITEM);
//            if (item != null) {
//                UUID uuid = item.getId();
//                // TODO: Gọi API từ uuid, sau đó thêm danh sách bài hát vào RecyclerView
//
//            }

        }
    }
}
