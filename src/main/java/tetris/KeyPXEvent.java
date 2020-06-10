package tetris;

import static tetris.PlayMusic.playMusic;

import timer.CountDown;

public abstract class KeyPXEvent {
	
	final void playmusic() {
	    new Thread(() -> {
	        playMusic("wav/tap.wav", false);
	    }).start();
	}
	
	abstract void pressKey(boolean paused, DrawingBoard canvas, CountDown countdown);
	
}
