package com.mue.music.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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

import com.mue.music.BaseApplication;
import com.mue.music.R;
import com.mue.music.api.ApiHandler;
import com.mue.music.model.Album;
import com.mue.music.model.Artist;
import com.mue.music.model.Genre;
import com.mue.music.model.Track;
import com.mue.music.model.domain.ApiBody;
import com.mue.music.model.domain.InfiniteList;
import com.mue.music.model.domain.PageRequest;
import com.mue.music.repository.AlbumRepository;
import com.mue.music.repository.ArtistRepository;
import com.mue.music.repository.GenreRepository;
import com.mue.music.repository.TrackRepository;
import com.mue.music.ui.adapter.home.CardType;
import com.mue.music.ui.adapter.search.GenreAdapter;
import com.mue.music.ui.adapter.search.SeachAdapter;

import java.util.List;

import javax.inject.Inject;

public class SearchFragment extends Fragment implements Runnable {
    private RecyclerView recyclerView;
    private EditText searchBar;
    private GenreAdapter genreAdapter;
    private SeachAdapter<Artist> artistAdapter;
    private SeachAdapter<Album> albumAdapter;
    private SeachAdapter<Track> trackAdapter;
    private SeachAdapter<?> currentAdapter;
    private LinearLayout underSearchNav;
    private View loader;
    private CardType cardType;
    private Handler handler;
    private int selectedNavId;


    public SearchFragment() {
        handler = new Handler();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseApplication application = (BaseApplication) getActivity().getApplication();
        application.getApplicationComponents().inject(this);
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
        loader = view.findViewById(R.id.loader);

        trackAdapter = new SeachAdapter<>(List.of());
        albumAdapter = new SeachAdapter<>(List.of());
        artistAdapter = new SeachAdapter<>(List.of());
        currentAdapter = trackAdapter;
        setSelectedNavButton(R.id.nav_tracks);

        cardType = CardType.TRACK;
        addAdapter(trackAdapter);
        // Khởi tạo trạng thái ban đầu của RecyclerView

        // Bắt sự kiện đang thay đổi nội dung search (TextWatcher)
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    setLoading(false);
                    addAdapter(genreAdapter);
                    underSearchNav.setVisibility(View.GONE);
                } else {
                    if (s.length() == 1)
                        setAdapter();
                    underSearchNav.setVisibility(View.VISIBLE);
                    handler.removeCallbacks(SearchFragment.this);
                    handler.postDelayed(SearchFragment.this, 600);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        ;

        // Bắt sự kiện bấm vào các nút trên thanh điều hướng
        view.findViewById(R.id.nav_tracks).setOnClickListener(v -> {
            setSelectedNavButton(R.id.nav_tracks);
            cardType = CardType.TRACK;
            handleChangeTab();
        });

        view.findViewById(R.id.nav_albums).setOnClickListener(v -> {
            setSelectedNavButton(R.id.nav_albums);
            cardType = CardType.ALBUM;
            handleChangeTab();
        });

        view.findViewById(R.id.nav_artists).setOnClickListener(v -> {
            setSelectedNavButton(R.id.nav_artists);
            cardType = CardType.ARTIST;
            handleChangeTab();
        });

        setLoading(true);
        loadData();
        setLoading(false);
    }

    private void setAdapter() {
        switch (cardType) {
            case ALBUM:
                addAdapter(albumAdapter);
                break;
            case TRACK:
                addAdapter(trackAdapter);
                break;
            case ARTIST:
                addAdapter(artistAdapter);
                break;
        }
    }

    private void handleChangeTab() {

        if (searchBar.getText().toString().isEmpty()) {
            addAdapter(genreAdapter);
            underSearchNav.setVisibility(View.GONE);
        }
        setAdapter();
        handleSearch();


    }

    private void loadData() {
        genreRepository.findAll(new ApiHandler<List<Genre>>() {
            @Override
            public void onSuccess(ApiBody<List<Genre>> body) {
                genreAdapter = new GenreAdapter(body.getData());
                initRecyclerView();
            }

            @Override
            public void onDone() {
                setLoading(false);
            }
        });
    }

    private void setLoading(boolean b) {
        if (b) {
            recyclerView.setVisibility(View.GONE);
            loader.setVisibility(View.VISIBLE);
        } else {
            loader.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }


    private void initializeData() {


        // Add more tracks as needed
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(genreAdapter);
    }

    private void addAdapter(RecyclerView.Adapter<?> adapter) {
        if (adapter instanceof GenreAdapter) {
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


    @Override
    public void run() {
        handler.removeCallbacks(this);
        handleSearch();
    }

    private void handleSearch() {
        String keyword = searchBar.getText().toString();
        if (keyword.isEmpty())
            return;
        setLoading(true);
        if (CardType.TRACK == cardType) {
            trackRepository.search(keyword, 20, new ApiHandler<InfiniteList<Track>>() {
                @Override
                public void onSuccess(ApiBody<InfiniteList<Track>> body) {
                    trackAdapter.update(body.getData().getContent());

                }
                @Override
                public void onDone() {
                    setLoading(false);
                }
            });
        } else if (cardType == CardType.ALBUM) {
            albumRepository.searchByNameAndArtistAndTrack(keyword, PageRequest.of(0, 20), new ApiHandler<InfiniteList<Album>>() {
                @Override
                public void onSuccess(ApiBody<InfiniteList<Album>> body) {
                    albumAdapter.update(body.getData().getContent());
                    Log.i("TEST", body.getData().getContent().toString());
                }

                @Override
                public void onDone() {
                    setLoading(false);

                }
            });
        } else if(cardType == CardType.ARTIST){
            artistRepository.searchByName(keyword, 20, new ApiHandler<InfiniteList<Artist>>() {
                @Override
                public void onSuccess(ApiBody<InfiniteList<Artist>> body) {
                    artistAdapter.update(body.getData().getContent());
                }

                @Override
                public void onDone() {
                    setLoading(false);
                }
            });
        }
    }

    private GenreRepository genreRepository;
    private TrackRepository trackRepository;
    private AlbumRepository albumRepository;
    private ArtistRepository artistRepository;

    @Inject
    public void setGenreRepository(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Inject
    public void setTrackRepository(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Inject
    public void setAlbumRepository(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Inject
    public void setArtistRepository(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }
}
