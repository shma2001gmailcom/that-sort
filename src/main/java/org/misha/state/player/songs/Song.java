package org.misha.state.player.songs;


/**
 * author: misha
 * date: 3/1/18
 */
public abstract class Song {
   protected String name;
    
    public String getName() {
        return name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return getName();
    }
    
    public abstract void play();
}
