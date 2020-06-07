package tetris;

import static tetris.PlayMusic.playMusic;

import timer.CountDown;

public abstract class KeyPXEvent {
	private static final int MINUTE_TILL_NEXT_LVL = 1;
	protected boolean paused = false;    
	
	final void playmusic() {
	    new Thread(() -> {
	        playMusic("wav/tap.wav", false);
	    }).start();
	}
	
	abstract void function(DrawingBoard canvas, CountDown countdown);
	
}
