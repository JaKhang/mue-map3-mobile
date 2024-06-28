package com.mue.music.ui.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mue.music.R;
import com.mue.music.model_test_ui.Album;
import com.mue.music.model_test_ui.Artist;
import com.mue.music.model_test_ui.Category;
import com.mue.music.model_test_ui.Track;
import com.mue.music.ui.adapter.search.AlbumAdapter;
import com.mue.music.ui.adapter.search.ArtistAdapter;
import com.mue.music.ui.adapter.search.CategoryAdapter;
import com.mue.music.ui.adapter.search.TrackAdapter;
import com.mue.music.util.SearchFilterUtil;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
    private RecyclerView recyclerView;
    private EditText searchBar;
    private CategoryAdapter categoryAdapter;
    private TrackAdapter trackAdapter;
    private AlbumAdapter albumAdapter;
    private ArtistAdapter artistAdapter;
    private List<Category> categoryList;
    private List<Track> trackList = new ArrayList<>();
    private List<Album> albumList = new ArrayList<>();
    private List<Artist> artistList = new ArrayList<>();
    private LinearLayout underSearchNav;
    private int selectedNavId = -1;

    public SearchFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        underSearchNav = view.findViewById(R.id.under_search_nav);
        recyclerView = view.findViewById(R.id.recycler_view);
        searchBar = view.findViewById(R.id.search_bar);

        categoryAdapter = new CategoryAdapter(categoryList);
        trackAdapter = new TrackAdapter(trackList);
        albumAdapter = new AlbumAdapter(albumList);
        artistAdapter = new ArtistAdapter(artistList);

        // Khởi tạo trạng thái ban đầu của RecyclerView
        initRecyclerView();

        // Bắt sự kiện đang thay đổi nội dung search (TextWatcher)
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    addAdapter(categoryAdapter);
                    selectedNavId = -1;
                    underSearchNav.setVisibility(View.GONE);
                } else {
                    underSearchNav.setVisibility(View.VISIBLE);
                    if (selectedNavId == -1) {
                        setSelectedNavButton(R.id.nav_tracks);
                        SearchFilterUtil.filterTracks(s.toString(), trackList, trackAdapter);
                        addAdapter(trackAdapter);
                    } else if (selectedNavId == R.id.nav_tracks) {
                        SearchFilterUtil.filterTracks(s.toString(), trackList, trackAdapter);
                        addAdapter(trackAdapter);
                    } else if (selectedNavId == R.id.nav_albums) {
                        SearchFilterUtil.filterAlbums(s.toString(), albumList, albumAdapter);
                        addAdapter(albumAdapter);
                    } else if (selectedNavId == R.id.nav_artists) {
                        SearchFilterUtil.filterArtists(s.toString(), artistList, artistAdapter);
                        addAdapter(artistAdapter);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // Bắt sự kiện bấm vào các nút trên thanh điều hướng
        view.findViewById(R.id.nav_tracks).setOnClickListener(v -> {
            SearchFilterUtil.filterTracks(searchBar.getText().toString(), trackList, trackAdapter);
            setSelectedNavButton(R.id.nav_tracks);
            addAdapter(trackAdapter);
        });

        view.findViewById(R.id.nav_albums).setOnClickListener(v -> {
            SearchFilterUtil.filterAlbums(searchBar.getText().toString(), albumList, albumAdapter);
            setSelectedNavButton(R.id.nav_albums);
            addAdapter(albumAdapter);
        });

        view.findViewById(R.id.nav_artists).setOnClickListener(v -> {
            SearchFilterUtil.filterArtists(searchBar.getText().toString(), artistList, artistAdapter);
            setSelectedNavButton(R.id.nav_artists);
            addAdapter(artistAdapter);
        });
    }

    private void initializeData() {
        categoryList = new ArrayList<>();
        categoryList.add(new Category(1, "Tracks", R.color.green));
        categoryList.add(new Category(2, "Podcasts", R.color.purple));
        categoryList.add(new Category(3, "Charts", R.color.pink));
        categoryList.add(new Category(4, "You Saved", R.color.red));
        categoryList.add(new Category(5, "Artist", R.color.blue));

        trackList = new ArrayList<>();
        trackList.add(new Track("1", 1, "Track 1", "Artist 1"));
        trackList.add(new Track("2", 1, "Track 2", "Artist 2"));
        // Add more tracks as needed
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(categoryAdapter);
    }

    private void addAdapter(RecyclerView.Adapter adapter) {
        if (adapter instanceof CategoryAdapter) {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        recyclerView.setAdapter(adapter);
    }

    private void setSelectedNavButton(int selectedNavId) {
        // Kiểm tra nếu nút được chọn hiện tại khác với nút đã chọn
        if (this.selectedNavId == selectedNavId) {
            return; // Nếu giống nhau thì không cần cập nhật lại
        }
        this.selectedNavId = selectedNavId;

        // Reset trạng thái của tất cả các nút
        underSearchNav.findViewById(R.id.nav_tracks).setSelected(false);
        underSearchNav.findViewById(R.id.nav_artists).setSelected(false);
        underSearchNav.findViewById(R.id.nav_albums).setSelected(false);
        underSearchNav.findViewById(R.id.nav_youLiked).setSelected(false);

        // Đặt trạng thái selected cho nút được chọn
        underSearchNav.findViewById(selectedNavId).setSelected(true);
    }
}
