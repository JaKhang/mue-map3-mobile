package com.mue.music.service;

import com.mue.music.model.Album;
import com.mue.music.model.AlbumDetails;
import com.mue.music.model.Artist;
import com.mue.music.model.ArtistDetails;
import com.mue.music.model.AuthInfo;
import com.mue.music.model.domain.ApiBody;
import com.mue.music.model.domain.InfiniteList;
import com.mue.music.model.request.LoginRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiService {

    // Auth
    @GET("/api/v1/albums")
    Call<ApiBody<AuthInfo>> authenticate(@Body LoginRequest request);

    @GET("/Albums/{id}")
    Call<ApiBody<AlbumDetails>> getPrincipal(
            @Part Long id
    );



    // Artist
    @GET("/api/v1/artists?")
    Call<ApiBody<InfiniteList<Artist>>> findArtists(
            @Query("page")  Integer page,
            @Query("size")  Integer size,
            @Query("sort")  String sort,
            @Query("query") String query);

    @GET("/api/v1/artists/{id}")
    Call<ApiBody<ArtistDetails>> findAristById(
            @Part Long id
    );



    //album
    @GET("/api/v1/albums")
    Call<ApiBody<InfiniteList<Album>>> findAlbum(
            @Query("page")  Integer page,
            @Query("size")  Integer size,
            @Query("sort")  String sort,
            @Query("query") String query);

    @GET("/api/v1/albums/{id}")
    Call<ApiBody<Album>> findAlbumById(
            @Part Long id
    );

}
