package tetris;

public class KeyUp extends KeyLeftRightUpDownREvent{

	@Override
	char pressKey(Block currentpieces, Block nextpieces, DrawingBoard canvas, Board b1, long totalPoint, char rotateDirection) {
		if (execPressKey) {
	        if (rotateDirection == 'R') {
	            currentpieces.rotateRight(true);        
	        } else {
	            currentpieces.rotateLeft(true);
	        }
		}
		return rotateDirection;
		
	}
}
