package com.mue.music.model;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Track {
    private UUID id;
    private String name;
    private String alias;
    private String thumbnail;
    private SimpleObject album;
    private List<SimpleObject> artists;
    private List<SimpleObject> genres;
    private int duration;
    private boolean liked;

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return Objects.equals(id, track.id);
    }

    public String getArtistsName() {
        StringJoiner stringJoiner = new StringJoiner(", ");
        for (SimpleObject artist : artists){
            stringJoiner.add(artist.getName());
        }
        return stringJoiner.toString();
    }
}
