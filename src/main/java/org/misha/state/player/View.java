package org.misha.state.player;

/**
 * author: misha
 * date: 3/1/18
 */
class View {
    private final Button lockButton;
    private final Button playButton;
    private final Button nextButton;
    private final Button prevButton;
    
    View(final Button lockButton, final Button playButton, final Button nextButton, final Button prevButton) {
        this.lockButton = lockButton;
        this.playButton = playButton;
        this.nextButton = nextButton;
        this.prevButton = prevButton;
    }
    
    Button playButton() {
        return playButton;
    }
    
    Button lockButton() {
        return lockButton;
    }
    
    Button nextButton() {
        return nextButton;
    }
    
    Button prevButton() {
        return prevButton;
    }
}
