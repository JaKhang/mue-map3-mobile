package com.mue.music.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.mue.music.R;
import com.mue.music.util.RenderRecyclerComponentUtil;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Khởi tạo dữ liệu mẫu (list các URL ảnh)
        List<String> podcastUrlList = new ArrayList<>();
        podcastUrlList.add("https://i.scdn.co/image/ab6765630000ba8aba6206e9e46f1e9ddabd0ed3");
        podcastUrlList.add("https://i.scdn.co/image/ab6765630000ba8ab8c2ea6689e5497fcf248ea9");
        podcastUrlList.add("https://i.scdn.co/image/ab6765630000ba8a0194fea3b62b99b4088d19a6");

        List<String> trackUrlList = new ArrayList<>();
        trackUrlList.add("https://i.scdn.co/image/ab67616d0000b27365a4420fd76223a14d5d3d81");
        trackUrlList.add("https://i.scdn.co/image/ab67616d0000b273ae71c97bfe3e39d1f63e7ebc");
        trackUrlList.add("https://i.scdn.co/image/ab67616d0000b2738e04b5ec21c60f8745a435e5");

        List<String> artistUrlList = new ArrayList<>();
        artistUrlList.add("https://i.scdn.co/image/ab6761610000e5eb8de0c8ed480aabd579520b48");

        List<String> savedUrlList = new ArrayList<>();
        savedUrlList.add("https://i.scdn.co/image/ab67616d0000b2738cb0cde7228e1cf1b728c635");

        // Render ra các RecyclerView có image là hình vuông (Đã viết một util chung để render)
        List<RecyclerView> squareRecyclerViews = RenderRecyclerComponentUtil.renderRecyclerViews(view,
                R.id.podcast_recycler_view, R.id.track_recycler_view, R.id.saved_track_recycler_view );
        List<List<String>> squareImageList = new ArrayList<>();
        squareImageList.add(podcastUrlList);
        squareImageList.add(trackUrlList);
        squareImageList.add(savedUrlList);

        List<RecyclerView> circleRecyclerViews = RenderRecyclerComponentUtil.renderRecyclerViews(view, R.id.artist_recycler_view);
        List<List<String>> circleImageList = new ArrayList<>();
        circleImageList.add(artistUrlList);

        // Khởi tạo Adapter và set Adapter cho các RecyclerView
        // (đối với các item có hình vuông như Track, Podcast)
        RenderRecyclerComponentUtil.setAdapterForSquareItem(squareImageList, squareRecyclerViews, R.layout.album_item, R.layout.track_item, R.layout.saved_item);

        // (đối với các item có hình tròn như Artist)
        RenderRecyclerComponentUtil.setAdapterForCircleItem(circleImageList, circleRecyclerViews, R.layout.artist_item);
    }
}
