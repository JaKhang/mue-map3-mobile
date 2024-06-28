package com.mue.music.util;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mue.music.ui.adapter.home.CircleItemRecyclerAdapter;
import com.mue.music.ui.adapter.home.SquareItemRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class RenderRecyclerComponentUtil {
    // Tạo RecyclerView và thiết lập LayoutManager
    public static List<RecyclerView> renderRecyclerViews(View view, int ...recyclerViewIds) {
        List<RecyclerView> recyclerViews = new ArrayList<>();
        for (Integer idView : recyclerViewIds) {
            RecyclerView recyclerView = view.findViewById(idView);
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
            recyclerViews.add(recyclerView);
        }
        return recyclerViews;
    }

    public static void setAdapterForSquareItem(List<List<String>> urlList, List<RecyclerView> recyclerViews, int ...layoutViewIds) {
        for (int i = 0; i < recyclerViews.size(); i++) {
            // Khởi tạo Adapter và set Adapter cho từng RecyclerView
            SquareItemRecyclerAdapter adapter = new SquareItemRecyclerAdapter(urlList.get(i), layoutViewIds[i]);
            recyclerViews.get(i).setAdapter(adapter);
        }
    }

    public static void setAdapterForCircleItem(List<List<String>> urlList, List<RecyclerView> recyclerViews, int ...layoutViewIds) {
        for (int i = 0; i < recyclerViews.size(); i++) {
            // Khởi tạo Adapter và set Adapter cho từng RecyclerView
            CircleItemRecyclerAdapter adapter = new CircleItemRecyclerAdapter(urlList.get(i), layoutViewIds[i]);
            recyclerViews.get(i).setAdapter(adapter);
        }
    }
}
