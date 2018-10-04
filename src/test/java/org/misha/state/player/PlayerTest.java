package org.misha.state.player;

import org.junit.Before;
import org.junit.Test;

import static org.misha.state.player.MockPlayer.makePlayer;

/**
 * author: misha
 * date: 3/1/18
 */
public class PlayerTest {
    private Player player;
    
    @Before
    public void before() {
        player = makePlayer();
    }
    
    private void startPlayback() {
        player.view.playButton().onClick();
    }
    
    private void stopPlayback() {
        player.view.lockButton().onClick();
    }
    
    private void nextSong() {
        player.view.nextButton().onClick();
    }
    
    private void previousSong() {
        player.view.prevButton().onClick();
    }
    
    @Test
    public void testPlayer() {
        startPlayback();
        stopPlayback();
        nextSong();
        previousSong();
        stopPlayback();
        startPlayback();
        previousSong();
        nextSong();
        stopPlayback();
    }
}