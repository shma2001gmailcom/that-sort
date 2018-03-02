package org.misha.state.player.songs;

import org.apache.log4j.Logger;

/**
 * author: misha
 * date: 3/2/18
 */
public class ConcreteSong extends Song {
    private final Logger log = Logger.getLogger(Song.class);
    
    ConcreteSong(final String name) {
        this.name = name;
    }
    
    @Override
    public void play() {
        log.debug(toString());
    }
}
