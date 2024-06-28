package com.mue.music.service.impl;

import com.mue.music.model.Album;
import com.mue.music.model.Artist;
import com.mue.music.model.PlayList;
import com.mue.music.model.Track;
import com.mue.music.model.domain.ApiBody;
import com.mue.music.model.domain.InfiniteList;
import com.mue.music.model.domain.PageRequest;
import com.mue.music.model.request.PlayListRequest;
import com.mue.music.model.request.UserActionRequest;
import com.mue.music.service.AbstractService;
import com.mue.music.service.ApiHandler;
import com.mue.music.service.ApiService;
import com.mue.music.service.UserService;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;

public class DefaultUserService extends AbstractService implements UserService {
    public DefaultUserService(ApiService apiService) {
        super(apiService);
    }

    @Override
    public void likeTracks(List<UUID> ids, ApiHandler<Void> handler) {
        Call<ApiBody<Void>> call = apiService.likeTracks(new UserActionRequest(ids));
        enqueue(call, handler);
    }

    @Override
    public void likeAlbums(List<UUID> ids, ApiHandler<Void> handler) {
        Call<ApiBody<Void>> call = apiService.likeAlbums(new UserActionRequest(ids));
        enqueue(call, handler);
    }

    @Override
    public void likePlayLists(List<UUID> ids, ApiHandler<Void> handler) {
        Call<ApiBody<Void>> call = apiService.likePlayLists(new UserActionRequest(ids));
        enqueue(call, handler);
    }

    @Override
    public void likeArtists(List<UUID> ids, ApiHandler<Void> handler) {
        Call<ApiBody<Void>> call = apiService.likeArtists(new UserActionRequest(ids));
        enqueue(call, handler);
    }

    @Override
    public void unLikeTracks(List<UUID> ids, ApiHandler<Void> handler) {
        Call<ApiBody<Void>> call = apiService.unLikeTracks(new UserActionRequest(ids));
        enqueue(call, handler);
    }

    @Override
    public void unLikeAlbums(List<UUID> ids, ApiHandler<Void> handler) {
        Call<ApiBody<Void>> call = apiService.unLikeAlbums(new UserActionRequest(ids));
        enqueue(call, handler);
    }

    @Override
    public void unLikePlayLists(List<UUID> ids, ApiHandler<Void> handler) {
        Call<ApiBody<Void>> call = apiService.unLikePlayLists(new UserActionRequest(ids));
        enqueue(call, handler);
    }

    @Override
    public void unLikeArtists(List<UUID> ids, ApiHandler<Void> handler) {
        Call<ApiBody<Void>> call = apiService.unLikeArtists(new UserActionRequest(ids));
        enqueue(call, handler);
    }

    @Override
    public void findLikedTracks(PageRequest pageRequest, String keyword, ApiHandler<InfiniteList<Track>> handler) {
        Call<ApiBody<InfiniteList<Track>>> call = apiService.findSavedTracks(pageRequest.getPage(), pageRequest.getSize(), pageRequest.toSortString(), keyword);
        enqueue(call, handler);
    }

    @Override
    public void findLikedAlbums(PageRequest pageRequest, String keyword, ApiHandler<InfiniteList<Album>> handler) {
        Call<ApiBody<InfiniteList<Album>>> call = apiService.findSavedAlbums(pageRequest.getPage(), pageRequest.getSize(), pageRequest.toSortString(), keyword);
        enqueue(call, handler);
    }

    @Override
    public void findLikedPlayLists(PageRequest pageRequest, String keyword, ApiHandler<InfiniteList<PlayList>> handler) {
        Call<ApiBody<InfiniteList<PlayList>>> call = apiService.findSavedLists(pageRequest.getPage(), pageRequest.getSize(), pageRequest.toSortString(), keyword);
        enqueue(call, handler);
    }

    @Override
    public void findLikedArtists(PageRequest pageRequest, String keyword, ApiHandler<InfiniteList<Artist>> handler) {
        Call<ApiBody<InfiniteList<Artist>>> call = apiService.findLikedArtists(pageRequest.getPage(), pageRequest.getSize(), pageRequest.toSortString(), keyword);
        enqueue(call, handler);
    }

    @Override
    public void findPlaylistOfCurrent(PageRequest pageRequest, String keyword, ApiHandler<InfiniteList<PlayList>> handler) {
        Call<ApiBody<InfiniteList<PlayList>>> call = apiService.findPlaylistOfCurrentUser(pageRequest.getPage(), pageRequest.size, pageRequest.toSortString(), keyword);
        enqueue(call, handler);
    }

    @Override
    public void createPlayList(PlayListRequest playListRequest, ApiHandler<UUID> handler) {
        Call<ApiBody<UUID>> call = apiService.createPlayList(playListRequest);
        enqueue(call, handler);
    }

    @Override
    public void removePlayList(UUID playlistId, ApiHandler<UUID> handler) {
        Call<ApiBody<Void>> call = apiService.removePLayList(playlistId);
    }

    @Override
    public void addTracksToPLayList(UUID playlistId, List<UUID> trackIds, ApiHandler<Void> handler) {
        Call<ApiBody<Void>> call = apiService.addTrackToPlayList(playlistId, new UserActionRequest(trackIds));
        enqueue(call, handler);
    }

    @Override
    public void removeTracksToPLayList(UUID playlistId, List<UUID> trackIds, ApiHandler<Void> handler) {
        Call<ApiBody<Void>> call = apiService.removeFromToPlayList(playlistId, new UserActionRequest(trackIds));
        enqueue(call, handler);
    }


}
