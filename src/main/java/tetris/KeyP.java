package tetris;

import java.awt.Canvas;
import timer.CountDown;

public class KeyP extends KeyPXEvent {

	@Override
	void pressKey(boolean paused, DrawingBoard canvas, CountDown countdown) {
        canvas.setPaused(paused);
        countdown.pause(paused);
        canvas.repaint();
	}
}
