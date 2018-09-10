package org.misha.state;

import org.misha.state.player.Player;
import org.misha.state.player.songs.PlayList;

import static org.misha.state.player.songs.PlayList.playList;

/**
 * author: misha
 * date: 3/2/18
 */
public class PlayerLauncher {
    
    public static void main(String... args) {
        Player player = new Player();
        PlayList playList = playList("All you need is love", "Us and them");
        player.setPlayList(playList);
        player.startPlayback();
        player.stopPlayback();
        player.nextSong();
        player.previousSong();
        player.stopPlayback();
        player.startPlayback();
        player.previousSong();
        player.nextSong();
        player.stopPlayback();
    }
}
