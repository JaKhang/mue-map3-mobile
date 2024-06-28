package com.mue.music.service;

import com.mue.music.model.Album;
import com.mue.music.model.Artist;
import com.mue.music.model.PlayList;
import com.mue.music.model.Track;
import com.mue.music.model.domain.InfiniteList;
import com.mue.music.model.domain.PageRequest;
import com.mue.music.model.request.PlayListRequest;

import java.util.List;
import java.util.UUID;

public interface UserService {
    void likeTracks(List<UUID> ids, ApiHandler<Void> handler);

    void likeAlbums(List<UUID> ids, ApiHandler<Void> handler);

    void likePlayLists(List<UUID> ids, ApiHandler<Void> handler);

    void likeArtists(List<UUID> ids, ApiHandler<Void> handler);

    void unLikeTracks(List<UUID> ids, ApiHandler<Void> handler);

    void unLikeAlbums(List<UUID> ids, ApiHandler<Void> handler);

    void unLikePlayLists(List<UUID> ids, ApiHandler<Void> handler);

    void unLikeArtists(List<UUID> ids, ApiHandler<Void> handler);

    void findLikedTracks(PageRequest pageRequest, String keyword, ApiHandler<InfiniteList<Track>> handler);

    void findLikedAlbums(PageRequest pageRequest, String keyword, ApiHandler<InfiniteList<Album>> handler);

    void findLikedPlayLists(PageRequest pageRequest, String keyword, ApiHandler<InfiniteList<PlayList>> handler);

    void findLikedArtists(PageRequest pageRequest, String keyword, ApiHandler<InfiniteList<Artist>> handler);

    void findPlaylistOfCurrent(PageRequest pageRequest, String keyword, ApiHandler<InfiniteList<PlayList>> handler);

    void createPlayList(PlayListRequest playListRequest, ApiHandler<UUID> handler);

    void removePlayList(UUID playlistId, ApiHandler<UUID> handler);

    void addTracksToPLayList(UUID playlistId, List<UUID> trackIds, ApiHandler<Void> handler);

    void removeTracksToPLayList(UUID playlistId, List<UUID> trackIds, ApiHandler<Void> handler);
}
