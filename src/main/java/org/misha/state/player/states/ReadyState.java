package org.misha.state.player.states;

import org.misha.state.player.Player;
import org.misha.state.player.State;

/**
 * author: misha
 * date: 3/1/18
 */
public final class ReadyState extends State {
    public ReadyState(final Player player) {
        super(player);
    }

    @Override
    public void clickLock() {
        player.changeState(new LockedState(player));
    }

    @Override
    public void clickNext() {
        player.nextSong();
    }

    @Override
    public void clickPrevious() {
        player.previousSong();
    }

    @Override
    public void clickPlay() {
        player.currentSong().play();
    }

    @Override
    public boolean equals(final Object o) {
        return o instanceof ReadyState;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
