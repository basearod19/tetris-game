package tetris;

import java.awt.Canvas;
import timer.CountDown;

public class KeyP extends KeyPXEvent {

	@Override
	void function(DrawingBoard canvas, CountDown countdown) {
		// TODO Auto-generated method stub
        paused = !paused;
        canvas.setPaused(paused);
        countdown.pause(paused);
	}
}
