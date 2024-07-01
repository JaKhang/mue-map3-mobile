package com.mue.music.repository.impl;

import com.mue.music.api.Api;
import com.mue.music.repository.UserRepository;
import com.mue.music.model.Album;
import com.mue.music.model.Artist;
import com.mue.music.model.PlayList;
import com.mue.music.model.Track;
import com.mue.music.model.domain.ApiBody;
import com.mue.music.model.domain.InfiniteList;
import com.mue.music.model.domain.PageRequest;
import com.mue.music.model.request.PlayListRequest;
import com.mue.music.model.request.UserActionRequest;
import com.mue.music.repository.AbstractRepository;
import com.mue.music.api.ApiHandler;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;

public class DefaultUserRepository extends AbstractRepository implements UserRepository {
    public DefaultUserRepository(Api API) {
        super(API);
    }

    @Override
    public void likeTracks(List<UUID> ids, ApiHandler<Void> handler) {
        Call<ApiBody<Void>> call = api.likeTracks(new UserActionRequest(ids));
        enqueue(call, handler);
    }

    @Override
    public void likeAlbums(List<UUID> ids, ApiHandler<Void> handler) {
        Call<ApiBody<Void>> call = api.likeAlbums(new UserActionRequest(ids));
        enqueue(call, handler);
    }

    @Override
    public void likePlayLists(List<UUID> ids, ApiHandler<Void> handler) {
        Call<ApiBody<Void>> call = api.likePlayLists(new UserActionRequest(ids));
        enqueue(call, handler);
    }

    @Override
    public void likeArtists(List<UUID> ids, ApiHandler<Void> handler) {
        Call<ApiBody<Void>> call = api.likeArtists(new UserActionRequest(ids));
        enqueue(call, handler);
    }

    @Override
    public void unLikeTracks(List<UUID> ids, ApiHandler<Void> handler) {
        Call<ApiBody<Void>> call = api.unLikeTracks(ids);
        enqueue(call, handler);
    }

    @Override
    public void unLikeAlbums(List<UUID> ids, ApiHandler<Void> handler) {
        Call<ApiBody<Void>> call = api.unLikeAlbums(new UserActionRequest(ids));
        enqueue(call, handler);
    }

    @Override
    public void unLikePlayLists(List<UUID> ids, ApiHandler<Void> handler) {
        Call<ApiBody<Void>> call = api.unLikePlayLists(new UserActionRequest(ids));
        enqueue(call, handler);
    }

    @Override
    public void unLikeArtists(List<UUID> ids, ApiHandler<Void> handler) {
        Call<ApiBody<Void>> call = api.unLikeArtists(new UserActionRequest(ids));
        enqueue(call, handler);
    }

    @Override
    public void findLikedTracks(PageRequest pageRequest, String keyword, ApiHandler<InfiniteList<Track>> handler) {
        Call<ApiBody<InfiniteList<Track>>> call = api.findSavedTracks(pageRequest.getPage(), pageRequest.getSize(), pageRequest.toSortString(), keyword);
        enqueue(call, handler);
    }

    @Override
    public void findLikedAlbums(PageRequest pageRequest, String keyword, ApiHandler<InfiniteList<Album>> handler) {
        Call<ApiBody<InfiniteList<Album>>> call = api.findSavedAlbums(pageRequest.getPage(), pageRequest.getSize(), pageRequest.toSortString(), keyword);
        enqueue(call, handler);
    }

    @Override
    public void findLikedPlayLists(PageRequest pageRequest, String keyword, ApiHandler<InfiniteList<PlayList>> handler) {
        Call<ApiBody<InfiniteList<PlayList>>> call = api.findSavedLists(pageRequest.getPage(), pageRequest.getSize(), pageRequest.toSortString(), keyword);
        enqueue(call, handler);
    }

    @Override
    public void findLikedArtists(PageRequest pageRequest, String keyword, ApiHandler<InfiniteList<Artist>> handler) {
        Call<ApiBody<InfiniteList<Artist>>> call = api.findLikedArtists(pageRequest.getPage(), pageRequest.getSize(), pageRequest.toSortString(), keyword);
        enqueue(call, handler);
    }

    @Override
    public void findPlaylistOfCurrent(PageRequest pageRequest, String keyword, ApiHandler<InfiniteList<PlayList>> handler) {
        Call<ApiBody<InfiniteList<PlayList>>> call = api.findPlaylistOfCurrentUser(pageRequest.getPage(), pageRequest.size, pageRequest.toSortString(), keyword);
        enqueue(call, handler);
    }

    @Override
    public void createPlayList(PlayListRequest playListRequest, ApiHandler<UUID> handler) {
        Call<ApiBody<UUID>> call = api.createPlayList(playListRequest);
        enqueue(call, handler);
    }

    @Override
    public void removePlayList(UUID playlistId, ApiHandler<UUID> handler) {
        Call<ApiBody<Void>> call = api.removePLayList(playlistId);
    }

    @Override
    public void addTracksToPLayList(UUID playlistId, List<UUID> trackIds, ApiHandler<Void> handler) {
        Call<ApiBody<Void>> call = api.addTrackToPlayList(playlistId, new UserActionRequest(trackIds));
        enqueue(call, handler);
    }

    @Override
    public void removeTracksToPLayList(UUID playlistId, List<UUID> trackIds, ApiHandler<Void> handler) {
        Call<ApiBody<Void>> call = api.removeFromToPlayList(playlistId, new UserActionRequest(trackIds));
        enqueue(call, handler);
    }


}
