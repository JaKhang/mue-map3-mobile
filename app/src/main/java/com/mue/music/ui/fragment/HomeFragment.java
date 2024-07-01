package com.mue.music.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.mue.music.R;
import com.mue.music.model.Album;
import com.mue.music.model.Artist;
import com.mue.music.model.SimpleObject;
import com.mue.music.model.Track;
import com.mue.music.model.enums.AlbumType;
import com.mue.music.ui.adapter.home.CardItem;
import com.mue.music.util.RenderRecyclerComponentUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        List<CardItem> trackItems = new ArrayList<>();
        List<SimpleObject> artists1 = new ArrayList<>();
        artists1.add(new SimpleObject(UUID.randomUUID(), "GUrbane"));
        Track track1 = new Track("Da Key", "https://i.scdn.co/image/ab67616d0000b27365a4420fd76223a14d5d3d81", artists1);
        trackItems.add(track1);
        List<SimpleObject> artists2 = new ArrayList<>();
        artists2.add(new SimpleObject(UUID.randomUUID(), "VXLLAIN"));
        artists2.add(new SimpleObject(UUID.randomUUID(), "VOJ"));
        artists2.add(new SimpleObject(UUID.randomUUID(), "Narvent"));
        Track track2 = new Track("Distant Echoes", "https://i.scdn.co/image/ab67616d0000b273ae71c97bfe3e39d1f63e7ebc", artists2);
        trackItems.add(track2);
        List<SimpleObject> artists3 = new ArrayList<>();
        artists3.add(new SimpleObject(UUID.randomUUID(), "Sơn Tùng MTP"));
        Track track3 = new Track("Đừng làm trái tim anh đau", "https://i.scdn.co/image/ab67616d0000b273a1bc26cdd8eecd89da3adc39", artists3);
        trackItems.add(track3);

        List<CardItem> albumItems = new ArrayList<>();
        List<SimpleObject> artists4 = new ArrayList<>();
        artists4.add(new SimpleObject(UUID.randomUUID(), "GREY D"));
        artists4.add(new SimpleObject(UUID.randomUUID(), "tlinh"));
        Album album = new Album("vaicaunoicokhiennguoithaydoi", "https://i.scdn.co/image/ab67616d0000b273f5f1c702277641302f435ad7", AlbumType.SINGLE, artists4);
        albumItems.add(album);

        List<CardItem> artistItems = new ArrayList<>();
        CardItem cardItem = new Artist("Ronboogz", "https://i.scdn.co/image/ab6761610000e5eb8de0c8ed480aabd579520b48");
        artistItems.add(cardItem);

        List<CardItem> savedItems = new ArrayList<>();
        savedItems.add(track1);
        savedItems.add(album);

        // Render ra các RecyclerView có image là hình vuông (Đã viết một util chung để render)
        List<RecyclerView> squareRecyclerViews = RenderRecyclerComponentUtil.renderRecyclerViews(view, R.id.track_recycler_view,
                R.id.album_recycler_view, R.id.saved_track_recycler_view );
        List<List<CardItem>> squareImageList = new ArrayList<>();
        squareImageList.add(trackItems);
        squareImageList.add(albumItems);
        squareImageList.add(savedItems);

        List<RecyclerView> circleRecyclerViews = RenderRecyclerComponentUtil.renderRecyclerViews(view, R.id.artist_recycler_view);
        List<List<CardItem>> circleImageList = new ArrayList<>();
        circleImageList.add(artistItems);

        // Khởi tạo Adapter và set Adapter cho các RecyclerView
        // (đối với các item có hình vuông như Track, Podcast)
        RenderRecyclerComponentUtil.setAdapterForSquareItem(squareImageList, squareRecyclerViews, R.layout.track_item, R.layout.album_item, R.layout.saved_item);

        // (đối với các item có hình tròn như Artist)
        RenderRecyclerComponentUtil.setAdapterForCircleItem(circleImageList, circleRecyclerViews, R.layout.artist_item);
    }
}
