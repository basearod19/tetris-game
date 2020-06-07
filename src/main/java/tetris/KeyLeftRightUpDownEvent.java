package tetris;

import static tetris.PlayMusic.playMusic;
import tetris.Tetris;

public abstract class KeyLeftRightUpDownEvent {
	
	protected int pressKey = 0;
	protected int rowsCleared = 0;
    public static final int ONE_ROW_CLEARED_POINT = 100;
    public static final int TWO_ROW_CLEARED_POINT = 300;
    public static final int THREE_ROW_CLEARED_POINT = 600;
    public static final int FOUR_ROW_CLEARED_POINT = 900;
	public static boolean gameOver = false; 
	public char rotateDirection = 'R';
	boolean execPressKey = pressKey != 1;

	
	abstract void function(Block currentpieces, Block nextpieces, DrawingBoard canvas, Board b1, long totalpoint);
	
	final void playmusic() {
	    new Thread(() -> {
	        playMusic("wav/rotate.wav", false);
	    }).start();
	}
}
