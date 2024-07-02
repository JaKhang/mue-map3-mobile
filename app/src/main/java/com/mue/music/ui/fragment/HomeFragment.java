package com.mue.music.ui.fragment;

import static com.mue.music.util.RenderRecyclerComponentUtil.initRecycler;
import static com.mue.music.util.RenderRecyclerComponentUtil.renderCircleItem;
import static com.mue.music.util.RenderRecyclerComponentUtil.renderSquareItem;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.mue.music.BaseApplication;
import com.mue.music.R;
import com.mue.music.api.ApiHandler;
import com.mue.music.model.Album;
import com.mue.music.model.Artist;
import com.mue.music.model.Track;
import com.mue.music.model.domain.ApiBody;
import com.mue.music.model.domain.InfiniteList;
import com.mue.music.model.domain.PageRequest;
import com.mue.music.model.domain.Sort;
import com.mue.music.repository.ArtistRepository;
import com.mue.music.ui.adapter.home.CardItem;
import com.mue.music.repository.AlbumRepository;
import com.mue.music.repository.PlayListRepository;
import com.mue.music.repository.UserRepository;
import com.mue.music.ui.adapter.home.RecyclerHandler;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.inject.Inject;

public class HomeFragment extends Fragment implements RecyclerHandler {
    private RecyclerView artistsView;
    private RecyclerView bandsView;
    private RecyclerView albumsView;
    private RecyclerView savedTracksView;
    private RecyclerView tracksView;
    private Set<Integer> taskId = new TreeSet<>();
    private View mainContent;
    private View loader;


    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        BaseApplication application = (BaseApplication) getActivity().getApplication();
        application.getApplicationComponents().inject(this);
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainContent = view.findViewById(R.id.main_home);
        loader = view.findViewById(R.id.loader);

        setLoading(true);
        init(view);
        loadData();

    }

    private void init(View view) {
        albumsView = initRecycler(view, R.id.album_recycler_view);
        artistsView = initRecycler(view, R.id.artist_recycler_view);
        bandsView = initRecycler(view, R.id.band_recycler_view);
        savedTracksView = initRecycler(view, R.id.saved_track_recycler_view);
        tracksView = initRecycler(view, R.id.track_recycler_view);
    }

    private void loadData() {
        taskId.add(1);
        taskId.add(2);
        taskId.add(3);
        taskId.add(4);
        taskId.add(5);
        albumRepository.findAll(PageRequest.of(0, 10, "releaseDate", Sort.Direction.DESC), new ApiHandler<InfiniteList<Album>>() {
            @Override
            public void onSuccess(ApiBody<InfiniteList<Album>> body) {
                ApiHandler.super.onSuccess(body);
                List<CardItem> albums = body.getData().getContent().stream().map((album -> (CardItem) album)).collect(Collectors.toList());
                renderSquareItem(albums, albumsView, HomeFragment.this);

            }

            @Override
            public void onDone() {
                handleDone(1);
            }
        });
        artistRepository.findAll(PageRequest.of(0, 10, "singedTracks", Sort.Direction.DESC), "isBand:true", new ApiHandler<InfiniteList<Artist>>() {
            @Override
            public void onSuccess(ApiBody<InfiniteList<Artist>> body) {
                ApiHandler.super.onSuccess(body);
                List<CardItem> artists = body.getData().getContent().stream().map((album -> (CardItem) album)).collect(Collectors.toList());
                renderCircleItem(artists, bandsView, HomeFragment.this);

            }

            @Override
            public void onDone() {
                handleDone(2);
            }
        });
        artistRepository.findAll(PageRequest.of(0, 10, "singedTracks", Sort.Direction.DESC), "isBand:false", new ApiHandler<InfiniteList<Artist>>() {
            @Override
            public void onSuccess(ApiBody<InfiniteList<Artist>> body) {
                ApiHandler.super.onSuccess(body);
                List<CardItem> artists = body.getData().getContent().stream().map((artist -> (CardItem) artist)).collect(Collectors.toList());
                renderCircleItem(artists, artistsView, HomeFragment.this);

            }

            @Override
            public void onDone() {
                handleDone(3);
            }
        });
        userRepository.findLikedTracks(PageRequest.of(0, 6), "", new ApiHandler<InfiniteList<Track>>() {
                    @Override
                    public void onSuccess(ApiBody<InfiniteList<Track>> body) {
                        ApiHandler.super.onSuccess(body);
                        List<CardItem> tracks = body.getData().getContent().stream().map((track -> (CardItem) track)).collect(Collectors.toList());
                        renderSquareItem(tracks, savedTracksView, HomeFragment.this);
                    }

                    @Override
                    public void onDone() {
                        handleDone(4);
                    }
                });
        albumRepository.findByGenreId(UUID.fromString("4d8d0496-a8c1-4a96-a44a-5b0ff249b926"), PageRequest.of(0, 10, "releaseDate", Sort.Direction.DESC), new ApiHandler<InfiniteList<Album>>() {
            @Override
            public void onSuccess(ApiBody<InfiniteList<Album>> body) {
                ApiHandler.super.onSuccess(body);
                List<CardItem> albums = body.getData().getContent().stream().map((album -> (CardItem) album)).collect(Collectors.toList());
                renderSquareItem(albums, tracksView, HomeFragment.this);

            }

            @Override
            public void onDone() {
                handleDone(5);
            }
        });


    }

    private void handleDone(int i) {
        taskId.remove(i);
        if (taskId.isEmpty()){
            setLoading(false);
        }
    }

    private void setLoading(boolean b) {
        if (b){
            mainContent.setVisibility(View.GONE);
            loader.setVisibility(View.VISIBLE);
        } else{
            mainContent.setVisibility(View.VISIBLE);
            loader.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(CardItem cardItem) {

    }


    /*------------------
         Render
    --------------------*/
    private UserRepository userRepository;
    private AlbumRepository albumRepository;
    private PlayListRepository playListRepository;
    private ArtistRepository artistRepository;

    @Inject
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
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
