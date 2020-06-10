package tetris;

public class KeyR extends KeyLeftRightUpDownREvent{

	@Override
	char pressKey(Block currentpieces, Block nextpieces, DrawingBoard canvas, Board b1, long totalPoints, char rotateDirection) {
		if (rotateDirection == 'R') {
	        rotateDirection = 'L';
	        canvas.setRotationDirection("Left");
	        return rotateDirection;
	    } else {
	        rotateDirection = 'R';
	        canvas.setRotationDirection("Right");
	        return rotateDirection;
	    }
	}
}
