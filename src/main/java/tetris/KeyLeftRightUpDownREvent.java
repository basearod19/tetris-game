package tetris;

import static tetris.PlayMusic.playMusic;
import tetris.Tetris;

public abstract class KeyLeftRightUpDownREvent {
	protected int rowsCleared = 0;
	protected int pressKey = 0;
	protected boolean execPressKey = pressKey != 1;

	
	abstract char pressKey(Block currentpieces, Block nextpieces, DrawingBoard canvas, Board b1, long totalpoint, char rotateDirection);
	
	final void playmusic() {
	    new Thread(() -> {
	        playMusic("wav/rotate.wav", false);
	    }).start();
	}
}
