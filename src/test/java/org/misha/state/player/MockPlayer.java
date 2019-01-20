package org.misha.state.player;

import org.apache.log4j.Logger;
import org.misha.state.player.songs.PlayList;
import org.misha.state.player.songs.Song;

/**
 * author: misha
 * date: 3/1/18
 */
class MockPlayer {
    static Player makePlayer() {
        Player player = new Player();
        player.setPlayList(new PlayList() {{
            put(new Song() {
                private final Logger log = Logger.getLogger(Song.class);

                @Override
                public void play() {
                    log.debug(toString());
                }

                {
                    setName("All you need is love");
                }
            });
            put(new Song() {
                private final Logger log = Logger.getLogger(Song.class);

                @Override
                public void play() {
                    log.debug(toString());
                }

                {
                    setName("Us and them");
                }
            });
        }});
        return player;
    }
}
