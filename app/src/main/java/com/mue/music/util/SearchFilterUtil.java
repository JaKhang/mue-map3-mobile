package com.mue.music.util;

import com.mue.music.model_test_ui.Album;
import com.mue.music.model_test_ui.Artist;
import com.mue.music.model_test_ui.Track;
import com.mue.music.ui.adapter.search.AlbumAdapter;
import com.mue.music.ui.adapter.search.ArtistAdapter;
import com.mue.music.ui.adapter.search.TrackAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchFilterUtil {
    public static void filterTracks(String query, List<Track> trackList, TrackAdapter trackAdapter) {
        List<Track> filteredTracks = new ArrayList<>();
        for (Track track : trackList) {
            if (track.getName().toLowerCase().contains(query.toLowerCase()) ||
                    track.getArtist().toLowerCase().contains(query.toLowerCase())) {
                filteredTracks.add(track);
            }
        }
        trackAdapter.updateTracks(filteredTracks);
    }

    public static void filterAlbums(String query, List<Album> albumList, AlbumAdapter albumAdapter) {
        List<Album> filteredAlbums = new ArrayList<>();
        for (Album album : albumList) {
            if (album.getName().toLowerCase().contains(query.toLowerCase()) ||
                    album.getArtist().toLowerCase().contains(query.toLowerCase())) {
                filteredAlbums.add(album);
            }
        }
        albumAdapter.updateAlbums(filteredAlbums);
    }

    public static void filterArtists(String string, List<Artist> artistList, ArtistAdapter artistAdapter) {
        List<Artist> filteredArtists = new ArrayList<>();
        for (Artist artist : artistList) {
            if (artist.getName().toLowerCase().contains(string.toLowerCase())) {
                filteredArtists.add(artist);
            }
        }
        artistAdapter.updateArtists(filteredArtists);
    }
}
