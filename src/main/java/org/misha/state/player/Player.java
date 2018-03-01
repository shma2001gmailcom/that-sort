package org.misha.state.player;

import org.apache.log4j.Logger;
import org.misha.state.player.songs.PlayList;
import org.misha.state.player.songs.Song;
import org.misha.state.player.states.ReadyState;

/**
 * author: misha
 * date: 3/1/18
 */
public class Player {
    private static final Logger log = Logger.getLogger(Player.class);
    private static final String SONG = "song: ";
    private State state;
    private PlayList playList;
    private Song currentSong;
    final View view;
    
    Player() {
        this.state = new ReadyState(this);
        view = new View(new Button() {
    
            @Override
            void onClick() {
                Player.this.clickLock();
            }
        },new Button() {
            @Override
            void onClick() {
                Player.this.clickPlay();
            }
        }, new Button() {
    
            @Override
            void onClick() {
                Player.this.clickNext();
            }
        }, new Button() {
    
            @Override
            void onClick() {
                Player.this.clickPrevious();
            }
        });
    }
    
    private void clickPrevious() {
        currentSong = playList.previous();
        state.clickPrevious();
    }
    
    private void clickNext() {
        currentSong = playList.next();
        state.clickNext();
    }
    
    private void clickPlay() {
        state.clickPlay();
    }
    
    private void clickLock() {
        state.clickLock();
    }
    
    public void changeState(final State state) {
        this.state = state;
    }
    
    public void startPlayback() {
        currentSong.play();
    }
    
    void stopPlayback() {
        log.debug("stopPlayback");
    }
    
    public void nextSong() {
        log.debug("nextSong");
        currentSong = playList.next();
        log.debug(SONG + currentSong);
    }
    
    public void previousSong() {
        currentSong = playList.previous();
        log.debug(SONG + currentSong);
    }
    
    public State getState() {
        return state;
    }
    
    void setPlayList(PlayList playList) {
        this.playList = playList;
        currentSong = playList.get(0);
    }
    
    public Song currentSong() {
        return currentSong;
    }
}
