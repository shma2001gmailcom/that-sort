package org.misha.state.player.songs;

import java.util.ArrayList;
import java.util.List;

/**
 * author: misha
 * date: 3/1/18
 */
public class PlayList {
    private final List<Song> songs = new ArrayList<>();
    private int current = 0;

    public static PlayList playList(final String... names) {
        PlayList playList = new PlayList();
        for (String name : names) {
            playList.put(new ConcreteSong(name));
        }
        return playList;
    }

    public Song get(int i) {
        int index = i % songs.size();
        this.current = index;
        return songs.get(index);
    }

    public Song next() {
        return get(++current);
    }

    protected void put(Song song) {
        songs.add(song);
    }

    public Song previous() {
        return get(--current < 0 ? -current : current);
    }
}
