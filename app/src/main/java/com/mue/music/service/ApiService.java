package com.mue.music.service;

import com.mue.music.model.Album;
import com.mue.music.model.AlbumDetails;
import com.mue.music.model.Artist;
import com.mue.music.model.ArtistDetails;
import com.mue.music.model.AuthInfo;
import com.mue.music.model.PlayList;
import com.mue.music.model.Principal;
import com.mue.music.model.Track;
import com.mue.music.model.domain.ApiBody;
import com.mue.music.model.domain.InfiniteList;
import com.mue.music.model.enums.Bitrate;
import com.mue.music.model.request.LoginRequest;
import com.mue.music.model.request.PlayListRequest;
import com.mue.music.model.request.RegisterRequest;
import com.mue.music.model.request.UserActionRequest;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiService {

    /*------------------
        Authentication
    --------------------*/
    @POST("/api/v1/auth")
    Call<ApiBody<AuthInfo>> authenticate(@Body LoginRequest request);

    @GET("/api/v1/me")
    Call<ApiBody<Principal>> getPrincipal();

    @POST("/api/v1/auth/register")
    Call<ApiBody<UUID>> register(@Body RegisterRequest registerRequest);


    /*------------------
        Artist
    --------------------*/
    @GET("/api/v1/artists?")
    Call<ApiBody<InfiniteList<Artist>>> findArtists(
            @Query("page") Integer page,
            @Query("size") Integer size,
            @Query("sort") String sort,
            @Query("query") String query);

    @GET("/api/v1/artists/{id}")
    Call<ApiBody<ArtistDetails>> findAristById(
            @Part UUID id
    );


    /*------------------
            Album
    --------------------*/
    @GET("/api/v1/albums?")
    Call<ApiBody<InfiniteList<Album>>> findAlbums(
            @Query("page") Integer page,
            @Query("size") Integer size,
            @Query("sort") String sort,
            @Query("query") String query);

    @GET("/api/v1/albums/{id}")
    Call<ApiBody<AlbumDetails>> findAlbumById(
            @Part UUID id
    );

    @GET("/api/v1/albums/search?")
    Call<ApiBody<InfiniteList<Album>>> searchByNameOrArtistOrTrack(
            @Query("page") Integer page,
            @Query("size") Integer size,
            @Query("sort") String sort,
            @Query("keyword") String keyword);


    /*------------------
            User
    --------------------*/
    @PUT("/api/v1/me/artists?")
    Call<ApiBody<Void>> likeArtists(@Body UserActionRequest userActionRequest);

    @DELETE("/api/v1/me/artists?")
    Call<ApiBody<Void>> unLikeArtists(@Body UserActionRequest userActionRequest);

    @GET("/api/v1/me/artists?")
    Call<ApiBody<InfiniteList<Artist>>> findLikedArtists(@Query("page") Integer page,
                                                         @Query("size") Integer size,
                                                         @Query("sort") String sort,
                                                         @Query("query") String keyword
    );

    @PUT("/api/v1/me/tracks?")
    Call<ApiBody<Void>> likeTracks(@Body UserActionRequest userActionRequest);

    @DELETE("/api/v1/me/tracks?")
    Call<ApiBody<Void>> unLikeTracks(@Body UserActionRequest userActionRequest);

    @GET("/api/v1/me/tracks?")
    Call<ApiBody<InfiniteList<Track>>> findSavedTracks(@Query("page") Integer page,
                                                       @Query("size") Integer size,
                                                       @Query("sort") String sort,
                                                       @Query("query") String keyword
    );

    @PUT("/api/v1/me/playLists?")
    Call<ApiBody<Void>> likePlayLists(@Body UserActionRequest userActionRequest);

    @DELETE("/api/v1/me/playLists?")
    Call<ApiBody<Void>> unLikePlayLists(@Body UserActionRequest userActionRequest);

    @GET("/api/v1/me/playLists?")
    Call<ApiBody<InfiniteList<PlayList>>> findSavedLists(@Query("page") Integer page,
                                                         @Query("size") Integer size,
                                                         @Query("sort") String sort,
                                                         @Query("query") String keyword
    );


    @PUT("/api/v1/me/playLists?")
    Call<ApiBody<Void>> likeAlbums(@Body UserActionRequest userActionRequest);

    @DELETE("/api/v1/me/playLists?")
    Call<ApiBody<Void>> unLikeAlbums(@Body UserActionRequest userActionRequest);

    @GET("/api/v1/me/playLists?")
    Call<ApiBody<InfiniteList<Album>>> findSavedAlbums(@Query("page") Integer page,
                                                       @Query("size") Integer size,
                                                       @Query("sort") String sort,
                                                       @Query("query") String keyword
    );

    /*------------------
         Playlist
    --------------------*/

    @POST("/api/v1/playlists/{id}/tracks")
    Call<ApiBody<Void>> addTrackToPlayList(@Part("id") UUID playlistId, @Body UserActionRequest userActionRequest);

    @DELETE("/api/v1/playlists/{id}/tracks")
    Call<ApiBody<Void>> removeFromToPlayList(@Part UUID id, @Body UserActionRequest userActionRequest);

    @POST("/api/v1/playlists")
    Call<ApiBody<UUID>> createPlayList(@Body PlayListRequest playListRequest);

    @DELETE("/api/v1/playlists/{playlistId}")
    Call<ApiBody<Void>> removePLayList(UUID playlistId);

    @GET("/api/v1/me/playlists/owners?")
    Call<ApiBody<InfiniteList<PlayList>>> findPlaylistOfCurrentUser(@Query("page") Integer page,
                                                                    @Query("size") Integer size,
                                                                    @Query("sort") String sort,
                                                                    @Query("query") String keyword);


    @GET("/api/v1/artists/{id}/albums?")
    Call<ApiBody<InfiniteList<Album>>> findAlbumByArtistId(@Part("id") UUID artistId,
                                                           @Query("page") Integer page,
                                                           @Query("size") Integer size,
                                                           @Query("sort") String sort,
                                                           @Query("query") String keyword);


    /*------------------
            Track
    --------------------*/
    @GET("/api/v1/tracks/{id}/streaming")
    Call<ApiBody<Map<Bitrate, String>>> getStreamingUrl(UUID id);
}
