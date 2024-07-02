package com.mue.music.util;

import android.graphics.Color;

import com.mue.music.model.Artist;
import com.mue.music.model.SimpleObject;

import java.time.Duration;
import java.util.Collection;
import java.util.StringJoiner;

public class CommonUtils {
    public static String getArtistNames(Collection<SimpleObject> artists) {
        StringJoiner stringJoiner = new StringJoiner(", ");
        for (SimpleObject artist : artists) {
            stringJoiner.add(artist.getName());
        }
        return stringJoiner.toString();
    }

    public static String getArtistDetailNames(Collection<Artist> artists) {
        StringJoiner stringJoiner = new StringJoiner(", ");
        for (Artist artist : artists) {
            stringJoiner.add(artist.getName());
        }
        return stringJoiner.toString();
    }

    public static int makeColorDarker(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] *= 0.35f; // Reduce brightness
        hsv[1] *= 0.5f;
        return Color.HSVToColor(hsv);
    }

    public static int calculatePercent(int part, int total) {
        return (int) (part / (double) total * 100);

    }

    public static int calculatePartFormPercent(int percent, int total) {
        return (total * percent) /100;

    }

    public static String convertTimeString(long seconds) {

        long HH = seconds / 3600;
        long MM = (seconds % 3600) / 60;
        long SS = seconds % 60;
        StringJoiner stringJoiner = new StringJoiner(":");
        if (HH > 0)
            stringJoiner.add(HH + "");
        if (MM < 10)
            stringJoiner.add("0" + MM);
        else
            stringJoiner.add(MM + "");
        if (SS < 10)
            stringJoiner.add("0" + SS);
        else
            stringJoiner.add("" + SS);
        return stringJoiner.toString();
    }
}
