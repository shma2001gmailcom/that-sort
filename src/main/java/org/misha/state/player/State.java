package org.misha.state.player;

/**
 * author: misha
 * date: 3/1/18
 */
public abstract class State {
    protected final Player player;
    
    protected State(final Player player) {
        this.player = player;
    }
    
    public abstract void clickLock();
    
    public abstract void clickNext();
    
    public abstract void clickPrevious();
    
    public abstract void clickPlay();
    
    @Override
    public abstract boolean equals(Object o);
    
    @Override
    public abstract int hashCode();
}
