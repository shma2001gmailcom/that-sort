package org.misha.state.player.states;

import org.misha.state.player.Player;
import org.misha.state.player.State;

/**
 * author: misha
 * date: 3/1/18
 */
public final class LockedState extends State {
    
    LockedState(final Player player) {
        super(player);
    }
    
    @Override
    public void clickLock() {
        //do nothing
    }
    
    @Override
    public void clickNext() {
        //do nothing
    }
    
    @Override
    public void clickPrevious() {
        //do nothing
    }
    
    @Override
    public void clickPlay() {
        player.changeState(new PlayingState(player));
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof LockedState;
    }
    
    @Override
    public int hashCode() {
        return 3;
    }
}
