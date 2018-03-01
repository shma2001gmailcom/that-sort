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
    
    private void startPlayback() throws Exception {
        player.view.playButton().onClick();
    }
    
    private void stopPlayback() throws Exception {
        player.view.lockButton().onClick();
    }
    
    private void nextSong() throws Exception {
        player.view.nextButton().onClick();
    }
    
    private void previousSong() throws Exception {
        player.view.prevButton().onClick();
    }
    
    @Test
    public void testPlayer() throws Exception {
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