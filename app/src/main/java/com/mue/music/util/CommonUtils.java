package com.mue.music.util;

import com.mue.music.model.Artist;
import com.mue.music.model.SimpleObject;

import java.util.Collection;
import java.util.StringJoiner;

public class CommonUtils {
    public static String getArtistNames(Collection<SimpleObject> artists){
        StringJoiner stringJoiner = new StringJoiner(", ");
        for (SimpleObject artist : artists){
            stringJoiner.add(artist.getName());
        }
        return stringJoiner.toString();
    }

    public static String getArtistDetailNames(Collection<Artist> artists){
        StringJoiner stringJoiner = new StringJoiner(", ");
        for (Artist artist : artists){
            stringJoiner.add(artist.getName());
        }
        return stringJoiner.toString();
    }
}
