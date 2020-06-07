package tetris;

public class KeyR extends KeyLeftRightUpDownEvent{

	@Override
	void function(Block currentpieces, Block nextpieces, DrawingBoard canvas, Board b1, long totalPoints) {
	    if (rotateDirection == 'R') {
	        rotateDirection = 'L';
	        canvas.setRotationDirection("Left");
	    } else {
	        rotateDirection = 'R';
	        canvas.setRotationDirection("Right");
	    }
		
	}

}
