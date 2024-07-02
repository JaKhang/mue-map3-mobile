package com.mue.music.util;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mue.music.R;
import com.mue.music.ui.adapter.home.CardItem;
import com.mue.music.ui.adapter.home.CircleItemRecyclerAdapter;
import com.mue.music.ui.adapter.home.RecyclerHandler;
import com.mue.music.ui.adapter.home.SquareItemRecyclerAdapter;

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

    public static RecyclerView initRecycler(View view, int id) {
        RecyclerView recyclerView = view.findViewById(id);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
        return recyclerView;
    }

    public static void renderSquareItem(List<CardItem> cardItems, RecyclerView view, RecyclerHandler handler){
        SquareItemRecyclerAdapter adapter = new SquareItemRecyclerAdapter(cardItems, R.layout.album_item, handler);
        view.setAdapter(adapter);
    }

    public static void renderCircleItem(List<CardItem> cardItems, RecyclerView view,RecyclerHandler handler){
        CircleItemRecyclerAdapter adapter = new CircleItemRecyclerAdapter(cardItems, R.layout.artist_item, handler);
        view.setAdapter(adapter);
    }


    public static void setAdapterForSquareItem(List<List<CardItem>> items, List<RecyclerView> recyclerViews, int... layoutViewIds) {
        for (int i = 0; i < items.size(); i++) {
            // Khởi tạo Adapter và set Adapter cho từng RecyclerView
//            SquareItemRecyclerAdapter adapter = new SquareItemRecyclerAdapter(items.get(i), layoutViewIds[i]);
//            recyclerViews.get(i).setAdapter(adapter);
        }
    }

    public static void setAdapterForCircleItem(List<List<CardItem>> items, List<RecyclerView> recyclerViews, int... layoutViewIds) {
        for (int i = 0; i < recyclerViews.size(); i++) {
//            // Khởi tạo Adapter và set Adapter cho từng RecyclerView
//            CircleItemRecyclerAdapter adapter = new CircleItemRecyclerAdapter(items.get(i), layoutViewIds[i]);
//            recyclerViews.get(i).setAdapter(adapter);
        }
    }
}
