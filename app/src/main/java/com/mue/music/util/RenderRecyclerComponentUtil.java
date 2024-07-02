package com.mue.music.util;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mue.music.R;
import com.mue.music.ui.adapter.detail.ListTrackRecyclerAdapter;
import com.mue.music.ui.adapter.home.CardItem;
import com.mue.music.ui.adapter.home.CardType;
import com.mue.music.ui.adapter.home.CircleItemRecyclerAdapter;
import com.mue.music.ui.adapter.home.SquareItemRecyclerAdapter;
import com.mue.music.ui.fragment.ArtistFragment;

import java.util.ArrayList;
import java.util.List;

public class RenderRecyclerComponentUtil {
    public static List<RecyclerView> renderRecyclerViews(View view, int... recyclerViewIds) {
        List<RecyclerView> recyclerViews = new ArrayList<>();
        for (Integer idView : recyclerViewIds) {
            RecyclerView recyclerView = view.findViewById(idView);
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
            recyclerViews.add(recyclerView);
        }
        return recyclerViews;
    }

    public static void setAdapterForSquareItem(List<List<CardItem>> items, List<RecyclerView> recyclerViews, int... layoutViewIds) {
        for (int i = 0; i < recyclerViews.size(); i++) {
            // Khởi tạo Adapter và set Adapter cho từng RecyclerView
            SquareItemRecyclerAdapter adapter = new SquareItemRecyclerAdapter(items.get(i), layoutViewIds[i]);
            recyclerViews.get(i).setAdapter(adapter);
        }
    }

    public static void setAdapterForCircleItem(Fragment fragment, List<List<CardItem>> items, List<RecyclerView> recyclerViews, int... layoutViewIds) {
        for (int i = 0; i < recyclerViews.size(); i++) {
            int index = i;
            // Khởi tạo Adapter và set Adapter cho từng RecyclerView
            CircleItemRecyclerAdapter adapter = new CircleItemRecyclerAdapter(items.get(i), layoutViewIds[i], position -> {
                List<CardItem> itemsForThisAdapter = items.get(index);
                CardItem itemClick = itemsForThisAdapter.get(position);
                navigating(fragment, itemClick);
            });
            recyclerViews.get(i).setAdapter(adapter);
        }
    }

    public static void setAdapterForDetailItem(Fragment fragment, List<List<CardItem>> items, List<RecyclerView> recyclerViews, int... layoutViewIds) {
        for (int i = 0; i < recyclerViews.size(); i++) {
            int index = i;
            // Khởi tạo Adapter và set Adapter cho từng RecyclerView
           ListTrackRecyclerAdapter adapter = new ListTrackRecyclerAdapter(items.get(i), position -> {
                List<CardItem> itemsForThisAdapter = items.get(index);
                CardItem itemClick = itemsForThisAdapter.get(position);
                navigating(fragment, itemClick);
            });
            recyclerViews.get(i).setAdapter(adapter);
        }
    }

    private static void navigating(Fragment fragment, CardItem itemClick) {
        if (itemClick.getCardType() == CardType.ARTIST) {
            navigateToArtistFragment(fragment, itemClick);
        }
    }

    private static void navigateToArtistFragment(Fragment fragment, CardItem itemClick) {
        ArtistFragment artistFragment = ArtistFragment.newInstance(itemClick);
        FragmentTransaction transaction = fragment.getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, artistFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
